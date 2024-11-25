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
	
	static ArrayList<Card> cards = Board.getInstance().getCards();
	ArrayList<Card> people = new ArrayList<>();
	ArrayList<Card> rooms = new ArrayList<>();
	ArrayList<Card> weapons = new ArrayList<>();
	
	static String room;
	static Card person;
	static Card weapon;
	
	static Card returnCard;
	
	public MakeSuggestion(Room curRoom) {
		//set up cards lists
		this.setModal(true);
		room = curRoom.getName();
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
		//THIS LINEE IS BAD
		JComboBox<Card> pDM = new JComboBox<Card>();
		JComboBox<Card> wDM = new JComboBox<Card>();
		
		for(Card card:people) {
			pDM.addItem(card);
		}
		for(Card card:weapons) {
			wDM.addItem(card);
		}

		JTextField currentRoom = new JTextField(curRoom.getName());
		currentRoom.setEditable(false);
		
		setLayout(new GridLayout(4,2));
		
		submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	//THIS LINE IS BAD
                person = (Card) pDM.getSelectedItem();
                weapon = (Card) wDM.getSelectedItem();
                //TODO
                Solution suggestion = new Solution(person.getName(), weapon.getName(), room);
                returnCard = Board.getInstance().handleSuggestion(suggestion, Board.getInstance().players.getFirst());
                
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
	
	public static Card getPerson() {
		return person;
	}
	
	public static Card getWeapon() {
		return weapon;
	}
	
	public static String getRoom() {
		return room;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		cards.add(new Card("a person", Card.CardType.PERSON));
		cards.add(new Card("a weapon", Card.CardType.WEAPON));
		cards.add(new Card("another person", Card.CardType.PERSON));
		cards.add(new Card("another weapon", Card.CardType.WEAPON));
		MakeSuggestion  s = new MakeSuggestion(new Room("a room"));
	}

}
