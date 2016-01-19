
import java.util.ArrayList;
class comGUI
{
	public static final int tableIndex = 0;
	public static final int rightPlayerIndex = 1;
	public static final int leftPlayerIndex = 2;
	public static final int upPlayerIndex = 3;
	public static final int myPlayerOpenIndex = 4;
	public static final int myPlayerHandIndex = 5;
	
	
	private int numRightPlayer, numUpPlayer, numLeftPlayer;
	private int numRightPlayerExposedKong, numUpPlayerExposedKong, numLeftPlayerExposedKong;
	
	private ArrayList<Tile> rightPlayerOpen;
	private ArrayList<Tile> upPlayerOpen;
	private ArrayList<Tile> leftPlayerOpen;
	private ArrayList<Tile> myPlayerOpen;
	private ArrayList<Tile> table;
	private ArrayList<Tile> myPlayerHand;
	
	public FrameTest frame;
	
	public comGUI.PlayerGUI player;
	
	public comGUI()
	{
		numLeftPlayer = 0;
		numRightPlayer = 0;
		numUpPlayer = 0;
		
		leftPlayerOpen = new ArrayList<Tile>();
		upPlayerOpen = new ArrayList<Tile>();
		rightPlayerOpen = new ArrayList<Tile>();
		myPlayerOpen = new ArrayList<Tile>();
		myPlayerHand = new ArrayList<Tile>();
		table = new ArrayList<Tile>();
		
		frame = new FrameTest();
		//player = new comGUI.PlayerGUI("A");
	}
	public void initPlayerGUI(String name, int score)
	{
		player = new comGUI.PlayerGUI(name, score);
	}
	
	public void renewGUI()
	{
		ArrayList<ArrayList<Tile>> temp = new ArrayList<ArrayList<Tile>>();
		temp.add(table);
		temp.add(rightPlayerOpen);
		temp.add(leftPlayerOpen);
		temp.add(upPlayerOpen);
		temp.add(myPlayerOpen);
		temp.add(myPlayerHand);
		
		int[] tempNum = new int[3];
		tempNum[0] = numRightPlayer;
		tempNum[1] = numLeftPlayer;
		tempNum[2] = numUpPlayer;
		
		frame.setAllContent(temp, tempNum);
	}
	
	
	public void assignTile(ArrayList<ArrayList<Tile>> allTile)
	{
		table = allTile.get(tableIndex);
		rightPlayerOpen = allTile.get(rightPlayerIndex);
		leftPlayerOpen = allTile.get(leftPlayerIndex);
		upPlayerOpen = allTile.get(upPlayerIndex);
		myPlayerOpen = allTile.get(myPlayerOpenIndex);
		//myPlayerHand = allTile.get(myPlayerHandIndex);
		myPlayerHand = player.myhand;
	}
	public void assignTile(int which, ArrayList<Tile> allTile)
	{
		//getTileList(which) = allTile;
		if(which == tableIndex)
			table = allTile;
		else if(which == rightPlayerIndex)
			rightPlayerOpen = allTile;
		else if(which == leftPlayerIndex)
			leftPlayerOpen = allTile;
		else if(which == upPlayerIndex)
			upPlayerOpen = allTile;
		else if(which == myPlayerOpenIndex)
			myPlayerOpen = allTile;
		//else
			//myPlayerHand = allTile;
	}
	public void assignHandNum(int which, int num)
	{
		if(which == rightPlayerIndex)
			numRightPlayer = num;
		else if(which == leftPlayerIndex)
			numLeftPlayer = num;
		else
			numUpPlayer = num;
	}
	public void assignExposedKongNum(int which, int num)
	{
		if(which == rightPlayerIndex)
			numRightPlayerExposedKong = num;
		else if(which == leftPlayerIndex)
			numLeftPlayerExposedKong = num;
		else
			numUpPlayerExposedKong = num;
	}
	
	public void showGUI()
	{
		frame.start();
	}
	
	
	/*private ArrayList<Tile> getTileList(int which)
	{
		if(which == tableIndex)
			return table;
		else if(which == rightPlayerIndex)
			return rightPlayerOpen;
		else if(which == leftPlayerIndex)
			return leftPlayerOpen;
		else if(which == upPlayerIndex)
			return upPlayerOpen;
		else if(which == myPlayerOpenIndex)
			return myPlayerOpen;
		else
			return myPlayerHand;
	}*/
	
	
	public class PlayerGUI extends Player
	{
		private ArrayList<Tile> myhand;
		private Hand hand;
		private Tile newTile;
		private ArrayList<Tile> discardTile;
		private boolean[] choice;
		
