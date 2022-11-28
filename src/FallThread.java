/**
 * Oszt�ly, amely �jabb t�rgyakat ad a list�hoz.
 *
 */
public class FallThread  implements Runnable{
	 /**
	  * A lista, amihez az �j t�rgyakat adja
	  */
	private FallingCollectibles falling;
	
	public FallThread(FallingCollectibles f) {
		this.falling = f;
	}
	
	/**
	 * Ha �ppen nem fut a roundCheck f�ggv�ny, akkor �jabb t�rgyat ad list�hoz
	 */
	public void run() {
		falling.addCollectible();
	}
	
}
