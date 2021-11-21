import java.io.Serializable;
import java.util.ArrayList;

import javafx.util.Pair;

public class BaccaratInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private ArrayList<Pair<String, Integer>> playerHand, bankerHand;
	private int playerInitialTotal, bankerInitialTotal, playerTotal, bankerTotal;
	private String whoToBet, winner;
	private double initialBet, winnings;
	private boolean naturalWin, playerWin, playerDraw, bankerDraw;

	BaccaratInfo(double betAmount, String bettingOn) {
		setInitialBet(betAmount);
		setWhoToBet(bettingOn);
	}

	public String getWhoToBet() {
		return whoToBet;
	}

	public void setWhoToBet(String whoToBet) {
		this.whoToBet = whoToBet;
	}

	public double getInitialBet() {
		return initialBet;
	}

	public void setInitialBet(double initialBet) {
		this.initialBet = initialBet;
	}

	public int getPlayerInitialTotal() {
		return playerInitialTotal;
	}

	public void setPlayerInitialTotal(int playerInitialTotal) {
		this.playerInitialTotal = playerInitialTotal;
	}

	public int getBankerInitialTotal() {
		return bankerInitialTotal;
	}

	public void setBankerInitialTotal(int bankerInitialTotal) {
		this.bankerInitialTotal = bankerInitialTotal;
	}

	public int getPlayerTotal() {
		return playerTotal;
	}

	public void setPlayerTotal(int playerTotal) {
		this.playerTotal = playerTotal;
	}

	public int getBankerTotal() {
		return bankerTotal;
	}

	public void setBankerTotal(int bankerTotal) {
		this.bankerTotal = bankerTotal;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public double getWinnings() {
		return winnings;
	}

	public void setWinnings(double winnings) {
		this.winnings = winnings;
	}

	public boolean isNaturalWin() {
		return naturalWin;
	}

	public void setNaturalWin(boolean isNaturalWin) {
		this.naturalWin = isNaturalWin;
	}

	public boolean isPlayerWin() {
		return playerWin;
	}

	public void setPlayerWin(boolean playerWin) {
		this.playerWin = playerWin;
	}

	public boolean isPlayerDraw() {
		return playerDraw;
	}

	public void setPlayerDraw(boolean playerDraw) {
		this.playerDraw = playerDraw;
	}

	public boolean isBankerDraw() {
		return bankerDraw;
	}

	public void setBankerDraw(boolean bankerDraw) {
		this.bankerDraw = bankerDraw;
	}

	public ArrayList<Pair<String, Integer>> getPlayerHand() {
		return playerHand;
	}

	public ArrayList<Pair<String, Integer>> getBankerHand() {
		return bankerHand;
	}

	public void setPlayerHand(ArrayList<Card> playerInitialHand) {
		ArrayList<Pair<String, Integer>> temp = new ArrayList<Pair<String, Integer>>();
		for(Card e: playerInitialHand) {
			temp.add(new Pair<String, Integer>(e.getSuite(), e.getValue()));
		}
		this.playerHand = temp;
	}

	public void setBankerHand(ArrayList<Card> bankerInitialHand) {
		ArrayList<Pair<String, Integer>> temp = new ArrayList<Pair<String, Integer>>();
		for(Card e: bankerInitialHand) {
			temp.add(new Pair<String, Integer>(e.getSuite(), e.getValue()));
		}
		this.bankerHand = temp;
	}

	public void setPlayerExtraCard(Card playerExtraCard) {
		playerHand.add(new Pair<String, Integer>(playerExtraCard.getSuite(), playerExtraCard.getValue()));
	}

	public void setBankerExtraCard(Card bankerExtraCard) {
		bankerHand.add(new Pair<String, Integer>(bankerExtraCard.getSuite(), bankerExtraCard.getValue()));
	}
}
