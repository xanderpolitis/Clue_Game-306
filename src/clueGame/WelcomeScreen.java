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
	
	public WelcomeScreen() {
		
		this.setBackground(Board.getInstance().getPlayers().getFirst().getColor());
		showMessageDialog(null, "You are a "+playerName+". "
		+ "\n Can you find the solution before the computer players?");
	}
}
