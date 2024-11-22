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

public class ClueCardGUI extends JPanel{

	private JPanel peoplePanel = new JPanel(new GridLayout(0,1));
	private JPanel roomsPanel = new JPanel(new GridLayout(0,1));
	private JPanel weaponsPanel = new JPanel(new GridLayout(0,1));
	
	HumanPlayer player = new HumanPlayer( "Col. Mustard", Color.ORANGE, 0, 0);
	
	public ClueCardGUI() {
		setLayout(new GridLayout(3,1));
		add(createPeople(Board.getInstance()));
		add(createRooms(Board.getInstance()));
		add(createWeapons(Board.getInstance()));
		setBorder(new TitledBorder (new EtchedBorder(), "Known Cards"));
	}	

	public JPanel createPeople(Board board) {
		JLabel hand = new JLabel("In Hand:", 10);
		peoplePanel.add(hand);
		if(board.getPlayers().getFirst().getHand().isEmpty()) {
			JLabel none = new JLabel("None", 10);
			none.setBackground(new Color(40, 40, 40));
			peoplePanel.add(none);
		}else {
			boolean empty = true;
			for(Card card:board.getPlayers().getFirst().getHand()) {
				if(card.getType() == Card.CardType.PERSON) {
					empty = false;
					JTextField person = new JTextField(card.getName());
					person.setBackground(card.getColor());
					person.setEditable(false);

					peoplePanel.add(person);
				}
			}
			if(empty) {
				JLabel none = new JLabel("None", 10);
				none.setBackground(new Color(40, 40, 40));
				peoplePanel.add(none);
			}
		}

		JLabel seen = new JLabel("Seen:");
		peoplePanel.add(seen);
		if(board.getPlayers().getFirst().seenCards.isEmpty()) {
			JLabel none = new JLabel("None", 10);
			none.setBackground(new Color(40, 40, 40));
			peoplePanel.add(none);
		}else {
			boolean empty = true;
			for(Card card:board.getPlayers().getFirst().seenCards) {
				if(card.getType() == Card.CardType.PERSON) {
					empty = false;
					JTextField weapon = new JTextField(card.getName());
					weapon.setBackground(card.getColor());
					weapon.setEditable(false);

					peoplePanel.add(weapon);
				}
			}
			if(empty) {
				JLabel none = new JLabel("None", 10);
				none.setBackground(new Color(40, 40, 40));
				peoplePanel.add(none);
			}
		}
		peoplePanel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		return peoplePanel;
	}	

	public JPanel createRooms(Board board) {
		JLabel hand = new JLabel("In Hand:");
		roomsPanel.add(hand);
		if(board.getPlayers().getFirst().getHand().isEmpty()) {
			JLabel none = new JLabel("None", 10);
			none.setBackground(new Color(40, 40, 40));
			roomsPanel.add(none);
		}else {
			boolean empty = true;
			for(Card card:board.getPlayers().getFirst().getHand()) {
				if(card.getType() == Card.CardType.ROOM) {
					empty = false;
					JTextField room = new JTextField(card.getName());
					room.setBackground(card.getColor());
					room.setEditable(false);

					roomsPanel.add(room);
				}
			}
			if(empty) {
				JLabel none = new JLabel("None", 10);
				none.setBackground(new Color(40, 40, 40));
				roomsPanel.add(none);
			}
		}
		JLabel seen = new JLabel("Seen:");
		roomsPanel.add(seen);
		if(board.getPlayers().getFirst().seenCards.isEmpty()) {
			JLabel none = new JLabel("None", 10);
			none.setBackground(new Color(40, 40, 40));
			roomsPanel.add(none);
		}else {
			boolean empty = true;
			for(Card card:board.getPlayers().getFirst().seenCards) {
				if(card.getType() == Card.CardType.ROOM) {
					empty = false;
					JTextField room = new JTextField(card.getName());
					room.setBackground(card.getColor());
					room.setEditable(false);

					roomsPanel.add(room);
				}
			}
			if(empty) {
				JLabel none = new JLabel("None", 10);
				none.setBackground(new Color(40, 40, 40));
				roomsPanel.add(none);
			}
		}
		roomsPanel.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		return roomsPanel;
	}

	public JPanel createWeapons(Board board) {
		JLabel hand = new JLabel("In Hand:");
		weaponsPanel.add(hand);
		if(board.getPlayers().getFirst().getHand().isEmpty()) {
			JLabel none = new JLabel("None", 10);
			none.setBackground(new Color(40, 40, 40));
			weaponsPanel.add(none);
		}else {
			boolean empty = true;
			for(Card card:board.getPlayers().getFirst().getHand()) {
				if(card.getType() == Card.CardType.WEAPON) {
					empty = false;
					JTextField weapon = new JTextField(card.getName(), 10);
					weapon.setBackground(card.getColor());
					weapon.setEditable(false);

					weaponsPanel.add(weapon);
				}
			}
			if(empty) {
				JLabel none = new JLabel("None");
				none.setBackground(new Color(40, 40, 40));
				weaponsPanel.add(none);
			}
		}

		JLabel seen = new JLabel("Seen:");
		weaponsPanel.add(seen);
		if(board.getPlayers().getFirst().seenCards.isEmpty()) {
			JLabel none = new JLabel("None");
			none.setBackground(new Color(40, 40, 40));
			weaponsPanel.add(none);
		}else {
			boolean empty = true;
			for(Card card:board.getPlayers().getFirst().seenCards) {
				if(card.getType() == Card.CardType.WEAPON) {
					empty = false;
					JTextField weapon = new JTextField(card.getName(), 10);
					weapon.setBackground(card.getColor());
					weapon.setEditable(false);

					weaponsPanel.add(weapon);
				}
			}
			if(empty) {
				JLabel none = new JLabel("None", 10);
				none.setBackground(new Color(40, 40, 40));
				none.setBorder(new EtchedBorder());
				weaponsPanel.add(none);
			}
		}
		weaponsPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		return weaponsPanel;
	}

	public void updatePanel(Board board) {
	
		peoplePanel.removeAll();
		peoplePanel = createPeople(board);
		add(peoplePanel);
		
		roomsPanel.removeAll();
		add(createRooms(board));
		
		weaponsPanel.removeAll();
		add(createWeapons(board));
		
		this.invalidate();
		this.validate();
		this.repaint();
		//this.revalidate();
		
	}

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("ClueCardGUI");
		frame.setSize(180, 750);	

		//TEST CODE
		Board.getInstance().getPlayers().add(new HumanPlayer( "Mr. Bad guy", Color.GREEN, 0, 0));
		Board.getInstance().getPlayers().getFirst().addCard(new Card("A room" , Card.CardType.ROOM));
		Board.getInstance().getPlayers().getFirst().addCard(new Card("A weapon" , Card.CardType.WEAPON));
		Board.getInstance().getPlayers().getFirst().addCard(new Card("A person" , Card.CardType.PERSON));
		// Create the JPanel and add it to the JFrame
		ClueCardGUI gui = new ClueCardGUI();
		frame.add(gui, BorderLayout.CENTER);
		// Now let's view it
		frame.setVisible(true);
		
		Board.getInstance().getPlayers().getFirst().seenCards.add(new Card("A person" , Card.CardType.PERSON));
		Board.getInstance().getPlayers().getFirst().seenCards.add(new Card("A room" , Card.CardType.ROOM));
		Board.getInstance().getPlayers().getFirst().seenCards.add(new Card("A weapon" , Card.CardType.WEAPON));
		
		gui.updatePanel(Board.getInstance());
	}
}
