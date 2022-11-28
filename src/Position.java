/**
 * Osztály a pozíciók tárolására.
 */
public class Position {
	/**
	 * Tagváltozó az x koordináta tárolására.
	 */
	private int x;
	/**
	 * Tagváltozó az y koordináta tárolására.
	 */
	private int y;
	
	/**
	 * Az osztály konstruktora.
	 * @param x Az x pozíció.
	 * @param y Az y pozíció.
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter
	 * @return x értéke.
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Getter
	 * @return y értéke.
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Setter
	 * @param newx Az értéke, amire x-et átállítja.
	 */
	public void setX(int newx) {
		this.x = newx;
	}
	
	/**
	 * Setter
	 * @param newy Az érték, amire y-t átállítja.
	 */
	
	public void setY(int newy) {
		this.y = newy;
	} 
	
}
