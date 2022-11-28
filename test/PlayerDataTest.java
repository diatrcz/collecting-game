import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * PlayerDataval kapcsolatos tesztek.
 */
public class PlayerDataTest {
	
	private Player player;
	private PlayerData data;
	
	@Before
	public void setUp() {
		player = new Player();
		player.setName("player");
		player.setScore(100);
		data = new PlayerData();
	}
	
	/**
	 * Hozzáadja-e az új játékost a listához.
	 */
	@Test
	public void AddPlayerTest() {
		data.addPlayer(player);
		Assert.assertEquals(1, data.players.size());
	}
	
	/**
	 * Helyesen végzi-e el a növekvõ sorba rendezést.
	 */
	@Test
	public void SortTest() {
		Player p2 = new Player();
		p2.setName("p2");
		p2.setScore(0);
		
		data.addPlayer(p2);
		data.addPlayer(player);
		
		Player p3 = new Player();
		p3.setName("p3");
		p3.setScore(50);
		
		data.addPlayer(p3);
		
		data.Sort();
		
		Assert.assertEquals(0, data.players.get(2).getScore());
		Assert.assertEquals(50, data.players.get(1).getScore());
		Assert.assertEquals(100, data.players.get(0).getScore());
		
	}
	
}
