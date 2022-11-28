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
	 * Hozz�adja-e az �j j�t�kost a list�hoz.
	 */
	@Test
	public void AddPlayerTest() {
		data.addPlayer(player);
		Assert.assertEquals(1, data.players.size());
	}
	
	/**
	 * Helyesen v�gzi-e el a n�vekv� sorba rendez�st.
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
