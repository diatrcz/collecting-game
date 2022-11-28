import java.util.ArrayList;
//import java.util.Iterator;
import java.util.Random;

/**
 * Oszt�ly, ami a gy�jthet� t�rgyakat t�rolja.
 */
public class FallingCollectibles {
	
	/**
	 * Tagv�ltoz�, amiben a gy�jthet� objektumok vannak.
	 */
	private ArrayList<Collectible> falling = new ArrayList<Collectible>(); 
	/**
	 * A J�t�kos.
	 */
	private Player player;
	/**
	 * Megadja, hogy �ppen fut-e a roundCheck met�dus
	 */
	private Boolean roundCheckRunning;
	/**
	 * Ennyivel v�ltoztatj�k meg a pozic��jukat a t�rgyak.
	 */
	private int positionChange;
	
	/**
	 * Az oszt�ly konstruktora.
	 * @param player A j�t�kos.
	 */
	public FallingCollectibles(Player player) {
		this.player = player;
		this.roundCheckRunning = false;
		this.positionChange = 1;
	}
	
	/**
	 * Visszaadja a list�t.
	 * @return A lista, amiben a gy�jthet� t�rgyak vannak.
	 */
	public ArrayList<Collectible> getList(){
		return falling;
	}
	
	/**
	 * getter
	 * @return roundCheckRunning v�ltoz�.
	 */
	public Boolean getRoundCheckRunning() {
		return this.roundCheckRunning;
	}
	
	/**
	 * Setter
	 * @param bool Erre �ll�tja be a RoundCheckRunning �rt�k�t.
	 */
	public void setRounCheckRunning(Boolean bool) {
		this.roundCheckRunning = bool;
	}
	
	/**
	 * getter
	 * @return poisitionChange
	 */
	public int getPositionChange() {
		return this.positionChange;
	}
	
	/**
	 * setter
	 * @param num ennyire �ll�tja be a positionChanget
	 */
	public void setPositionChange(int num) {
		this.positionChange = num;
	}
	
	/**
	 * Megn�veli a positionChange �rt�k�t.
	 * @param num Ennyivel n�veli meg az �rt�k�t.
	 */
	public void increasePositionChange(int num) {
		this.positionChange += num;
	}
	
	/**
	 * Megn�zi, hogy az adott pozici� t�l k�zel van e, minden olyan list�ban szerpl� t�rgyhoz, 
	 * amelyeknek megegyezik az y koordin�t�juk a poz�ci��val.
	 * Ha igen, megv�ltoztatja azt, �s �jra ellen�riz�st v�gez.
	 * @param p A pozic��, amit �ssze kell hasonl�tani.
	 */
	public void positionCheck(Position p) {
		for(Collectible tmp: falling) {
			for(int j = 0; j < 50; j++) {
				if(p.getY()+j == tmp.getPosition().getY()) {
					for(int i = 0; i < 45; i++) {
						if(p.getX()+i == tmp.getPosition().getX()) {
							int num = p.getX();
							num += 50;
							p.setX(num);
							if(p.getX() >= 420) {
								num -= 100;
								p.setX(num);
							}
							positionCheck(p);
						
						}
						else if(p.getX()-i == tmp.getPosition().getX()) {
							int num = p.getX();
							num += 100;
							p.setX(num);
							if(p.getX() >= 420) {
								num -= 200;
								p.setX(num);
							}
							positionCheck(p);
						}
					}
				}
			}
		}
		return;
	}
	
	/**
	 * Hozz�aad egy �j t�rgyat a list�hoz.
	 * A t�rgy y pozic��ja -40, az x-et random v�lasztja meg.
	 * Ellen�rz�st v�gez, hogy nincs-e �tfed�s m�s t�rgyakkal.
	 */
	public void addCollectible(){
		if(!(this.roundCheckRunning)) {
			Random rand = new Random();
			int randint = rand.nextInt(1000);
			int tmp = randint % 11;
	
			int xcord = rand.nextInt(420);
			Position p = new Position(xcord, -40);
			positionCheck(p);
		
			switch(tmp) {
			case 1:
				falling.add(new Heart(p));
				break;
			case 2 & 5 & 8: 
				falling.add(new Orange(p));
				break;
			case 3 & 6:
				falling.add(new BlueBerry(p));
				break;
			case 4 & 7:
				falling.add(new Bomb(p));
				break;
			default:
				falling.add(new Apple(p));
				break;
			}
		}
	}
	
	/**
	 * V�gig megy a list�n.
	 * Az elej�n true-ra �ll�tja a roundCheckRunning �rt�k�t, a v�g�n falsera.
	 * Ha a t�rgy hozz��rt a j�t�koshoz, megh�vja a collideWith f�ggv�nyt, �s elt�vol�tja a t�rgyat a list�b�l.
	 * Ha a t�rgy hozz��rt a f�ldh�z elt�vol�tja a t�rgyat a list�b�l.
	 * Ha egyik sem, akkor megv�ltoztatja a pozici�j�t.
	 */
	public void roundCheck() {
		this.roundCheckRunning = true;
		if(falling.size() > 0) {
			/*Iterator<Collectible> itr = falling.iterator();
			while(itr.hasNext()) {
				Collectible tmp = itr.next();
				if(tmp.touchPlayer(player)) {
					tmp.collideWith(player);
					itr.remove();
				}
				else if(tmp.getPosition().getY() >= 720) {
					itr.remove();
				}
				else
					tmp.changePosition(1);
			}	*/
			for(int i = 0; i < falling.size(); i++) {
				if(falling.get(i).touchPlayer(player)) {
					falling.get(i).collideWith(player);
					falling.remove(i);
				}
				else if(falling.get(i).getPosition().getY() >= 720) {
					falling.remove(i);
				}
				else {
					falling.get(i).changePosition(this.positionChange);
				}
			}
		}
		this.roundCheckRunning = false;
		
	}
}
