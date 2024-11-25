package clueGame;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;



@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener {
	private static Board theInstance = new Board();

	static ClueGame frame = null;

	public static int xSize;
	public static int ySize;

	public BoardCell[][] grid;
	ArrayList<String[]> layout = new ArrayList<>();
	private int numRows = 0;
	private int numColumns = 0;


	private Map<Character, Room> rooms = new HashMap<>();


	private String boardConfigFile;
	private String roomConfigFile;
	private static String datapath = "data/";


	private Set<BoardCell> visited = new HashSet<BoardCell>();
	private Set<BoardCell> targets = new HashSet<BoardCell>();


	protected ArrayList<Player> players = new ArrayList<>();
	protected ArrayList<Card> cards = new ArrayList<>();


	//Variables to do with next
	public int roll = 1;
	public static int currPlayer = 0;
	private boolean firstTurn = true;
	public String guess  = "                                               ";
	public String result = "                                                ";
	private boolean finished = true;

	private Solution theAnswer;


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
		if(letters.charAt(0) != 'X' && letters.charAt(0) != 'W') {
			//set as a room
			cell.setRoom(rooms.get(cell.getInitial()));
		}

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

		//ADDS ADJACENCY
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
		setupCards();
		frame = new ClueGame();
		theInstance.addMouseListener(theInstance);
	}


	public void setupCards() {
		boolean room = false;
		boolean weapon = false;
		boolean person = false;
		String personString = null;
		String weaponString = null;
		String roomString = null;
		//setup theAnswer
		for (Card card:cards) {
			if(room && person && weapon) {break;}
			if(card.getType() == Card.CardType.PERSON && !person) {
				person = true;
				personString = card.getName();
			}
			if(card.getType() == Card.CardType.WEAPON && !weapon) {
				weapon = true;
				weaponString = card.getName();
			}
			if(card.getType() == Card.CardType.ROOM && !room) {
				room = true;
				roomString = card.getName();
			}
		}

		theAnswer = new Solution(personString, weaponString, roomString);

		ArrayList<Card> tempCards = (ArrayList<Card>) cards.clone();
		Collections.shuffle(tempCards);
		Collections.shuffle(cards);
		for (Player player:players){
			while(player.hand.size() < 3) {
				if(!theAnswer.doesContain(tempCards.getFirst())) {
					player.addCard(tempCards.getFirst());
					tempCards.remove(0);
				}else {
					tempCards.remove(0);
				}
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

	@SuppressWarnings("resource")
	public void loadSetupConfig() throws FileNotFoundException, BadConfigFormatException {
		try {
			File txtFile = new File(roomConfigFile);
			Scanner txtReader = new Scanner(txtFile);
			String scan;

			ArrayList<String> playerNames = new ArrayList<>();
			ArrayList<String> weaponNames = new ArrayList<>();

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
					Card card = new Card(parts[1], Card.CardType.ROOM);
					cards.add(card);
				}
				if (parts[0].equals("Space")) {
					Room room = new Room(parts[1]);
					rooms.put(parts[2].charAt(0), room);
				}
				if ( parts[0].equals("Player")) {
					playerNames.add(scan);
				}

				if ( parts[0].equals("Weapon")) {
					weaponNames.add(parts[1]);
				}
			}

			//choose a random element in the list and make a humanPlayer
			Collections.shuffle(playerNames);

			//reading in all the players and adding them to cards
			String[] parts = playerNames.getFirst().split(", ");
			HumanPlayer human = new HumanPlayer(
					parts[1], MyColor.getColor(parts[2]), 
					Integer.valueOf(parts[3]), Integer.valueOf(parts[4]));
			players.addFirst(human);
			Card card = new Card(parts[1], Card.CardType.PERSON);
			cards.add(card);
			playerNames.remove(0);

			//Computer Players
			for(String player:playerNames) {
				parts = player.split(", ");
				ComputerPlayer comp = new ComputerPlayer(
						parts[1], MyColor.getColor(parts[2]),
						Integer.valueOf(parts[3]), Integer.valueOf(parts[4]));
				players.addLast(comp);
				card = new Card(parts[1], Card.CardType.PERSON);
				cards.add(card);
			}
			//adding weapons to cards
			for(String weapon:weaponNames) {
				card = new Card(weapon, Card.CardType.WEAPON);
				cards.add(card);
			}

			txtReader.close();
			return;
		} catch(Exception e){
			System.out.println(e);
			throw new BadConfigFormatException();
		}
	}

	@SuppressWarnings("resource")
	public void loadLayoutConfig() throws BadConfigFormatException{
		try {
			String scan;
			File csvFile = new File(boardConfigFile);

			Scanner csvReader = new Scanner(csvFile);

			while(csvReader.hasNextLine()) {
				scan = csvReader.nextLine();
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
						if (secondChar == '^' || secondChar == 'v' || secondChar == '>'||
								secondChar == '<'||secondChar == '*'|| secondChar == '#'){
							continue;
						} else if (secondChar == 'X' || secondChar == 'W' 
								|| !rooms.containsKey(secondChar)){
							throw new BadConfigFormatException();
						}
					}

					if(numRows != layout.size()) {
						throw new BadConfigFormatException();
					}
				}
			}
			csvReader.close();
			return;
		} catch(IOException e){
			throw new BadConfigFormatException();
		}
	}


	public Set<BoardCell> getAdjList(int i, int j) {
		return grid[i][j].getAdjList();
	}

	public void findAllTargets(BoardCell startCell, int numSteps) {
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

	public void setGuess(String g) {
		guess = g;
	}

	public void setResult(String r) {
		guess = r;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public ArrayList<Player> getPlayers(){
		return players;
	}

	public ArrayList<Card> getCards(){
		return cards;
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

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		xSize = getWidth()/30;
		ySize = getHeight()/30;

		ArrayList<BoardCell> labelList = new ArrayList<>();
		ArrayList<BoardCell> doorList = new ArrayList<>();

		//paints the board
		for (BoardCell[] cellList:grid) {
			for(BoardCell cell:cellList) {
				cell.paintCell(g);
				if(cell.isDoorway()) {
					doorList.add(cell);
				}
				if(cell.isLabel()) {
					labelList.add(cell);
				}
			}
		}

		//if a target is a room center get that room and draw all cells part of that room
		if (currPlayer == 0) {
			Set<BoardCell> tempTargets = new HashSet<BoardCell>();

			for (BoardCell i:targets) {
				tempTargets.add(i);
			}
			tempTargets.forEach(target -> {
				if (target.isRoomCenter()) {
					for(BoardCell[] cells:grid) {
						for(BoardCell cell:cells) {
							if(target.getRoom() == cell.getRoom()) {
								targets.add(cell);
							}
						}
					}
				}
			});
			
			for (BoardCell target:targets) {
				target.paintTarget(g);
			}

			//			for (BoardCell target:targets) {
			//				if (target.isRoomCenter()) {
			//					for(BoardCell[] cells:grid) {
			//						for(BoardCell cell:cells) {
			//							if(target.getRoom() == cell.getRoom()) {
			//								targets.add(cell);
			//							}
			//						}
			//					}
			//				}
			//				target.paintTarget(g);
			//			}
		}

		//DRAW ALL LABELS
		for (BoardCell cell:labelList) {
			cell.paintLabel(g);
		}

		//DRAW ALL DOORS
		for (BoardCell cell:doorList) {
			cell.paintDoor(g);
		}

		//DRAW ALL PLAYERS
		for (Player p:players) {
			p.paintPlayer(g);
			if(grid[p.row][p.col].isRoom()) {
				grid[p.row][p.col].getRoom().addPlayerInRoom();
			}
		}
		for (Room r:rooms.values()) {
			r.setPlayerInRoom(0);
		}
	}

	public void next() {
		if(!finished) {
			repaint();
			return;
		}

		nextPlayer();

		Random rand = new Random();
		roll = rand.nextInt(8)+1;
		calcTargets(grid[players.get(currPlayer).row][players.get(currPlayer).col], roll);

		//set the finished flag to false to prevent
		finished = false;

		frame.updatePanels(theInstance);

		//if its  a computer
		if(players.get(currPlayer) instanceof ComputerPlayer) {
			BoardCell cell = ((ComputerPlayer) players.get(currPlayer)).selectTarget(targets);
			movePlayer(players.get(currPlayer), cell);
			int tempR = players.get(currPlayer).row;
			int tempC = players.get(currPlayer).col;
			if(grid[tempR][tempC].isRoom()) {
				((ComputerPlayer) players.get(currPlayer)).createSuggestion(grid[tempR][tempC].getRoom());
			}
		}
		repaint();
	}

	public void nextPlayer() {
		if (firstTurn) {
			firstTurn = false;
			return;
		}

		if(players.get(currPlayer) == players.getLast()) {
			currPlayer = 0;
		} else {
			currPlayer++;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		//On a mouse click if its a target, move a player
		for (BoardCell cell:targets) {
			if ((cell.col*xSize<x && x<(cell.col+1)*xSize)&&(cell.row*ySize<y && y<(cell.row+1)*ySize)) {
				movePlayer(players.getFirst(), cell);
				break;
			}
		}
		revalidate();
		repaint();
	}

	private void movePlayer(Player player, BoardCell cell) {
		if (cell.isRoom()) {	
			cell.setOccupied(false);
			cell = cell.getRoom().getCenterCell();
			player.col = cell.col;
			player.row = cell.row;

			targets.clear();
			finished = true;

			revalidate();
			repaint();

			player.createSuggestion(cell.getRoom());

			//you guess a player, find that player and add them to the room	
			String personName = MakeSuggestion.getPerson().getName();
			int i = -1;
			for(Player p:players) {
				i++;
				if (p.getName().equals(personName)) {
					break;
				}
			}
			players.get(i).col = player.col;
			players.get(i).row = player.row;
			players.get(i).paintPlayer(getGraphics());


			if (MakeSuggestion.returnCard != null) {
				player.addSeenCard(MakeSuggestion.returnCard);

				guess = MakeSuggestion.getPerson()+", "
						+MakeSuggestion.getRoom()+", "
						+MakeSuggestion.getWeapon();
				result = MakeSuggestion.returnCard.getName();

				frame.getControlPanel().setGuess(guess);
				frame.getControlPanel().setGuessResult(result);

				frame.updatePanels(getInstance());
				return;
			}
			return;
		}

		grid[player.row][player.col].setOccupied(false);
		player.col = cell.col;
		player.row = cell.row;
		cell.setOccupied(true);

		targets.clear();
		finished = true;

		revalidate();
		repaint();

		return;
	}

	public static void main(String[] args) {

		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "room_names.txt");
		board.initialize();

		frame.setSize(997, 860);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Clue");
		frame.setVisible(true);


		WelcomeScreen welcome = new WelcomeScreen();
		welcome.setSize(200, 100);

	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
