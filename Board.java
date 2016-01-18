import java.lang.*;
import java.util.*;

public class Board{
	public static int wind;	//0東 1南 2西 3北
	private static Shuffler shuffler;
	public static void main(String args[]){
		wind = 0;
		shuffler = new Shuffler();
		Player[] player = new Player[4];
		ArrayList<ArrayList<Tile>> allTiles = new ArrayList<ArrayList<Tile>>();//0萬 1筒 2條 3字
		for(int i = 0 ; i < 4 ; i++){
			allTiles.add(new ArrayList<Tile>());
			player[i] = new PlayerText("Player"+i);
		}
		while(true){
			for(int i = 0 ; i < 4 ; i++){
				for(int j = 0 ; j < 4 ; j++){
					allTiles.get(j).clear();
				}
				for(int j = 0 ; j < 13 ; j++){
					Tile tmpTile = shuffler.getNext();
					allTiles.get(tmpTile.suit).add(tmpTile);
				}
				player[i].initHand(allTiles);
			}
			wind = (wind+1)%4;
		}

	}

}
