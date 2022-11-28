import java.awt.Color;
import java.awt.Graphics;

/**
 * Oszt�ly  a bomb�k megval�s�t�s�ra.
 */
public class Bomb extends LifeManipulator{
	
	/**
	 * Az oszt�ly konstruktora.
	 * A lifeValue �rt�ke -1,
	 * a colour �rt�ke Color(70, 66, 73);
	 * @param p A kiindul�si poz�ci�.
	 */
	public Bomb(Position p) {
		this.lifeValue = -1;
		this.colour = new Color(70, 66, 73);
		this.p = p;
	}
	
	/**
	 * Megrajzolja a bomb�t.
	 * @param g A Graphics sozt�ly p�ld�nya, ami a rajzol�st v�gzi.
	 */
	public void draw(Graphics g) {
		g.setColor(this.colour);
		g.fillRect(this.p.getX()+20, this.p.getY()-5, 5, 15);
		g.fillOval(this.p.getX(), this.p.getY(), 45, 45);
		
		g.setColor(new Color(132, 4, 0));
		g.fillOval(this.p.getX()+18, this.p.getY()-9, 8, 8);
		
		
	}

}
