package clueGame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
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

public class LoseScreen extends JFrame {

	public LoseScreen() {
		ImageIcon icon = new ImageIcon("data/"+"loser.gif");
		System.out.println("You LOSE!!!!");
		JLabel label = new JLabel();
		label.setIcon(icon);

		this.add(label);
		
		
		setSize(997, 860);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue");
		setVisible(true);

	}
}
