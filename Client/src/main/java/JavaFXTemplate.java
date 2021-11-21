import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXTemplate extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			primaryStage.setTitle("Client");

			Parent root = FXMLLoader.load(getClass().getResource("/FXML/ClientStartScene.fxml"));
			Scene scene = new Scene(root, 700, 750);

			primaryStage.setOnCloseRequest(e->{
				Platform.exit();
				System.exit(0);
			});

			root.getStylesheets().add("/CSS/clientStartStyle.css");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
