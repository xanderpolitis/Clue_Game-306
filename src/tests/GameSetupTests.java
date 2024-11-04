package tests;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import clueGame.Board;

public class GameSetupTests {
	private Board board;
	
	@BeforeEach
	public void setup() {
		board = new Board();
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
