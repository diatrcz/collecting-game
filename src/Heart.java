import java.awt.Color;
import java.awt.Graphics;

/**
 * Oszt�ly a sz�vek megval�s�t�s�ra.
 */
public class Heart extends LifeManipulator {
	
	/**
	 * Az oszt�ly konstruktora.
	 * A lifeValue �rt�ke 1,
	 * a colour �rt�ke Color(246, 0, 0);
	 * @param p A kiindul�si poz�ci�.
	 */
	public Heart(Position p) {
		this.lifeValue = 1;
		this.colour = new Color(246, 0, 0);
		this.p = p;
	}
	
	/**
	 * A sz�v k�t k�rb�l �s egy h�romsz�gb�l lett kirajzolva, 
	 * alapja egy egyenl� oldal� h�romsz�g, 
	 * a k�t �v pedig k�t azonos �tm�r�j� k�r.
	 * @param p A Graphics oszt�ly p�ld�nya, ami a rajzol�st v�gzi.
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
	 * Ha  a j�t�kos �letet 1-re cs�kken, az �letek sz�m�t jel�l� sz�v elkezd villogni,
	 * azaz ha piros a sz�ne, a p�lya sz�n�vel lesz egyenl�,
	 * ha a p�lya sz�n�vel egyenl� a sz�ne, akkor �jra piros lesz,
	 * ha a j�t�kos �sszegy�jt egy �gb�l hull� sz�vet, a sz�ne �jra piros lesz.
	 * @param player Az aktu�lis j�t�kos.
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
