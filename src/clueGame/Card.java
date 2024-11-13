package clueGame;

import java.awt.Color;

public class Card {
	private String cardName;
	private CardType type;
	private Color color;
	
	public Card(String cardName, CardType type) {
		this.cardName = cardName;
		this.type = type;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public CardType getType() {
		return type;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public Color getColor() {
		return color;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Card card = (Card) obj;
		return cardName.equals(card.cardName) && type == card.type;
	}
}
