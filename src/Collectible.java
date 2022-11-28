/**
 * Absztrakt oszt�ly, amib�l a gy�jthet� t�rgyakat sz�rmaztatjuk.
 */
public abstract class Collectible extends GameObject{
	
	/**
	 * Mi t�rt�nik, ha a j�t�kos elkapja.
	 * @param p
	 */
	public abstract void collideWith(Player p);
	
	/**
	 * Megv�ltoztatja a t�rgy y koordin�t�j�t.
	 * @param y Ennyivel v�ltozik a koordin�ta.
	 */
	public void changePosition(int y) {
		int tmp = this.p.getY();
		tmp += y;
		this.p.setY(tmp);	
	}
	
	
	/**
	 * Ellen�rzi, hogy a j�t�kos �s az adott t�rgy fedik-e egym�st,
	 * azaz a j�t�kos elkapta-e az adott t�rgyat.
	 * @param player A j�t�kos, akinek a koordin�t�ihoz viszony�tunk.
	 * @return true, ha a j�t�kos hozz��rt a t�rgyhoz, false, ha nem �rt hozz�.
	 */
	public Boolean touchPlayer(Player player) {
		int playerx = player.getPosition().getX();
		int playery = player.getPosition().getY();
		int collecty = this.p.getY();
		if(collecty < playery && collecty >= playery-40) {
			for(int i = 0; i < 45; i++) {
				for(int j = 0; j < 15; j++) {
					if(this.p.getX() + i == playerx + j)
						return true;
				}
			}
		}
		return false;	
	}
	
}
