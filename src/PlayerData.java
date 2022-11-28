import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * A játékosok adatait tároló osztály.
 */
@SuppressWarnings("serial")
public class PlayerData extends AbstractTableModel{
	
	/**
	 * Tagváltozó a játkosok adatainak tárolására.
	 */
	List<Player> players = new ArrayList<Player>();
	
	/**
	 * Hozzáad egy új játékost a listához.
	 * @param player A játékos, akit hozzá kell adni a listához.
	 */
	public void addPlayer(Player player) {
    	players.add(player);
    }
	
	/**
	 * Növekvõ sorrendbe helyezi a benne lévõ elemeket pontszám alapján.
	 */
	public void Sort() {
		Collections.sort(players,
				(p1, p2) -> (Long.compare(p1.getScore(), p2.getScore()))
			);
			
		Collections.reverse(players);
	}
	
	/**
	 * Hány sora van a táblázatnak.
	 * @return A sorok száma.
	 */
	@Override
	public int getRowCount() {
		return players.size();
	}
	
	/**
	 * Hány oszlopa van a táblázatnak.
	 * @return Az oszlopok száma.
	 */
	@Override
	public int getColumnCount() {
		return 2;
	}
	
	/**
	 * Visszaadja egy bizonyos cella értékét.
	 * @param rowIndex A sor sorszáma.
	 * @param columnIndex Az oszlop sorszáma.
	 * @return A cella értéke.
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Player player = players.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return player.getName();
		default: 
			return player.getScore();
		
		}
	}
	
	/**
	 * Megadja az oszlopok nevét.
	 * @param columnIndex Az oszlop sorszáma.
	 * @return Az oszlop neve.
	 */
	public String getColumnName(int columnIndex) {
		switch(columnIndex) {
		case 0:
			return "Name";
		default:
			return "Score";
		}
	}
	
	/**
	 * Megadja az oszlop elemeinek az osztályát.
	 * @param columnIndex Az oszlop sorszáma.
	 * @return Az oszlop elemeinek az osztálya.
	 */
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
		case 0:
			return String.class;
		default:
			return Long.class;
		}
	}
}
