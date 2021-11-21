import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ServerStartController implements Initializable{

	@FXML
	Button startButton;

	@FXML
	private VBox root;

	@FXML
	private TextField portNumber;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void startButtonMethod(ActionEvent e) throws IOException {
		String port = portNumber.getText();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ServerMainScene.fxml"));
		Parent root2 = loader.load();
		ServerMainController controller = loader.getController();

		try {
			int portNumber = Integer.parseInt(port);
			controller.setPort(portNumber);
			controller.startServer();
		} catch(Exception ex) {
			controller.setText("Error! Invalid port number: " + port + ".");
		}

		root2.getStylesheets().add("/CSS/serverMainStyle.css");
		root.getScene().setRoot(root2);
	}
}
