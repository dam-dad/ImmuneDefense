package dad.javafx.immunedefense.transitions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class CreditsTransition implements Initializable {
	
	@FXML
    private AnchorPane root;

    @FXML
    private AnchorPane CreditsPane;

    @FXML
    private Button volverAlMenuButton;

    @FXML
    private ImageView creditsImageView;
	
    private FadeTransition transitionFadeIn;
    
    private FadeTransition transitionFadeOut;
    
	public CreditsTransition() throws IOException {
		transitionFadeIn = new FadeTransition();
		transitionFadeOut = new FadeTransition();
			
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/CreditsTransition.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		transitionFadeIn.setAutoReverse(true);
		transitionFadeIn.setCycleCount(1);
		transitionFadeIn.setDelay(Duration.ZERO);
		transitionFadeIn.setDuration(Duration.seconds(5));
		transitionFadeIn.setFromValue(0.0);
		transitionFadeIn.setToValue(1.0);
		transitionFadeIn.setRate(1.0);
		transitionFadeIn.setNode(CreditsPane);
		transitionFadeIn.setInterpolator(Interpolator.LINEAR);
		
		//Transición FadeOut Logo
		transitionFadeOut.setAutoReverse(true);
		transitionFadeOut.setCycleCount(1);
		transitionFadeOut.setDelay(Duration.seconds(6));
		transitionFadeOut.setDuration(Duration.seconds(5));
		transitionFadeOut.setFromValue(1.0);
		transitionFadeOut.setToValue(0.0);
		transitionFadeOut.setRate(1.0);
		transitionFadeOut.setNode(creditsImageView);
		transitionFadeOut.setInterpolator(Interpolator.LINEAR);
	}
	
	public void playTransitions() {
		transitionFadeIn.play();
		transitionFadeOut.play();
	}

	public AnchorPane getRoot() {
		return root;
	}

	public AnchorPane getCreditsPane() {
		return CreditsPane;
	}

	public Button getVolverAlMenuButton() {
		return volverAlMenuButton;
	}

	public ImageView getCreditsImageView() {
		return creditsImageView;
	}
}
