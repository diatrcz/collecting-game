/**
 * Absztrakt osztály, amelybõl azokat az osztályokat számaztatjuk,
 * amelyek azokat a gyûjthetõ tárgyakat valósítják meg, 
 * amik megváltoztatják a játékos életének értékét.
 */
public abstract class LifeManipulator extends Collectible{
	
	/**
	 * Tagváltozó, hogy mennyivel változtatja meg a játékos életét.
	 */
	protected int lifeValue;
	
	 /**
	  * Setter
	  * @param amennyire be kell állítani a LifeValue értékét.
	  */
	public void setLifeValue(int num) {
		this.lifeValue = num;
	}
	
	/**
	 * Getter
	 * @return a LifeValue értéke.
	 */
	public int getLifeValue() {
		return this.lifeValue;
	}
	
	/**
	 * Ha a játékos hozzáér az élete a LifeValue értékével nõ.
	 */
	@Override
	public void collideWith(Player p) {
		int tmp = p.getLife();
		tmp += this.lifeValue;
		p.setLife(tmp);
		
	}

}
