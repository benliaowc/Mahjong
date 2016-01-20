import java.lang.*;
import java.util.*;

public class PlayerText extends Player{
	public PlayerText(String s, int i){
		super(s, i);
	}
	public Action doSomething(int from, Tile tile){
		return new Action(0, new ArrayList<Tile>());
	}
	public void failed(){

	}

}
