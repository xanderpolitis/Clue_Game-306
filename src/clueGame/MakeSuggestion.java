package clueGame;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MakeSuggestion extends JDialog {

	
	JLabel roomLabel = new JLabel("Current Room");
	JLabel personLabel = new JLabel("Person");
	JLabel weaponLabel = new JLabel("Weapon");
	
	JButton cancel = new JButton("Cancel");
	JButton submit = new JButton("Submit");
	
	ArrayList<Card> cards = Board.getInstance().getCards();
	ArrayList<Card> people = new ArrayList<>();
	ArrayList<Card> rooms = new ArrayList<>();
	ArrayList<Card> weapons = new ArrayList<>();
	
	Card room;
	Card person;
	Card weapon;
	
	public MakeSuggestion(Room curRoom) {
		//set up cards lists
		for(Card card: cards) {
			if(card.getType()==Card.CardType.PERSON) {
				people.add(card);
			}
			if(card.getType()==Card.CardType.WEAPON) {
				weapons.add(card);
			}
			if(card.getType()==Card.CardType.ROOM) {
				rooms.add(card);
			}
		}
		//This line is broken
		JComboBox pDM = new JComboBox<>(people.toArray());
		JComboBox wDM = new JComboBox<>(weapons.toArray());

		JTextField currentRoom = new JTextField(curRoom.getName());
		currentRoom.setEditable(false);
		
		setLayout(new GridLayout(4,2));
		
		submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                person = (Card) pDM.getSelectedItem();
                weapon = (Card) wDM.getSelectedItem();
                //TODO
                
                dispose();
            }
        });
		
		cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
		
		
		add(roomLabel);
		add(currentRoom);
		add(personLabel);
		add(pDM);
		add(weaponLabel);
		add(wDM);
		add(cancel);
		add(submit);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle("Make A Suggestion");
		this.setSize(300, 300);	
		this.setVisible(true);
		this.setModal(true);
	}
	
	public Card getPerson() {
		return person;
	}
	
	public Card getWeapon() {
		return weapon;
	}
	
	public Card getRoom() {
		return room;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MakeSuggestion  s = new MakeSuggestion(new Room("a room"));
	}

}
