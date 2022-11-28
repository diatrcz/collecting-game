import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Játékossal kapcsolatos tesztek
 *
 */
public class PlayerTest {
	
	private Player player;
	
	@Before
	public void setUp() {
		player = new Player();
		player.setName(null);
	}
	
	
	/**
	 * Ha nem írnak be semmit a TextFieldbe, akkor nem állítja be a játékos nevének.
	 */
	@Test
	public void GameStartTestWithNoText() {
		Game game = new Game();
		game.StartButtonPressed("", player);
		Assert.assertNull(player.getName());	
	}
	
	/**
	 * Ha beírnak valamit a Textfieldbe, akkor beállítja a játékos nevének.
	 */
	@Test
	public void GameStartTestWithText() {
		Game game = new Game();
		game.StartButtonPressed("player1", player);
		Assert.assertEquals("player1", player.getName());
	}
	
	/**
	 * Ha összegyûjt a gyümölcsöket, akkor jól változik-e a pontszáma.
	 */
	@Test
	public void PlayerScoreChange() {
		Apple apple = new Apple(player.getPosition());
		apple.collideWith(player);
		Assert.assertEquals(1, player.getScore());
		
		Orange orange = new Orange(player.getPosition());
		orange.collideWith(player);
		Assert.assertEquals(3, player.getScore());
		
		BlueBerry blueberry = new BlueBerry(player.getPosition());
		blueberry.collideWith(player);
		Assert.assertEquals(6, player.getScore());
		
	}
	
	/**
	 * Ha összegyûjt szívet vagy bombát jól változik-e az élete.
	 */
	@Test
	public void PlayerLifeChange() {
		Heart heart = new Heart(player.getPosition());
		heart.collideWith(player);
		Assert.assertEquals(4, player.getLife());
		
		Bomb bomb = new Bomb(player.getPosition());
		bomb.collideWith(player);
		Assert.assertEquals(3, player.getLife());
	}
	
	/**
	 * Jól változik-e a pozíció, illetve tényleg csak az x pozíció változik.
	 */
	@Test
	public void PlayerPositionChange() {
		player.setPosition(new Position(0,0));
		player.changePosition(20);
		Assert.assertEquals(20, player.getPosition().getX());
		Assert.assertEquals(0, player.getPosition().getY());
	}

}
