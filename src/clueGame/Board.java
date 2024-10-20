package clueGame;

import java.util.*;
import java.io.*;



public class Board {
	private static Board theInstance = new Board();
	private BoardCell[][] grid;
	private int numRows = 0;
	private int numColumns = 0;
	
	private Map<Character, Room> rooms = new HashMap<>();
	
	private String boardConfigFile;
	private String roomConfigFile;
	private static String datapath = "data/";
	
	
	ArrayList<String[]> layout = new ArrayList<>();

	private Board() {}

	public static Board getInstance() {
		return theInstance;
	}

	public void setConfigFiles(String boardFile, String roomFile) {
		this.boardConfigFile = datapath + boardFile;
		this.roomConfigFile = datapath + roomFile;
	}

	public void initHelper(BoardCell cell, String letters) {
		cell.setInitial(letters.charAt(0));
		if (letters.length() == 2) {
			switch(letters.charAt(1)) {
				case '^':
					cell.setDoorDirection(DoorDirection.UP);
					break;
				case '>':
					cell.setDoorDirection(DoorDirection.RIGHT);
					break;
				case '<':
					cell.setDoorDirection(DoorDirection.LEFT);
					break;
				case 'v':
					cell.setDoorDirection(DoorDirection.DOWN);
					break;
				case '*':
					cell.setRoomCenter();
					break;
				case '#':
					cell.setLabel();
					break;
				default:
					cell.setSecretPassage(letters.charAt(1));
			}
		}
	}
	
	public void initialize() throws BadConfigFormatException {
		loadSetupConfig();
		loadLayoutConfig();
		
		grid = new BoardCell[numColumns][numRows];
		
		for(int i=0; i < numColumns-1; i++) {
			for(int j=0; j < numRows-1; j++) {
				grid[i][j] = new BoardCell(i, j);
				initHelper(grid[i][j], layout.get(i)[j]);
			}
		}
	}


	public BoardCell getCell(int col, int row) {
		return grid[col][row];
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public Room getRoom(BoardCell cell){
		return rooms.get(cell.getInitial());
	}

	public Room getRoom(char c){
		return rooms.get(c);
	}

	public void loadSetupConfig() throws BadConfigFormatException {
		try {
			File txtFile = new File(roomConfigFile);
			Scanner txtReader = new Scanner(txtFile);
			String scan;
			while (txtReader.hasNextLine()) {
				scan = txtReader.nextLine();
				if (scan.contains("//")) {
					continue;
				}
				String[] parts = scan.split(", ");
				if (parts.length < 3) {
					throw new BadConfigFormatException();
				}
				if (parts[0].equals("Room")) {
					Room room = new Room(parts[1]);
					rooms.put(parts[2].charAt(0), room);
				}
			}
			txtReader.close();

		} catch(IOException e){
			throw new BadConfigFormatException();
		}
	}

	public void loadLayoutConfig() throws BadConfigFormatException{
		try {
			String scan;
			File csvFile = new File(boardConfigFile);
			Scanner csvReader1 = new Scanner(csvFile);
			while(csvReader1.hasNextLine()){
				scan = csvReader1.nextLine();
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
			System.out.println(numColumns);

			Scanner csvReader3 = new Scanner(csvFile);
			
			
			while(csvReader3.hasNextLine()) {
				scan = csvReader3.nextLine();
				layout.add(scan.split(","));
			}	
			
			numColumns = layout.get(0).length;
			numRows = layout.size();
			
			for(int i=0; i<numColumns-1; i++) {
				if (layout.get(i).length != numColumns) { 
					throw new BadConfigFormatException();
				}
				
				for(int j=0; j<numRows-1; j++) {
					if(numRows != layout.size()) {
						throw new BadConfigFormatException();
					}
					if (!rooms.containsKey(layout.get(i)[j].charAt(0)) && layout.get(i)[j].charAt(0) != 'X' && layout.get(i)[j].charAt(0) != 'W') {
						System.out.println(layout.get(i)[j].charAt(0));
						throw new BadConfigFormatException();
					}
				}
			}
			System.out.println(numColumns);
			System.out.println(numRows);
			csvReader1.close();
			csvReader2.close();
			csvReader3.close();
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
