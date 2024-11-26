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

public class MakeAccusation extends JDialog {

	
	JLabel roomLabel = new JLabel("Room");
	JLabel personLabel = new JLabel("Person");
	JLabel weaponLabel = new JLabel("Weapon");
	
	JButton cancel = new JButton("Cancel");
	JButton submit = new JButton("Submit");
	
	static ArrayList<Card> cards = Board.getInstance().getCards();
	ArrayList<Card> people = new ArrayList<>();
	ArrayList<Card> rooms = new ArrayList<>();
	ArrayList<Card> weapons = new ArrayList<>();
	
	static Card room;
	static Card person;
	static Card weapon;
	
	static Card returnCard;
	
	public MakeAccusation() {
		//set up cards lists
		this.setModal(true);
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
		JComboBox<Card> rDM = new JComboBox<Card>();
		
		for(Card card:people) {
			pDM.addItem(card);
		}
		for(Card card:weapons) {
			wDM.addItem(card);
		}
		for(Card card:rooms) {
			rDM.addItem(card);
		}
		
		setLayout(new GridLayout(4,2));
		
		submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	//THIS LINE IS BAD
                person = (Card) pDM.getSelectedItem();
                weapon = (Card) wDM.getSelectedItem();
                room = (Card) rDM.getSelectedItem();
                //TODO
                Solution suggestion = new Solution(person.getName(), weapon.getName(), room.getName());
                Board.getInstance().handleAccusation(suggestion, Board.getInstance().players.getFirst());
                
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
		add(rDM);
		add(personLabel);
		add(pDM);
		add(weaponLabel);
		add(wDM);
		add(cancel);
		add(submit);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle("Make An Accusation");
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
	
	public static Card getRoom() {
		return room;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		cards.add(new Card("a person", Card.CardType.PERSON));
		cards.add(new Card("a weapon", Card.CardType.WEAPON));
		cards.add(new Card("another person", Card.CardType.PERSON));
		cards.add(new Card("another weapon", Card.CardType.WEAPON));
		cards.add(new Card("a room", Card.CardType.ROOM));
		cards.add(new Card("another room", Card.CardType.ROOM));
		
		MakeAccusation  s = new MakeAccusation();
	}

}
