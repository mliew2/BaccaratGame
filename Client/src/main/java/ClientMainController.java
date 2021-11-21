import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ClientMainController implements Initializable {

	@FXML
	Button playButton, exitButton;

	@FXML
	TextField betAmountText, currentWinningsText;

	@FXML
	ChoiceBox<String> betChoice;

	@FXML
	Label playerTotal, bankerTotal;

	@FXML
	ImageView playerCard1, playerCard2, playerCard3, bankerCard1, bankerCard2, bankerCard3;

	@FXML
	ListView<String> clientList;

	private PauseTransition initialPause, playerDrawPause, bankerDrawPause, resultPause;

	static final int imageWidth = 100;
	static final int imageHeight = 145;
	private HashMap<String, Image> deckImages;

	private String ip;
	private int port;
	private Client client;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		deckImages = new HashMap<String, Image>();
		String suites[] = {"Diamonds", "Clubs", "Hearts", "Spades"};

		for(int i = 0; i < 4; i++) {
			for(int j = 1; j < 14; j++) {
				String key = suites[i] + j;
				deckImages.put(key, new Image("Deck/" + suites[i] + j + ".jpg"));
			}
		}

		playerCard1.setFitWidth(imageWidth);
		playerCard1.setFitHeight(imageHeight);
		playerCard1.setPreserveRatio(true);

		playerCard2.setFitWidth(imageWidth);
		playerCard2.setFitHeight(imageHeight);
		playerCard2.setPreserveRatio(true);

		playerCard3.setFitWidth(imageWidth);
		playerCard3.setFitHeight(imageHeight);
		playerCard3.setPreserveRatio(true);

		bankerCard1.setFitWidth(imageWidth);
		bankerCard1.setFitHeight(imageHeight);
		bankerCard1.setPreserveRatio(true);

		bankerCard2.setFitWidth(imageWidth);
		bankerCard2.setFitHeight(imageHeight);
		bankerCard2.setPreserveRatio(true);

		bankerCard3.setFitWidth(imageWidth);
		bankerCard3.setFitHeight(imageHeight);
		bankerCard3.setPreserveRatio(true);

		betChoice.getItems().add("Banker");
		betChoice.getItems().add("Player");
		betChoice.getItems().add("Draw");
		betChoice.setValue("Banker");

		initialPause = new PauseTransition(Duration.seconds(3));
		playerDrawPause = new PauseTransition(Duration.seconds(3));
		bankerDrawPause = new PauseTransition(Duration.seconds(3));
		resultPause = new PauseTransition(Duration.seconds(3));
	}

	public void playButtonMethod(ActionEvent e) {
		playButton.setDisable(true);
		String betAmount = betAmountText.getText();
		String whoToBet = betChoice.getValue();

		//reset cards if playing new game
		playerCard1.setImage(null);
		playerCard2.setImage(null);
		playerCard3.setImage(null);

		bankerCard1.setImage(null);
		bankerCard2.setImage(null);
		bankerCard3.setImage(null);

		currentWinningsText.setText("");

		try {
			double bet = Double.parseDouble(betAmount);
			BaccaratInfo info = new BaccaratInfo(bet, whoToBet);
			setText("New game");
			client.send(info);
		} catch (Exception ex) {
			playButton.setDisable(false);
			setText("Error! Invalid bet amount.");
		}
	}

	public void exitButtonMethod() {
		Platform.exit();
		System.exit(0);
	}

	public void startClient() {
		try {
			client = new Client(data->{
				Platform.runLater(()->{
					if(data instanceof BaccaratInfo) {
						displayGame((BaccaratInfo) data);
					} else {
						setText(data.toString());
					}
				});
			}, ip, port);
			client.start();
		} catch (Exception e) {
			setText("Error");
		}
	}

	public void setText(String text) {
		clientList.getItems().add(text);
	}

	public void displayGame(BaccaratInfo info) {
		displayInitialHand(info);

		initialPause.setOnFinished(e->displayNaturalWin(info));
		playerDrawPause.setOnFinished(e->displayPlayerDraw(info));
		bankerDrawPause.setOnFinished(e->displayBankerDraw(info));
		resultPause.setOnFinished(e->displayResult(info));

		initialPause.play();
	}

	public void displayInitialHand(BaccaratInfo info) {
		playerCard1.setImage(deckImages.get(info.getPlayerHand().get(0).getKey() + info.getPlayerHand().get(0).getValue()));
		playerCard2.setImage(deckImages.get(info.getPlayerHand().get(1).getKey() + info.getPlayerHand().get(1).getValue()));

		bankerCard1.setImage(deckImages.get(info.getBankerHand().get(0).getKey() + info.getBankerHand().get(0).getValue()));
		bankerCard2.setImage(deckImages.get(info.getBankerHand().get(1).getKey() + info.getBankerHand().get(1).getValue()));

		playerTotal.setText(info.getPlayerInitialTotal() + "");
		bankerTotal.setText(info.getBankerInitialTotal() + "");
	}

	public void displayNaturalWin(BaccaratInfo info) {
		if(info.isNaturalWin()) {
			setText("There was a natural win");
			displayResult(info);
		} else if(!info.isNaturalWin()) {
			setText("There was no natural win");
			playerDrawPause.play();
		}
	}

	public void displayPlayerDraw(BaccaratInfo info) {
		if(info.isPlayerDraw()) {
			playerCard3.setImage(deckImages.get(info.getPlayerHand().get(2).getKey() + info.getPlayerHand().get(2).getValue()));
			playerTotal.setText(info.getPlayerTotal() + "");
			setText("Player got another card");
		} else {
			setText("Player did not get another card");
		}

		bankerDrawPause.play();
	}

	public void displayBankerDraw(BaccaratInfo info) {
		if(info.isBankerDraw()) {
			bankerCard3.setImage(deckImages.get(info.getBankerHand().get(2).getKey() + info.getBankerHand().get(2).getValue()));
			bankerTotal.setText(info.getBankerTotal() + "");
			setText("Banker got another card");
		} else {
			setText("Banker did not get another card");
		}
		resultPause.play();
	}

	public void displayResult(BaccaratInfo info ) {
		playButton.setDisable(false);

		String winner = info.getWinner();
		String temp = ""; 

		if(winner.equals("Draw")) {
			temp += winner + "\n";
		} else {
			temp += winner + " won\n";
		}

		temp += "You bet on " + info.getWhoToBet() + "\n";

		if(info.getWinner().equals(info.getWhoToBet())) {
			temp += "You won!\n";
		} else {
			temp += "You lost\n";
		}

		setText(temp);
		currentWinningsText.setText(info.getWinnings() + "");
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
