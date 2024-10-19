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


	private Board() {}

	public static Board getInstance() {
		return theInstance;
	}

	public void setConfigFiles(String boardFile, String roomFile) {
		this.boardConfigFile = datapath + boardFile;
		this.roomConfigFile = datapath + roomFile;
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


			Scanner csvReader3 = new Scanner(csvFile);
			
			ArrayList<String[]> layout = new ArrayList<>();
			while(csvReader3.hasNextLine()) {
				scan = csvReader3.nextLine();
				layout.add(scan.split(","));
			}	
			
			int COLS = layout.get(0).length;
			int ROWS = layout.size();
			
			for(int i=0; i<numColumns-1; i++) {
				if (layout.get(i).length != COLS) { 
					throw new BadConfigFormatException();
				}
				
				for(int j=0; j<numRows-1; j++) {
					if(numRows != ROWS) {
						throw new BadConfigFormatException();
					}
					if (!rooms.containsKey(layout.get(i)[j].charAt(0)) && layout.get(i)[j].charAt(0) != 'X' && layout.get(i)[j].charAt(0) != 'W') {
						System.out.println(layout.get(i)[j].charAt(0));
						throw new BadConfigFormatException();
					}
				}
			}
			
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
