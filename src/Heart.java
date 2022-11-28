import java.awt.Color;
import java.awt.Graphics;

/**
 * Osztály a szívek megvalósítására.
 */
public class Heart extends LifeManipulator {
	
	/**
	 * Az osztály konstruktora.
	 * A lifeValue értéke 1,
	 * a colour értéke Color(246, 0, 0);
	 * @param p A kiindulási pozíció.
	 */
	public Heart(Position p) {
		this.lifeValue = 1;
		this.colour = new Color(246, 0, 0);
		this.p = p;
	}
	
	/**
	 * A szív két körbõl és egy háromszögbõl lett kirajzolva, 
	 * alapja egy egyenlõ oldalú háromszög, 
	 * a két ív pedig két azonos átmérõjû kör.
	 * @param p A Graphics osztály példánya, ami a rajzolást végzi.
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(this.colour);
		
		int[] trianglex = {this.p.getX(), this.p.getX()+40, this.p.getX()+20};
		int[] triangley = {this.p.getY(), this.p.getY(), this.p.getY()+30};
		g.fillOval(this.p.getX()-10+11, this.p.getY()-10, 20, 20);
		g.fillOval(this.p.getX()+40-10-11, this.p.getY()-10, 20, 20);
		g.fillPolygon(trianglex, triangley, trianglex.length);
		
	}
	
	/**
	 * Ha  a játékos életet 1-re csökken, az életek számát jelölõ szív elkezd villogni,
	 * azaz ha piros a színe, a pálya színével lesz egyenlõ,
	 * ha a pálya színével egyenlõ a színe, akkor újra piros lesz,
	 * ha a játékos összegyûjt egy égbõl hulló szívet, a színe újra piros lesz.
	 * @param player Az aktuális játékos.
	 */
	public void colourChange(Player player) {
		final Color HEART_RED = new Color(246, 0, 0);
		final Color LIGHT_BLUE = new Color(154,194,247);
		
		if(player.getLife() == 1 && this.colour.equals(HEART_RED))
			this.colour = LIGHT_BLUE;
		else if(player.getLife() == 1 && this.colour.equals(LIGHT_BLUE))
			this.colour = HEART_RED;
		else if(player.getLife() > 1 && this.colour.equals(LIGHT_BLUE))
			this.colour = HEART_RED;
	}

}
