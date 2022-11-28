/**
 * Oszt�ly a poz�ci�k t�rol�s�ra.
 */
public class Position {
	/**
	 * Tagv�ltoz� az x koordin�ta t�rol�s�ra.
	 */
	private int x;
	/**
	 * Tagv�ltoz� az y koordin�ta t�rol�s�ra.
	 */
	private int y;
	
	/**
	 * Az oszt�ly konstruktora.
	 * @param x Az x poz�ci�.
	 * @param y Az y poz�ci�.
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter
	 * @return x �rt�ke.
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Getter
	 * @return y �rt�ke.
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Setter
	 * @param newx Az �rt�ke, amire x-et �t�ll�tja.
	 */
	public void setX(int newx) {
		this.x = newx;
	}
	
	/**
	 * Setter
	 * @param newy Az �rt�k, amire y-t �t�ll�tja.
	 */
	
	public void setY(int newy) {
		this.y = newy;
	} 
	
}
