package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ComputerPlayer extends Player {
	private List<Card> unseenCards;
	private Room currentRoom;
	
	public ComputerPlayer(String name, Color color, int row, int column) {
		super(name, color, row, column);
	}
	
	public ComputerPlayer(String name) {
		super(name);
		this.unseenCards = new ArrayList<>();
	}
	
	public void addUnseedCard(Card card) {
		unseenCards.add(card);
	}
	
	public Solution createSuggestion(Room room) {
		Card roomCard = new Card(room.getName(), Card.CardType.ROOM);
		Card personCard = getRandomUnseenCard(Card.CardType.PERSON);
		Card weaponCard = getRandomUnseenCard(Card.CardType.WEAPON);
	}
}
