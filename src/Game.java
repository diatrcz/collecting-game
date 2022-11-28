import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;

/**
 *Ebben az osztályban történik a játék GUI megvalósítása.
 */

@SuppressWarnings("serial")
public class Game extends JFrame{
	/**
	 * Tagváltozó az aktuális játékosnak.
	 */
	private Player player = new Player();
	
	/**
	 * A játkosok adatai. 
	 * A program indulás után betölti az adatokat fájlból, bezáráskor pedig kimenti oda.
	 */
	private PlayerData data;
	
	/**
	 * Tagváltozó az start ablaknak.
	 */
	private JInternalFrame start;
	/**
	 * Tagváltozó a game ablaknak.
	 */
	private JInternalFrame game;
	/**
	 * Tagváltozó az end ablaknak.
	 */
	private JInternalFrame end;
	
	/**
	 * Tagváltozó a TableRowSorter példányának
	 */
	private TableRowSorter<TableModel> rowSorter;
	/**
	 * Tagváltozó a név szerinti keresés TextFieldjének.
	 */
	private JTextField namesearch;
	/**
	 * Tagváltozó a sorszám szerinti keresés TextFieldjének.
	 */
	private JTextField scoresearch;
	
	
	/**
	 * A játék pályáját rajzolja ki egy JInternalFrame()-be,û
	 * egészen addig látszik, amíg a játékos életet nullára nem vált.
	 */
	private void GameField() {
		
		game = new JInternalFrame();
		game.setLayout(new GridLayout(1,1));
		//game.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Play panel = new Play();
		game.add(panel);
		
		this.add(game);
		game.setVisible(true);
	}
	
	
	/**
	 * Belsõ osztály, ami megrajzolja a játék objektumait,
	 * illetve irányítja õket.
	 */
	private class Play extends JPanel implements ActionListener, KeyListener {
		
		/**
		 * ActionListener timer
		 */
		private Timer t = new Timer(5, this);
		/**
		 * Amennivel a játékos pozíciója változik.
		 */
		private int velx;
		/**
		 * Lehessen kezelni az égbõl esõ tárgyakat.
		 */
		private FallingCollectibles falling = new FallingCollectibles(player);
		/**
		 * Az életet jelzõ szív
		 */
		private Heart heart = new Heart(new Position(410, 10));
		
		/**
		 * A háttér színe
		 */
		private static final Color LIGHT_BLUE = new Color(154,194,247);
		/**
		 * A föld színei
		 */
		private static final Color BROWN = new Color(84, 49, 3);
		private static final Color DARK_GREEN = new Color(40, 110, 0);
		
		//FallThread fall = new FallThread(falling);
		
		/**
		 * Idõzíteni lehessen a kétfajta szálat.
		 */
		ScheduledExecutorService executor1 = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService executor2 = Executors.newScheduledThreadPool(1);
		
		/**
		 * Az osztály paraméter nélküli konstruktora, 
		 * beállítja a hátteret,
		 * elindítja a timert, illetve a szálakat, amik a gyûjthetõ objektumok viselkedéséért felelõsek.
		 * Idõközönként gyorsítják az esésis sebességüket, illetve egyre több jelenik meg egyszerre.
		 */
		public Play() {
			setBackground(LIGHT_BLUE);
			t.start();
			addKeyListener(this);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			executor1.scheduleAtFixedRate(new FallPositionThread(falling), 60, 60, TimeUnit.SECONDS);
			for(int i = 0; i < 10; i++) {
				int result = 0;
				if(i != 0) {
					Random r = new Random();
					int low = 18000*i;
					int high = 21000*i;
					result = r.nextInt(high-low) + low;
					executor2.scheduleAtFixedRate(new FallThread(falling), result, 1550, TimeUnit.MILLISECONDS);
				}
				else {
					executor2.scheduleAtFixedRate(new FallThread(falling), result, 3, TimeUnit.SECONDS);
				}
			}
		}
		
		/**
		 * Megrajzolja a pálya elemeit.
		 * @param g A grafikus osztály példánya, ami a rajzolást végzi.
		 */
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			velx = 0;

			g.setColor(BROWN);
			g.fillRect(0, 750, 500, 100);
			
			g.setColor(DARK_GREEN);
			g.fillRect(0, 750, 500, 25);
			
			g.setFont(new Font("Baskerville", Font.PLAIN , 20));
			g.setColor(Color.BLACK);
			g.drawString("Score: " + String.valueOf(player.getScore()), 0, 25);
			g.drawString(": " + String.valueOf(player.getLife()), 450, 25);
			
