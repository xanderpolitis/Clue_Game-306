package tests;

import static org.junit.Assert.*;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTest {

    private static Board board;

    @Before
    public void setUp() throws BadConfigFormatException {
        // Initialize the board with the layout file
        board.setConfigFiles("ClueLayout.csv", "room_names.txt");
        board.initialize();
    }

    // Adjacency Tests

    @Test
    public void testAdjacencyWalkways() {
        // Test a cell that is a walkway
        Set<BoardCell> adjList = board.getAdjList(3, 5);
        assertTrue(adjList.contains(board.getCell(3, 6)));
        assertTrue(adjList.contains(board.getCell(2, 5))); 
        assertEquals(2, adjList.size()); 
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
        board.calcTargets(3, 5, 2);
        Set<BoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(3, 7)));
        assertTrue(targets.contains(board.getCell(1, 5)));
        assertEquals(4, targets.size());
    }

    @Test
    public void testTargetsEnteringRoom() {
        // Test entering a room from a walkway
        board.calcTargets(4, 4, 1);
        Set<BoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(5, 5))); 
        assertEquals(1, targets.size());
    }

    @Test
    public void testTargetsLeavingRoomNoSecretPassage() {
        // Test targets when leaving a room that has no secret passage
        board.calcTargets(6, 6, 2);
        Set<BoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(5, 5)));
        assertTrue(targets.contains(board.getCell(4, 4)));
        assertEquals(3, targets.size());
    }

    @Test
    public void testTargetsLeavingRoomWithSecretPassage() {
        // Test targets when leaving a room with a secret passage
        board.calcTargets(1, 1, 1);
        Set<BoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(8, 8)));
        assertEquals(1, targets.size());
    }

    @Test
    public void testTargetsBlockedByPlayer() {
        // Test when a player is blocking a cell
        board.getCell(3, 6).setOccupied(true);
        board.calcTargets(3, 5, 2);
        Set<BoardCell> targets = board.getTargets();
        assertFalse(targets.contains(board.getCell(3, 6)));
        assertEquals(3, targets.size());
    }
}

