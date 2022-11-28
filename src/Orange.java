import java.awt.Color;
import java.awt.Graphics;

/**
 * Oszt�ly a narancsok megval�s�t�s�hoz.
 */
public class Orange extends Fruit {
	
	/**
	 * Az oszt�ly konstruktora,
	 * a value �rt�ke 2,
	 * a colour �rt�ke Color(222, 135, 21);
	 * @param p A kiindul�si poz�ci�.
	 */
	public Orange(Position p) {
		this.value = 2;
		this.colour = new Color(222, 135, 21);
		this.p = p;
	}

	/**
	 * Megrajzolja a narancsot.
	 * @param g A Graphics sozt�ly p�ld�nya, ami a rajzol�st v�gzi.
	 */
	public void draw(Graphics g) {
		g.setColor(this.colour);
		g.fillOval(this.p.getX(), this.p.getY(), 45, 45);
		
		g.setColor(new Color(240, 148, 27));
		g.fillOval(this.p.getX()+9, this.p.getY()+23, 30, 20);
		
	}
}
