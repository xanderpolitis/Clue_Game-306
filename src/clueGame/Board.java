package clueGame;

import java.util.*;

import experiment.TestBoardCell;

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

	private Set<BoardCell> visited = new HashSet<BoardCell>();
	private Set<BoardCell> targets = new HashSet<BoardCell>();

	protected static ArrayList<Player> players = new ArrayList<>();
	
	private Solution theAnswer;


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
				rooms.get(letters.charAt(0)).setCenterCell(cell);
				break;
			case '#':
				cell.setLabel();
				rooms.get(letters.charAt(0)).setLabelCell(cell);
				break;
			default:
				cell.setSecretPassage(letters.charAt(1));
			}
		}
	}

	public void initialize()  {
		try {
			layout.clear();
			loadSetupConfig();
			loadLayoutConfig();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (BadConfigFormatException e){
			System.out.println(e.getMessage());
		}

		grid = new BoardCell[numRows][numColumns];

		for(int i=0; i < numRows; i++) {
			for(int j=0; j < numColumns; j++) {
				grid[i][j] = new BoardCell(i, j);
				initHelper(grid[i][j], layout.get(i)[j]);
			}
		}

		for(int i = 0; i< numRows;i++) {
			for(int j =0; j< numColumns; j++) {
				BoardCell cell = grid[i][j];
				BoardCell cellMoved;
				BoardCell roomCenterCell;
				if(grid[i][j].getInitial() == 'W') {
					if(i > 0 && grid[i-1][j].getInitial() == 'W') {
						grid[i][j].addAdjacency(grid[i-1][j]);
					}
					if(j > 0 && grid[i][j-1].getInitial() == 'W') {
						grid[i][j].addAdjacency(grid[i][j-1]);
					}
					if(i+1 < numRows && grid[i+1][j].getInitial() == 'W') {
						grid[i][j].addAdjacency(grid[i+1][j]);
					}
					if(j+1 < numColumns && grid[i][j+1].getInitial() == 'W') {
						grid[i][j].addAdjacency(grid[i][j+1]);
					}
				}
				switch(grid[i][j].getDoorDirection()) {
				//UP, DOWN, LEFT, RIGHT, NONE
				case UP:
					cell = grid[i][j];
					cellMoved = grid[i-1][j];
					roomCenterCell = rooms.get(cellMoved.getInitial()).getCenterCell();
					cell.addAdjacency(roomCenterCell);
					roomCenterCell.addAdjacency(cell);
					break;
				case DOWN:
					cell = grid[i][j];
					cellMoved = grid[i+1][j];
					roomCenterCell = rooms.get(cellMoved.getInitial()).getCenterCell();
					cell.addAdjacency(roomCenterCell);
					roomCenterCell.addAdjacency(cell);
					break;
				case LEFT:
					cell = grid[i][j];
					cellMoved = grid[i][j-1];
					roomCenterCell = rooms.get(cellMoved.getInitial()).getCenterCell();
					cell.addAdjacency(roomCenterCell);
					roomCenterCell.addAdjacency(cell);
					break;
				case RIGHT:
					cell = grid[i][j];
					cellMoved = grid[i][j+1];
					roomCenterCell = rooms.get(cellMoved.getInitial()).getCenterCell();
					cell.addAdjacency(roomCenterCell);
					roomCenterCell.addAdjacency(cell);
					break;
				case NONE:
					cell = grid[i][j];
					if(cell.isSecretPassage()) { // if SP, 
						// get the room of the current cells initial, get that room's CC
						roomCenterCell = rooms.get(cell.getInitial()).getCenterCell();
						cell = rooms.get(cell.getSecretPassage()).getCenterCell();
						cell.addAdjacency(roomCenterCell);
						roomCenterCell.addAdjacency(cell);
					}
				}
			}
		}
	}



	public BoardCell getCell(int col, int row) { // Reactor later
		return grid[col][row];
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public Room getRoom(BoardCell cell){
		System.out.println(cell);
		return rooms.get(cell.getInitial());
	}

	public Room getRoom(char c){
		return rooms.get(c);
	}

	public void loadSetupConfig() throws FileNotFoundException, BadConfigFormatException {
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
				if (parts[0].equals("Space")) {
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

			for(String[] i : layout) {
				if (i.length != numColumns) { 
					throw new BadConfigFormatException();
				}

				for(String j : i) {
					String cell = j;
					char initial = j.charAt(0);
					if (cell.length() == 1){
						if (!rooms.containsKey(initial)) {
							throw new BadConfigFormatException();
						}
					}else if (cell.length() == 2){
						char secondChar = j.charAt(1);
						if (secondChar == '^' || secondChar == 'v' || secondChar == '>'|| secondChar == '<'||
								secondChar == '*'|| secondChar == '#'){
							continue;
						} else if (secondChar == 'X' || secondChar == 'W' || !rooms.containsKey(secondChar)){
							throw new BadConfigFormatException();
						}
					}


					if(numRows != layout.size()) {
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
		return grid[i][j].getAdjList();
	}

	public void findAllTargets(BoardCell startCell, int numSteps) {

		//		if (visited.contains(startCell)) {
		//			return;
		//		}
		//
		//		visited.add(startCell);
		//
		//		if(startCell.getOccupied()) {
		//			return;
		//		}
		//
		//		if(numSteps == 0 || startCell.isRoomCenter()) {
		//			targets.add(startCell);
		//			return;
		//		}
		//
		//		for (BoardCell adjCell : startCell.getAdjList()) {
		//			findAllTargets(adjCell, numSteps-1); 
		//			visited.remove(adjCell);
		//		}
		//		return;



		for (BoardCell adjCell : startCell.getAdjList()) {
			if(visited.contains(adjCell) || (adjCell.getOccupied() && !adjCell.isRoomCenter())) {
				continue;
			}
			visited.add(adjCell);
			
			if(adjCell.isRoomCenter()||adjCell.isSecretPassage()) {
				targets.add(adjCell);
				continue;
			}
			if(numSteps == 1) {
				targets.add(adjCell);
			}else {
				findAllTargets(adjCell, numSteps-1); 
			}
			visited.remove(adjCell);
		}
	}

	public void calcTargets(BoardCell startCell, int pathlength) {
		targets.clear();
		visited.clear();


		visited.add(startCell);

		findAllTargets(startCell, pathlength);

		return;
	}

	public Set<BoardCell> getTargets(){
		return targets;
	}
	
	public void addPlayer(Player player) {
        players.add(player);
    }
	
	public void setSolution(Solution solution) {
		this.theAnswer = solution;
	}
	
	public boolean checkAccusation(Solution accusation) {
		return this.theAnswer.equals(accusation);
	}
	
    public Card handleSuggestion(Solution suggestion, Player accuser) {
        for (Player player : players) {
            if (player != accuser) {
                Card disprovingCard = player.disproveSuggestion(suggestion);
                if (disprovingCard != null) {
                    return disprovingCard;
                }
            }
        }
        return null;
    }
}