		public PlayerGUI(String name, int score)
		{
			super(name, score);
		}
		
		/*public PlayerGUI(String name)
		{
			super(name);
		}*/
		
		public void failed()
		{
			frame.actionFail();
		}
		public void initTiles(ArrayList<Tile> t)
		{
			myhand = new ArrayList<Tile>();
			hand = new Hand();
			
			for(Tile temp : t){
				myhand.add(temp.same());
				hand.add(temp.same());
			}
		}
		

		public Action doSomething(int from, Tile tile)
		{
			if(from == 0){
				myhand.add(tile);
				hand.add(tile);
			}
			else{
				newTile = tile.same();
				doSelect(newTile, from);
			}
			return new Action(0, new ArrayList<Tile>());
		}
		
		
		
		private void doSelect(Tile newTile, int from)
		{
			//boolean[] b = {true, true, true, true, true};
			//boolean[] b = {false, false, false, false, false};
			
			boolean[] b = {true, true, false, true, false};
			int tempType = hand.chowable(newTile);
			ArrayList<Tile> temp = hand.tingable(newTile);
			
			/*if(tempType != 0 && from == 3){
				b[0] = true;
				frame.setChowOption(tempType, getChewChoice(tempType, newTile));
			}
			
			if(from != 0)
				b[1] = hand.pongable(newTile);
			
			b[2] = hand.kongable(newTile);
			
			if(temp == null)
				b[4] = true;
			else if(temp.size() != 0)
				b[3] = true;*/
			frame.setThrower(2, new Tile(13));
			b[0] = true;
			tempType = 7;
			frame.setChowOption(tempType, getChewChoice(tempType, new Tile(13)));
			frame.setSelect(b);
			waitOK();
		}
		private void waitOK()
		{
			while(frame.ok == false){}
			discardTile = new ArrayList<Tile>();
			for(Tile t : frame.push)
				discardTile.add(t);
			choice = frame.getChoice();
			for(int i = 0; i < 6; i++)
				if(choice[i])
					System.out.println(i);
			for(Tile t : frame.push)
				System.out.println(t);
			frame.ok = false;
		}
		private boolean remove(Tile t)
		{
			boolean b = false;
			for(Tile temp : myhand)
				if(temp.equals(t)){
					myhand.remove(temp);
					b = true;
				}
			return b;
		}
		private ArrayList<ArrayList<Tile>> getChewChoice(int flag, Tile newTile)
		{
			ArrayList<ArrayList<Tile>> temp = new ArrayList<ArrayList<Tile>>();
			if((flag & 0b001) > 0){
				ArrayList<Tile> temp1 = new ArrayList<Tile>();
				temp1.add(newTile.same(-2));
				temp1.add(newTile.same(-1));
				temp1.add(newTile.same());
				temp.add(temp1);
			}
			if((flag & 0b010) > 0){
				ArrayList<Tile> temp1 = new ArrayList<Tile>();
				temp1.add(newTile.same(-1));
				temp1.add(newTile.same());
				temp1.add(newTile.same(1));
				temp.add(temp1);
			}
			if((flag & 0b100) > 0){
				ArrayList<Tile> temp1 = new ArrayList<Tile>();
				temp1.add(newTile.same());
				temp1.add(newTile.same(1));
				temp1.add(newTile.same(2));
				temp.add(temp1);
			}
			return temp;
		}
	}
	
	
	
	
	
			/*public boolean doDraw(Tile tile, ArrayList<ArrayList<Tile>> currentTable)
		{
			newTile = tile.same();
			doSelect(newTile);
			return true;
		}*/
		/*public boolean doChow(Tile tile, ArrayList<ArrayList<Tile>> currentTable){return choice[0];}
		public boolean doPong(Tile tile, ArrayList<ArrayList<Tile>> currentTable){return choice[1];}
		public boolean doKong(Tile tile, ArrayList<ArrayList<Tile>> currentTable){return choice[2];}
		public boolean doReach(Tile tile, ArrayList<ArrayList<Tile>> currentTable){return choice[3];}
		public boolean doHu(Tile tile, ArrayList<ArrayList<Tile>> currentTable){return choice[4];}*/
		
		//public Tile replace(Tile tile, ArrayList<ArrayList<Tile>> currentTable){return new Tile(0);}
		//public Tile kong(ArrayList<ArrayList<Tile>> currentTable){return new Tile(0);}
}