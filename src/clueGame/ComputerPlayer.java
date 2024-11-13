package clueGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {
 private Room currentRoom;

 public void setCurrentRoom(Room room) {
     this.currentRoom = room;
 }

 public Solution createSuggestion() {
     Card roomCard = new Card(currentRoom.getName(), Card.CardType.ROOM);
     Card personCard = getRandomUnseenCard(Card.CardType.PERSON);
     Card weaponCard = getRandomUnseenCard(Card.CardType.WEAPON);

     return new Solution(personCard.getName(), weaponCard.getName(), roomCard.getName());
 }

 private Card getRandomUnseenCard(Card.CardType type) {
     List<Card> unseenOfType = new ArrayList<>();
     for (Card card : seenCards) {
         if (card.getType() == type) {
             unseenOfType.add(card);
         }
     }
     Random rand = new Random();
     return unseenOfType.get(rand.nextInt(unseenOfType.size()));
 }

 public BoardCell selectTarget(List<BoardCell> targets) {
     for (BoardCell cell : targets) {
         if (cell.isRoom() && !seenCards.contains(new Card(cell.getRoom().getName(), Card.CardType.ROOM))) {
             return cell;
         }
     }
     return targets.get(new Random().nextInt(targets.size()));
 }

 @Override
 public void makeMove() {
     // Movement decision logic, simplified here
 }
}

