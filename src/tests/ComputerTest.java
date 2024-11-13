package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.BoardCell;
import clueGame.Card;
import clueGame.ComputerPlayer;
import clueGame.Room;
import clueGame.Solution;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

public class ComputerTest {
	private ComputerPlayer computerPlayer;

    @BeforeEach
    public void setup() {
        computerPlayer = new ComputerPlayer();
    }

    // 1. Testing `createSuggestion` in ComputerPlayer

    @Test
    public void testRoomMatchesLocation() {
        Room room = new Room("Kitchen");
        computerPlayer.setCurrentRoom(room);
        Solution suggestion = computerPlayer.createSuggestion();

        assertEquals("Kitchen", suggestion.room);
    }

    @Test
    public void testOnlyOneUnseenWeaponOrPerson() {
        computerPlayer.addSeenCard(new Card("Revolver", Card.CardType.WEAPON));
        computerPlayer.addSeenCard(new Card("Rope", Card.CardType.WEAPON));
        computerPlayer.addSeenCard(new Card("Miss Scarlet", Card.CardType.PERSON));
        
        Room room = new Room("Library");
        computerPlayer.setCurrentRoom(room);
        
        Solution suggestion = computerPlayer.createSuggestion();

        assertEquals("Library", suggestion.room);
        assertEquals("Knife", suggestion.weapon);  // Assuming unseen weapon is Knife
    }
}
