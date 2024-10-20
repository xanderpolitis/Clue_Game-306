package clueGame;

import java.util.HashSet;
import java.util.Set;

import experiment.TestBoardCell;

public class BoardCell {
    private int row;
    private int col;
    private char initial;
    private DoorDirection doorDirection;
    
    private Set<TestBoardCell> adjList;
    
    private boolean isRoomCenter;
    private boolean isRoomLabel;
    private boolean occupied;
    private char secretPassage;
    
    public char specialCharacter;

    
    public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
		this.adjList = new HashSet<>();
		this.isRoomCenter = false;
		this.occupied = false;
	}
    
    public void addAdjacency(TestBoardCell cell) {
		adjList.add(cell);
	}
    
    public Set<TestBoardCell> getAdjList(){	
		return adjList;
	}
    
    public boolean isDoorway() {
        return doorDirection != DoorDirection.NONE;
    }

    public DoorDirection getDoorDirection() {
        return doorDirection;
    }

    public void setRoomCenter() {
		this.isRoomCenter = true;
	}
    
    public boolean isRoomCenter() {
        return isRoomCenter;
    }

    public boolean isLabel() {
        return isRoomLabel;
    }

    public char getSecretPassage() {
        return secretPassage;
    }
    
    public char getInitia() {
    	return initial;
    }

	public void setSpecialCell(char charAt) {
		specialCharacter = charAt;
	}

	public void setOccupied(boolean b) {
		occupied = b;
	}

}

