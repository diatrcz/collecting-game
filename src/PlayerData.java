import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * A j�t�kosok adatait t�rol� oszt�ly.
 */
@SuppressWarnings("serial")
public class PlayerData extends AbstractTableModel{
	
	/**
	 * Tagv�ltoz� a j�tkosok adatainak t�rol�s�ra.
	 */
	List<Player> players = new ArrayList<Player>();
	
	/**
	 * Hozz�ad egy �j j�t�kost a list�hoz.
	 * @param player A j�t�kos, akit hozz� kell adni a list�hoz.
	 */
	public void addPlayer(Player player) {
    	players.add(player);
    }
	
	/**
	 * N�vekv� sorrendbe helyezi a benne l�v� elemeket pontsz�m alapj�n.
	 */
	public void Sort() {
		Collections.sort(players,
				(p1, p2) -> (Long.compare(p1.getScore(), p2.getScore()))
			);
			
		Collections.reverse(players);
	}
	
	/**
	 * H�ny sora van a t�bl�zatnak.
	 * @return A sorok sz�ma.
	 */
	@Override
	public int getRowCount() {
		return players.size();
	}
	
	/**
	 * H�ny oszlopa van a t�bl�zatnak.
	 * @return Az oszlopok sz�ma.
	 */
	@Override
	public int getColumnCount() {
		return 2;
	}
	
	/**
	 * Visszaadja egy bizonyos cella �rt�k�t.
	 * @param rowIndex A sor sorsz�ma.
	 * @param columnIndex Az oszlop sorsz�ma.
	 * @return A cella �rt�ke.
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
	 * Megadja az oszlopok nev�t.
	 * @param columnIndex Az oszlop sorsz�ma.
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
	 * Megadja az oszlop elemeinek az oszt�ly�t.
	 * @param columnIndex Az oszlop sorsz�ma.
	 * @return Az oszlop elemeinek az oszt�lya.
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
