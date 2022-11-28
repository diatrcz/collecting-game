import java.awt.Color;
import java.awt.Graphics;

/**
 * Osztály a narancsok megvalósításához.
 */
public class Orange extends Fruit {
	
	/**
	 * Az osztály konstruktora,
	 * a value értéke 2,
	 * a colour értéke Color(222, 135, 21);
	 * @param p A kiindulási pozíció.
	 */
	public Orange(Position p) {
		this.value = 2;
		this.colour = new Color(222, 135, 21);
		this.p = p;
	}

	/**
	 * Megrajzolja a narancsot.
	 * @param g A Graphics soztály példánya, ami a rajzolást végzi.
	 */
	public void draw(Graphics g) {
		g.setColor(this.colour);
		g.fillOval(this.p.getX(), this.p.getY(), 45, 45);
		
		g.setColor(new Color(240, 148, 27));
		g.fillOval(this.p.getX()+9, this.p.getY()+23, 30, 20);
		
	}
}
