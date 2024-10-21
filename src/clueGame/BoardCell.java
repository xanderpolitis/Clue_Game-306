package clueGame;

import java.util.HashSet;
import java.util.Set;

import experiment.TestBoardCell;

public class BoardCell {
    private int row;
    private int col;
    
    private char initial = 'X';
    private DoorDirection doorDirection = DoorDirection.NONE;
    public char specialCharacter;
    private char secretPassage;
    
    private Set<BoardCell> adjList;
    
    private boolean isRoomCenter;
    private boolean isRoomLabel;
    private boolean occupied;

    
 

    
    public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
		this.adjList = new HashSet<>();
		this.isRoomCenter = false;
		this.occupied = false;
	}
    
    public void addAdjacency(BoardCell cell) {
		adjList.add(cell);
	}
    
    public Set<BoardCell> getAdjList(){	
		return adjList;
	}
    
    public boolean isDoorway() {
        return doorDirection != DoorDirection.NONE;
    }

    public DoorDirection getDoorDirection() {
        return doorDirection;
    }

    public void setDoorDirection(DoorDirection d) {
        doorDirection = d;
    }
    
    
    public boolean isRoomCenter() {
        return isRoomCenter;
    }

    public void setRoomCenter() {
 		this.isRoomCenter = true;
 	}
    
    public boolean isLabel() {
        return isRoomLabel;
    }
    
    public void setLabel() {
        isRoomLabel = true;
    }
    
    public boolean getLabel(BoardCell c) {
        return isRoomLabel;
    }

    public char getSecretPassage() {
        return secretPassage;
    }
    
    public void setSecretPassage(char c) {
 		this.secretPassage = c;
 	}
    
    public char getInitial() {
    	return initial;
    }
    
    public void setInitial(char c) {
    	initial = c;
    }

	public void setSpecialCell(char charAt) {
		specialCharacter = charAt;
	}

	public void setOccupied(boolean b) {
		occupied = b;
	}
	
	public boolean getOccupied() {
		return occupied;
	}

}

