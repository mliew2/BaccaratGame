import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class BaccaratDealerTest {

	static String suites[] = {"Diamonds", "Clubs", "Hearts", "Spades"};

	@Test
	void generateDeckTest() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		assertEquals(52, dealer.deck.size(), "Generate Deck test 1");
	}

	@Test
	void generateDeckTest2() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		boolean pass = true;
		for(int i = 0; i < 4; i++) {
			for(int j = 1; j < 14; j++) {
				int index = (i * 13) + j - 1;
				Card card = dealer.deck.get(index);
				if(!card.getSuite().equals(suites[i]) || card.getValue() != j) {
					pass = false;
				}
			}
		}
		assertTrue(pass, "Generate Deck test 2");
	}

	@Test
	void dealHandTest() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		dealer.dealHand();
		assertEquals(50, dealer.deck.size(), "Deal Hand test 1");
	}

	@Test
	void dealHandTest2() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		ArrayList<Card> temp = dealer.dealHand();
		boolean pass = true;
		int i = 1;
		for(Card e: temp) {
			if(!e.getSuite().equals(suites[0]) || e.getValue() != i) {
				pass = false;
			}
			i++;
		}
		assertTrue(pass, "Deal Hand test 2");
	}

	@Test
	void drawOneTest() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		dealer.dealHand();
		dealer.dealHand();
		dealer.drawOne();
		dealer.drawOne();
		assertEquals(46, dealer.deck.size(), "Draw One test 1");
	}

	@Test
	void drawOneTest2() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		dealer.dealHand();
		dealer.dealHand();
		dealer.drawOne();
		Card card = dealer.drawOne();
		boolean pass = true;
		if(!card.getSuite().equals(suites[0]) || card.getValue() != 6) {
			pass = false;
		}
		assertTrue(pass, "Draw One test 2");
	}

	@Test
	void shuffleDeckTest() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		dealer.shuffleDeck();
		assertEquals(52, dealer.deck.size(), "Shuffle Deck test 1");
	}

	@Test
	void shuffleDeckTest2() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		ArrayList<Card> originalDeck = dealer.deck;
		dealer.shuffleDeck();
		boolean pass = false;
		int count = 0;
		for(int i = 0; i < 52; i++) {
			Card card1 = dealer.deck.get(i);
			Card card2 = originalDeck.get(i);
			if(card1.getSuite().equals(card2.getSuite()) && card1.getValue() == card2.getValue()) {
				count++;
			}
		}
		if(count == 52) {
			pass = true;
		}
		assertFalse(pass, "Shuffle Deck test 2");
	}
}
