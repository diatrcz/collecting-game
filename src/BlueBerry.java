import java.awt.Color;
import java.awt.Graphics;

/**
 * Oszt�ly az �fony�k megval�s�t�s�hoz.
 */
public class BlueBerry extends Fruit{
	
	/**
	 * Az oszt�ly konstruktora,
	 * a value �rt�ke 3,
	 * a colour �rt�ke Color(153, 51, 230);
	 * @param p A kiindul�si poz�ci�.
	 */
	public BlueBerry(Position p) {
		this.value = 3;
		this.colour = new Color(153, 51, 230);
		this.p = p;
	}
	
	/**
	 * Megrajzolja az �fony�t.
	 * @param g A Graphics sozt�ly p�ld�nya, ami a rajzol�st v�gzi.
	 */
	public void draw(Graphics g) {
		g.setColor(this.colour);
		g.fillOval(this.p.getX(), this.p.getY(), 45, 45);
		
		g.setColor(new Color(168, 61, 250));
		g.fillOval(this.p.getX()+9, this.p.getY()+23, 30, 20);
		
	}

}
