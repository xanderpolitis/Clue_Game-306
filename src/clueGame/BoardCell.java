package clueGame;

import java.awt.Color;
import java.awt.Graphics;
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
    private String label = "A ROOM";
    
    private Set<BoardCell> adjList;
    
    private boolean isSecretPassage;
    private boolean isRoomCenter;
    private boolean isRoomLabel;
    private boolean occupied;
    private Room room;
    private boolean isRoom;
    
    public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
		this.adjList = new HashSet<>();
		this.isRoomCenter = false;
		this.occupied = false;
		this.room = room;
		this.isRoom = (room != null); //fix this line 
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
    
    //TODO
    // THIS FUNCTION NEEDS TO ADD A STRING THAT IS THE LABEL FIX IN BOARD
    public void setLabel() {
        isRoomLabel = true;
    }
    
    public boolean getLabel(BoardCell c) {
        return isRoomLabel;
    }

    public boolean isSecretPassage() {
        return isSecretPassage;
    }
    
    public char getSecretPassage() {
        return secretPassage;
    }
    
    public void setSecretPassage(char c) {
 		this.secretPassage = c;
 		isSecretPassage = true;
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
	
	public boolean isRoom() {
		return isRoom;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room r) {
		room = r;
		isRoom = true;
		label = room.getName();
	}
	
	//this is painting all of the cells individually
	public void paintComponent(Graphics g){
		if(this.isRoom) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(col*BoardGUI.xSize, row*BoardGUI.ySize, BoardGUI.xSize, BoardGUI.ySize);
		} else if (this.initial != 'X') {
			g.setColor(Color.YELLOW);
			g.fillRect(col*BoardGUI.xSize, row*BoardGUI.ySize, BoardGUI.xSize, BoardGUI.ySize);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(col*BoardGUI.xSize, row*BoardGUI.ySize, BoardGUI.xSize, BoardGUI.ySize);
		}
	}
	public void paintDoor(Graphics g) {
			g.setColor(Color.BLUE);
			switch(doorDirection) {
			case DoorDirection.UP:
				g.drawRect(col*BoardGUI.xSize, row*BoardGUI.ySize, BoardGUI.ySize, BoardGUI.ySize/9);
				break;
			case DoorDirection.RIGHT:
				g.drawRect((col+1)*BoardGUI.xSize, (row)*BoardGUI.ySize, BoardGUI.xSize/9, BoardGUI.xSize);
				break;
			case DoorDirection.LEFT:
				g.drawRect(col*BoardGUI.xSize, row*BoardGUI.ySize, BoardGUI.xSize/9, BoardGUI.xSize);
				break;
			case DoorDirection.DOWN:
				g.drawRect((col)*BoardGUI.xSize, (row+1)*BoardGUI.ySize, BoardGUI.ySize, BoardGUI.ySize/9);
				break;
			default:
				break;	
			}
	}
			
	
	public void paintLabel(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawChars(this.label.toCharArray(), 0, this.label.length(), col*BoardGUI.xSize, row*BoardGUI.ySize);
	}

}

