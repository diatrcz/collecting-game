/**
 * Osztály, amely újabb tárgyakat ad a listához.
 *
 */
public class FallThread  implements Runnable{
	 /**
	  * A lista, amihez az új tárgyakat adja
	  */
	private FallingCollectibles falling;
	
	public FallThread(FallingCollectibles f) {
		this.falling = f;
	}
	
	/**
	 * Ha éppen nem fut a roundCheck függvény, akkor újabb tárgyat ad listához
	 */
	public void run() {
		falling.addCollectible();
	}
	
}
