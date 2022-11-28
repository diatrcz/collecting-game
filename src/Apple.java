import java.awt.Color;
import java.awt.Graphics;

/**
 * Osztály az almák megvalósításához.
 */
public class Apple extends Fruit {
	
	/**
	 * Az osztály konstruktora.
	 * A value értéke 1,
	 * a colour értéke Color(132, 4, 0).
	 * @param p A tárgy kezdeti pozíciója.
	 */
	public Apple(Position p) {
		this.value = 1;
		this.colour = new Color(132, 4, 0);
		this.p = p;
	}
	
	/**
	 * Megrajzolja az almát.
	 * @param g A Graphics soztály példánya, ami a rajzolást végzi.
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
