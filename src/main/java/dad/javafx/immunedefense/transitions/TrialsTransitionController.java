package dad.javafx.immunedefense.transitions;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TrialsTransitionController extends Application {

	private MenuTransition view;
	
	@Override
	public void start(Stage theStage) throws Exception {
		view = new MenuTransition();
		Scene scene = new Scene(view.getRoot());
		theStage.setTitle("Probar transición");
		theStage.setScene(scene);
		theStage.setResizable(false);
		theStage.show();
		view.playTransitions();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
