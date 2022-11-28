import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * A j�t�kost le�r� oszt�ly.
 * Szerializ�lhat�.
 */
@SuppressWarnings("serial")
public class Player extends GameObject implements Serializable {
	/**
	 * Tagv�ltoz� a j�t�kos nev�re.
	 */
	private String name;
	/**
	 * Tagv�ltoz� a j�t�kos �let�re.
	 */
	private int life;
	/**
	 * Tagv�ltoz� a j�t�kos pontj�hoz.
	 */
	private long score;
	
	/**
	 * Az oszt�ly konstruktora.
	 */
	public Player() {
		this.name = "";
		this.p = new Position(250,712);
		this.colour = new Color(106, 0, 110);
		this.life = 3;
		this.score = 0;
	}
	
	/**
	 * Setter
	 * @param str Amire a n�v �rt�k�t be kell �ll�tani.
	 */
	public void setName(String str) {
		this.name = str;
	}
	
	/**
	 * Setter
	 * @param num Amire az �let �rt�k�t be kell �ll�tani.
	 */
	public void setLife(int num) {
		this.life = num;
	}
	
	/**
	 * Setter
	 * @param num Amire a pontsz�m �rt�k�t be kell �ll�tani.
	 */
	public void setScore(long num) {
		this.score = num;
	}
	
	/**
	 * Getter
	 * @return A pontsz�m �rt�k�t.
	 */
	public long getScore() {
		return this.score;
	}
	
	/**
	 * Getter 
	 * @return Az �let �rt�ke.
	 */
	public int getLife() {
		return this.life;
	}
	
	/** 
	 * Getter
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * A j�t�kos poz�ci�j�nak x �rt�k�t v�ltoztatja meg.
	 * @param x A sz�m annyivel megv�ltozik az x poz�ci� �rt�ke.
	 */
	public void changePosition(int x) {
		int tmp = p.getX();
		tmp += x;
		p.setX(tmp);
	}
	
	/**
	 * A j�t�kos kirajzol�sa. 
	 * Egy t�glalap.
	 * @param g A Graphics oszt�ly p�ld�nya, ami a rajzol�st v�gzi. 
	 */
	public void draw(Graphics g) {
		g.setColor(this.colour);
		g.fillRect(this.p.getX(), this.p.getY(), 15, 40);
	}

}
