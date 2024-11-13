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

	JPanel peoplePanel = new JPanel(new GridLayout(0,1));
	JPanel roomsPanel = new JPanel(new GridLayout(0,1));
	JPanel weaponsPanel = new JPanel(new GridLayout(0,1));
	
	public ClueCardGUI() {
		setLayout(new GridLayout(3,1));
		add(createPeople());
		add(createRooms());
		add(createWeapons());
		setBorder(new TitledBorder (new EtchedBorder(), "Known Cards"));
	}	

	public JPanel createPeople() {
		JLabel hand = new JLabel("In Hand:");
		peoplePanel.add(hand);
		if(Board.players.getFirst().getHand().isEmpty()) {
			JLabel none = new JLabel("None");
			none.setBackground(new Color(40, 40, 40));
			peoplePanel.add(none);
		}else {
			boolean empty = true;
			for(Card card:Board.players.getFirst().getHand()) {
				if(card.getType() == CardType.PERSON) {
					empty = false;
					JTextField person = new JTextField(card.getCardName());
					person.setBackground(card.getColor());

					peoplePanel.add(person);
				}
			}
			if(empty) {
				JLabel none = new JLabel("None");
				none.setBackground(new Color(40, 40, 40));
				peoplePanel.add(none);
			}
		}

		JLabel seen = new JLabel("Seen:");
		peoplePanel.add(seen);
		if(Board.seenCards.isEmpty()) {
			JLabel none = new JLabel("None");
			none.setBackground(new Color(40, 40, 40));
			peoplePanel.add(none);
		}else {
			boolean empty = true;
			for(Card card:Board.seenCards) {
				if(card.getType() == CardType.PERSON) {
					empty = false;
					JTextField weapon = new JTextField(card.getCardName());
					weapon.setBackground(card.getColor());

					peoplePanel.add(weapon);
				}
			}
			if(empty) {
				JLabel none = new JLabel("None");
				none.setBackground(new Color(40, 40, 40));
				peoplePanel.add(none);
			}
		}
		peoplePanel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		return peoplePanel;
	}	

	public JPanel createRooms() {
		JLabel hand = new JLabel("In Hand:");
		roomsPanel.add(hand);
		if(Board.players.getFirst().getHand().isEmpty()) {
			JLabel none = new JLabel("None");
			none.setBackground(new Color(40, 40, 40));
			roomsPanel.add(none);
		}else {
			boolean empty = true;
			for(Card card:Board.players.getFirst().getHand()) {
				if(card.getType() == CardType.ROOM) {
					empty = false;
					JTextField room = new JTextField(card.getCardName());
					room.setBackground(card.getColor());

					roomsPanel.add(room);
				}
			}
			if(empty) {
				JLabel none = new JLabel("None");
				none.setBackground(new Color(40, 40, 40));
				roomsPanel.add(none);
			}
		}
		JLabel seen = new JLabel("Seen:");
		roomsPanel.add(seen);
		if(Board.seenCards.isEmpty()) {
			JLabel none = new JLabel("None");
			none.setBackground(new Color(40, 40, 40));
			roomsPanel.add(none);
		}else {
			boolean empty = true;
			for(Card card:Board.seenCards) {
				if(card.getType() == CardType.ROOM) {
					empty = false;
					JTextField room = new JTextField(card.getCardName());
					room.setBackground(card.getColor());

					roomsPanel.add(room);
				}
			}
			if(empty) {
				JLabel none = new JLabel("None");
				none.setBackground(new Color(40, 40, 40));
				roomsPanel.add(none);
			}
		}
		roomsPanel.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		return roomsPanel;
	}

	public JPanel createWeapons() {
		JLabel hand = new JLabel("In Hand:");
		weaponsPanel.add(hand);
		if(Board.players.getFirst().getHand().isEmpty()) {
			JLabel none = new JLabel("None");
			none.setBackground(new Color(40, 40, 40));
			weaponsPanel.add(none);
		}else {
			boolean empty = true;
			for(Card card:Board.players.getFirst().getHand()) {
				if(card.getType() == CardType.WEAPON) {
					empty = false;
					JTextField weapon = new JTextField(card.getCardName());
					weapon.setBackground(card.getColor());

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
		if(Board.seenCards.isEmpty()) {
			JLabel none = new JLabel("None");
			none.setBackground(new Color(40, 40, 40));
			weaponsPanel.add(none);
		}else {
			boolean empty = true;
			for(Card card:Board.seenCards) {
				if(card.getType() == CardType.WEAPON) {
					empty = false;
					JTextField weapon = new JTextField(card.getCardName());
					weapon.setBackground(card.getColor());

					weaponsPanel.add(weapon);
				}
			}
			if(empty) {
				JLabel none = new JLabel("None");
				none.setBackground(new Color(40, 40, 40));
				none.setBorder(new EtchedBorder());
				weaponsPanel.add(none);
			}
		}
		weaponsPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		return weaponsPanel;
	}

	public void updateCardPanels() {
	
		peoplePanel.removeAll();
		peoplePanel = createPeople();
		add(peoplePanel);
		
		roomsPanel.removeAll();
		add(createRooms());
		
		weaponsPanel.removeAll();
		add(createWeapons());
		
//		this.invalidate();
//		this.validate();
//		this.repaint();
		this.revalidate();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("ClueControlGUI");
		frame.setSize(180, 750);	

		//TEST CODE
		Board.players.add(new HumanPlayer( "Mr. Bad guy", Color.GREEN, 0, 0));
		Board.players.getFirst().updateHand(new Card("A room" , CardType.ROOM));
		Board.players.getFirst().updateHand(new Card("A weapon" , CardType.WEAPON));
		Board.players.getFirst().updateHand(new Card("A person" , CardType.PERSON));

		// Create the JPanel and add it to the JFrame
		ClueCardGUI gui = new ClueCardGUI();
		frame.add(gui, BorderLayout.CENTER);
		// Now let's view it
		frame.setVisible(true);
		
		Board.seenCards.add(new Card("A person" , CardType.PERSON));
		Board.seenCards.add(new Card("A room" , CardType.ROOM));
		Board.seenCards.add(new Card("A weapon" , CardType.WEAPON));
		
		gui.updateCardPanels();

	}
}
