import java.lang.*;
import java.util.*;

public class test {
	
	private static Shuffler test = new Shuffler();
	private static Hand H = new Hand();
	private static ArrayList<Tile> playerHand = new ArrayList<Tile>();

	public static void main(String args[]){
		Tile a = new Tile(0, 3, 3);
		for(int i = 0;i < 13;i++){
			Tile t = test.getNext();			
			H.add(t);
			Tile anotherT = new Tile(t.suit,t.value,t.index);
			playerHand.add(anotherT);
		}
		System.out.println(H);

		H.replace(new Tile(0, 8, 8), playerHand.get(3));

		playerHand.remove(3);
		playerHand.add(3, new Tile(0, 8, 8));

		System.out.println(H);

		H.discard(playerHand.get(3));
		playerHand.remove(3);
		
		System.out.println(H);

		
		

	}
	
}
