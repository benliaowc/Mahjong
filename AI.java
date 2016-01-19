import java.lang.*;
import java.util.*;

public class AI extends Player{

	private final int DONOTHING = 0 ;
       	private final int DRAW = 1 ;
       	private final int CHOW = 2 ;
	private final int PONG = 3 ;
	private final int KONG = 4 ;
	private final int ADD_KONG = 5 ;
	private final int CONCEAL_KONG = 6 ;
	private final int RICHI = 7 ;
	private final int RON = 8 ;
	private final int HU = 9 ;
	private int exposed ;

	public AI(int score){
		super("AI", score ) ;
		exposed = 0 ;
	}

	//ask the player whether to draw/chow/pong/kong/reach/hu or not
	private boolean doChow(Tile tile){
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
		return true ;
	}

	private boolean doPong(Tile tile){
		if( !hand.pongable(tile) )
			return false ;
		else if( tile.suit == 3 && tile.value > 3 )
			return true ;
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
				return true ;
			}
		}
		return false ;
	}

	private boolean doRichi(Tile tile){
		if( exposed == 0 ){
			ArrayList<Tile> tingTile = hand.tingable(tile) ;
			if( tingTile != null && tingTile.size() > 0 )
				return true ;
		}
		return false ;
	}

	private boolean huable(Tile tile){
		return true ;
	}

	private Tile decideDiscard(Hand _hand){

		Hand tmp = new Hand(_hand.getAll()) ;

		/* initialize discard tile */
		Tile res = null ;
		for( int suit = 3 ; suit >= 0 ; suit-- ){
			if( tmp.getAll().get(suit).size() > 0 ){
				res = tmp.getAll().get(suit).get(0) ;
				break ;
			}
		}

		/* remove all shuns in the hand */
		for( int suit = 0 ; suit <= 2 ; suit++ ){
			Collections.sort(tmp.getAll().get(suit));

			int i = 0 ;
			int s = tmp.getAll().get(suit).size() ;
			while(i < s - 2){
				Tile a = tmp.getAll().get(suit).get(i);
				Tile b = tmp.getAll().get(suit).get(i+1);
				Tile c = tmp.getAll().get(suit).get(i+2);
				if(a.index + 1 == b.index && b.index + 1 == c.index){
					tmp.discard(a) ;
					tmp.discard(b) ;
					tmp.discard(c) ;
					s = tmp.getAll().get(suit).size() ;
					continue ;
				}
				else {
					i++ ;
					s = tmp.getAll().get(suit).size() ;
				}
			}
		}

		for( int suit = 3 ; suit >= 0 ; suit-- ){
			if( tmp.getAll().get(suit).size() > 0 ){
				res = tmp.getAll().get(suit).get(0) ;
				break ;
			}
		}

		/* remove all triplets in the hand */
		for( int suit = 0 ; suit <= 3 ; suit++ ){
			Collections.sort(tmp.getAll().get(suit));

			int i = 0 ;
			int s = tmp.getAll().get(suit).size() ;
			while(i < s){
				Tile a = tmp.getAll().get(suit).get(i) ;
				if( a.getSize() >= 3 ){
					tmp.discard(a) ;
					tmp.discard(a) ;
					tmp.discard(a) ;
					s = tmp.getAll().get(suit).size() ;
					continue ;
				}
				else {
					i++ ;
					s = tmp.getAll().get(suit).size() ;
				}
			}
		}

		for( int suit = 3 ; suit >= 0 ; suit-- ){
			if( tmp.getAll().get(suit).size() > 0 ){
				res = tmp.getAll().get(suit).get(0) ;
				break ;
			}
		}

		/* remove all pairs in the hand */
		for( int suit = 0 ; suit <= 3 ; suit++ ){
			Collections.sort(tmp.getAll().get(suit));

			int i = 0 ;
			int s = tmp.getAll().get(suit).size() ;
			while(i < s){
				Tile a = tmp.getAll().get(suit).get(i) ;
				if( a.getSize() >= 2 ){
					tmp.discard(a) ;
					tmp.discard(a) ;
					s = tmp.getAll().get(suit).size() ;
					continue ;
				}
				else {
					i++ ;
					s = tmp.getAll().get(suit).size() ;
				}
			}
		}

		for( int suit = 3 ; suit >= 0 ; suit-- ){
			if( tmp.getAll().get(suit).size() > 0 ){
				res = tmp.getAll().get(suit).get(0) ;
				break ;
			}
		}

		return res ;
	}

	public Action doSomething(int from, Tile tile){ //from 0 draw 1 next 2 opposite 3 previous
		if(from == 0){ //draw, richi, add kong, private kong, hu
			if( hand.tingable(tile) != null ) /* huable */
				return new Action(HU, new ArrayList<Tile>()) ;
			else if( doRichi(tile) ){
				ArrayList<Tile> tingTile = hand.tingable(tile) ;
				ArrayList<Tile> discard = new ArrayList<Tile>() ;
				discard.add( tingTile.get(0) ) ;
				return new Action(RICHI, discard) ; 
			}
			else {
				hand.add(tile) ;
				ArrayList<Tile> discard = new ArrayList<Tile>() ;
				discard.add( decideDiscard(hand) ) ;
				return new Action(DRAW, discard) ;
			}
		}
		else if(from == 3){//chow, pong, kong, ron
			if( hand.tingable(tile) != null ) /* huable */
				return new Action(HU, new ArrayList<Tile>()) ;
			else if( doChow(tile) ){
				Hand tmp = new Hand(hand.getAll()) ;
				int flag = tmp.chowable(tile) ;
				if( (flag & 0b001) > 0 ){
					tmp.discard(new Tile(tile.suit, tile.value, tile.index-2)) ;
					tmp.discard(new Tile(tile.suit, tile.value, tile.index-1)) ;
					tmp.discard(tile) ;
					ArrayList<Tile> discard = new ArrayList<Tile>() ;
					discard.add( decideDiscard(tmp) ) ;
					discard.add(new Tile(tile.suit, tile.value, tile.index-2)) ;
					discard.add(new Tile(tile.suit, tile.value, tile.index-1)) ;
					discard.add(tile) ;
					return new Action(CHOW, discard) ;
				}
				else if( (flag & 0b010) > 0 ){
					tmp.discard(new Tile(tile.suit, tile.value, tile.index-1)) ;
					tmp.discard(tile) ;
					tmp.discard(new Tile(tile.suit, tile.value, tile.index+1)) ;
					ArrayList<Tile> discard = new ArrayList<Tile>() ;
					discard.add( decideDiscard(tmp) ) ;
					discard.add(new Tile(tile.suit, tile.value, tile.index-1)) ;
					discard.add(tile) ;
					discard.add(new Tile(tile.suit, tile.value, tile.index+1)) ;
					return new Action(CHOW, discard) ;
				}
				else {
					tmp.discard(tile) ;
					tmp.discard(new Tile(tile.suit, tile.value, tile.index+1)) ;
					tmp.discard(new Tile(tile.suit, tile.value, tile.index+2)) ;
					ArrayList<Tile> discard = new ArrayList<Tile>() ;
					discard.add( decideDiscard(tmp) ) ;
					discard.add(tile) ;
					discard.add(new Tile(tile.suit, tile.value, tile.index+1)) ;
					discard.add(new Tile(tile.suit, tile.value, tile.index+2)) ;
					return new Action(CHOW, discard) ;
				}
			}
			else if( doPong(tile) ){
				Hand tmp = new Hand(hand.getAll()) ;
				tmp.discard(tile) ;
				tmp.discard(tile) ;
				tmp.discard(tile) ;
				ArrayList<Tile> discard = new ArrayList<Tile>() ;
				discard.add( decideDiscard(tmp) ) ;
				discard.add(tile) ;
				discard.add(tile) ;
				discard.add(tile) ;
				return new Action(PONG, discard) ;
			}
			else
				return new Action(DONOTHING, new ArrayList<Tile>()) ;
		}
		else{// pong, kong, ron
			if( hand.tingable(tile) != null ) /* huable */
				return new Action(HU, new ArrayList<Tile>()) ;
			else if( doPong(tile) ){
				Hand tmp = new Hand(hand.getAll()) ;
				tmp.discard(tile) ;
				tmp.discard(tile) ;
				tmp.discard(tile) ;
				ArrayList<Tile> discard = new ArrayList<Tile>() ;
				discard.add( decideDiscard(tmp) ) ;
				discard.add(tile) ;
				discard.add(tile) ;
				discard.add(tile) ;
				return new Action(PONG, discard) ;
			}
			else
				return new Action(DONOTHING, new ArrayList<Tile>()) ;
		}
	}

	//if chow/pong failed, use this method to notify player
	public void failed(){
		if( exposed > 0 )
			exposed-- ;
	}
}
