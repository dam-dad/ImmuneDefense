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

public class MenuTransition implements Initializable{
	
	@FXML
    private BorderPane root;

    @FXML
    private ImageView image;
	
    private FadeTransition transitionFadeIn;
    
    private FadeTransition transitionFadeOut;
    
	public MenuTransition() throws IOException {
		transitionFadeIn = new FadeTransition();
		transitionFadeOut = new FadeTransition();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/MenuTransition.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Transición FadeIn Logo
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
	
	public void playTransitions() {
		transitionFadeIn.play();
		transitionFadeOut.play();
	}
	
	public BorderPane getRoot() {
		return root;
	}

	public ImageView getImage() {
		return image;
	}

	public FadeTransition getTransitionFadeOut() {
		return transitionFadeOut;
	}
	
}
