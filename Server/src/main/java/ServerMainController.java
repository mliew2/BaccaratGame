import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Pair;

public class ServerMainController implements Initializable{

	@FXML
	private TextField noOfClientsText;
	
	@FXML
	private ListView<String> serverList;
	
	private int port;
	private Server server;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		noOfClientsText.setText("0");
	}

	public void startServer() {
		server = new Server(data->{
			Platform.runLater(()->{
				if(data instanceof BaccaratInfo) {
					displayGameInfo((BaccaratInfo) data);
				} else if (data instanceof String){
					setText(data.toString());
				} else {
					setNoOfClients(data.toString());
				}
			});
		}, port);
	}

	public void offButtonMethod(ActionEvent e) throws IOException {
		Platform.exit();
		System.exit(0);
	}

	public void displayGameInfo(BaccaratInfo info) {
		String gameInfo = "Player Initial Hand: " + generateCardInfo(info.getPlayerHand().get(0)) + ", " + generateCardInfo(info.getPlayerHand().get(1)) + "\n";
		gameInfo += "Banker Initial Hand: " + generateCardInfo(info.getBankerHand().get(0)) + ", " + generateCardInfo(info.getBankerHand().get(1)) + "\n";

		if(info.isPlayerDraw()) {
			gameInfo += "Player Extra Card: " + generateCardInfo(info.getPlayerHand().get(2)) + "\n";
		}
		if(info.isBankerDraw()) {
			gameInfo += "Banker Extra Card: " + generateCardInfo(info.getBankerHand().get(2)) + "\n";
		}

		String winner = info.getWinner();
		if(winner.equals("Draw")) {
			gameInfo += winner + "\n";
		} else {
			gameInfo += winner + " won\n";
		}

		double winnings = info.getWinnings();
		if(winnings < 0) {
			gameInfo += "Client lost $" + (winnings * -1) + "\n";
		} else {
			gameInfo += "Client won $" + winnings + "\n";
		}

		setText(gameInfo);
	}

	public String generateCardInfo(Pair<String, Integer> card) {
		String value = "";
		switch(card.getValue()) {
		case 1: value = "Ace";
		break;
		case 11: value = "Jack";
		break;
		case 12: value = "Queen";
		break;
		case 13: value = "King";
		break;
		default: value = card.getValue() + "";
		}

		return value + " of " + card.getKey();
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setText(String text) {
		serverList.getItems().add(text);
	}
	
	public void setNoOfClients(String text) {
		noOfClientsText.setText(text);
	}
}
