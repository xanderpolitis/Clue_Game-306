package clueGame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Set;

public class WelcomeScreen extends JOptionPane {
	
	String playerName = Board.getInstance().getPlayers().getFirst().getName();
	
	JLabel label = new JLabel("You are a "+playerName+". \n Can you find the solution before the computer players?");
	
	public WelcomeScreen() {
		
		
//		add(label);
		showMessageDialog(label, "You are a "+playerName+". "
		+ "\n Can you find the solution before the computer players?");
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "room_names.txt");
		board.initialize();
		
		//DO NOT TOUCH

//		JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setTitle("ClueControlGUI");
//		frame.setSize(750, 180);	
		// Create the JPanel and add it to the JFrame
		WelcomeScreen gui = new WelcomeScreen();
//		frame.add(gui, BorderLayout.CENTER);
//		// Now let's view it
//		frame.setVisible(true);
//		// Create the JPanel and add it to the JFrame
//		
//
//		// Now let's view it
//		gui.setVisible(true);
	}
}
