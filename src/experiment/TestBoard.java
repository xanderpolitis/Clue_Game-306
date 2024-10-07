package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoard {
	private Set<TestBoardCell> target;
	private TestBoardCell[][] grid;
	
	public TestBoard() {
		this.target = new HashSet<>();
		grid = new TestBoardCell[4][4];
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				grid[row][col] = new TestBoardCell(row, col);
			}
		}
	}	
			
	public void calcTargets(TestBoardCell startCell, int pathlength) {
		
	}
	
	public TestBoardCell getCell(int row, int col) {
		return grid[row][col];
	}
	
	public Set<TestBoardCell> getTargets(){
		return target;
	}
}
