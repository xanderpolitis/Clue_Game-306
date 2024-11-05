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
			board.initialize();
			// TODO Auto-generated catch block
    }

    // Adjacency Tests

    @Test
    public void testAdjacencyWalkways() {
        // Test a cell that is a walkway
        Set<BoardCell> adjList = board.getAdjList(12, 4);
        assertTrue(adjList.contains(board.getCell(12, 5)));
        assertTrue(adjList.contains(board.getCell(13, 4))); 
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
        Set<BoardCell> adjList = board.getAdjList(4, 6);
        assertTrue(adjList.contains(board.getCell(3, 2)));
        assertTrue(adjList.contains(board.getCell(5, 6)));
        assertEquals(4, adjList.size());
    }

    @Test
    public void testAdjacencySecretPassage() {
        // Test that secret passage connections work between rooms
        Set<BoardCell> adjList = board.getAdjList(3, 2);
        assertTrue(adjList.contains(board.getCell(26, 28)));
    }

    @Test
    public void testAdjacencyAtBoardEdge() {
        // Test a cell at the edge of the board
        Set<BoardCell> adjList = board.getAdjList(30, 0);
        assertTrue(adjList.contains(board.getCell(30, 1)));
        assertTrue(adjList.contains(board.getCell(29, 0)));
        assertEquals(2, adjList.size());
    }

    @Test
    public void testAdjacencyBesideRoomNotDoorway() {
        // Test a cell beside a room that is not a doorway
        Set<BoardCell> adjList = board.getAdjList(0, 6);
        assertTrue(adjList.contains(board.getCell(1, 6)));
        assertTrue(adjList.contains(board.getCell(0, 7)));
        assertEquals(2, adjList.size());
    }

    // Target Tests

    @Test
    public void testTargetsAlongWalkways() {
        // Test targets along walkways with a movement range of 2
    	BoardCell cell = board.getCell(30, 17);
        board.calcTargets(cell, 2);
        Set<BoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(29, 18)));
        assertTrue(targets.contains(board.getCell(29, 16)));
        assertEquals(3, targets.size());
    }

    @Test
    public void testTargetsEnteringRoom() {
        // Test entering a room from a walkway
    	BoardCell cell = board.getCell(27, 9);
        board.calcTargets(cell, 1);
        Set<BoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(27, 8)));
        assertTrue(targets.contains(board.getCell(30, 11)));
    }

    @Test
    public void testTargetsLeavingRoomNoSecretPassage() {
        // Test targets when leaving a room that has no secret passage
    	BoardCell cell = board.getCell(20, 1);
        board.calcTargets(cell, 2);
        Set<BoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(22, 5)));
        assertTrue(targets.contains(board.getCell(21, 6)));
        assertEquals(3, targets.size());
    }

    @Test
    public void testTargetsLeavingRoomWithSecretPassage() {
        // Test targets when leaving a room with a secret passage
    	BoardCell cell = board.getCell(3, 2);
        board.calcTargets(cell, 1);
        Set<BoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(26, 28)));
        assertEquals(2, targets.size());
    }

    @Test
    public void testTargetsBlockedByPlayer() {
        // Test when a player is blocking a cell
    	BoardCell cell = board.getCell(13,3);
        board.getCell(13, 4).setOccupied(true);
        board.calcTargets(cell, 1);
        Set<BoardCell> targets = board.getTargets();
        assertFalse(targets.contains(board.getCell(13, 4)));
        assertEquals(3, targets.size());
    }
}

