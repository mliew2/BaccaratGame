import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BaccaratGameLogicTest {
	static ArrayList<Card> player1 , player2, player3, player4, banker1, banker2, banker3, banker4, banker5, banker6;
	static String suites[] = {"Diamonds", "Clubs", "Hearts", "Spades"};
	static String whoToBet[] = {"Player", "Banker", "Draw"};
	
	@BeforeAll
	static void init() {
		player1 = new ArrayList<Card>();
		player2 = new ArrayList<Card>();
		player3 = new ArrayList<Card>();
		player4 = new ArrayList<Card>();
		banker1 = new ArrayList<Card>();
		banker2 = new ArrayList<Card>();
		banker3 = new ArrayList<Card>();
		banker4 = new ArrayList<Card>();
		banker5 = new ArrayList<Card>();
		banker6 = new ArrayList<Card>();
		
		player1.add(new Card(suites[0], 4));
		player1.add(new Card(suites[2], 4));
		//player1 total 8
		
		player2.add(new Card(suites[1], 9));
		player2.add(new Card(suites[2], 2));
		//player2 total 1
		
		player3.add(new Card(suites[3], 3));
		player3.add(new Card(suites[1], 3));
		//player3 total 6
		
		player4.add(new Card(suites[3], 4));
		player4.add(new Card(suites[3], 4));
		player4.add(new Card(suites[0], 9));
		//player4 total 7
		
		banker1.add(new Card(suites[2], 1));
		banker1.add(new Card(suites[0], 10));
		//banker1 total 1
		
		banker2.add(new Card(suites[1], 12));
		banker2.add(new Card(suites[2], 3));
		//banker2 total 3
		
		banker3.add(new Card(suites[0], 3));
		banker3.add(new Card(suites[3], 1));
		//banker3 total 4
		
		banker4.add(new Card(suites[1], 5));
		banker4.add(new Card(suites[2], 12));
		//banker4 total 5
		
		banker5.add(new Card(suites[1], 4));
		banker5.add(new Card(suites[2], 2));
		//banker5 total 6
		
		banker6.add(new Card(suites[2], 9));
		banker6.add(new Card(suites[2], 8));
		//banker6 total 7
	}
	
	@Test
	void handTotalTest1() {
		assertEquals(8, BaccaratGameLogic.handTotal(player1), "Hand Total test 1");
	}
	
	@Test
	void handTotalTest2() {
		assertEquals(1, BaccaratGameLogic.handTotal(player2), "Hand Total test 2");
	}
	
	@Test
	void handTotalTest3() {
		assertEquals(6, BaccaratGameLogic.handTotal(player3), "Hand Total test 3");
	}
	
	@Test
	void handTotalTest4() {
		assertEquals(7, BaccaratGameLogic.handTotal(player4), "Hand Total test 4");
	}
	
	@Test
	void handTotalTest5() {
		assertEquals(1, BaccaratGameLogic.handTotal(banker1), "Hand Total test 5");
	}
	
	@Test
	void handTotalTest6() {
		assertEquals(3, BaccaratGameLogic.handTotal(banker2), "Hand Total test 6");
	}
	
	@Test
	void handTotalTest7() {
		assertEquals(4, BaccaratGameLogic.handTotal(banker3), "Hand Total test 7");
	}
	
	@Test
	void handTotalTest8() {
		assertEquals(5, BaccaratGameLogic.handTotal(banker4), "Hand Total test 8");
	}
	
	@Test
	void handTotalTest9() {
		assertEquals(6, BaccaratGameLogic.handTotal(banker5), "Hand Total test 9");
	}
	
	@Test
	void handTotalTest10() {
		assertEquals(7, BaccaratGameLogic.handTotal(banker6), "Hand Total test 10");
	}
	
	@Test
	void playerDrawTest1() {
		assertFalse(BaccaratGameLogic.evaluatePlayerDraw(player1), "Evaluate Player Draw test 1");
	}
	
	@Test
	void playerDrawTest2() {
		assertTrue(BaccaratGameLogic.evaluatePlayerDraw(player2), "Evaluate Player Draw test 2");
	}
	
	@Test
	void playerDrawTest3() {
		assertFalse(BaccaratGameLogic.evaluatePlayerDraw(player3), "Evaluate Player Draw test 3");
	}
	
	@Test
	void bankerDrawTest1() {
		boolean pass = true;
		for(int i = 1; i < 14; i++) {
			Card card = new Card(suites[0], i);
			if(!BaccaratGameLogic.evaluateBankerDraw(banker1, card)) {
				pass = false;
			}
		}
		assertTrue(pass, "Evaluate Player Draw test 1");
	}
	
	@Test
	void bankerDrawTest2() {
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(banker1, null), "Evaluate Player Draw test 2");
	}
	
	@Test
	void bankerDrawTest3() {
		boolean pass = true;
		for(int i = 1; i < 14; i++) {
			if(i == 8)
				continue;
			Card card = new Card(suites[0], i);
			if(!BaccaratGameLogic.evaluateBankerDraw(banker2, card)) {
				pass = false;
			}
		}
		assertTrue(pass, "Evaluate Player Draw test 3");
	}
	
	@Test
	void bankerDrawTest4() {
		Card card = new Card(suites[0], 8);
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(banker2, card), "Evaluate Player Draw test 4");
	}
	
	@Test
	void bankerDrawTest5() {
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(banker2, null), "Evaluate Player Draw test 5");
	}
	
	@Test
	void bankerDrawTest6() {
		boolean pass = true;
		for(int i = 2; i < 8; i++) {
			Card card = new Card(suites[0], i);
			if(!BaccaratGameLogic.evaluateBankerDraw(banker3, card)) {
				pass = false;
			}
		}
		assertTrue(pass, "Evaluate Player Draw test 6");
	}
	
	@Test
	void bankerDrawTest7() {
		boolean pass = true;
		for(int i = 8; i < 14; i++) {
			Card card = new Card(suites[0], i);
			if(BaccaratGameLogic.evaluateBankerDraw(banker3, card)) {
				pass = false;
			}
		}
		assertTrue(pass, "Evaluate Player Draw test 7");
	}
	
	@Test
	void bankerDrawTest8() {
		Card card = new Card(suites[0], 1);
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(banker3, card), "Evaluate Player Draw test 8");
	}
	
	@Test
	void bankerDrawTest9() {
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(banker3, null), "Evaluate Player Draw test 9");
	}
	
	@Test
	void bankerDrawTest10() {
		boolean pass = true;
		for(int i = 4; i < 8; i++) {
			Card card = new Card(suites[0], i);
			if(!BaccaratGameLogic.evaluateBankerDraw(banker4, card)) {
				pass = false;
			}
		}
		assertTrue(pass, "Evaluate Player Draw test 10");
	}
	
	@Test
	void bankerDrawTest11() {
		boolean pass = true;
		for(int i = 8; i < 14; i++) {
			Card card = new Card(suites[0], i);
			if(BaccaratGameLogic.evaluateBankerDraw(banker4, card)) {
				pass = false;
			}
		}
		assertTrue(pass, "Evaluate Player Draw test 11");
	}
	
	@Test
	void bankerDrawTest12() {
		boolean pass = true;
		for(int i = 1; i < 3; i++) {
			Card card = new Card(suites[0], i);
			if(BaccaratGameLogic.evaluateBankerDraw(banker4, card)) {
				pass = false;
			}
		}
		assertTrue(pass, "Evaluate Player Draw test 12");
	}
	
	@Test
	void bankerDrawTest13() {
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(banker4, null), "Evaluate Player Draw test 13");
	}
	
	@Test
	void bankerDrawTest14() {
		boolean pass = true;
		for(int i = 6; i < 8; i++) {
			Card card = new Card(suites[0], i);
			if(!BaccaratGameLogic.evaluateBankerDraw(banker5, card)) {
				pass = false;
			}
		}
		assertTrue(pass, "Evaluate Player Draw test 14");
	}
	
	@Test
	void bankerDrawTest15() {
		boolean pass = true;
		for(int i = 1; i < 6; i++) {
			Card card = new Card(suites[0], i);
			if(BaccaratGameLogic.evaluateBankerDraw(banker5, card)) {
				pass = false;
			}
		}
		assertTrue(pass, "Evaluate Player Draw test 15");
	}
	
	@Test
	void bankerDrawTest16() {
		boolean pass = true;
		for(int i = 8; i < 14; i++) {
			Card card = new Card(suites[0], i);
			if(BaccaratGameLogic.evaluateBankerDraw(banker5, card)) {
				pass = false;
			}
		}
		assertTrue(pass, "Evaluate Player Draw test 16");
	}
	
	@Test
	void bankerDrawTest17() {
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(banker5, null), "Evaluate Player Draw test 17");
	}
	
	@Test
	void bankerDrawTest18() {
		boolean pass = true;
		for(int i = 1; i < 14; i++) {
			Card card = new Card(suites[0], i);
			if(BaccaratGameLogic.evaluateBankerDraw(banker6, card)) {
				pass = false;
			}
		}
		assertTrue(pass, "Evaluate Player Draw test 18");
	}
	
	@Test
	void bankerDrawTest19() {
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(banker6, null), "Evaluate Player Draw test 19");
	}

	@Test
	void whoWonTest1() {
		assertEquals(whoToBet[0], BaccaratGameLogic.whoWon(player1, banker1), "Who Won test 1");
	}
	
	@Test
	void whoWonTest2() {
		assertEquals(whoToBet[0], BaccaratGameLogic.whoWon(player1, banker2), "Who Won test 2");
	}
	
	@Test
	void whoWonTest3() {
		assertEquals(whoToBet[0], BaccaratGameLogic.whoWon(player1, banker3), "Who Won test 3");
	}
	
	@Test
	void whoWonTest4() {
		assertEquals(whoToBet[0], BaccaratGameLogic.whoWon(player1, banker4), "Who Won test 4");
	}
	
	@Test
	void whoWonTest5() {
		assertEquals(whoToBet[0], BaccaratGameLogic.whoWon(player1, banker5), "Who Won test 5");
	}
	
	@Test
	void whoWonTest6() {
		assertEquals(whoToBet[0], BaccaratGameLogic.whoWon(player1, banker6), "Who Won test 6");
	}
	
	@Test
	void whoWonTest7() {
		assertEquals(whoToBet[2], BaccaratGameLogic.whoWon(player2, banker1), "Who Won test 7");
	}
	
	@Test
	void whoWonTest8() {
		assertEquals(whoToBet[1], BaccaratGameLogic.whoWon(player2, banker2), "Who Won test 8");
	}
	
	@Test
	void whoWonTest9() {
		assertEquals(whoToBet[1], BaccaratGameLogic.whoWon(player2, banker3), "Who Won test 9");
	}
	
	@Test
	void whoWonTest10() {
		assertEquals(whoToBet[1], BaccaratGameLogic.whoWon(player2, banker4), "Who Won test 10");
	}
	
	@Test
	void whoWonTest11() {
		assertEquals(whoToBet[1], BaccaratGameLogic.whoWon(player2, banker5), "Who Won test 11");
	}
	
	@Test
	void whoWonTest12() {
		assertEquals(whoToBet[1], BaccaratGameLogic.whoWon(player2, banker6), "Who Won test 12");
	}
	
	@Test
	void whoWonTest13() {
		assertEquals(whoToBet[0], BaccaratGameLogic.whoWon(player3, banker1), "Who Won test 13");
	}
	
	@Test
	void whoWonTest14() {
		assertEquals(whoToBet[0], BaccaratGameLogic.whoWon(player3, banker2), "Who Won test 14");
	}
	
	@Test
	void whoWonTest15() {
		assertEquals(whoToBet[0], BaccaratGameLogic.whoWon(player3, banker3), "Who Won test 15");
	}
	
	@Test
	void whoWonTest16() {
		assertEquals(whoToBet[0], BaccaratGameLogic.whoWon(player3, banker4), "Who Won test 16");
	}
	
	@Test
	void whoWonTest17() {
		assertEquals(whoToBet[2], BaccaratGameLogic.whoWon(player3, banker5), "Who Won test 17");
	}
	
	@Test
	void whoWonTest18() {
		assertEquals(whoToBet[1], BaccaratGameLogic.whoWon(player3, banker6), "Who Won test 18");
	}
	
	@Test
	void whoWonTest19() {
		assertEquals(whoToBet[0], BaccaratGameLogic.whoWon(player4, banker1), "Who Won test 19");
	}
	
	@Test
	void whoWonTest20() {
		assertEquals(whoToBet[0], BaccaratGameLogic.whoWon(player4, banker2), "Who Won test 20");
	}
	
	@Test
	void whoWonTest21() {
		assertEquals(whoToBet[0], BaccaratGameLogic.whoWon(player4, banker3), "Who Won test 21");
	}
	
	@Test
	void whoWonTest22() {
		assertEquals(whoToBet[0], BaccaratGameLogic.whoWon(player4, banker4), "Who Won test 22");
	}
	
	@Test
	void whoWonTest23() {
		assertEquals(whoToBet[0], BaccaratGameLogic.whoWon(player4, banker5), "Who Won test 23");
	}
	
	@Test
	void whoWonTest24() {
		assertEquals(whoToBet[2], BaccaratGameLogic.whoWon(player4, banker6), "Who Won test 24");
	}
}
