import java.lang.*;
import java.util.*;

public abstract class Player{
	private String name;
	public Player(String s){
		name = s;
	}

	@Override
	public String toString(){
		return "Hi, I am Player "+name+".";
	}

	//the 13 tiles at the beginning
	public abstract void initTiles(ArrayList<Tile> t);

	//ask the player whether to draw/chow/pong/kong/hu or not
	public abstract boolean doDraw(Tile tile, ArrayList<ArrayList<Tile>> currentTable);
	public abstract boolean doChow(Tile tile, ArrayList<ArrayList<Tile>> currentTable);
	public abstract boolean doPong(Tile tile, ArrayList<ArrayList<Tile>> currentTable);
	public abstract boolean doKong(Tile tile, ArrayList<ArrayList<Tile>> currentTable);
	public abstract boolean doHu(Tile tile, ArrayList<ArrayList<Tile>> currentTable);

	//if chow/pong failed, use this method to notify player
	public abstract void failed();

	//else give the player new tile, and ask the player which tile to discard. return null if the player wants to Kong
	public abstract Tile replace(Tile tile, ArrayList<ArrayList<Tile>> currentTable);

	//ask the player which to kong, remember to call replace() after kong
	public abstract Tile kong(ArrayList<ArrayList<Tile>> currentTable);

}
