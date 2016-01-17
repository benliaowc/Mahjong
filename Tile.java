import java.lang.*;
import java.util.*;

public class Tile implements Comparable<Tile>{
	
	private String[] suit_dictionary = {"�U", "��", "��"};
	private String[] value_dictionary = {"�@", "�G", "�T", "�|", "��", "��", "�C", "�K", "�E"};
	private String[] word_dictionary = {"�F", "�n", "��", "�_", "��", "�o", "��"};
	
	public final int suit;
	public final int value;
	public final int index;	
	private int size;

	public Tile(int i){
		suit = i/9;
		value = i%9;
		index = i;
		size = 1;
	}

	public Tile(int s, int v, int i){
		suit = s;
		value = v;
		index = i;
		size = 1;
	}

	public int getSize(){
		return size;
	}

	public void addSize(int s){
		size += s;
	}

	public void setSize(int s){
		size = s;
	}
	
	public String toString(){
		if(suit == 3)
			return word_dictionary[value];
		else
			return value_dictionary[value] + suit_dictionary[suit];
	}

	@Override
	public boolean equals(Object that) {
		if(that instanceof Tile) {
			return this.index == ((Tile)that).index;
		}
		return false;	
	}
	
	@Override
	public int compareTo(Tile that) {
		if(this.index > that.index) return 1;
		if(this.index == that.index) return 0;
		return -1;
	}

	public Tile same(){
		Tile t = new Tile(suit, value, index);
		t.setSize(size);
		return t;
	}
	public Tile same(int i){
		if(suit != 3 && (value + i < 0 || value + i > 8)){
			return null;		
		}
		if(suit == 3 && (value + i < 0 || value + i > 6)){
			return null;		
		}
		Tile t = new Tile(suit, value + i, index + i);
		t.setSize(size);
		return t;
	}
}
