package clueGame;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
	private int secretPassagesTaken = 0;
	
	public Room currentRoom = null;
	
	private Card personCard = null;
	private Card roomCard = null;
	private Card weaponCard = null;
	
	public static Card returnCard = null;

	public ComputerPlayer(String name, Color color, int row, int col) {
		super(name, color, row, col);
	}

	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}
	@Override
	public Solution createSuggestion(Room room) {
		if(personCard != null && roomCard != null && weaponCard != null) {
			makeAccusation();
		}
		
		Card roomCard = new Card(room.getName(), Card.CardType.ROOM);
		Card personCard = getRandomUnseenCard(Card.CardType.PERSON);
		Card weaponCard = getRandomUnseenCard(Card.CardType.WEAPON);

		return new Solution(personCard.getName(), weaponCard.getName(), roomCard.getName());
	}

	private Card getRandomUnseenCard(Card.CardType type) {
		ArrayList<Card> unseenOfType = new ArrayList<>();
		for (Card card : Board.getInstance().cards) {
			if (card.getType() == type && !seenCards.contains(card)) {
				unseenOfType.add(card);
			}
		}
		Collections.shuffle(unseenOfType);
		returnCard = unseenOfType.getFirst();
		return unseenOfType.getFirst();
	}

	public BoardCell selectTarget(Set<BoardCell> targets) {
		String roomName = null;
		for (BoardCell cell : targets) {
			if(cell.isRoom()) {
				roomName = cell.getRoom().getName();
			}
			Card tempCard = null;
			for(Card card:Board.getInstance().cards) {
				if(card.getName() == roomName) {
					tempCard = card;
					break;
				}
			}
			if (cell.isRoom() && !seenCards.contains(tempCard)) {
				if(cell.isSecretPassage()) {
					secretPassagesTaken++;
				}
				if(cell.isSecretPassage() && secretPassagesTaken > 1) {
					continue;
				}
				return cell;
			}
		}
		int size = targets.size();
		if(size == 0) {
			return Board.getInstance().grid[Board.getInstance().players.get(Board.getInstance().currPlayer).row][Board.getInstance().players.get(Board.getInstance().currPlayer).col];
		}
		int item = new Random().nextInt(size);
		int i = 0;

		for(BoardCell cell : targets)
		{
			if (i == item) {
				if(cell.isRoom()) {
					currentRoom = cell.getRoom();
				}
				return cell; 
			}
			i++;
		}
		return null;
	}


	public void setPerson(Card p) {
		personCard = p;
	}
	
	public void setRoom(Card r) {
		personCard = r;
	}
	
	public void setWeapon(Card w) {
		personCard = w;
	}

	@Override
	public void makeMove() {
		// Movement decision logic, simplified here
	}

	@Override
	public Solution makeAccusation() {
		// TODO Auto-generated method stub
		return new Solution(personCard.getName(), weaponCard.getName(), roomCard.getName());
	}
}

