package clueGame;

public class Room {
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	int playersInRoom;
	
	public Room(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BoardCell getCenterCell() {
		return centerCell;
	}
	
	public void setCenterCell(BoardCell centerCell) {
		this.centerCell = centerCell;
	}
	
	public void addPlayerInRoom() {
		playersInRoom++;
	}
	
	public void subtractPlayerInRoom() {
		playersInRoom--;
	}
	
	public int getPlayersInRoom(){
		return playersInRoom;
	}
	
	public void setPlayerInRoom(int s) {
		playersInRoom = s;
	}
	
	public BoardCell getLabelCell() {
		return labelCell;
	}
	
	public void setLabelCell(BoardCell labelCell) {
		this.labelCell = labelCell;
	}
}



