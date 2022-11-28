/**
 * Oszt�ly, ami megn�veli az es�si sebess�g�t egy list�n bel�li t�rgyaknak
 *
 */
public class FallPositionThread implements Runnable{
	
	/**
	 * Tagv�ltoz�, ami a list�t t�rolja.
	 */
	private FallingCollectibles falling;
	
	/**
	 * Az oszt�ly konstruktora.
	 * @param f A lista.
	 */
	public FallPositionThread(FallingCollectibles f) {
		this.falling = f;
	}
	
	/**
	 * Am�g a PositionChange kisebb n�gyn�l megn�veli eggyel.
	 */
	public void run() {
		if(falling.getPositionChange() < 4) {
			falling.increasePositionChange(1);
		}
	}
	
}