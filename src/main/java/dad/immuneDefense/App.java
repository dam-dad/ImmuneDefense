package dad.immuneDefense;

import dad.immuneDefense.mainMenu.MainMenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	private MainMenuController controller;
	
	public void start(Stage primaryStage) throws Exception {
		
		controller=new MainMenuController();
		
		Scene scene=new Scene(controller.getView(),720,600);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Immune Defense");
	
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		
			launch(args);
		
	}

}
