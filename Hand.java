import java.lang.*;
import java.util.*;

import java.lang.*;
import java.util.*;

public class Hand {
	
	private ArrayList<ArrayList<Tile>> allTiles = new ArrayList<ArrayList<Tile>>();


	public Hand(){
		allTiles.add(new ArrayList<Tile>());	//wan
		allTiles.add(new ArrayList<Tile>());	//tong
		allTiles.add(new ArrayList<Tile>());	//tiao
		allTiles.add(new ArrayList<Tile>());	//zi
	}

	private ArrayList<Tile> ting = new ArrayList<Tile>();


	public void add(Tile newTile){
		int index = allTiles.get(newTile.suit).indexOf(newTile);
		if(index >= 0) allTiles.get(newTile.suit).get(index).addSize(1);
		else allTiles.get(newTile.suit).add(newTile);
	}
	
	public boolean discard(Tile discardTile){ //If no this tile in hand return false (An error)
		int index = allTiles.get(discardTile.suit).indexOf(discardTile);
		if(index < 0) return false;
		if(allTiles.get(discardTile.suit).get(index).getSize() > 1){ 
			allTiles.get(discardTile.suit).get(index).addSize(-1);
			return true;			
		}
		allTiles.get(discardTile.suit).remove(index);
		return true;
	}

	public boolean replace(Tile newTile, Tile oldTile){ //If this old tile is not in hand return false (An error)
		
		int oldIndex = allTiles.get(oldTile.suit).indexOf(oldTile);
		if(oldIndex < 0) return false;
		if(allTiles.get(oldTile.suit).get(oldIndex).getSize() > 1) allTiles.get(oldTile.suit).get(oldIndex).addSize(-1);			
		else allTiles.get(oldTile.suit).remove(oldIndex);

		int newIndex = allTiles.get(newTile.suit).indexOf(newTile);

		if(newIndex >= 0) allTiles.get(newTile.suit).get(newIndex).addSize(1);
		else allTiles.get(newTile.suit).add(newTile);

		return true;
	}

	public boolean pongable(Tile newTile){
		for(Tile t : allTiles.get(newTile.suit)){
			if(newTile.index == t.index && t.getSize() >= 2){
				return true;			
			}		
		}	
		return false;
	}

	public int chowable(Tile newTile){
		int flag = 0;		
		if(newTile.suit == 3) return 0;
		if(newTile.value >= 2)
			 if(allTiles.get(newTile.suit).contains(new Tile(newTile.suit, newTile.value, newTile.index - 1)))
				 if(allTiles.get(newTile.suit).contains(new Tile(newTile.suit, newTile.value, newTile.index - 2)))
					flag |= 0b001;
		if(newTile.value <= 7)
			 if(allTiles.get(newTile.suit).contains(new Tile(newTile.suit, newTile.value, newTile.index + 1)))
				 if(allTiles.get(newTile.suit).contains(new Tile(newTile.suit, newTile.value, newTile.index + 2)))
					flag |= 0b100;

		if(newTile.value <= 8 && newTile.value >= 1)
			 if(allTiles.get(newTile.suit).contains(new Tile(newTile.suit, newTile.value, newTile.index - 1)))
				 if(allTiles.get(newTile.suit).contains(new Tile(newTile.suit, newTile.value, newTile.index + 1)))
					flag |= 0b010;
				
		return flag;
	}

	public boolean kongable(Tile newTile){
		for(Tile t : allTiles.get(newTile.suit)){
			if(newTile.index == t.index && t.getSize() == 3){
				return true;			
			}		
		}	
		return false;
	}
	
	public boolean huable(Tile newTile){
		for(Tile t : ting){
			if(newTile.index == t.index) return true;		
		}	
		return false;
	}	

	//public Tile[] tingable(Tile newTile){
	//}

	public String toString(){
		String s = "";
		for(ArrayList<Tile> temp:allTiles){
			for(Tile t:temp){
				for(int i = 0;i < t.getSize();i++)
				s += (t.toString() + " ");	
			}		
		}	
		return s;
	}	
}
