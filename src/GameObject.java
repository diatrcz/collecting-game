import java.awt.Color;
import java.awt.Graphics;

/**
 * Abstract oszt�ly,
 * amib�l sz�rmaztatni lehet a j�tkos
 * �s az �gb�l hull� t�rgyak oszt�lyait.
 */
public abstract class GameObject {
	/**
	 * Tagv�ltoz�, ami az objektum poz�ci�j�t t�rolja.
	 */
	protected Position p;
	
	/**
	 * Tagv�ltoz� a t�rgy sz�n�nek.
	 */
	protected Color colour;
	
	
	/**
	 * 
	 * Setter
	 * @param p be�ll�tja a poz�ci�t p-re.
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
	 * @return A t�rgy sz�ne.
	 */
	public Color getColour() {
		return this.colour;
	}
	
	/**
	 * Setter
	 * @param c A sz�n, amire be kell �ll�tani a t�rgy sz�n�t.
	 */
	public void setColour(Color c) {
		this.colour = c;
	}
	
	/**
	 * Megrajzolja a t�rgyakat.
	 * @param g A Graphics oszt�ly egy p�ld�nya, ami a rajzol�st v�gzi.
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * A poz�ci� megv�ltoztat�sa. 
	 * J�t�kos eset�n az x �rt�k�t v�ltoztatjuk, minden m�s eset�n az y-n�t.
	 * @param num Amennyivel meg kell v�ltoztatni a poz�ci�t.
	 */
	public abstract void changePosition(int num);
	
	
}
