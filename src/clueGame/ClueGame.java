package clueGame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Set;

public class ClueGame extends JFrame {
	
	public BoardGUI boardPanel = new BoardGUI();
	public ClueCardGUI cardPanel = new ClueCardGUI();
	public ClueControlGUI controlPanel = new ClueControlGUI();
	//cardpannel.setPreferedSize(x, y)
	
	
	public ClueGame() {
		add(boardPanel, BorderLayout.CENTER);
		add(cardPanel, BorderLayout.EAST);
		add(controlPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue");
		setSize(960, 900);
		setVisible(true);
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "room_names.txt");
		board.initialize();
		//TEST CODE
		Board.getInstance().getPlayers().add(new HumanPlayer( "Mr. Bad guy", Color.GREEN, 17, 07));
		Board.getInstance().getPlayers().getFirst().addCard(new Card("A room" , Card.CardType.ROOM));
		Board.getInstance().getPlayers().getFirst().addCard(new Card("A weapon" , Card.CardType.WEAPON));
		Board.getInstance().getPlayers().getFirst().addCard(new Card("A person" , Card.CardType.PERSON));
		Board.getInstance().getPlayers().getFirst().seenCards.add(new Card("A person" , Card.CardType.PERSON));
		Board.getInstance().getPlayers().getFirst().seenCards.add(new Card("A room" , Card.CardType.ROOM));
		Board.getInstance().getPlayers().getFirst().seenCards.add(new Card("A weapon" , Card.CardType.WEAPON));
		// Create the JPanel and add it to the JFrame
		
		//DO NOT TOUCH
		ClueGame frame = new ClueGame();

	}
}
