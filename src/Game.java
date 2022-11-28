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
 *Ebben az oszt�lyban t�rt�nik a j�t�k GUI megval�s�t�sa.
 */

@SuppressWarnings("serial")
public class Game extends JFrame{
	/**
	 * Tagv�ltoz� az aktu�lis j�t�kosnak.
	 */
	private Player player = new Player();
	
	/**
	 * A j�tkosok adatai. 
	 * A program indul�s ut�n bet�lti az adatokat f�jlb�l, bez�r�skor pedig kimenti oda.
	 */
	private PlayerData data;
	
	/**
	 * Tagv�ltoz� az start ablaknak.
	 */
	private JInternalFrame start;
	/**
	 * Tagv�ltoz� a game ablaknak.
	 */
	private JInternalFrame game;
	/**
	 * Tagv�ltoz� az end ablaknak.
	 */
	private JInternalFrame end;
	
	/**
	 * Tagv�ltoz� a TableRowSorter p�ld�ny�nak
	 */
	private TableRowSorter<TableModel> rowSorter;
	/**
	 * Tagv�ltoz� a n�v szerinti keres�s TextFieldj�nek.
	 */
	private JTextField namesearch;
	/**
	 * Tagv�ltoz� a sorsz�m szerinti keres�s TextFieldj�nek.
	 */
	private JTextField scoresearch;
	
	
	/**
	 * A j�t�k p�ly�j�t rajzolja ki egy JInternalFrame()-be,�
	 * eg�szen addig l�tszik, am�g a j�t�kos �letet null�ra nem v�lt.
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
	 * Bels� oszt�ly, ami megrajzolja a j�t�k objektumait,
	 * illetve ir�ny�tja �ket.
	 */
	private class Play extends JPanel implements ActionListener, KeyListener {
		
		/**
		 * ActionListener timer
		 */
		private Timer t = new Timer(5, this);
		/**
		 * Amennivel a j�t�kos poz�ci�ja v�ltozik.
		 */
		private int velx;
		/**
		 * Lehessen kezelni az �gb�l es� t�rgyakat.
		 */
		private FallingCollectibles falling = new FallingCollectibles(player);
		/**
		 * Az �letet jelz� sz�v
		 */
		private Heart heart = new Heart(new Position(410, 10));
		
		/**
		 * A h�tt�r sz�ne
		 */
		private static final Color LIGHT_BLUE = new Color(154,194,247);
		/**
		 * A f�ld sz�nei
		 */
		private static final Color BROWN = new Color(84, 49, 3);
		private static final Color DARK_GREEN = new Color(40, 110, 0);
		
		//FallThread fall = new FallThread(falling);
		
		/**
		 * Id�z�teni lehessen a k�tfajta sz�lat.
		 */
		ScheduledExecutorService executor1 = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService executor2 = Executors.newScheduledThreadPool(1);
		
		/**
		 * Az oszt�ly param�ter n�lk�li konstruktora, 
		 * be�ll�tja a h�tteret,
		 * elind�tja a timert, illetve a sz�lakat, amik a gy�jthet� objektumok viselked�s��rt felel�sek.
		 * Id�k�z�nk�nt gyors�tj�k az es�sis sebess�g�ket, illetve egyre t�bb jelenik meg egyszerre.
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
		 * Megrajzolja a p�lya elemeit.
		 * @param g A grafikus oszt�ly p�ld�nya, ami a rajzol�st v�gzi.
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
		 * Az ActionListener k�telez�en megval�s�tand� f�ggv�nye.
		 * Ha a j�t�kos �lete nagyobb null�n�l,
		 * megv�ltoztatja a gy�jthet� objektumok pozici�j�t,
		 * ha a jobbra/blara ny�l vagy az A/D bet�k le vannak nyomva megv�ltoztatja a j�t�kos pozic��j�t,
		 * �jrarajzolja a p�ly�t.
		 * Ha a j�t�kos �lete null�ra cs�kkent
		 * elt�nteti a p�ly�t, �s az internalframet, amibe el lett helyezve,
		 * a j�t�kos �ltet -1-re �ll�tja, hogy egyik loopba se ugorjon be t�bbet.
		 * Megh�vja az End met�dust.
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
		 * Be�ll�tja a velx v�ltoz�t 10-re;
		 */
		public void right() {
			if(player.getPosition().getX() > 451)
				velx = 0;
			else velx = 10;
		}
		
		/**
		 * Be�ll�tja a belx v�ltoz�t -10-re;
		 */
		public void left() {
			if(player.getPosition().getX() < 2)
				velx = 0;
			else velx = -10;
		}
		
