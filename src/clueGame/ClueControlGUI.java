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

public class ClueControlGUI extends JPanel{

	int roll = 1;
	String rollStr = Integer.toString(roll);
	
	String guess  = "I have no guess!";
	String result = "So you have nothing?";
	Player player = new ComputerPlayer( "Col. Mustard", Color.ORANGE, 0, 0);
	Color backround = player.getColor();
	
	//Make all update-able Text Fields or any other ones GLOBAL
	
	JTextField guessResult = new JTextField(result);
	JTextField playerText = new JTextField(this.player.getName());
	JTextField curGuess = new JTextField(guess);
	JTextField rollNum = new JTextField(rollStr);
	

	public ClueControlGUI() {
		setLayout(new GridLayout(2,0));
		add(createNorth(), BorderLayout.NORTH);
		add(createSouth(), BorderLayout.SOUTH);
	}	

	public void setTurn(Player player, int roll) {
		this.roll = roll;
		this.player = player;
		this.backround = player.getColor();
		this.rollStr = Integer.toString(roll);
		
		playerText.setText(player.getName());
		rollNum.setText(rollStr);
		playerText.setBackground(backround);
	}
	
	public void setGuess(String guess) {
		this.guess = guess;
		
		curGuess.setText(guess);
	}
	
	public void setGuessResult(String result) {
		this.result = result;
		
		guessResult.setText(result);
	}

	public JPanel createNorth() {
		JPanel northPanel = new JPanel(new GridLayout(1,4));

		JButton AccusationButton = new JButton("Make Accusation");
		JButton NextButton = new JButton("Next");

		northPanel.add(createNorth1());
		northPanel.add(createNorth2());
		northPanel.add(AccusationButton);
		northPanel.add(NextButton);

		return northPanel;
	}	

	public JPanel createNorth1() {
		JPanel northPanel1 = new JPanel(new GridLayout(2,0));

		JLabel label = new JLabel("Whose turn?");

		playerText.setEditable(false);
		playerText.setBackground(backround);

		northPanel1.add(label, BorderLayout.NORTH);
		northPanel1.add(playerText, BorderLayout.SOUTH);

		return northPanel1;
	}


	public JPanel createNorth2() {
		JPanel northPanel2 = new JPanel();

		JLabel label = new JLabel("Roll:");

		rollNum.setEditable(false);

		northPanel2.add(label, BorderLayout.NORTH);
		northPanel2.add(rollNum, BorderLayout.SOUTH);

		return northPanel2;
	}

	public JPanel createSouth() {
		JPanel southPanel = new JPanel(new GridLayout(0,2));

		southPanel.add(createSouth1());
		southPanel.add(createSouth2());

		return southPanel;
	}

	public JPanel createSouth1() {
		JPanel southPanel1 = new JPanel();
		
		curGuess.setEditable(false);

		southPanel1.add(curGuess, BorderLayout.SOUTH);
		southPanel1.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));

		return southPanel1;
	}

	public JPanel createSouth2() {
		JPanel southPanel2 = new JPanel();
		
		guessResult.setEditable(false);
		
		southPanel2.add(guessResult, BorderLayout.SOUTH);
		southPanel2.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));

		return southPanel2;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("ClueControlGUI");
		frame.setSize(750, 180);	
		// Create the JPanel and add it to the JFrame
		ClueControlGUI gui = new ClueControlGUI();
		frame.add(gui, BorderLayout.CENTER);
		// Now let's view it
		frame.setVisible(true);

		//change these later and move this to main
		gui.setTurn(new ComputerPlayer( "Mr. Bad guy", Color.GREEN, 0, 0), 5);
		gui.setGuess( "I have no guess!");
		gui.setGuessResult( "So you have nothing?");
	}

}
