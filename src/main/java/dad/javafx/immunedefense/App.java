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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author Christian, Adrian, Guillermo y Jose Juan
 * Esta es la clase controladora principal del juego en general, en donde se realizan distintas tareas como:
 * -Declarar el setOnFinished de la transición del principio, para así transicionar a la escena del menú principal.
 * -Sincronizar todos los controladores desde las transiciones, hasta las del juego en sí.
 * -Declarar setOnAction de botones los cuales quieran cambiar a la escena principal.
 * Entre otras tareas necesarias.
 */
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

	/**
	 * @param primaryStage Ventana principal y única del juego, en donde se producen todos los eventos visuales y no visuales.
	 */
	public void start(Stage primaryStage) throws Exception {
		controller = new MainMenuController();

		//Controlador de las transiciones del principio
		menuTransition = new MenuTransition();

		//Transición de aparición del menú principal
		fadeOut = new FadeTransition();
		
		//Controlador de Transición de Créditos
		creditsTransition = new CreditsTransition();
		
		//setOnAction del botón de nueva partida, el cual aparece al principio
		controller.getNuevaPartidaB().setOnAction(e -> {

			gameControllerEasy = new GameController(0);
			primaryStage.setScene(new Scene(gameControllerEasy.getView()));
			
			// volver al menu despues de game over
			setOnActionEasyLevel(primaryStage);
			
			//Comprobación de la elección respecto al audio del usuario y apagando o encendiendo la música dependiendo de su elección.
			if (controller.getSonidoB().isSelected()) {
				controller.changeFromMenuToLevelMusic();
				controller.getAudioLevels().stop();
			} else {
				controller.changeFromMenuToLevelMusic();
			}
		});

		//setOnAction dedicado al cambio de escena a las opciones
		controller.getAjustesB().setOnAction(e -> {
			optionController = controller.getOptions();
			optionScene = new Scene(optionController.getView());
			primaryStage.setScene(optionScene);

			volverMenuDesdeOpciones(primaryStage);
		});
		
		//setonAction dedicado al cambio de escena a la pantalla de como jugar
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
		
		//setOnAction dedicado al botón el cual aparece con los créditos, para volver al menú.
		creditsTransition.getVolverAlMenuButton().setOnAction(e -> {
			controller.getAudioCredits().stop();
			controller.getAudio().setFramePosition(0);
			controller.getAudio().start();
			primaryStage.setScene(scene);
		});

		// Scene principal, o menú principal el cuál aparecerá después de la transición de FadeOut
		scene = new Scene(controller.getView(), 800, 600);
		scene.getRoot().setStyle("-fx-background-color: #000000;");
		
		//Añadiendo Estilos a la escena principal
		scene.getStylesheets().addAll(this.getClass().getResource("/Estilos/MainMenu.css").toExternalForm());
		
		//scene dedicado a la transición del principio
		transitionScene = new Scene(menuTransition.getRoot(), 800, 600);
		
		
		primaryStage.setScene(transitionScene);
		primaryStage.setTitle("Immune Defense");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("/Images/Logo.png"));
		primaryStage.show();
		menuTransition.playTransitions();

		//setOnFinished() para así cambiar de escena, y realizar la transición al menú principal
		menuTransition.getTransitionFadeOut().setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				scene.getRoot().setStyle("-fx-background-color: #000000;");
				primaryStage.setScene(scene);
				transition();
			}
		});

	}
	
	/**
	 * En este método se declaran los setOnAction de los botones los cuales no son placements de torretas o muros, 
	 * sino que van más con los botones los cuales aparecen al final del juego, en los casos de que hayas perdido o ganado,
	 * eso sí distintos botones dependiendo del caso.
	 * @param primaryStage Ventana principal y única del juego
	 */
	@SuppressWarnings("rawtypes")
	private void setOnActionEasyLevel(Stage primaryStage) {
		
		//setOnAction dedicado a el botón de volverAlMenú que aparece cuando has perdido
		gameControllerEasy.getBotonReiniciar().setOnAction((Event) -> {
			volverMenuDesdeJuego(primaryStage);
		});

		//setOnAction dedicado a el botón Continuar el cual aparece si ganas el juego.
		gameControllerEasy.getContinuarButton().setOnAction((Event) -> {
			gameControllerMedium = new GameController(1);
			primaryStage.setScene(new Scene(gameControllerMedium.getView()));
			setOnActionMediumLevel(primaryStage);
		});
		
		//setOnActiopn dedicado a el botón volverAlMenú que aparece cuando has ganado
		gameControllerEasy.getBotonReiniciarGanar().setOnAction((Event) -> {
			volverMenuDesdeJuego(primaryStage);
		});
	}

	/**
	 * En este caso hace el mismo trabajo que el método setOnActionEasyLevel, pero ahora trabajando con el nivel de dificultad Intermedia.
	 * @param primaryStage Ventana Principal y única del juego
	 */
	private void setOnActionMediumLevel(Stage primaryStage) {
		
		//setOnAction dedicado a el botón de volverAlMenú que aparece cuando has perdido
		gameControllerMedium.getBotonReiniciar().setOnAction((Event) -> {
			volverMenuDesdeJuego(primaryStage);
		});

		//setOnAction dedicado a el botón Continuar el cual aparece si ganas el juego.
		gameControllerMedium.getContinuarButton().setOnAction((Event) -> {
			gameControllerHard = new GameController(2);
			
			primaryStage.setScene(new Scene(gameControllerHard.getView()));
			
			setOnActionHardLevel(primaryStage);
		});
		
		//setOnActiopn dedicado a el botón volverAlMenú que aparece cuando has ganado
		gameControllerMedium.getBotonReiniciarGanar().setOnAction((Event) -> {
			volverMenuDesdeJuego(primaryStage);
		});
	}
	
	/**
	 * En este caso hace el mismo trabajo que los 2 anteriores métodos (seOnActionMediumLevel y setOnActionEasyLevel), pero trabajando con el nivel más difícil.
	 * @param primaryStage Ventana Principal y única del juego
	 */
	private void setOnActionHardLevel(Stage primaryStage) {
		
		//setOnAction dedicado a el botón de reintentar el nivel cuando hayas perdido
		gameControllerHard.getBotonReiniciar().setOnAction((Event) -> {
			volverMenuDesdeJuego(primaryStage);
		});
			
		//setOnAction dedicado a el botón de volverAlMenú que aparece cuando has perdido
		gameControllerHard.getButtonCreditos().setOnAction((Event) -> {
			primaryStage.setScene(new Scene(creditsTransition.getRoot()));
			creditsTransition.playTransitions();
			controller.changeFromLevelToCreditsMusic();
		});
		
		//setOnActiopn dedicado a el botón volverAlMenú que aparece cuando has ganado
		gameControllerHard.getBotonReiniciarGanar().setOnAction((Event) -> {
			volverMenuDesdeJuego(primaryStage);
		});
	}
	
	/**
	 * Este método es el usado para configurar la transición la cual ocurre después del cambio de Scene, entre la transición del principio y el menú, respectivamente.
	 * Además se le añaden los estilos finales a la Scene del menú.
	 */
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

	/**
	 * Método para volver al menú principal desde el menú de opciones.
	 * @param primaryStage Ventana principal y única de este juego.
	 */
	private void volverMenuDesdeOpciones(Stage primaryStage) {

		optionController.getBackOption().setOnAction((Event) -> {

			primaryStage.setScene(scene);

		});
	}
	
	/**
	 * Método para volver al Menú principal desde la parte de como jugar.
	 * @param primaryStage Ventana Principal y única de este juego.
	 */
	private void tutorialBack(Stage primaryStage) {
		howToController.getVolverB().setOnAction((Event)->{
			primaryStage.setScene(scene);
		});
	}

	/**
	 * Método para volver al menú principal desde el juego en sí.
	 * @param primaryStage Ventana principal y única de este juego.
	 */
	private void volverMenuDesdeJuego(Stage primaryStage) {
		primaryStage.setScene(scene);
		if (controller.getSonidoB().isSelected()) {
			controller.changeFromLevelToMenuMusic();
			controller.getAudio().stop();
		} else {
			controller.changeFromLevelToMenuMusic();
		}
	}
	
	/**
	 * Método principal para el lanzamiento del juego en sí.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
