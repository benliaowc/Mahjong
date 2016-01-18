import java.lang.*;
import java.util.*;

enum Status {
	FREE, DRAW, CHOW, PONG, KONG, REACH
}

public class AI extends Player{

	private Hand hand ;
	private int exposed ;
	private Status status ;

	public AI(){
		super("AI") ;
		exposed = 0 ;
		status = Status.FREE ;
	}

	//the 13 tiles at the beginning
	public void initTiles(ArrayList<Tile> t){
		hand = new Hand() ;
		for( int i = 0 ; i < 13 ; i++ ){
			hand.add(t.get(i)) ;
		}
	}

	//ask the player whether to draw/chow/pong/kong/reach/hu or not
	public boolean doDraw(Tile tile, ArrayList<ArrayList<Tile>> currentTable){
		status = Status.DRAW ;
		return true ;
	}

	public boolean doChow(Tile tile, ArrayList<ArrayList<Tile>> currentTable){
		if( hand.chowable(tile) == 0 )
			return false ;
		if( exposed == 0 )
			return false ;

		Hand tmp = new Hand(hand.getAll()) ;
		tmp.add(tile) ;
		Collections.sort(tmp.getAll().get(tile.suit));

		/* remove all shuns in the hand */
		int i = 0 ;
		int s = tmp.getAll().get(tile.suit).size() ;
		while(i < s - 2){
			Tile a = tmp.getAll().get(tile.suit).get(i);
			Tile b = tmp.getAll().get(tile.suit).get(i+1);
			Tile c = tmp.getAll().get(tile.suit).get(i+2);
			if(a.index + 1 == b.index && b.index + 1 == c.index){
				hand.discard(a) ;
				hand.discard(b) ;
				hand.discard(c) ;
				s = tmp.getAll().get(tile.suit).size() ;
				continue ;
			}
			else {
				i++ ;
				s = tmp.getAll().get(tile.suit).size() ;
			}
		}

		/* check if the tile you want to chow is left or not */
		s = tmp.getAll().get(tile.suit).size() ;
		for( int j = 0 ; j < s ; j++ ){
			if( tmp.getAll().get(tile.suit).get(j).equals(tile) )
				return false ;
		}
		exposed++ ;
		status = Status.CHOW ;
		return true ;
	}

	public boolean doPong(Tile tile, ArrayList<ArrayList<Tile>> currentTable){
		if( !hand.pongable(tile) )
			return false ;
		else if( tile.suit == 3 && tile.value > 3 ){
			status = Status.PONG ;
			return true ;
		}
		else if( exposed == 0 )
			return false ;

		Hand tmp = new Hand(hand.getAll()) ;
		tmp.add(tile) ;
		Collections.sort(tmp.getAll().get(tile.suit));

		/* remove all shuns in the hand */
		int i = 0 ;
		int s = tmp.getAll().get(tile.suit).size() ;
		while(i < s - 2){
			Tile a = tmp.getAll().get(tile.suit).get(i);
			Tile b = tmp.getAll().get(tile.suit).get(i+1);
			Tile c = tmp.getAll().get(tile.suit).get(i+2);
			if(a.index + 1 == b.index && b.index + 1 == c.index){
				hand.discard(a) ;
				hand.discard(b) ;
				hand.discard(c) ;
				s = tmp.getAll().get(tile.suit).size() ;
				continue ;
			}
			else {
				i++ ;
				s = tmp.getAll().get(tile.suit).size() ;
			}
		}

		/* check if the tile you want to pong is left and size >= 3 or not */
		s = tmp.getAll().get(tile.suit).size() ;
		for( int j = 0 ; j < s ; j++ ){
			if( tmp.getAll().get(tile.suit).get(j).equals(tile) && tmp.getAll().get(tile.suit).get(j).getSize() >= 3 ){
				exposed++ ;
				status = Status.PONG ;
				return true ;
			}
		}
		return false ;
	}

	public boolean doKong(Tile tile, ArrayList<ArrayList<Tile>> currentTable){
		return false ;
	}

	public boolean doReach(Tile tile, ArrayList<ArrayList<Tile>> currentTable){
		if( exposed == 0 && hand.tingable(tile) != null ){
			status = Status.REACH ;
			return true ;
		}
		else
			return false ;
	}

	public boolean doHu(Tile tile, ArrayList<ArrayList<Tile>> currentTable){
		//		if( hand.huable(tile) )
		return true ;
	}

	//if chow/pong failed, use this method to notify player
	public void failed(){
		if( exposed > 0 )
			exposed-- ;
	}

	//else give the player new tile, and ask the player which tile to discard. return null if the player wants to Kong
	public Tile replace(Tile tile, ArrayList<ArrayList<Tile>> currentTable){
		if( status == Status.REACH ){
			ArrayList<Tile> discardTile = hand.tingable(tile) ;
			status = Status.FREE ;
			return discardTile.get(0) ;
		}
		else if( status == Status.KONG ){
			status = Status.FREE ;
			return null ;
		}

		/* have not done */
		if( status == Status.PONG ){
			status = Status.FREE ;
			return null ;
		}
		else if( status == Status.CHOW ){
			status = Status.FREE ;
			return null ;
		} 
		else if( status == Status.DRAW ){
			status = Status.FREE ;
			return null ;
		}

		return null ; /* should not happen */
	}

	//ask the player which to kong, remember to call replace() after kong
	public Tile kong(ArrayList<ArrayList<Tile>> currentTable){
		return null ; 
	}

}
