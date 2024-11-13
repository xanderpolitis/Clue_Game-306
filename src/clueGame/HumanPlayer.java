package clueGame;

import java.awt.Color;

import java.util.Scanner;

public class HumanPlayer extends Player {

	String name;
	Color color;
	int row;
	int col;
	
	public HumanPlayer(String name, Color color, int row, int col) {
		super(name, color, row, col);
	}

	private Scanner scanner = new Scanner(System.in);

	public Solution createSuggestion(Room room) {
		System.out.println("Enter your suggestion for person, weapon, and room (current room is " + room.getName() + ")");
		System.out.print("Person: ");
		String person = scanner.nextLine();
		System.out.print("Weapon: ");
		String weapon = scanner.nextLine();

		return new Solution(person, weapon, room.getName());
	}

	@Override
	public void makeMove() {
		System.out.println("Make your move: [1] Move, [2] Make a suggestion, [3] End turn");
		// Capture and process player input for moves
	}

}
