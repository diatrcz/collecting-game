/**
 * Osztály, ami megnöveli az esési sebességét egy listán belüli tárgyaknak
 *
 */
public class FallPositionThread implements Runnable{
	
	/**
	 * Tagváltozó, ami a listát tárolja.
	 */
	private FallingCollectibles falling;
	
	/**
	 * Az osztály konstruktora.
	 * @param f A lista.
	 */
	public FallPositionThread(FallingCollectibles f) {
		this.falling = f;
	}
	
	/**
	 * Amíg a PositionChange kisebb négynél megnöveli eggyel.
	 */
	public void run() {
		if(falling.getPositionChange() < 4) {
			falling.increasePositionChange(1);
		}
	}
	
}