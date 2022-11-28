import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * J�t�kossal kapcsolatos tesztek
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
	 * Ha nem �rnak be semmit a TextFieldbe, akkor nem �ll�tja be a j�t�kos nev�nek.
	 */
	@Test
	public void GameStartTestWithNoText() {
		Game game = new Game();
		game.StartButtonPressed("", player);
		Assert.assertNull(player.getName());	
	}
	
	/**
	 * Ha be�rnak valamit a Textfieldbe, akkor be�ll�tja a j�t�kos nev�nek.
	 */
	@Test
	public void GameStartTestWithText() {
		Game game = new Game();
		game.StartButtonPressed("player1", player);
		Assert.assertEquals("player1", player.getName());
	}
	
	/**
	 * Ha �sszegy�jt a gy�m�lcs�ket, akkor j�l v�ltozik-e a pontsz�ma.
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
	 * Ha �sszegy�jt sz�vet vagy bomb�t j�l v�ltozik-e az �lete.
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
	 * J�l v�ltozik-e a poz�ci�, illetve t�nyleg csak az x poz�ci� v�ltozik.
	 */
	@Test
	public void PlayerPositionChange() {
		player.setPosition(new Position(0,0));
		player.changePosition(20);
		Assert.assertEquals(20, player.getPosition().getX());
		Assert.assertEquals(0, player.getPosition().getY());
	}

}
