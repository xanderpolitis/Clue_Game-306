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

	@Override
	public Solution createSuggestion(Room room) {
		MakeSuggestion s = new MakeSuggestion(room);
		return new Solution(s.getPerson().getName(), s.getWeapon().getName(), room.getName());
	}

	@Override
	public void makeMove() {
		System.out.println("Make your move: [1] Move, [2] Make a suggestion, [3] End turn");
		// Capture and process player input for moves
	}

}
