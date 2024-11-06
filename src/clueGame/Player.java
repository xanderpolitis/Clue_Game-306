package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	private Color color;
	private int row, column;
	private List<Card> hand;
	
	public Player(String name, Color color, int row, int column) {
		this.name = name;
		this.color = color;
		this.row = row;
		this.column = column;
		this.hand = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void updateHand(Card card) {
		hand.add(card);
	}
	
	public List<Card> getHand(){
		return hand;
	}
}


