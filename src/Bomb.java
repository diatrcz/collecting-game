import java.awt.Color;
import java.awt.Graphics;

/**
 * Osztály  a bombák megvalósítására.
 */
public class Bomb extends LifeManipulator{
	
	/**
	 * Az osztály konstruktora.
	 * A lifeValue értéke -1,
	 * a colour értéke Color(70, 66, 73);
	 * @param p A kiindulási pozíció.
	 */
	public Bomb(Position p) {
		this.lifeValue = -1;
		this.colour = new Color(70, 66, 73);
		this.p = p;
	}
	
	/**
	 * Megrajzolja a bombát.
	 * @param g A Graphics soztály példánya, ami a rajzolást végzi.
	 */
	public void draw(Graphics g) {
		g.setColor(this.colour);
		g.fillRect(this.p.getX()+20, this.p.getY()-5, 5, 15);
		g.fillOval(this.p.getX(), this.p.getY(), 45, 45);
		
		g.setColor(new Color(132, 4, 0));
		g.fillOval(this.p.getX()+18, this.p.getY()-9, 8, 8);
		
		
	}

}
