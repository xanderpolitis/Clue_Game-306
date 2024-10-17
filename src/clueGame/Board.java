package clueGame;

import java.util.*;
import java.io.*;

public class Board {
    private static Board theInstance = new Board();
    private BoardCell[][] grid;
    private int numRows = 0;
    private int numColumns = 0;
    private Map<Character, Room> rooms;
    private String boardConfigFile;
    private String roomConfigFile;
    
    
    
    private Board() { 
    	
    }

    public static Board getInstance() {
        return theInstance;
    }

    public void setConfigFiles(String boardFile, String roomFile) {
        this.boardConfigFile = boardFile;
        this.roomConfigFile = roomFile;
    }

    public void initialize() throws BadConfigFormatException {
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
    
	public void loadSetupConfig() throws BadConfigFormatException {
		try {
			File txtFile = new File("room_names.txt");
			Scanner txtReader = new Scanner(txtFile);
			String scan;
			while ((scan = txtReader.nextLine()) != null) {
				String[] parts = scan.split(", ");
				if (parts.length < 3) {
					throw new BadConfigFormatException();
				}
				if (parts[0].equals("Room")) {
					Room room = new Room(parts[1]);
					rooms.put(parts[2].charAt(0), room);
				}
			}
            
		} catch(IOException e){
			throw new BadConfigFormatException();
		}
		
	}

	public void loadLayoutConfig() throws BadConfigFormatException{
		try {
			File csvFile = new File("ClueLayout.csv");
			Scanner csvReader1 = new Scanner(csvFile);
			while(csvReader1.hasNextLine()){
            	numRows++;
            	
			}
			numColumns = 1;
			Scanner csvReader2 = new Scanner(csvFile);
			while(csvReader2.hasNext()){
                csvReader2.next();
				while(csvReader2.hasNext(",")){
                    csvReader2.next();
	                numColumns++;
	            }
            }
            
            
            Scanner csvReader3 = new Scanner(csvFile);
            int i=1;
            int j=0;
            while(csvReader3.hasNext()){
                if(csvReader3.hasNext(",")){
                	csvReader3.next();
                	i++;
                }
                if(csvReader3.hasNext("/n")){
                    if(i!=numColumns || i==1){
                        throw new BadConfigFormatException();
                    }
                    i=0;
                	csvReader3.next();
                	j++;
                }
                
                if(!rooms.containsKey(csvReader3.next())){
                    throw new BadConfigFormatException();
                }
            }
            if(j != numRows){
                throw new BadConfigFormatException();
            }
            
		} catch(IOException e){
			throw new BadConfigFormatException();
		}
	
	}

	public Set<BoardCell> getAdjList(int i, int j) {
		return null;
	}

	public Set<BoardCell> getTargets() {
		return null;
	}

	public void calcTargets(int i, int j, int k) {
		
	}
}