			heart.colourChange(player);
			heart.draw(g);
			
			player.draw(g);
			if(falling.getList().size() > 0) {
				for(int i = 0; i < falling.getList().size(); i++)
					falling.getList().get(i).draw(g);
			}
		}
		
		
		/**
		 * Az ActionListener kötelezõen megvalósítandó függvénye.
		 * Ha a játékos élete nagyobb nullánál,
		 * megváltoztatja a gyûjthetõ objektumok pozicióját,
		 * ha a jobbra/blara nyíl vagy az A/D betûk le vannak nyomva megváltoztatja a játékos pozicíóját,
		 * újrarajzolja a pályát.
		 * Ha a játékos élete nullára csökkent
		 * eltûnteti a pályát, és az internalframet, amibe el lett helyezve,
		 * a játékos éltet -1-re állítja, hogy egyik loopba se ugorjon be többet.
		 * Meghívja az End metódust.
		 */
		public void actionPerformed(ActionEvent e) {
			if(player.getLife() > 0) {
				falling.roundCheck();
				player.changePosition(velx);
				repaint();
			}
			else if(player.getLife() == 0 ){
				this.setVisible(false);
				game.setVisible(false);
				player.setLife(-1);
				End();
			}
		}
		
		/**
		 * Beállítja a velx változót 10-re;
		 */
		public void right() {
			if(player.getPosition().getX() > 451)
				velx = 0;
			else velx = 10;
		}
		
		/**
		 * Beállítja a belx változót -10-re;
		 */
		public void left() {
			if(player.getPosition().getX() < 2)
				velx = 0;
			else velx = -10;
		}
		
