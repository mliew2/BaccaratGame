import java.util.ArrayList;

public class BaccaratGame {
	ArrayList<Card> playerHand;
	ArrayList<Card> bankerHand;
	BaccaratDealer theDealer;
	double currentBet;
	String whoToBet;
	double totalWinnings;
	String winner;

	BaccaratGame(double betAmount, String bettingOn) {
		currentBet = betAmount;
		whoToBet = bettingOn;
	}

	public double evaluateWinnings() {
		winner = BaccaratGameLogic.whoWon(playerHand, bankerHand);
		if (whoToBet.equals(winner)) {
			if(whoToBet.equals("Draw"))
				totalWinnings = currentBet * 8;
			else
				totalWinnings = currentBet * 1;
		} else {
			totalWinnings = currentBet * -1;
		}
		return totalWinnings;
	}

	BaccaratInfo playGame() {
		BaccaratInfo game = new BaccaratInfo(currentBet, whoToBet);

		theDealer = new BaccaratDealer();
		theDealer.shuffleDeck();
		playerHand = theDealer.dealHand();
		bankerHand = theDealer.dealHand();

		game.setPlayerHand(playerHand);
		game.setBankerHand(bankerHand);

		int playerTotal = BaccaratGameLogic.handTotal(playerHand);
		int bankerTotal = BaccaratGameLogic.handTotal(bankerHand);
		game.setPlayerInitialTotal(playerTotal);
		game.setBankerInitialTotal(bankerTotal);

		if(playerTotal > 7 || bankerTotal > 7) {
			game.setNaturalWin(true);

		} else {
			Card playerExtraCard = null;

			if(BaccaratGameLogic.evaluatePlayerDraw(playerHand)) {
				game.setPlayerDraw(true);

				playerExtraCard = theDealer.drawOne();
				game.setPlayerExtraCard(playerExtraCard);

				playerHand.add(playerExtraCard);
				playerTotal = BaccaratGameLogic.handTotal(playerHand);
				game.setPlayerTotal(playerTotal);
			} else {
				game.setPlayerDraw(false);
			}

			if(BaccaratGameLogic.evaluateBankerDraw(bankerHand, playerExtraCard)) {
				game.setBankerDraw(true);

				Card bankerExtraCard = theDealer.drawOne();;
				game.setBankerExtraCard(bankerExtraCard);

				bankerHand.add(bankerExtraCard);
				bankerTotal = BaccaratGameLogic.handTotal(bankerHand);
				game.setBankerTotal(bankerTotal);
			} else {
				game.setBankerDraw(false);
			}
		}

		game.setWinnings(evaluateWinnings());
		game.setWinner(winner);

		if(totalWinnings > 0) {
			game.setPlayerWin(true);
		} else {
			game.setPlayerWin(false);
		}

		return game;
	}
}
