import java.util.ArrayList;

public class BaccaratGameLogic {

	//evaluate who won the game and return it as a String
	//hand1 must be player's hand and hand2 must be banker's hand
	public static String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
		if(handTotal(hand1) > handTotal(hand2))
			return "Player";
		else if(handTotal(hand1) < handTotal(hand2))
			return "Banker";
		else
			return "Draw";
	}

	//return the total of the hand passed in as an int
	//if total is more than 10, modulo 10 to get the last digit
	public static int handTotal(ArrayList<Card> hand) {
		int total = 0;
		for(Card temp: hand) {
			if (temp.getValue() < 10) {
				total += temp.getValue();
			} else {
				total += 0;
			}

			if(total >= 10)
				total %= 10;
		}
		return total; 
	}

	//evaluate if banker should draw an extra card according to baccarat rules
	//return true if yes, false if no
	public static boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
		int total = handTotal(hand);

		if (total < 3) {
			return true;
		} else if (total < 6 && playerCard == null) {
			return true;
		} else if (total == 3) {
			if(playerCard.getValue() == 8)
				return false;
			else
				return true;
		} else if (total == 4) {
			if(playerCard.getValue() > 1 && playerCard.getValue() < 8)
				return true;
			else 
				return false;
		} else if (total == 5) {
			if(playerCard.getValue() > 3 && playerCard.getValue() < 8)
				return true;
			else 
				return false;
		} else if (total == 6 && playerCard != null) {
			if(playerCard.getValue() > 5 && playerCard.getValue() < 8)
				return true;
			else 
				return false;
		}
		
		return false;
	}

	//evaluate if player should draw extra card
	//return true if player hand total is less than 6 else return false
	public static boolean evaluatePlayerDraw(ArrayList<Card> hand) {
		if(handTotal(hand) < 6)
			return true;
		return false;
	}
}
