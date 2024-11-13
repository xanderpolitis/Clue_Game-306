package clueGame;

import java.awt.Color;

public class Card {

 public enum CardType { PERSON, WEAPON, ROOM }

 private String name;
 private CardType type;
 private Color color;

 public Card(String name, CardType type) {
     this.name = name;
     this.type = type;
 }

 public String getName() {
     return name;
 }

 public CardType getType() {
     return type;
 }

 @Override
	public boolean equals(Object obj) {
     if (this == obj) return true;
     if (obj == null || getClass() != obj.getClass()) return false;
     Card card = (Card) obj;
     return name.equals(card.name) && type == card.type;
 }

 @Override
	public int hashCode() {
     return name.hashCode() + type.hashCode();
 }

 @Override
	public String toString() {
     return name + " (" + type + ")";
 }	
	public void setColor(Color c) {
		color = c;
	}
	
	public Color getColor() {
		return color;
	}
}


