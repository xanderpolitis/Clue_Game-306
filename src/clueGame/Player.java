package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	public Card disproveSuggestion(Solution suggestion) {
		List<Card> matchingCards = new ArrayList<>();
		for (Card card : hand) {
			if (card.getCardName().equals(suggestion.getPerson()) || card.getCardName().equals(suggestion.getWeapon()) || card.getCardName().equals(suggestion.getRoom())) {
				matchingCards.add(card);
			}
		}
		if (matchingCards.isEmpty()) {
			return null;
		} else {
			Random rand = new Random();
			return matchingCards.get(rand.nextInt(matchingCards.size()));
		}
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


