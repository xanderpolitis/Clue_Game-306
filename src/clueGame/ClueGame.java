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
}
