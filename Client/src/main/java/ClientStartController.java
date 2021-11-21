import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ClientStartController implements Initializable{

	@FXML
	VBox root;

	@FXML
	TextField ipAddress, portNumber;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void connectButtonMethod (ActionEvent e) throws IOException {
		String ip, port;
		ip = ipAddress.getText();
		port = portNumber.getText();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ClientMainScene.fxml"));
		Parent root2 = loader.load();
		ClientMainController controller = loader.getController();

		try {
			int portNumber = Integer.parseInt(port);
			controller.setPort(portNumber);
			controller.setIp(ip);
			controller.startClient();
		} catch(Exception ex) {
			controller.setText("Error! Invalid port number: " + port);
		}

		root2.getStylesheets().add("/CSS/clientMainStyle.css");
		root.getScene().setRoot(root2);
	}
}
