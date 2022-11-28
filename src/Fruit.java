/**
 * Absztrakt oszt�ly, amelyb�l azokat az oszt�lyokat sz�rmaztatjuk,
 * amelyek megv�ltoztatj�k a j�t�kos pontsz�m�t.
 */
public abstract class Fruit extends Collectible {
	
	/**
	 * Tagv�ltoz� a t�rgy �rt�k�nek.
	 */
	protected int value;
	
	/**
	 * Setter
	 * @param num Ennyire �ll�tja be a t�rgy �rt�k�t.
	 */
	public void setValue(int num) {
		this.value = num;
	}
	
	/**
	 * Getter
	 * @return A t�rgy �rt�ke.
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Ha a j�t�kos �sszegy�jte egy gy�m�lcs�t,
	 * a pontsz�ma a gy�m�lcs �rt�k�vel n�.
	 * @param player Az aktu�lis j�t�kos.
	 */
	public void collideWith(Player player) {
		long tmp = player.getScore();
		tmp += this.value;
		player.setScore(tmp);
	}
	
}
