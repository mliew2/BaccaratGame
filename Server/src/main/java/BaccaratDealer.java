import java.util.ArrayList;
import java.util.Collections;

public class BaccaratDealer {

	ArrayList<Card> deck;
	
	//generate a new ordered deck
	public void generateDeck() {
		deck = new ArrayList<Card>();
		String suites[] = {"Diamonds", "Clubs", "Hearts", "Spades"};
		for(int i = 0; i < 4; i++) {
			for(int j = 1; j < 14; j++) {
				deck.add(new Card(suites[i], j));
			}
		}
	}

	//return ArrayList with first two elements of deck and remove them from deck
	public ArrayList<Card> dealHand() {
		ArrayList<Card> temp = new ArrayList<Card>();
		temp.add(deck.get(0));
		deck.remove(0);
		temp.add(deck.get(0));
		deck.remove(0);
		return temp;
	}

	//return first Card object from deck and remove from deck
	public Card drawOne() {
		Card temp = deck.get(0);
		deck.remove(0);
		return temp;
	}

	//shuffle the deck to randomize order of deck
	public void shuffleDeck() {
		generateDeck();
		Collections.shuffle(deck);
	}

	//return deck size in int
	public int deckSize() {
		return deck.size();
	}
}
