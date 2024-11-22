package clueGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;

import experiment.TestBoardCell;

public class BoardCell{
	protected int row;
	protected int col;

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

	private static boolean green = false;
	private final static Color greenColor = new Color(122, 148, 90);
	private final static Color greyColor = new Color(235, 236, 211);
	private final static Color roomColor = new Color(220, 190, 107);
	private final static Color targetColor = new Color(158, 247, 230);
	private final static Color textColor = new Color(10, 10, 10);


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
	public void paintCell(Graphics g){
		if(green && !(row == 30 && col == 30) ) {
			g.setColor(greenColor);
			green = false;
		} else {
			g.setColor(greyColor);
			green = true;
		}

		if(this.isRoom) {
			g.setColor(roomColor);
			g.fillRect(col*Board.xSize, row*Board.ySize, Board.xSize, Board.ySize);
		} else if (this.initial != 'X') {	
			g.fillRect(col*Board.xSize, row*Board.ySize, Board.xSize, Board.ySize);
			g.setColor(Color.BLACK);
			g.drawRect(col*Board.xSize, row*Board.ySize, Board.xSize, Board.ySize);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(col*Board.xSize, row*Board.ySize, Board.xSize, Board.ySize);
		}
	}

	public void paintDoor(Graphics g) {
		g.setColor(Color.BLUE);
		switch(doorDirection) {
		case DoorDirection.UP:
			g.fillRect(col*Board.xSize, row*Board.ySize, Board.ySize, Board.ySize/9);
			break;
		case DoorDirection.RIGHT:
			g.fillRect((col+1)*Board.xSize, (row)*Board.ySize, Board.xSize/9, Board.xSize);
			break;
		case DoorDirection.LEFT:
			g.fillRect(col*Board.xSize, row*Board.ySize, Board.xSize/9, Board.xSize);
			break;
		case DoorDirection.DOWN:
			g.fillRect((col)*Board.xSize, (row+1)*Board.ySize, Board.ySize, Board.ySize/9);
			break;
		default:
			break;	
		}
	}

	public void paintTarget(Graphics g){

		g.setColor(targetColor);
		g.fillRect(col*Board.xSize, row*Board.ySize, Board.xSize, Board.ySize);
	}

	public void paintLabel(Graphics g) {
		g.setColor(textColor);
		g.setFont(new Font("Times New Roman", Font.BOLD, 15));
		g.drawChars(this.label.toCharArray(), 0, this.label.length(), col*Board.xSize, row*Board.ySize);
	}

}

