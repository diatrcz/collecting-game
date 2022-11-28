import java.util.ArrayList;
//import java.util.Iterator;
import java.util.Random;

/**
 * Osztály, ami a gyûjthetõ tárgyakat tárolja.
 */
public class FallingCollectibles {
	
	/**
	 * Tagváltozó, amiben a gyûjthetõ objektumok vannak.
	 */
	private ArrayList<Collectible> falling = new ArrayList<Collectible>(); 
	/**
	 * A Játékos.
	 */
	private Player player;
	/**
	 * Megadja, hogy éppen fut-e a roundCheck metódus
	 */
	private Boolean roundCheckRunning;
	/**
	 * Ennyivel változtatják meg a pozicíójukat a tárgyak.
	 */
	private int positionChange;
	
	/**
	 * Az osztály konstruktora.
	 * @param player A játékos.
	 */
	public FallingCollectibles(Player player) {
		this.player = player;
		this.roundCheckRunning = false;
		this.positionChange = 1;
	}
	
	/**
	 * Visszaadja a listát.
	 * @return A lista, amiben a gyûjthetõ tárgyak vannak.
	 */
	public ArrayList<Collectible> getList(){
		return falling;
	}
	
	/**
	 * getter
	 * @return roundCheckRunning változó.
	 */
	public Boolean getRoundCheckRunning() {
		return this.roundCheckRunning;
	}
	
	/**
	 * Setter
	 * @param bool Erre állítja be a RoundCheckRunning értékét.
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
	 * @param num ennyire állítja be a positionChanget
	 */
	public void setPositionChange(int num) {
		this.positionChange = num;
	}
	
	/**
	 * Megnöveli a positionChange értékét.
	 * @param num Ennyivel növeli meg az értékét.
	 */
	public void increasePositionChange(int num) {
		this.positionChange += num;
	}
	
	/**
	 * Megnézi, hogy az adott pozició túl közel van e, minden olyan listában szerplõ tárgyhoz, 
	 * amelyeknek megegyezik az y koordinátájuk a pozícióéval.
	 * Ha igen, megváltoztatja azt, és újra ellenõrizést végez.
	 * @param p A pozicíó, amit össze kell hasonlítani.
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
	 * Hozzáaad egy új tárgyat a listához.
	 * A tárgy y pozicíója -40, az x-et random választja meg.
	 * Ellenõrzést végez, hogy nincs-e átfedés más tárgyakkal.
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
	 * Végig megy a listán.
	 * Az elején true-ra állítja a roundCheckRunning értékét, a végén falsera.
	 * Ha a tárgy hozzáért a játékoshoz, meghívja a collideWith függvényt, és eltávolítja a tárgyat a listából.
	 * Ha a tárgy hozzáért a földhöz eltávolítja a tárgyat a listából.
	 * Ha egyik sem, akkor megváltoztatja a pozicióját.
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
