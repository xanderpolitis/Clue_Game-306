package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import experiment.TestBoard;
import experiment.TestBoardCell;

public class BoardTestsExp {
	
	TestBoard board;
	
	@BeforeEach
	public void setUp() {
		board = new TestBoard();
	}
	
	@Test
    public void testAdjacencyTopLeft() {
        TestBoardCell cell = board.getCell(0, 0);
        Set<TestBoardCell> testList = cell.getAdjList();
        assertTrue(testList.contains(board.getCell(1, 0)));
        assertTrue(testList.contains(board.getCell(0, 1)));
        assertEquals(2, testList.size());
    }

    @Test
    public void testAdjacencyBottomRight() {
        TestBoardCell cell = board.getCell(3, 3);
        Set<TestBoardCell> testList = cell.getAdjList();
        assertTrue(testList.contains(board.getCell(2, 3)));
        assertTrue(testList.contains(board.getCell(3, 2)));
        assertEquals(2, testList.size());
    }

    @Test
    public void testAdjacencyRightEdge() {
        TestBoardCell cell = board.getCell(1, 3);
        Set<TestBoardCell> testList = cell.getAdjList();
        assertTrue(testList.contains(board.getCell(0, 3)));
        assertTrue(testList.contains(board.getCell(2, 3)));
        assertTrue(testList.contains(board.getCell(1, 2)));
        assertEquals(3, testList.size());
    }

    @Test
    public void testAdjacencyLeftEdge() {
        TestBoardCell cell = board.getCell(3, 0);
        Set<TestBoardCell> testList = cell.getAdjList();
        assertTrue(testList.contains(board.getCell(2, 0)));
        assertTrue(testList.contains(board.getCell(3, 1)));
        assertEquals(2, testList.size());
    }

    @Test
    public void testAdjacencyMiddle() {
        TestBoardCell cell = board.getCell(2, 2);
        Set<TestBoardCell> testList = cell.getAdjList();
        assertTrue(testList.contains(board.getCell(1, 2)));
        assertTrue(testList.contains(board.getCell(3, 2)));
        assertTrue(testList.contains(board.getCell(2, 1)));
        assertTrue(testList.contains(board.getCell(2, 3)));
        assertEquals(4, testList.size());
    }

    // Test Targets on Empty Board - 7 test cases

    @Test
    public void testTargetsNormal_TopLeft_1Step() {
        board.calcTargets(board.getCell(0, 0), 1);
        Set<TestBoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(1, 0)));
        assertTrue(targets.contains(board.getCell(0, 1)));
        assertEquals(2, targets.size());
    }

    @Test
    public void testTargetsNormal_TopLeft_2Steps() {
        board.calcTargets(board.getCell(0, 0), 2);
        Set<TestBoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(2, 0)));
        assertTrue(targets.contains(board.getCell(0, 2)));
        assertTrue(targets.contains(board.getCell(1, 1)));
        assertEquals(3, targets.size());
    }

    @Test
    public void testTargetsNormal_BottomRight_1Step() {
        board.calcTargets(board.getCell(3, 3), 1);
        Set<TestBoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(2, 3)));
        assertTrue(targets.contains(board.getCell(3, 2)));
        assertEquals(2, targets.size());
    }
    
    @Test
    public void testTargetsNormal_BottomRight_3Steps() {
        board.calcTargets(board.getCell(3, 3), 3);
        Set<TestBoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(0, 3)));
        assertTrue(targets.contains(board.getCell(1, 2)));
        assertEquals(6, targets.size());
    }

    @Test
    public void testTargetsNormal_Middle_1Step() {
        board.calcTargets(board.getCell(2, 2), 1);
        Set<TestBoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(1, 2)));
        assertTrue(targets.contains(board.getCell(3, 2)));
        assertTrue(targets.contains(board.getCell(2, 1)));
        assertTrue(targets.contains(board.getCell(2, 3)));
        assertEquals(4, targets.size());
    }
    //0  1  2  3
    //4  5  6  7
    //8  9  10 11
    //12 13 14 15
    @Test
    public void testTargetsNormal_Middle_3Steps() {
        board.calcTargets(board.getCell(2, 2), 3);
        Set<TestBoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(1, 2)));
        assertTrue(targets.contains(board.getCell(3, 2)));
        assertTrue(targets.contains(board.getCell(1, 0)));
        assertEquals(8, targets.size());
    }

    @Test
    public void testTargetsNormal_Middle_4Steps() {
        board.calcTargets(board.getCell(2, 2), 4);
        Set<TestBoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(0, 2)));
        assertTrue(!targets.contains(board.getCell(2, 2))); 
        assertTrue(targets.contains(board.getCell(3, 1)));
        assertEquals(7, targets.size());
    }

    // Test Targets with Rooms - 2 test cases
    //0  1  2  3
    //4  5  6  7
    //8  9  10 11
    //12 13 14 15
    @Test
    public void testTargetsRoom_TopLeft() {
        board.getCell(0, 1).setRoom(true); // Mark a room
        board.calcTargets(board.getCell(0, 0), 2);
        Set<TestBoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(1, 1))); // Can move down
        // Room cells should not be in the target
        assertTrue(targets.contains(board.getCell(2, 0))); 
        assertEquals(2, targets.size());
    }

    // Test Targets with Occupied Spaces - 2 test cases

    @Test
    public void testTargetsOccupied_Middle() {
        board.getCell(2, 1).setOccupied(true); // Mark cell as occupied
        board.calcTargets(board.getCell(2, 2), 1);
        Set<TestBoardCell> targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(1, 2))); // Can move up
        assertTrue(targets.contains(board.getCell(3, 2))); // Can move down
        assertTrue(!targets.contains(board.getCell(2, 1))); // Occupied, so not a valid target
        assertTrue(targets.contains(board.getCell(2, 3))); // Can move right
        assertEquals(3, targets.size());
    }
    //0  1  2  3
    //4  5  6  7
    //8  9  10 11
    //12 13 14 15
    @Test
    public void testTargetsOccupied_BottomLeft() {
        board.getCell(3, 1).setOccupied(true); // Mark cell as occupied
        board.calcTargets(board.getCell(3, 0), 2);
        Set<TestBoardCell> targets = board.getTargets();
        // Occupied cell should block movement to that direction
        assertTrue(targets.contains(board.getCell(2, 1)));
        assertTrue(targets.contains(board.getCell(1, 0))); // Can move up
        assertTrue(!targets.contains(board.getCell(3, 2))); // Can move further up
        assertEquals(2, targets.size());
    }

    // Test Mixed Conditions (Rooms and Occupied Cells) - 2 test cases


    @Test
    public void testTargetsMixed_Middle() {
        board.getCell(1, 2).setRoom(true); // Room at (1,2)
        board.getCell(2, 1).setOccupied(true); // Occupied at (2,1)
        board.calcTargets(board.getCell(2, 2), 2);
        Set<TestBoardCell> targets = board.getTargets();
        // Can move around blocked and room cells
        assertTrue(!targets.contains(board.getCell(1, 2))); // Blocked by room
        assertTrue(!targets.contains(board.getCell(2, 1))); // Blocked by occupied
        assertTrue(targets.contains(board.getCell(3, 3))); // Can move down
        assertTrue(targets.contains(board.getCell(1,3))); // Can move right
        assertEquals(3, targets.size());
    }
}
