package clueGame;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
	public Room currentRoom = null;

 	public ComputerPlayer(String name, Color color, int row, int col) {
		super(name, color, row, col);
	}
 
 public void setCurrentRoom(Room room) {
     this.currentRoom = room;
 }
 @Override
 public Solution createSuggestion(Room room) {
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
     return unseenOfType.getFirst();
 }

 public BoardCell selectTarget(Set<BoardCell> targets) {
     for (BoardCell cell : targets) {
         if (cell.isRoom() && !seenCards.contains(new Card(cell.getRoom().getName(), Card.CardType.ROOM))) {
             return cell;
         }
     }
     int size = targets.size();
     int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
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

 @Override
 public void makeMove() {
     // Movement decision logic, simplified here
 }
}

