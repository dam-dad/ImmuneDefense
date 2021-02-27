package dad.javafx.immunedefense.transitions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * 
 * @author Jose_Juan
 *	
 *Esta clase está dirigida a la transición la cual aparece al principio del programa,
 *la cual está formada por dos transiciones una de FadeIn y otra de FadeOut, para así
 *conseguir la transición final.
 */
public class MenuTransition implements Initializable{
	
	@FXML
    private BorderPane root;

    @FXML
    private ImageView image;
	
    private FadeTransition transitionFadeIn;
    
    private FadeTransition transitionFadeOut;
    
    /**
     * Este es el constructor el cual inicializa el FXML el cual contiene la imagen y 
     * el pane donde la misma está, además de que es donde se va a realizar la transición.
     * @throws IOException Lanza esta Exception debido al FXMLLoader.
     */
	public MenuTransition() throws IOException {
		transitionFadeIn = new FadeTransition();
		transitionFadeOut = new FadeTransition();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/MenuTransition.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	/**
	 * Este es el método a utilizar en el caso de que se esté utilizando un FXML, el cual se utiliza
	 * como constructor del FXML, no de la clase, del FXML. Funciona primero configurando las dos transiciones,
	 * con sus valores pertinentes, le cambiamos el fondo a negro, y se inicializaran las transiciones en otro
	 * sitio, mediante el método playTransitions().
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Transición FadeIn Logo
		/**
		 */
		transitionFadeIn.setAutoReverse(true);
		transitionFadeIn.setCycleCount(1);
		transitionFadeIn.setDelay(Duration.ZERO);
		transitionFadeIn.setDuration(Duration.seconds(5));
		transitionFadeIn.setFromValue(0.0);
		transitionFadeIn.setToValue(1.0);
		transitionFadeIn.setRate(1.0);
		transitionFadeIn.setNode(image);
		transitionFadeIn.setInterpolator(Interpolator.LINEAR);
		
		//Transición FadeOut Logo
		transitionFadeOut.setAutoReverse(true);
		transitionFadeOut.setCycleCount(1);
		transitionFadeOut.setDelay(Duration.seconds(6));
		transitionFadeOut.setDuration(Duration.seconds(5));
		transitionFadeOut.setFromValue(1.0);
		transitionFadeOut.setToValue(0.0);
		transitionFadeOut.setRate(1.0);
		transitionFadeOut.setNode(image);
		transitionFadeOut.setInterpolator(Interpolator.LINEAR);
		
		root.setStyle("-fx-background-color: #000000;");
	}
	
	/**
	 * Método para iniciar las transiciones
	 */
	public void playTransitions() {
		transitionFadeIn.play();
		transitionFadeOut.play();
	}
	
	/**
	 * @return el Pane el cual actúa como ráiz del FXML, llamado root.
	 */
	public BorderPane getRoot() {
		return root;
	}
	
	/**
	 * @return el ImageView, el cual muestra la imágen que se usa en la transición.
	 */
	public ImageView getImage() {
		return image;
	}
	
	/**
	 * @return la transición de FadeOut, la cual hace desaparecer la imagen
	 */
	public FadeTransition getTransitionFadeOut() {
		return transitionFadeOut;
	}
	
}
