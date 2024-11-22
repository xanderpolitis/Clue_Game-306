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
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Set;

public class BoardGUI extends JPanel{

	
	public static int xSize;
	public static int ySize;
	
	Graphics g = null;
	
	public BoardGUI(){
		setSize(500, 500);
		xSize = getWidth()/30;
		ySize = getHeight()/30;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		xSize = getWidth()/30;
		ySize = getHeight()/30;
		Board.getInstance().paintComponent(g);
	}
	
	
	
	public void updatePanel() {
		this.invalidate();
		this.validate();
		repaint();
	}
}
