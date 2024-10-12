package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoard {
	private Set<TestBoardCell> targets;
	private TestBoardCell[][] grid;
	private Set<TestBoardCell> visited;
	
	
	final static int COLS = 4;
	final static int ROWS = 4;
	
	public TestBoard() {
		this.targets = new HashSet<>();
		grid = new TestBoardCell[ROWS][COLS];
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				grid[row][col] = new TestBoardCell(row, col);
			}
		}
		
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if(row > 0) {
						grid[row][col].addAdjacency(grid[row-1][col]);
				}
				if(col > 0) {
						grid[row][col].addAdjacency(grid[row][col-1]);
				}
				if(row+1 < ROWS) {
						grid[row][col].addAdjacency(grid[row+1][col]);
				}
				if(col+1 < COLS) {
						grid[row][col].addAdjacency(grid[row][col+1]);
				}
			}
		}
	}
			
	
	public void findAllTargets(TestBoardCell startCell, int numSteps) {
		
		if (visited.contains(startCell)) {
			return;
		}
		
		visited.add(startCell);
		
		if(startCell.getOccupied()) {
			return;
		}
		
		if(numSteps == 0 || startCell.room()) {
			targets.add(startCell);
			return;
		}
		
		for (TestBoardCell adjCell : startCell.getAdjList()) {
				findAllTargets(adjCell, numSteps-1); 
				visited.remove(adjCell);
		}
		return;
		
		
		
//		for (TestBoardCell adjCell : startCell.getAdjList()) {
//			if(visited.contains(adjCell)) {
//				continue;
//			}
//			visited.add(adjCell);
//			if(numSteps == 1) {
//				targets.add(adjCell);
//			}else {
//				findAllTargets(adjCell, numSteps-1); 
//			}
//			visited.remove(adjCell);
//		}
	}
	
	
	public void calcTargets(TestBoardCell startCell, int pathlength) {
		targets = new HashSet<>();
		visited = new HashSet<>();

		//visited.add(startCell);
		
		findAllTargets(startCell, pathlength);
		
		return;
	}
	
	public TestBoardCell getCell(int row, int col) {
		return grid[row][col];
	}
	
	public Set<TestBoardCell> getTargets(){
		return targets;
	}
}