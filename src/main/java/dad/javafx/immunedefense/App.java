package dad.javafx.immunedefense;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import dad.javafx.immunedefense.mainmenu.MainMenuController;
import dad.javafx.immunedefense.mainmenu.OptionController;
import dad.javafx.immunedefense.map.GameController;
import dad.javafx.immunedefense.transitions.MenuTransition;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {
	private MainMenuController controller;
	
	private OptionController optionController;
	
	private MenuTransition menuTransition;
	
	private FadeTransition fadeOut;
	
	private Scene scene;
	
	private Scene menuScene;
	
	private Scene transitionScene;
	
	//mapa
	private Scene mapScene;
	
	private Scene optionScene;
	
	private GameController gameController;
	

	
	public void start(Stage primaryStage) throws Exception {
		controller=new MainMenuController();
		
		menuTransition = new MenuTransition();
		
		fadeOut = new FadeTransition();
		
		controller.getNuevaPartidaB().setOnAction(e -> {
			try {
				gameController = new GameController();
				mapScene = new Scene(gameController.getView());
				primaryStage.setScene(mapScene);
				gameController.getBotonReiniciar().setOnAction((Event) -> {
					try {
						primaryStage.setScene(scene);
						controller.changeFromLevelToMenuMusic();
					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				controller.changeFromMenuToLevelMusic();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		/**
		 * Creando iteracciones entre opciones y menú
		 */
		controller.getAjustesB().setOnAction(e -> {
			try {
				optionController = new OptionController();
				optionScene = new Scene(optionController.getView());
				primaryStage.setScene(optionScene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		/*
		optionController.getBackOption().setOnAction(e -> {
			try {
				controller = new MainMenuController();
				scene = new Scene(controller.getView());
				primaryStage.setScene(scene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});*/
		
		//Scene después de FadeOut
		scene=new Scene(controller.getView(),800,600);
		scene.getRoot().setStyle("-fx-background-color: #000000;");
		scene.getStylesheets().addAll(this.getClass().getResource("/Estilos/MainMenu.css").toExternalForm());
		//scene.getRoot().setId("view_transition");
		
		transitionScene = new Scene(menuTransition.getRoot(),800,600);
		
		primaryStage.setScene(transitionScene);
		primaryStage.setTitle("Immune Defense");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image ("/Images/Logo.png"));
		primaryStage.show();
		menuTransition.playTransitions();
		menuTransition.getTransitionFadeOut().setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				scene.getRoot().setStyle("-fx-background-color: #000000;");
				primaryStage.setScene(scene);
				transition();
			}
		});
		
	}

	private void transition() {
		
		fadeOut.setAutoReverse(true);
		fadeOut.setCycleCount(1);
		fadeOut.setDelay(Duration.seconds(1));
		fadeOut.setDuration(Duration.seconds(5));
		fadeOut.setFromValue(0.0);
		fadeOut.setToValue(1.0);
		fadeOut.setRate(1.0);
		fadeOut.setNode(scene.getRoot());
		fadeOut.setInterpolator(Interpolator.LINEAR);
		fadeOut.play();
		scene.getStylesheets().addAll(this.getClass().getResource("/Estilos/MainMenu.css").toExternalForm());
	}

	public static void main(String[] args) {
			launch(args);
	}

}
