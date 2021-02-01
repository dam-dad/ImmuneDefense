package dad.immuneDefense.mainMenu;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

	public class MainMenuController implements Initializable {
		//Sonido
	    private  Clip audio;
	    private InputStream ruta;
	    private Image offMusic=new Image(getClass().getResourceAsStream("/Images/no_musica.png"));
		//Vista
	    @FXML
	    private BorderPane view;
	    
	    @FXML
	    private Button ajustesB;

	    @FXML
	    private ToggleButton sonidoB;
	   
	    @FXML
	    private ImageView imagenSonido;

	    @FXML
	    private Button nuevaPartidaB;

	    @FXML
	    private Button cargarB;

	    @FXML
	    private Button masB;
	    
	    public MainMenuController() throws IOException{
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/Menus/MainMenuView.fxml"));
			loader.setController(this);
			loader.load();
		}
		public BorderPane getView() {
			return this.view;
		}
		
		public void initialize(URL location, ResourceBundle resources) {
			ruta=getClass().getResourceAsStream("/SoundTrack/MainMenu.wav");
            try {
				audio = AudioSystem.getClip();
				audio.open(AudioSystem.getAudioInputStream(ruta)) ;
				audio.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
            
	    	sonidoB.setOnAction(event->{
	    		if(sonidoB.isSelected()) {
	    			audio.stop();
	    		}else {
	    			audio.loop(Clip.LOOP_CONTINUOUSLY);
	    		}
	    	});
            
		}
	    @FXML
	    void onDesactivarSonido(ActionEvent event) {
	    	imagenSonido.setImage(offMusic);
	    }

	    @FXML
	    void onLoadGame(ActionEvent event) {

	    }

	    @FXML
	    void onMoreB(ActionEvent event) {

	    }

	    @FXML
	    void onNewGame(ActionEvent event) {

	    }

	    @FXML
	    void onOptions(ActionEvent event) {

	    }


	}


