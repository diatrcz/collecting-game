/**
 * Absztrakt oszt�ly, amelyb�l azokat az oszt�lyokat sz�maztatjuk,
 * amelyek azokat a gy�jthet� t�rgyakat val�s�tj�k meg, 
 * amik megv�ltoztatj�k a j�t�kos �let�nek �rt�k�t.
 */
public abstract class LifeManipulator extends Collectible{
	
	/**
	 * Tagv�ltoz�, hogy mennyivel v�ltoztatja meg a j�t�kos �let�t.
	 */
	protected int lifeValue;
	
	 /**
	  * Setter
	  * @param amennyire be kell �ll�tani a LifeValue �rt�k�t.
	  */
	public void setLifeValue(int num) {
		this.lifeValue = num;
	}
	
	/**
	 * Getter
	 * @return a LifeValue �rt�ke.
	 */
	public int getLifeValue() {
		return this.lifeValue;
	}
	
	/**
	 * Ha a j�t�kos hozz��r az �lete a LifeValue �rt�k�vel n�.
	 */
	@Override
	public void collideWith(Player p) {
		int tmp = p.getLife();
		tmp += this.lifeValue;
		p.setLife(tmp);
		
	}

}
