package clueGame;

import java.util.Map;

public class Board {
    private static Board theInstance = new Board();
    private BoardCell[][] grid;
    private int numRows;
    private int numColumns;
    private Map<Character, Room> rooms;
    private String boardConfigFile;
    private String roomConfigFile;

    private Board() { }

    public static Board getInstance() {
        return theInstance;
    }

    public void setConfigFiles(String boardFile, String roomFile) {
        this.boardConfigFile = boardFile;
        this.roomConfigFile = roomFile;
    }

    public void initialize() {
    	loadSetupConfig();
    	loadLayoutConfig();
    }


    public BoardCell getCell(int row, int col) {
        return grid[row][col];
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public Room getRoom(BoardCell c){
		Room r = new Room(" ");
    	return r;
    }
    
    public Room getRoom(char c){
		Room r = new Room("c");
    	return r;
    }
    
	public void loadSetupConfig() {
		
	}

	public void loadLayoutConfig() {
		
	}

}



