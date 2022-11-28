import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import org.junit.Assert;

/**
 * Összegyûjthetõ tárgyakkal kapcsolatos teztek.
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
	 * Jól változik-e a pozíció, illetve tényleg csak íz y pozíció változik.
	 */
	@Test
	public void CollectiblePositionChangeTest() {
		BlueBerry berry = new BlueBerry(new Position(0,0));
		berry.changePosition(5);
		Assert.assertEquals(0, berry.getPosition().getX());
		Assert.assertEquals(5, berry.getPosition().getY());
	}
	
	/**
	 * Jó értékkel tér-e vissza touchPlayer pozíció alapján.
	 */
	@Test
	public void CollectibleTouchPlayer() {
		Apple apple = new Apple(new Position(250, 710));
		Assert.assertTrue(apple.touchPlayer(player));
		
		Bomb bomb = new Bomb(new Position(0,0));
		Assert.assertFalse(bomb.touchPlayer(player));
	}
	
	/**
	 * Jól változik-e meg a szív szine, ha a játékos élete egyre csökken. 
	 * Jól változik-e meg a színe, ha kettõre nõ az élete a játékosnak.
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
	 * Valóban hozzáad-e egy új elemet a listához.
	 */
	@Test
	public void AddCollectibleTest() {
		falling.addCollectible();
		Assert.assertEquals(1, falling.getList().size());
	}
	
	/**
	 * Hozzáad-e új elemet az addCollectible, ha közben fut a roundCheck metódus.
	 */
	@Test 
	public void AddWhileRoundCheck() {
		falling.setRounCheckRunning(true);
		falling.addCollectible();
		Assert.assertEquals(0, falling.getList().size());
	}
	
	/**
	 * RoundCheck Közben jól változnak-e az x és y pozíciók.
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
	 * RoundCheck közben belép az if ágba, ha hozzáér a játékos az egyik eleméhez.
	 */
	@Test
	public void RoundCheckTestCollideWith() {
		BlueBerry blueberry = new BlueBerry(new Position(250, 710));
		falling.getList().add(blueberry);
		falling.roundCheck();
		Assert.assertEquals(0, falling.getList().size());
	}
	
	/**
	 * RoundCheck közben belép-e az if ágba, ha az adott elem leért a földre.
	 */
	@Test
	public void RounCheckTestTouchEarth() {
		Bomb bomb = new Bomb(new Position(123, 720));
		falling.getList().add(bomb);
		falling.roundCheck();
		Assert.assertEquals(0, falling.getList().size());
	}
	
	/**
	 * Megváltozik-e a pozíció, ha átlapolás van.
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
	 * Mûködik-e a rekurzió a metóduson belül.
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
	 * Másik oldalra és mûködik, és ha kicsûszna a keretbõl, akkor megváltoztatja mégegyszer az x pozíciót.
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
