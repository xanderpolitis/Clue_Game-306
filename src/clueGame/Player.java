package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Player {
	protected List<Card> hand = new ArrayList<>();
	protected List<Card> seenCards = new ArrayList<>();
	
	protected String name;
	//protected String color;
	Color color;
	protected int row;
	protected int col;

	public Player(String name, Color color, int row, int col) {
		this.name = name;
		this.color = color; 
		this.row = row; 
		this.col = col;
	}

	public String getName() {
		return name;
	}

	public void addCard(Card card) {
		hand.add(card);
		card.setColor(color);
	}

	public void addSeenCard(Card card) {
		seenCards.add(card);
	}

	public Card disproveSuggestion(Solution suggestion) {
		List<Card> matchingCards = new ArrayList<>();
		for (Card card : hand) {
			if (card.getName().equals(suggestion.person) ||
					card.getName().equals(suggestion.weapon) ||
					card.getName().equals(suggestion.room)) {
				matchingCards.add(card);
			}
		}
		if (matchingCards.isEmpty()) {
			return null;
		} else {
			Random rand = new Random();
			return matchingCards.get(rand.nextInt(matchingCards.size()));
		}
	}
	public List<Card> getHand(){
		return hand;
	}

	public Color getColor()
	{
		return this.color;
	}
	public void setColor(Color c) {
		this.color = c;
	}

	public abstract void makeMove();  // To be implemented by subclasses

	public void paintComponent(Graphics g) {
		g.setColor(this.color);
		g.drawOval(col*BoardGUI.xSize, row*BoardGUI.ySize, BoardGUI.xSize, BoardGUI.ySize);
		g.fillOval(col*BoardGUI.xSize, row*BoardGUI.ySize, BoardGUI.xSize, BoardGUI.ySize);
	}
	
	// Static method to read players from file
    public static List<Player> loadPlayersFromFile(String filename) {
        List<Player> players = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    String color = parts[1];
                    int row = Integer.parseInt(parts[2]);
                    int col = Integer.parseInt(parts[3]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return players;
    }
}



