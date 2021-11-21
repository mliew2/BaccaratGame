import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class BaccaratGameTest {
	static int betAmount = 5000;
	static String betOn[] = {"Player", "Banker", "Draw"};
	static String suites[] = {"Diamonds", "Clubs", "Hearts", "Spades"};

	@Test
	void constructorTest1() { 
		BaccaratGame game = new BaccaratGame(betAmount, betOn[0]);
		assertEquals(betAmount, game.currentBet, "Constructor test 1");
	}

	@Test
	void constructorTest2() { 
		BaccaratGame game = new BaccaratGame(betAmount, betOn[0]);
		assertEquals(betOn[0], game.whoToBet, "Constructor test 2");
	}

	@Test
	void evaluateWinningsTest1() { 
		BaccaratGame game = new BaccaratGame(betAmount, betOn[0]);
		game.playerHand = new ArrayList<Card>();
		game.bankerHand = new ArrayList<Card>();
		game.playerHand.add(new Card(suites[0], 5));
		game.playerHand.add(new Card(suites[1], 4));
		game.bankerHand.add(new Card(suites[2], 3));
		game.bankerHand.add(new Card(suites[3], 3));
		//player total 9, banker total 6
		//player bet on banker
		assertEquals(betAmount, game.evaluateWinnings(), "Evaluate Winning test 1");
	}

	@Test
	void evaluateWinningsTest2() { 
		BaccaratGame game = new BaccaratGame(betAmount, betOn[0]);
		game.playerHand = new ArrayList<Card>();
		game.bankerHand = new ArrayList<Card>();
		game.playerHand.add(new Card(suites[0], 1));
		game.playerHand.add(new Card(suites[1], 2));
		game.bankerHand.add(new Card(suites[2], 4));
		game.bankerHand.add(new Card(suites[3], 5));
		//player total 3, banker total 9
		//player bet on banker
		double winnings = betAmount * -1;
		assertEquals(winnings, game.evaluateWinnings(), "Evaluate Winning test 2");
	}

	@Test
	void evaluateWinningsTest3() { 
		BaccaratGame game = new BaccaratGame(betAmount, betOn[1]);
		game.playerHand = new ArrayList<Card>();
		game.bankerHand = new ArrayList<Card>();
		game.playerHand.add(new Card(suites[0], 1));
		game.playerHand.add(new Card(suites[1], 2));
		game.bankerHand.add(new Card(suites[2], 4));
		game.bankerHand.add(new Card(suites[3], 5));
		//player total 3, banker total 9
		//player bet on banker
		assertEquals(betAmount, game.evaluateWinnings(), "Evaluate Winning test 3");
	}

	@Test
	void evaluateWinningsTest4() { 
		BaccaratGame game = new BaccaratGame(betAmount, betOn[1]);
		game.playerHand = new ArrayList<Card>();
		game.bankerHand = new ArrayList<Card>();
		game.playerHand.add(new Card(suites[0], 5));
		game.playerHand.add(new Card(suites[1], 4));
		game.bankerHand.add(new Card(suites[2], 3));
		game.bankerHand.add(new Card(suites[3], 3));
		//player total 9, banker total 6
		//player bet on banker
		double winnings = betAmount * -1;
		assertEquals(winnings, game.evaluateWinnings(), "Evaluate Winning test 4");
	}

	@Test
	void evaluateWinningsTest5() { 
		BaccaratGame game = new BaccaratGame(betAmount, betOn[2]);
		game.playerHand = new ArrayList<Card>();
		game.bankerHand = new ArrayList<Card>();
		game.playerHand.add(new Card(suites[0], 3));
		game.playerHand.add(new Card(suites[1], 3));
		game.bankerHand.add(new Card(suites[2], 2));
		game.bankerHand.add(new Card(suites[3], 4));
		//player total 6, banker total 6
		//player bet on draw
		double winnings = betAmount * 8;
		assertEquals(winnings, game.evaluateWinnings(), "Evaluate Winning test 5");
	}

	@Test
	void evaluateWinningsTest6() {
		BaccaratGame game = new BaccaratGame(betAmount, betOn[2]);
		game.playerHand = new ArrayList<Card>();
		game.bankerHand = new ArrayList<Card>();
		game.playerHand.add(new Card(suites[0], 5));
		game.playerHand.add(new Card(suites[1], 4));
		game.bankerHand.add(new Card(suites[2], 3));
		game.bankerHand.add(new Card(suites[3], 3));
		//player total 9, banker total 6
		//player bet on draw
		double winnings = betAmount * -1;
		assertEquals(winnings, game.evaluateWinnings(), "Evaluate Winning test 6");
	}

	@Test
	void playGameTest1() {
		BaccaratGame game = new BaccaratGame(betAmount, betOn[2]);
		BaccaratInfo gameInfo = game.playGame();
		boolean pass = true;
		Card card2;
		int i = 0;
		for(Card card1: game.playerHand) {	
			card2 = new Card(gameInfo.getPlayerHand().get(i).getKey(), gameInfo.getPlayerHand().get(i).getValue());
			if(!card1.getSuite().equals(card2.getSuite()) || card1.getValue() != card2.getValue()) {
				pass = false;
			}
			i++;
		}
		assertTrue(pass, "Play game test 1");
	}

	@Test
	void playGameTest2() {
		BaccaratGame game = new BaccaratGame(betAmount, betOn[2]);
		BaccaratInfo gameInfo = game.playGame();
		boolean pass = true;
		Card card2;
		int i = 0;
		for(Card card1: game.bankerHand) {
			card2 = new Card(gameInfo.getBankerHand().get(i).getKey(), gameInfo.getBankerHand().get(i).getValue());
			if(!card1.getSuite().equals(card2.getSuite()) || card1.getValue() != card2.getValue()) {
				pass = false;
			}
			i++;
		}
		assertTrue(pass, "Play game test 2");
	}

	@Test
	void playGameTest3() {
		BaccaratGame game = new BaccaratGame(betAmount, betOn[2]);
		BaccaratInfo gameInfo = game.playGame();
		assertEquals(game.currentBet, gameInfo.getInitialBet(), "Play game test 3");
	}

	@Test
	void playGameTest4() {
		BaccaratGame game = new BaccaratGame(betAmount, betOn[2]);
		BaccaratInfo gameInfo = game.playGame();
		assertEquals(game.whoToBet, gameInfo.getWhoToBet(), "Play game test 3");
	}

	@Test
	void playGameTest5() {
		BaccaratGame game = new BaccaratGame(betAmount, betOn[2]);
		BaccaratInfo gameInfo = game.playGame();
		assertEquals(game.totalWinnings, gameInfo.getWinnings(), "Play game test 5");
	}

	@Test
	void playGameTest6() {
		BaccaratGame game = new BaccaratGame(betAmount, betOn[2]);
		BaccaratInfo gameInfo = game.playGame();
		assertEquals(game.winner, gameInfo.getWinner(), "Play game test 6");
	}
}
