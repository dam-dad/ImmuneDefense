package dad.javafx.immunedefense.map;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MapApp extends Application {

	private GameController controller;

	public void start(Stage theStage) throws IOException {

		controller = new GameController(1);

		Scene theScene = new Scene(controller.getView());

		theStage.setTitle("Canvas Example");
		theStage.setResizable(false);
		theStage.setScene(theScene);
		theStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}