		/**
		 * KeyListener kötelezõen megvalósítandó metódusa.
		 * Ha az A betû, vagy a balra nyíl le van nyomva meghívja a left metódust.
		 * Ha a D betû, vagy a jobbra nyíl le van nyomva meghívja a right metódust.
		 */
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
				left();
			}
			if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
				right();
			}
		}
		
		/**
		 * KeyListener kötelezõen megvalósítandó metódusa.
		 * Nincs rá szükség így nem implementáltam.
		 */
		public void keyTyped(KeyEvent e) {}
		

		/**
		 * KeyListener kötelezõen megvalósítandó metódusa.
		 * Nincs rá szükség így nem implementáltam.
		 */
		public void keyReleased(KeyEvent e) {}

	}
	
	/**
	 * A játék kezdetén megjelenõ internalframe.
	 * Elkéri a játékos nevét, illetve ismerteti a játék szabályait.
	 * Ha a "Start!" gombot lenyomják elkezdõdik a játék.
	 */
	private void Start() {
		
		this.setLayout(new BorderLayout());
		
		//Start menü az elején, hogy felvegye a játékos nevét
		start = new JInternalFrame();
		start.setTitle("Start");
		JPanel p = new JPanel();
		JLabel lb = new JLabel("Name: ");
		JTextField tf1 = new JTextField(30);
		p.add(lb);
		p.add(tf1);
		JButton startBttn = new JButton("Start!");
		startBttn.addActionListener(ae -> {
			StartButtonPressed(tf1.getText(), player);
		});
		p.add(startBttn);
		
		JTextArea rules = new JTextArea(8, 5);
		rules.setEditable(false);
		rules.setText("Collect as many fruits as possible!\r\n" +
					  "Apple --> 1 point\r\n" +
				      "Orange --> 2 poins\r\n" + 
					  "Blueberry --> 3 points\r\n" +
				      "Bevare there are bombs falling from the sky!\r\n" + 
					  "If you accidentally catch a bomb you lose a life.\r\n" +
				      "You can restore lives by catching hearts.\r\n" +  
					  "Good Luck!");
		p.add(rules);
		
		start.add(p);
		start.setVisible(true);
		this.add(start);
		
	}
	
	/**
	 * Ez a függvény hívódik meg, ha a játkos lenyomja a start gombot.
	 * @param str a TextAreaból kapott sztring.
	 * @param p A játékos, akinek be kell állítani a nevét.
	 * Ha a paraméterként kapott sztrin nem üres, akkor beállítja a játékos nevét,
	 * eltûnteti a start ablakot,
	 * Meghívja a GameField függvényt.
	 */
	public void StartButtonPressed(String str, Player p) {
		if(!(str.equals(""))) {
			p.setName(str);
			start.setVisible(false);
			GameField();
		}
	}
	
	/**
	 * A játék végén megjelenõ internalframet kezeli.
	 * Hozzáadja az eddigi játékosokat kezelõ listához az aktuális játékost.
	 * A listát pontszámok szerint csökkenõ sorrendbe rendezi.
	 * Megjeleníti a játékosok nevét és elért pontszámukat egy JTable-ben.
	 * Létrehoz két TextFieldet, amiben név és pontszám alapján lehet keresni.
	 * Hozzáaadja a TestFieldekhez a megfelelõ DocumentListenereket.
	 */
	private void End() {
		data.addPlayer(player);
		
		data.Sort();
		
		end = new JInternalFrame();
		end.setTitle("GameOver:( here's the ranklist: ");
		end.setLayout(new BorderLayout());
		
		JTable jt = new JTable(data);
		rowSorter = new TableRowSorter<>(jt.getModel());
		jt.setRowSorter(rowSorter);
		JScrollPane js = new JScrollPane(jt);
		
		JPanel p = new JPanel(new FlowLayout());
		
		JLabel jl = new JLabel("Name search: ");
		namesearch = new JTextField(10);
		namesearch.getDocument().addDocumentListener(new NameSearchTyped());
		
		JLabel jl2 = new JLabel("Score search: ");
		scoresearch = new JTextField(10);
		scoresearch.getDocument().addDocumentListener(new ScoreSearchTyped());
		
		p.add(jl);
		p.add(namesearch);
		p.add(jl2);
		p.add(scoresearch);
		
		end.add(js, BorderLayout.CENTER);
		end.add(p, BorderLayout.NORTH);
		end.setVisible(true);
		this.add(end);
	}
	
	/**
	 * DocumentListener a név szerinti kereséshez.
	 * Csak kezdõbetûk alapján keres.
	 * @author dyata
	 *
	 */
	private class NameSearchTyped implements DocumentListener {
		
		
		/**
		 * Kötelezõen megvalósítandó metódus. 
		 * Ha beleírnak a TextFieldbe, beállítja a rowSorter filterét a textFieldbõl kapott szövegre.
		 */
		@Override
		public void insertUpdate(DocumentEvent e) {
				String str = namesearch.getText();
				rowSorter.setRowFilter(RowFilter.regexFilter("^" + str, 0));
		}
		
		/**
		 * Kötelezõen megvalósítandó metódus. 
		 * Ha törölnek a TextFieldbõl, beállítja a rowSorter filterét a textFieldbõl kapott szövegre.
		 */
		@Override
		public void removeUpdate(DocumentEvent e) {
				String str = namesearch.getText();
				rowSorter.setRowFilter(RowFilter.regexFilter("^" + str, 0));
			
		}

		/**
		 * Kötelezõen megvalósítandó metódus, azonban nincs rá szükség, így nincs implementálva.
		 *
		 */
		@Override
		public void changedUpdate(DocumentEvent e) {
				/*String str = namesearch.getText();
				rowSorter.setRowFilter(RowFilter.regexFilter("^" + str, 0));*/
		}
		
	}
	
	/**
	 * DocumentumListener a sorszám szerinti kereséshez.
	 */
	private class ScoreSearchTyped implements DocumentListener{

		/**
		 * Kötelezõen megvalósítandó metódus. 
		 * Ha beleírnak a TextFieldbe, beállítja a rowSorter filterét a textFieldbõl kapott szövegre.
		 */
		@Override
		public void insertUpdate(DocumentEvent e) {
			String str = scoresearch.getText();
			rowSorter.setRowFilter(RowFilter.regexFilter("^" + str, 1));
			
		}

		/**
		 * Kötelezõen megvalósítandó metódus. 
		 * Ha törölnek a TextFieldbõl, beállítja a rowSorter filterét a textFieldbõl kapott szövegre.
		 */
		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = scoresearch.getText();
			rowSorter.setRowFilter(RowFilter.regexFilter("^" + str, 1));
			
		}

		/**
		 * Kötelezõen megvalósítandó metódus, azonban nincs rá szükség, így nincs implementálva.
		 *
		 */
		@Override
		public void changedUpdate(DocumentEvent e) {}
		
		
	}
	
	/**
	 * Az ablak konstruktora.
	 */
	@SuppressWarnings("unchecked")
	public Game() {
		super("Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		try {
			data = new PlayerData();
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("players.dat"));
			data.players = (List<Player>)in.readObject();
			in.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("players.dat"));
                    out.writeObject(data.players);
                    out.close();
                } 
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
		
		setSize(new Dimension(500, 900));
		setResizable(false);
        Start();
	}

}
