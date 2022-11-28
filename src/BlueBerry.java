import java.awt.Color;
import java.awt.Graphics;

/**
 * Osztály az áfonyák megvalósításához.
 */
public class BlueBerry extends Fruit{
	
	/**
	 * Az osztály konstruktora,
	 * a value értéke 3,
	 * a colour értéke Color(153, 51, 230);
	 * @param p A kiindulási pozíció.
	 */
	public BlueBerry(Position p) {
		this.value = 3;
		this.colour = new Color(153, 51, 230);
		this.p = p;
	}
	
	/**
	 * Megrajzolja az áfonyát.
	 * @param g A Graphics soztály példánya, ami a rajzolást végzi.
	 */
	public void draw(Graphics g) {
		g.setColor(this.colour);
		g.fillOval(this.p.getX(), this.p.getY(), 45, 45);
		
		g.setColor(new Color(168, 61, 250));
		g.fillOval(this.p.getX()+9, this.p.getY()+23, 30, 20);
		
	}

}
