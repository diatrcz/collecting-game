/**
 * Absztrakt osztály, amelybõl azokat az osztályokat származtatjuk,
 * amelyek megváltoztatják a játékos pontszámát.
 */
public abstract class Fruit extends Collectible {
	
	/**
	 * Tagváltozó a tárgy értékének.
	 */
	protected int value;
	
	/**
	 * Setter
	 * @param num Ennyire állítja be a tárgy értékét.
	 */
	public void setValue(int num) {
		this.value = num;
	}
	
	/**
	 * Getter
	 * @return A tárgy értéke.
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Ha a játékos összegyûjte egy gyümölcsöt,
	 * a pontszáma a gyümölcs értékével nõ.
	 * @param player Az aktuális játékos.
	 */
	public void collideWith(Player player) {
		long tmp = player.getScore();
		tmp += this.value;
		player.setScore(tmp);
	}
	
}