		/**
		 * KeyListener k�telez�en megval�s�tand� met�dusa.
		 * Ha az A bet�, vagy a balra ny�l le van nyomva megh�vja a left met�dust.
		 * Ha a D bet�, vagy a jobbra ny�l le van nyomva megh�vja a right met�dust.
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
		 * KeyListener k�telez�en megval�s�tand� met�dusa.
		 * Nincs r� sz�ks�g �gy nem implement�ltam.
		 */
		public void keyTyped(KeyEvent e) {}
		

		/**
		 * KeyListener k�telez�en megval�s�tand� met�dusa.
		 * Nincs r� sz�ks�g �gy nem implement�ltam.
		 */
		public void keyReleased(KeyEvent e) {}

	}
	
	/**
	 * A j�t�k kezdet�n megjelen� internalframe.
	 * Elk�ri a j�t�kos nev�t, illetve ismerteti a j�t�k szab�lyait.
	 * Ha a "Start!" gombot lenyomj�k elkezd�dik a j�t�k.
	 */
	private void Start() {
		
		this.setLayout(new BorderLayout());
		
		//Start men� az elej�n, hogy felvegye a j�t�kos nev�t
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
	 * Ez a f�ggv�ny h�v�dik meg, ha a j�tkos lenyomja a start gombot.
	 * @param str a TextAreab�l kapott sztring.
	 * @param p A j�t�kos, akinek be kell �ll�tani a nev�t.
	 * Ha a param�terk�nt kapott sztrin nem �res, akkor be�ll�tja a j�t�kos nev�t,
	 * elt�nteti a start ablakot,
	 * Megh�vja a GameField f�ggv�nyt.
	 */
	public void StartButtonPressed(String str, Player p) {
		if(!(str.equals(""))) {
			p.setName(str);
			start.setVisible(false);
			GameField();
		}
	}
	
	/**
	 * A j�t�k v�g�n megjelen� internalframet kezeli.
	 * Hozz�adja az eddigi j�t�kosokat kezel� list�hoz az aktu�lis j�t�kost.
	 * A list�t pontsz�mok szerint cs�kken� sorrendbe rendezi.
	 * Megjelen�ti a j�t�kosok nev�t �s el�rt pontsz�mukat egy JTable-ben.
	 * L�trehoz k�t TextFieldet, amiben n�v �s pontsz�m alapj�n lehet keresni.
	 * Hozz�aadja a TestFieldekhez a megfelel� DocumentListenereket.
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
	 * DocumentListener a n�v szerinti keres�shez.
	 * Csak kezd�bet�k alapj�n keres.
	 * @author dyata
	 *
	 */
	private class NameSearchTyped implements DocumentListener {
		
		
		/**
		 * K�telez�en megval�s�tand� met�dus. 
		 * Ha bele�rnak a TextFieldbe, be�ll�tja a rowSorter filter�t a textFieldb�l kapott sz�vegre.
		 */
		@Override
		public void insertUpdate(DocumentEvent e) {
				String str = namesearch.getText();
				rowSorter.setRowFilter(RowFilter.regexFilter("^" + str, 0));
		}
		
		/**
		 * K�telez�en megval�s�tand� met�dus. 
		 * Ha t�r�lnek a TextFieldb�l, be�ll�tja a rowSorter filter�t a textFieldb�l kapott sz�vegre.
		 */
		@Override
		public void removeUpdate(DocumentEvent e) {
				String str = namesearch.getText();
				rowSorter.setRowFilter(RowFilter.regexFilter("^" + str, 0));
			
		}

		/**
		 * K�telez�en megval�s�tand� met�dus, azonban nincs r� sz�ks�g, �gy nincs implement�lva.
		 *
		 */
		@Override
		public void changedUpdate(DocumentEvent e) {
				/*String str = namesearch.getText();
				rowSorter.setRowFilter(RowFilter.regexFilter("^" + str, 0));*/
		}
		
	}
	
	/**
	 * DocumentumListener a sorsz�m szerinti keres�shez.
	 */
	private class ScoreSearchTyped implements DocumentListener{

		/**
		 * K�telez�en megval�s�tand� met�dus. 
		 * Ha bele�rnak a TextFieldbe, be�ll�tja a rowSorter filter�t a textFieldb�l kapott sz�vegre.
		 */
		@Override
		public void insertUpdate(DocumentEvent e) {
			String str = scoresearch.getText();
			rowSorter.setRowFilter(RowFilter.regexFilter("^" + str, 1));
			
		}

		/**
		 * K�telez�en megval�s�tand� met�dus. 
		 * Ha t�r�lnek a TextFieldb�l, be�ll�tja a rowSorter filter�t a textFieldb�l kapott sz�vegre.
		 */
		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = scoresearch.getText();
			rowSorter.setRowFilter(RowFilter.regexFilter("^" + str, 1));
			
		}

		/**
		 * K�telez�en megval�s�tand� met�dus, azonban nincs r� sz�ks�g, �gy nincs implement�lva.
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
