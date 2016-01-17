import java.lang.*;
import java.util.*;

public class test {
	
	private static Shuffler test = new Shuffler();
	private static Hand H = new Hand();
	private static ArrayList<Tile> playerHand = new ArrayList<Tile>();

	public static void main(String args[]){
		Tile t;
		Tile anotherT;

		//Tile a = new Tile(0, 3, 3);
		
		/*for(int i = 0;i < 10;i++){
			t = new Tile(i/9,i%9,i);			
			H.add(t);
			anotherT = new Tile(t.suit,t.value,t.index);
			playerHand.add(anotherT);
		}*/
		H.add(new Tile(0,0,0));
		H.add(new Tile(0,1,1));
		H.add(new Tile(0,2,2));
		H.add(new Tile(0,3,3));
		H.add(new Tile(0,5,5));
		H.add(new Tile(0,6,6));
		H.add(new Tile(0,8,8));
		H.add(new Tile(0,7,7));
		H.add(new Tile(0,4,4));
		H.add(new Tile(1,0,9));
		H.add(new Tile(1,1,10));
		H.add(new Tile(1,0,9));
		H.add(new Tile(1,2,11));
		//H.sort();
		/*for(int i = 0;i < 13;i++){
			t = test.getNext();			
			H.add(t);
			anotherT = new Tile(t.suit,t.value,t.index);
			playerHand.add(anotherT);
		}*/

			
		System.out.println(H);
		
	//	H.sort();
		ArrayList<Tile> judge = H.tingable(new Tile(0,8,8));
		if(judge == null) System.out.println("HU!");
		else if(judge.isEmpty()) System.out.println("no Ting!");
		else System.out.println(judge);
		
		H.replace(new Tile(0,8,8), new Tile(1,0,9));

		System.out.println(H);

		judge = H.tingable(new Tile(0,5,5));
		if(judge == null) System.out.println("HU!");
		else if(judge.isEmpty()) System.out.println("no Ting!");
		else System.out.println(judge);

		System.out.println(H);

		//System.out.println(H);



		/*H.replace(new Tile(0, 8, 8), playerHand.get(3));

		playerHand.remove(3);
		playerHand.add(3, new Tile(0, 8, 8));

		System.out.println(H);

		H.discard(playerHand.get(3));
		playerHand.remove(3);
		
		System.out.println(H);*/
		

		
		

	}
	
}


