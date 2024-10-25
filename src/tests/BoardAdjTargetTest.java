package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTest {

    private static Board board;

    @BeforeAll
    public static void setUp() /*throws BadConfigFormatException */{
        // Initialize the board with the layout file
    	board = Board.getInstance();
        board.setConfigFiles("ClueLayout.csv", "room_names.txt");
        try {
			board.initialize();
		} catch (BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Adjacency Tests

    @Test
    public void testAdjacencyWalkways() {
        // Test a cell that is a walkway
        Set<BoardCell> adjList = board.getAdjList(14, 3);
        assertTrue(adjList.contains(board.getCell(14, 4)));
        assertTrue(adjList.contains(board.getCell(13, 3))); 
        assertEquals(4, adjList.size()); 
    }

    @Test
    public void testAdjacencyRoomNotCenter() {
        // Test a room cell that is not a center, should have no adjacencies
        Set<BoardCell> adjList = board.getAdjList(1, 1);
        assertEquals(0, adjList.size()); 
    }

    @Test
    public void testAdjacencyDoorways() {
        // Test a doorway adjacency, ensure it connects to both a room and a walkway
        Set<BoardCell> adjList = board.getAdjList(5, 5);
        assertTrue(adjList.contains(board.getCell(5, 4)));
        assertTrue(adjList.contains(board.getCell(6, 5)));
        assertEquals(2, adjList.size());
    }

    @Test
    public void testAdjacencySecretPassage() {
        // Test that secret passage connections work between rooms
        Set<BoardCell> adjList = board.getAdjList(1, 1);
        assertTrue(adjList.contains(board.getCell(8, 8)));
        assertEquals(1, adjList.size());
    }

    @Test
    public void testAdjacencyAtBoardEdge() {
        // Test a cell at the edge of the board
        Set<BoardCell> adjList = board.getAdjList(0, 0);
        assertTrue(adjList.contains(board.getCell(0, 1)));
        assertTrue(adjList.contains(board.getCell(1, 0)));
        assertEquals(2, adjList.size());
    }

    @Test
    public void testAdjacencyBesideRoomNotDoorway() {
        // Test a cell beside a room that is not a doorway
        Set<BoardCell> adjList = board.getAdjList(4, 4);
        assertFalse(adjList.contains(board.getCell(5, 4)));
        assertTrue(adjList.contains(board.getCell(4, 3)));
        assertEquals(1, adjList.size());
    }

    // Target Tests

    @Test
    public void testTargetsAlongWalkways() {
        // Test targets along walkways with a movement range of 2
    	BoardCell cell = board.getCell(3, 5);
        board.calcTargets(cell, 2);
        Set<BoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(3, 7)));
        assertTrue(targets.contains(board.getCell(1, 5)));
        assertEquals(4, targets.size());
    }

    @Test
    public void testTargetsEnteringRoom() {
        // Test entering a room from a walkway
    	BoardCell cell = board.getCell(4, 4);
        board.calcTargets(cell, 1);
        Set<BoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(5, 5))); 
        assertEquals(1, targets.size());
    }

    @Test
    public void testTargetsLeavingRoomNoSecretPassage() {
        // Test targets when leaving a room that has no secret passage
    	BoardCell cell = board.getCell(6, 6);
        board.calcTargets(cell, 2);
        Set<BoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(5, 5)));
        assertTrue(targets.contains(board.getCell(4, 4)));
        assertEquals(3, targets.size());
    }

    @Test
    public void testTargetsLeavingRoomWithSecretPassage() {
        // Test targets when leaving a room with a secret passage
    	BoardCell cell = board.getCell(1, 1);
        board.calcTargets(cell, 1);
        Set<BoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(8, 8)));
        assertEquals(1, targets.size());
    }

    @Test
    public void testTargetsBlockedByPlayer() {
        // Test when a player is blocking a cell
    	BoardCell cell = board.getCell(3,5);
        board.getCell(3, 6).setOccupied(true);
        board.calcTargets(cell, 2);
        Set<BoardCell> targets = board.getTargets();
        assertFalse(targets.contains(board.getCell(3, 6)));
        assertEquals(3, targets.size());
    }
}

