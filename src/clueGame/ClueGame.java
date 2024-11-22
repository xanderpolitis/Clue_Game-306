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
import java.awt.Rectangle;
import java.util.Set;

public class ClueGame extends JFrame {
	
	private BoardGUI boardPanel = new BoardGUI();
	private ClueCardGUI cardPanel = new ClueCardGUI();
	private ClueControlGUI controlPanel = new ClueControlGUI();
	Board board = Board.getInstance();
	
	public ClueGame() {
		
		add(board, BorderLayout.CENTER);
		board.setPreferredSize(new Dimension(400, 400));
		add(cardPanel, BorderLayout.EAST);
		cardPanel.setPreferredSize(new Dimension(130, 700));
		add(controlPanel, BorderLayout.SOUTH);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue");
		setSize(993, 860);
		setVisible(true);
		this.pack();
	}
	
	public void updatePanels(Board board){
		controlPanel.updatePanel(board);
		cardPanel.updatePanel(board);
		Board.getInstance().repaint();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "room_names.txt");
		board.initialize();
		//TEST CODE
//		Board.getInstance().getPlayers().getFirst().addCard(new Card("A room" , Card.CardType.ROOM));
//		Board.getInstance().getPlayers().getFirst().addCard(new Card("A weapon" , Card.CardType.WEAPON));
//		Board.getInstance().getPlayers().getFirst().addCard(new Card("A person" , Card.CardType.PERSON));
//		Board.getInstance().getPlayers().getFirst().seenCards.add(new Card("A person" , Card.CardType.PERSON));
//		Board.getInstance().getPlayers().getFirst().seenCards.add(new Card("A room" , Card.CardType.ROOM));
//		Board.getInstance().getPlayers().getFirst().seenCards.add(new Card("A weapon" , Card.CardType.WEAPON));
		// Create the JPanel and add it to the JFrame
		
		//DO NOT TOUCH
		ClueGame frame = new ClueGame(); //delete yellow later
		WelcomeScreen welcome = new WelcomeScreen(); // delete yellow things later
		
		
	}
}
