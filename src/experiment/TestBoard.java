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
				if(row != 0) {
					if(!grid[row-1][col].getOccupied() && grid[row-1][col].room()) {
						grid[row][col].addAdjacency(grid[row-1][col]);
					}
				}
				if(col != 0) {
					if(!grid[row][col-1].getOccupied() && grid[row][col-1].room()) {
						grid[row][col].addAdjacency(grid[row][col-1]);
					}
				}
				if(row+1 == ROWS) {
					if(!grid[row+1][col].getOccupied() && grid[row+1][col].room()) {
						grid[row][col].addAdjacency(grid[row+1][col]);
					}
					
				}
				if(col+1 == COLS) {
					if(!grid[row][col+1].getOccupied() && grid[row][col+1].room()) {
						grid[row][col].addAdjacency(grid[row][col+1]);
					}
				}
			}
		}
	}
			
	
	public void findAllTargets(TestBoardCell startCell, int numSteps) {
		for (TestBoardCell adjCell:startCell.getAdjList()) {
			if (visited.contains(adjCell)) {
				continue;
			}
			visited.add(adjCell);
			if(numSteps == 1) {
				targets.add(adjCell);
			}else {
				findAllTargets(adjCell, numSteps-1); 
			}
			visited.remove(adjCell);
		}	
	}
	
	
	public void calcTargets(TestBoardCell startCell, int pathlength) {
		visited = new HashSet<>();
		targets = new HashSet<>();
		
		visited.add(startCell);
		
		findAllTargets(startCell, pathlength);
	}
	
	public TestBoardCell getCell(int row, int col) {
		return grid[row][col];
	}
	
	public Set<TestBoardCell> getTargets(){
		return targets;
	}
}
