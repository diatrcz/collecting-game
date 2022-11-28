/**
 * Absztrakt osztály, amibõl a gyûjthetõ tárgyakat származtatjuk.
 */
public abstract class Collectible extends GameObject{
	
	/**
	 * Mi történik, ha a játékos elkapja.
	 * @param p
	 */
	public abstract void collideWith(Player p);
	
	/**
	 * Megváltoztatja a tárgy y koordinátáját.
	 * @param y Ennyivel változik a koordináta.
	 */
	public void changePosition(int y) {
		int tmp = this.p.getY();
		tmp += y;
		this.p.setY(tmp);	
	}
	
	
	/**
	 * Ellenõrzi, hogy a játékos és az adott tárgy fedik-e egymást,
	 * azaz a játékos elkapta-e az adott tárgyat.
	 * @param player A játékos, akinek a koordinátáihoz viszonyítunk.
	 * @return true, ha a játékos hozzáért a tárgyhoz, false, ha nem ért hozzá.
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
