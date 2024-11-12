package tests;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import clueGame.Board;

public class GameSetupTests {
	private Board board;
	
	@BeforeEach
	public void setup() {
    	board = Board.getInstance();
        board.setConfigFiles("ClueLayout.csv", "room_names.txt");
			board.initialize();
	}
	
	@Test
	public void testLoadPlayers() {
		
	}
	
	@Test
	public void testDeckSize() {
		
	}
	
	@Test
	public void testDealCards() {
		
	}
}
