package dad.javafx.immunedefense;

import java.io.IOException;

import dad.javafx.immunedefense.mainmenu.HowToPlayMenu;
import dad.javafx.immunedefense.mainmenu.MainMenuController;
import dad.javafx.immunedefense.mainmenu.OptionController;
import dad.javafx.immunedefense.map.GameController;
import dad.javafx.immunedefense.transitions.CreditsTransition;
import dad.javafx.immunedefense.transitions.MenuTransition;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {
	private MainMenuController controller;
	
	private OptionController optionController;
	
	private HowToPlayMenu howToController;

	private MenuTransition menuTransition;
	
	private CreditsTransition creditsTransition;

	private FadeTransition fadeOut;

	private Scene scene;

	private Scene transitionScene;
	// mapa
	private Scene mapScene;

	private Scene optionScene;
	
	private Scene howToScene;
	
	private GameController gameControllerEasy;
	
	private GameController gameControllerMedium;
	
	private GameController gameControllerHard;

	
	public void start(Stage primaryStage) throws Exception {
		controller = new MainMenuController();

		menuTransition = new MenuTransition();

		fadeOut = new FadeTransition();
		
		creditsTransition = new CreditsTransition();
		// nueva partida
		controller.getNuevaPartidaB().setOnAction(e -> {

			gameControllerEasy = new GameController(0);
			primaryStage.setScene(new Scene(gameControllerEasy.getView()));
			
			// volver al menu despues de game over
			setOnActionEasyLevel(primaryStage);
			
			if (controller.getSonidoB().isSelected()) {
				controller.changeFromMenuToLevelMusic();
				controller.getAudioLevels().stop();
			} else {
				controller.changeFromMenuToLevelMusic();
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

				volverMenuDesdeOpciones(primaryStage);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		controller.getHowPlayB().setOnAction(e ->{
			try {
				howToController= new HowToPlayMenu();
				howToScene= new Scene(howToController.getView());
				primaryStage.setScene(howToScene);
				
				tutorialBack(primaryStage);
			}catch(IOException er) {
				er.printStackTrace();
			}
		});
		
		creditsTransition.getVolverAlMenuButton().setOnAction(e -> {
			controller.getAudioCredits().stop();
			controller.getAudio().setFramePosition(0);
			controller.getAudio().start();
			primaryStage.setScene(scene);
		});

		// Scene después de FadeOut
		scene = new Scene(controller.getView(), 800, 600);
		scene.getRoot().setStyle("-fx-background-color: #000000;");
		scene.getStylesheets().addAll(this.getClass().getResource("/Estilos/MainMenu.css").toExternalForm());
		// scene.getRoot().setId("view_transition");

		transitionScene = new Scene(menuTransition.getRoot(), 800, 600);

		primaryStage.setScene(transitionScene);
		primaryStage.setTitle("Immune Defense");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("/Images/Logo.png"));
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
	
	private void setOnActionEasyLevel(Stage primaryStage) {
		gameControllerEasy.getBotonReintentar().setOnAction((Event) -> {
			Button[] buttons = cloneButtonsGameController(gameControllerEasy.getNivel());
			gameControllerEasy = new GameController(0);
			gameControllerEasy.setBotonReintentar(buttons[0]);
			gameControllerEasy.setBotonReintentar(buttons[1]);
			gameControllerEasy.setBotonReintentar(buttons[2]);
			gameControllerEasy.setBotonReintentar(buttons[3]);
			primaryStage.getScene().setRoot(gameControllerEasy.getView());
		});
		
		gameControllerEasy.getBotonReiniciar().setOnAction((Event) -> {
			volverMenuDesdeJuego(primaryStage);
		});

		// avanzar de nivel
		gameControllerEasy.getContinuarButton().setOnAction((Event) -> {
			gameControllerMedium = new GameController(1);
			primaryStage.setScene(new Scene(gameControllerMedium.getView()));
			setOnActionMediumLevel(primaryStage);
		});

		gameControllerEasy.getBotonReiniciarGanar().setOnAction((Event) -> {
			volverMenuDesdeJuego(primaryStage);
		});
	}
	
	private void setOnActionMediumLevel(Stage primaryStage) {
		gameControllerMedium.getBotonReintentar().setOnAction(((Event) -> {
			Button[] buttons = cloneButtonsGameController(gameControllerMedium.getNivel());
			gameControllerMedium = new GameController(1);
			gameControllerMedium.setBotonReintentar(buttons[0]);
			gameControllerMedium.setBotonReintentar(buttons[1]);
			gameControllerMedium.setBotonReintentar(buttons[2]);
			gameControllerMedium.setBotonReintentar(buttons[3]);
			primaryStage.getScene().setRoot(gameControllerMedium.getView());
		}));
		
		gameControllerMedium.getBotonReiniciar().setOnAction((Event) -> {
			volverMenuDesdeJuego(primaryStage);
		});

		// avanzar de nivel
		gameControllerMedium.getContinuarButton().setOnAction((Event) -> {
			gameControllerHard = new GameController(2);
			// nivel++;
			primaryStage.setScene(new Scene(gameControllerHard.getView()));
			
			setOnActionHardLevel(primaryStage);
		});

		gameControllerMedium.getBotonReiniciarGanar().setOnAction((Event) -> {
			volverMenuDesdeJuego(primaryStage);
		});
	}
	
	private void setOnActionHardLevel(Stage primaryStage) {
		gameControllerHard.getBotonReintentar().setOnAction(((Event) -> {
			Button[] buttons = cloneButtonsGameController(gameControllerHard.getNivel());
			gameControllerHard = new GameController(2);
			gameControllerHard.setBotonReintentar(buttons[0]);
			gameControllerHard.setBotonReintentar(buttons[1]);
			gameControllerHard.setButtonCreditos(buttons[2]);
			gameControllerHard.setBotonReintentar(buttons[3]);
			primaryStage.getScene().setRoot(gameControllerHard.getView());
		}));
		
		gameControllerHard.getBotonReiniciar().setOnAction((Event) -> {
			volverMenuDesdeJuego(primaryStage);
		});
			
		gameControllerHard.getButtonCreditos().setOnAction((Event) -> {
			primaryStage.setScene(new Scene(creditsTransition.getRoot()));
			creditsTransition.playTransitions();
			controller.changeFromLevelToCreditsMusic();
		});
		
		gameControllerHard.getBotonReiniciarGanar().setOnAction((Event) -> {
			volverMenuDesdeJuego(primaryStage);
		});
	}

	private Button[] cloneButtonsGameController(int nivel) {
		Button[] buttons = new Button[4];
		switch (nivel) {
		case (0):
			buttons[0] = gameControllerEasy.getBotonReintentar();
			buttons[1] = gameControllerEasy.getBotonReiniciar();
			buttons[2] = gameControllerEasy.getContinuarButton();
			buttons[3] = gameControllerEasy.getBotonReiniciarGanar();
		break;
		
		case (1):
			buttons[0] = gameControllerMedium.getBotonReintentar();
			buttons[1] = gameControllerMedium.getBotonReiniciar();
			buttons[2] = gameControllerMedium.getContinuarButton();
			buttons[3] = gameControllerMedium.getBotonReiniciarGanar();
		break;
		
		case (2):
			buttons[0] = gameControllerHard.getBotonReintentar();
			buttons[1] = gameControllerHard.getBotonReiniciar();
			buttons[2] = gameControllerHard.getButtonCreditos();
			buttons[3] = gameControllerHard.getBotonReiniciarGanar();
		break;
		}
		return buttons;
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

	private void volverMenuDesdeOpciones(Stage primaryStage) {

		optionController.getBackOption().setOnAction((Event) -> {

			primaryStage.setScene(scene);

		});
	}
	private void tutorialBack(Stage primaryStage) {
		howToController.getVolverB().setOnAction((Event)->{
			primaryStage.setScene(scene);
		});
	}

	private void volverMenuDesdeJuego(Stage primaryStage) {
		primaryStage.setScene(scene);
		if (controller.getSonidoB().isSelected()) {
			controller.changeFromLevelToMenuMusic();
			controller.getAudio().stop();
		} else {
			controller.changeFromLevelToMenuMusic();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
