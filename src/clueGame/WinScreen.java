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

public class WinScreen extends JFrame {
	
	public WinScreen() {
		ImageIcon icon = new ImageIcon("data/"+"winner.gif");
		System.out.println("You WIN!!!!");
		JLabel label = new JLabel();
		label.setIcon(icon);
		this.add(label);

		setSize(997, 860);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue");
		setVisible(true);
	}
}
