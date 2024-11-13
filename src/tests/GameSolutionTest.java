package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.Player;
import clueGame.Solution;

import static org.junit.jupiter.api.Assertions.*;

public class GameSolutionTest {
    private Board board;
    private Solution correctSolution;
    private Solution wrongPerson;
    private Solution wrongWeapon;
    private Solution wrongRoom;

    @BeforeEach
    public void setup() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout306.csv", "ClueSetup306.txt");		
		// Initialize will load config files 
		board.initialize();
        correctSolution = new Solution("Colonel Mustard", "Knife", "Kitchen");
        wrongPerson = new Solution("Miss Scarlet", "Knife", "Kitchen");
        wrongWeapon = new Solution("Colonel Mustard", "Revolver", "Kitchen");
        wrongRoom = new Solution("Colonel Mustard", "Knife", "Library");

        board.setSolution(correctSolution);  // Assuming `setSolution` method sets theAnswer
    }

    // 1. Testing `checkAccusation` in Board

    @Test
    public void testCorrectAccusation() {
        assertTrue(board.checkAccusation(correctSolution));
    }

    @Test
    public void testWrongPerson() {
        assertFalse(board.checkAccusation(wrongPerson));
    }

    @Test
    public void testWrongWeapon() {
        assertFalse(board.checkAccusation(wrongWeapon));
    }

    @Test
    public void testWrongRoom() {
        assertFalse(board.checkAccusation(wrongRoom));
    }

    // 2. Testing `disproveSuggestion` in Player

    @Test
    public void testSingleMatchingCard() {
        Player player = new Player() {
            @Override
            public void makeMove() {}
        };
        Card matchingCard = new Card("Knife", Card.CardType.WEAPON);
        player.addCard(matchingCard);
        Solution suggestion = new Solution("Colonel Mustard", "Knife", "Kitchen");

        assertEquals(matchingCard, player.disproveSuggestion(suggestion));
    }

    @Test
    public void testMultipleMatchingCards() {
        Player player = new Player() {
            @Override
            public void makeMove() {}
        };
        Card weaponCard = new Card("Knife", Card.CardType.WEAPON);
        Card roomCard = new Card("Kitchen", Card.CardType.ROOM);
        player.addCard(weaponCard);
        player.addCard(roomCard);
        Solution suggestion = new Solution("Colonel Mustard", "Knife", "Kitchen");

        Card result = player.disproveSuggestion(suggestion);
        assertTrue(result.equals(weaponCard) || result.equals(roomCard));
    }

    @Test
    public void testNoMatchingCard() {
        Player player = new Player() {
            @Override
            public void makeMove() {}
        };
        Solution suggestion = new Solution("Colonel Mustard", "Knife", "Kitchen");

        assertNull(player.disproveSuggestion(suggestion));
    }

    // 3. Testing `handleSuggestion` in Board

    @Test
    public void testNoOneCanDisprove() {
        Player accuser = new Player() {
            @Override
            public void makeMove() {}
        };
        Solution suggestion = new Solution("Miss Scarlet", "Wrench", "Library");

        assertNull(board.handleSuggestion(suggestion, accuser));
    }

    @Test
    public void testOnlyAccuserCanDisprove() {
        Player accuser = new Player() {
            @Override
            public void makeMove() {}
        };
        accuser.addCard(new Card("Wrench", Card.CardType.WEAPON));
        Solution suggestion = new Solution("Miss Scarlet", "Wrench", "Library");

        assertNull(board.handleSuggestion(suggestion, accuser));
    }

    @Test
    public void testFirstPlayerToDisprove() {
        Player player1 = new Player() {
            @Override
            public void makeMove() {}
        };
        player1.addCard(new Card("Miss Scarlet", Card.CardType.PERSON));
        Player player2 = new Player() {
            @Override
            public void makeMove() {}
        };
        player2.addCard(new Card("Library", Card.CardType.ROOM));
        board.addPlayer(player1);
        board.addPlayer(player2);

        Solution suggestion = new Solution("Miss Scarlet", "Wrench", "Library");
        assertEquals(new Card("Miss Scarlet", Card.CardType.PERSON), board.handleSuggestion(suggestion, player2));
    }
}
