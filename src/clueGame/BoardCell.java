package clueGame;

public class BoardCell {
    private int row;
    private int col;
    private char initial;
    private DoorDirection doorDirection;
    private boolean isRoomCenter;
    private boolean isRoomLabel;
    private char secretPassage;

    public boolean isDoorway() {
        return doorDirection != DoorDirection.NONE;
    }

    public DoorDirection getDoorDirection() {
        return doorDirection;
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
		
	}

	public void setOccupied(boolean b) {
		
	}

}

