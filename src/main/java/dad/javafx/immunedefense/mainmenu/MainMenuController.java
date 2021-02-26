package dad.javafx.immunedefense.mainmenu;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MainMenuController implements Initializable {
	// SubControllers
	OptionController options = new OptionController();
	// Sonido
	private Clip audio;
	private Clip audioLevels;
	private InputStream ruta;
	private InputStream rutaLevels;
	private Image offMusic = new Image(getClass().getResourceAsStream("/Images/no_musica.png"));
	private Image onMusic = new Image(getClass().getResourceAsStream("/Images/musica.png"));

	// Vista
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
    private Button segundoNIvelBoton;

    @FXML
    private Button tercerNivelBoton;


	@FXML
	private Button masB;

	public MainMenuController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/MainMenuView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public BorderPane getView() {
		return this.view;
	}

	public void initialize(URL location, ResourceBundle resources) {
		
		ruta = getClass().getResourceAsStream("/SoundTrack/MainMenu.wav");
		rutaLevels = getClass().getResourceAsStream("/SoundTrack/NormalLevel.wav");
		
		try {
			audio = AudioSystem.getClip();
			audio.open(AudioSystem.getAudioInputStream(ruta));
			
			FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);
			
			audio.loop(Clip.LOOP_CONTINUOUSLY);
			
			options.getVolumenSlider().setValue(audio.getLevel()*100);
			/**
			 * APLICANDO SLIDER PARA CONTROLAR EL VOLUMEN DE LA MÚSICA
			 */
			
			audioLevels = AudioSystem.getClip();
			audioLevels.open(AudioSystem.getAudioInputStream(rutaLevels));
			options.getVolumenSlider().setValue(audioLevels.getLevel()*100);
			
			/*options.getVolumenSlider().valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable) {
					FloatControl volume = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
					volume.setValue((float) options.getVolumenSlider().getValue());
					System.out.println(audio.getLevel()+" "+options.getVolumenSlider().getValue());
				}
			});*/
			
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		sonidoB.setOnAction(event -> {
			if (sonidoB.isSelected()) {
				if (audio.isOpen()) {
					audio.stop();
				}
				else {
					audioLevels.stop();
				}
				
				imagenSonido.setImage(offMusic);
			} else {
				if (audio.isOpen()) {
					audio.loop(Clip.LOOP_CONTINUOUSLY);
				}
				else {
					audioLevels.loop(Clip.LOOP_CONTINUOUSLY);
				}
				
				imagenSonido.setImage(onMusic);
			}
		});

	}



	@FXML
	void onMoreB(ActionEvent event) {
		
	}

	@FXML
	void onOptions(ActionEvent event) {

	}

	public OptionController getOptions() {
		return options;
	}

	public Clip getAudio() {
		return audio;
	}

	public InputStream getRuta() {
		return ruta;
	}

	public Image getOffMusic() {
		return offMusic;
	}

	public Image getOnMusic() {
		return onMusic;
	}

	public Button getAjustesB() {
		return ajustesB;
	}

	public ToggleButton getSonidoB() {
		return sonidoB;
	}

	public ImageView getImagenSonido() {
		return imagenSonido;
	}

	public Button getNuevaPartidaB() {
		return nuevaPartidaB;
	}

	public Button getNivelMedioBoton() {
		return segundoNIvelBoton;
	}
	
	public Button getNivelDificilBoton() {
		return tercerNivelBoton;
	}


	
	public void changeFromMenuToLevelMusic() {
		audio.stop();
		audioLevels.setFramePosition(0);
		audioLevels.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void changeFromLevelToMenuMusic() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		audioLevels.stop();
		audio.setFramePosition(0);
		audio.loop(Clip.LOOP_CONTINUOUSLY);
	}
}
