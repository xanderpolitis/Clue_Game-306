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
import java.awt.GridLayout;
import java.util.Set;

public class ClueGUI extends JFrame {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("ClueControlGUI");
		frame.setSize(180, 750);	

		//TEST CODE
		Board.players.add(new HumanPlayer( "Mr. Bad guy", Color.GREEN, 0, 0));
		Board.players.getFirst().addCard(new Card("A room" , Card.CardType.ROOM));
		Board.players.getFirst().addCard(new Card("A weapon" , Card.CardType.WEAPON));
		Board.players.getFirst().addCard(new Card("A person" , Card.CardType.PERSON));

		// Create the JPanel and add it to the JFrame
		ClueCardGUI CardPanel = new ClueCardGUI();
		frame.add(CardPanel, BorderLayout.EAST);
		
		ClueControlGUI ControlPanel = new ClueControlGUI();
		frame.add(ControlPanel, BorderLayout.SOUTH);
		
		ClueBoardGUI BoardPanel = new ClueBoardGUI();
		frame.add(BoardPanel, BorderLayout.CENTER);
		// Now let's view it
		frame.setVisible(true);
	}
}
