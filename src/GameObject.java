import java.awt.Color;
import java.awt.Graphics;

/**
 * Abstract osztály,
 * amibõl származtatni lehet a játkos
 * és az égbõl hulló tárgyak osztályait.
 */
public abstract class GameObject {
	/**
	 * Tagváltozó, ami az objektum pozícióját tárolja.
	 */
	protected Position p;
	
	/**
	 * Tagváltozó a tárgy színének.
	 */
	protected Color colour;
	
	
	/**
	 * 
	 * Setter
	 * @param p beállítja a pozíciót p-re.
	 */
	public void setPosition(Position p) {
		this.p = p;
	}
	
	/**
	 * Getter
	 * @return Visszaadja p-t.
	 */
	public Position getPosition() {
		return this.p;
	}
	
	/**
	 * Getter
	 * @return A tárgy színe.
	 */
	public Color getColour() {
		return this.colour;
	}
	
	/**
	 * Setter
	 * @param c A szín, amire be kell állítani a tárgy színét.
	 */
	public void setColour(Color c) {
		this.colour = c;
	}
	
	/**
	 * Megrajzolja a tárgyakat.
	 * @param g A Graphics osztály egy példánya, ami a rajzolást végzi.
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * A pozíció megváltoztatása. 
	 * Játékos esetén az x értékét változtatjuk, minden más esetén az y-nét.
	 * @param num Amennyivel meg kell változtatni a pozíciót.
	 */
	public abstract void changePosition(int num);
	
	
}
