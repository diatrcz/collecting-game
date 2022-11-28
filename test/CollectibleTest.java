import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import org.junit.Assert;

/**
 * �sszegy�jthet� t�rgyakkal kapcsolatos teztek.
 */
public class CollectibleTest {
	
	private Player player;
	private FallingCollectibles falling;
	
	@Before
	public void setUp() {
		player = new Player();
		falling = new FallingCollectibles(player);
	}
	
	/**
	 * J�l v�ltozik-e a poz�ci�, illetve t�nyleg csak �z y poz�ci� v�ltozik.
	 */
	@Test
	public void CollectiblePositionChangeTest() {
		BlueBerry berry = new BlueBerry(new Position(0,0));
		berry.changePosition(5);
		Assert.assertEquals(0, berry.getPosition().getX());
		Assert.assertEquals(5, berry.getPosition().getY());
	}
	
	/**
	 * J� �rt�kkel t�r-e vissza touchPlayer poz�ci� alapj�n.
	 */
	@Test
	public void CollectibleTouchPlayer() {
		Apple apple = new Apple(new Position(250, 710));
		Assert.assertTrue(apple.touchPlayer(player));
		
		Bomb bomb = new Bomb(new Position(0,0));
		Assert.assertFalse(bomb.touchPlayer(player));
	}
	
	/**
	 * J�l v�ltozik-e meg a sz�v szine, ha a j�t�kos �lete egyre cs�kken. 
	 * J�l v�ltozik-e meg a sz�ne, ha kett�re n� az �lete a j�t�kosnak.
	 */
	@Test
	public void heartColourChangeTest() {
		final Color HEART_RED = new Color(246, 0, 0);
		final Color LIGHT_BLUE = new Color(154,194,247);
		
		Heart heart = new Heart(new Position(0,0));
		player.setLife(1);
		heart.colourChange(player);
		Assert.assertEquals(LIGHT_BLUE, heart.getColour());
		
		heart.colourChange(player);
		Assert.assertEquals(HEART_RED, heart.getColour());
		
		heart.colourChange(player);
		player.setLife(2);
		heart.colourChange(player);
		Assert.assertEquals(HEART_RED, heart.getColour());
	}
	
	/**
	 * Val�ban hozz�ad-e egy �j elemet a list�hoz.
	 */
	@Test
	public void AddCollectibleTest() {
		falling.addCollectible();
		Assert.assertEquals(1, falling.getList().size());
	}
	
	/**
	 * Hozz�ad-e �j elemet az addCollectible, ha k�zben fut a roundCheck met�dus.
	 */
	@Test 
	public void AddWhileRoundCheck() {
		falling.setRounCheckRunning(true);
		falling.addCollectible();
		Assert.assertEquals(0, falling.getList().size());
	}
	
	/**
	 * RoundCheck K�zben j�l v�ltoznak-e az x �s y poz�ci�k.
	 */
	@Test
	public void RoundCheckTestPosChange() {
		Heart heart = new Heart(new Position(0,0));
		falling.getList().add(heart);
		falling.roundCheck();
		Assert.assertEquals(1, heart.getPosition().getY());
		Assert.assertEquals(0, heart.getPosition().getX());
	}
	
	/**
	 * RoundCheck k�zben bel�p az if �gba, ha hozz��r a j�t�kos az egyik elem�hez.
	 */
	@Test
	public void RoundCheckTestCollideWith() {
		BlueBerry blueberry = new BlueBerry(new Position(250, 710));
		falling.getList().add(blueberry);
		falling.roundCheck();
		Assert.assertEquals(0, falling.getList().size());
	}
	
	/**
	 * RoundCheck k�zben bel�p-e az if �gba, ha az adott elem le�rt a f�ldre.
	 */
	@Test
	public void RounCheckTestTouchEarth() {
		Bomb bomb = new Bomb(new Position(123, 720));
		falling.getList().add(bomb);
		falling.roundCheck();
		Assert.assertEquals(0, falling.getList().size());
	}
	
	/**
	 * Megv�ltozik-e a poz�ci�, ha �tlapol�s van.
	 */
	@Test
	public void PositionCheckTest() {
		Position pos = new Position(0, 0);
		Orange orange = new Orange(new Position(5, 0));
		
		falling.getList().add(orange);
		falling.positionCheck(pos);
		Assert.assertEquals(50, pos.getX());
	}
	
	/**
	 * M�k�dik-e a rekurzi� a met�duson bel�l.
	 */
	@Test
	public void RePositionCheckTest() {
		Position pos = new Position(0, 0);
		Orange orange = new Orange(new Position(5, 0));
		falling.getList().add(orange);
		
		Bomb bomb = new Bomb(new Position(49, 0));
		falling.getList().add(bomb);
		falling.positionCheck(pos);
		Assert.assertEquals(150, pos.getX());
	}
	
	/**
	 * M�sik oldalra �s m�k�dik, �s ha kics�szna a keretb�l, akkor megv�ltoztatja m�gegyszer az x poz�ci�t.
	 */
	@Test
	public void PositionCheckBackTest() {
		Position pos = new Position(372,0);
		Apple apple = new Apple(new Position(350, 20));
		falling.getList().add(apple);
		falling.positionCheck(pos);
		
		Assert.assertEquals(272, pos.getX());
	}

}
