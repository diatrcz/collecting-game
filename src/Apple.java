import java.awt.Color;
import java.awt.Graphics;

/**
 * Oszt�ly az alm�k megval�s�t�s�hoz.
 */
public class Apple extends Fruit {
	
	/**
	 * Az oszt�ly konstruktora.
	 * A value �rt�ke 1,
	 * a colour �rt�ke Color(132, 4, 0).
	 * @param p A t�rgy kezdeti poz�ci�ja.
	 */
	public Apple(Position p) {
		this.value = 1;
		this.colour = new Color(132, 4, 0);
		this.p = p;
	}
	
	/**
	 * Megrajzolja az alm�t.
	 * @param g A Graphics sozt�ly p�ld�nya, ami a rajzol�st v�gzi.
	 */
	public void draw(Graphics g) {
		g.setColor(this.colour);
		g.fillOval(this.p.getX(), this.p.getY(), 45, 45);
		
		g.setColor(new Color(156, 5, 0));
		g.fillOval(this.p.getX()+9, this.p.getY()+23, 30, 20);
		
		g.setColor(new Color(15, 65, 2));
		g.fillArc(this.p.getX()+10, this.p.getY()-15, 20, 40, 10, 30);
		
	}

}
