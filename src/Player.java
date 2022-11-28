import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * A játékost leíró osztály.
 * Szerializálható.
 */
@SuppressWarnings("serial")
public class Player extends GameObject implements Serializable {
	/**
	 * Tagváltozó a játékos nevére.
	 */
	private String name;
	/**
	 * Tagváltozó a játékos életére.
	 */
	private int life;
	/**
	 * Tagváltozó a játékos pontjához.
	 */
	private long score;
	
	/**
	 * Az osztály konstruktora.
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
	 * @param str Amire a név értékét be kell állítani.
	 */
	public void setName(String str) {
		this.name = str;
	}
	
	/**
	 * Setter
	 * @param num Amire az élet értékét be kell állítani.
	 */
	public void setLife(int num) {
		this.life = num;
	}
	
	/**
	 * Setter
	 * @param num Amire a pontszám értékét be kell állítani.
	 */
	public void setScore(long num) {
		this.score = num;
	}
	
	/**
	 * Getter
	 * @return A pontszám értékét.
	 */
	public long getScore() {
		return this.score;
	}
	
	/**
	 * Getter 
	 * @return Az élet értéke.
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
	 * A játékos pozíciójának x értékét változtatja meg.
	 * @param x A szám annyivel megváltozik az x pozíció értéke.
	 */
	public void changePosition(int x) {
		int tmp = p.getX();
		tmp += x;
		p.setX(tmp);
	}
	
	/**
	 * A játékos kirajzolása. 
	 * Egy téglalap.
	 * @param g A Graphics osztály példánya, ami a rajzolást végzi. 
	 */
	public void draw(Graphics g) {
		g.setColor(this.colour);
		g.fillRect(this.p.getX(), this.p.getY(), 15, 40);
	}

}
