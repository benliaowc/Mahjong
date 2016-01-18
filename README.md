# foop15_final32

## Player
+ `public Player(String s)`
	+ Constructor
	+ s: name of the player
+ `public String toString()`
	+ return "Hi, I am Player "+name+".";
+ `public abstract void initTiles(ArrayList<Tile> t);`
	+ tile: the 13 tiles at the beginning
+ `public abstract boolean doDraw(Tile tile, ArrayList<ArrayList<Tile>> currentTable);`
+ `public abstract boolean doChow(Tile tile, ArrayList<ArrayList<Tile>> currentTable);`
+ `public abstract boolean doPong(Tile tile, ArrayList<ArrayList<Tile>> currentTable);`
+ `public abstract boolean doKong(Tile tile, ArrayList<ArrayList<Tile>> currentTable);`
+ `public abstract boolean doReach(Tile tile, ArrayList<ArrayList<Tile>> currentTable);`
+ `public abstract boolean doHu(Tile tile, ArrayList<ArrayList<Tile>> currentTable);`
	+ ask the player whether to draw/chow/pong/kong/reach/hu or not
	+ tile: the tile the player drawed, or can be chow/pong/kong/hu
	+ currentTable: each list is other players' face-up or discarded tiles
+ `public abstract void failed();`
	+ if chow/pong failed(pong by other players?), use this method to notify player
+ `public abstract Tile replace(Tile tile, ArrayList<ArrayList<Tile>> currentTable);`
	+ draw/chow/pong success or doesn't want to hu. given the new tile and ask the player which tile to discard.
	+ tile: the new tile
	+ return the tile the player wants to discard or null if the player wants to Kong(Ang Kong or Ming Kong)
+ `public abstract Tile kong(ArrayList<ArrayList<Tile>> currentTable);`
	+ ask the player which to kong
	+ should call replace() after kong
	+ currentTable: each list is other players' face-up or discarded tiles
	+ return the tile the player wants to kong
