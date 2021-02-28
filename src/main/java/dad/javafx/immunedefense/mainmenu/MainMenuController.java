package dad.javafx.immunedefense.mainmenu;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
	private Clip audioCredits;
	private InputStream ruta;
	private InputStream rutaLevels;
	private InputStream rutaCredits;
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
	private Button howPlayB;

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
		rutaCredits = getClass().getResourceAsStream("/SoundTrack/Credits.wav");
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(0);
		try {
			audio = AudioSystem.getClip();
			audio.open(AudioSystem.getAudioInputStream(ruta));
			
			FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-30.0f);
			options.getVolumenText().textProperty().set(String.valueOf(100+(int)gainControl.getValue()));
			options.getVolumenSlider().setValue(100+gainControl.getValue());
			options.getVolumenSlider().valueProperty().addListener(new InvalidationListener() {
				
				@Override
				public void invalidated(Observable observable) {
					gainControl.setValue((float)options.getVolumenSlider().getValue()-100);
					options.getVolumenText().textProperty().set(String.valueOf(100+(int)gainControl.getValue()));
					if (gainControl.getValue()==-80.0f) {
						options.getVolumenText().textProperty().set(String.valueOf(80+(int)gainControl.getValue()));
					}
				}
			});
			
			audio.loop(Clip.LOOP_CONTINUOUSLY);
				
			audioLevels = AudioSystem.getClip();
			audioLevels.open(AudioSystem.getAudioInputStream(rutaLevels));
			
			
			audioCredits = AudioSystem.getClip();
			audioCredits.open(AudioSystem.getAudioInputStream(rutaCredits));
			
			
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		sonidoB.setOnAction(event -> {
			if (sonidoB.isSelected()) {
					audio.stop();
					audioLevels.stop();
				imagenSonido.setImage(offMusic);
			} else {
				audio.loop(Clip.LOOP_CONTINUOUSLY);				
				imagenSonido.setImage(onMusic);
			}
		});

	}
	public OptionController getOptions() {
		return options;
	}

	public Clip getAudio() {
		return audio;
	}
	
	public Clip getAudioLevels() {
		return audioLevels;
	}

	public Clip getAudioCredits() {
		return audioCredits;
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
	

	public Button getHowPlayB() {
		return howPlayB;
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
	
	public void changeFromMenuToLevelMusic() {
		audio.stop();
		audioLevels.setFramePosition(0);
		FloatControl gainControl = (FloatControl) audioLevels.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue((float)options.getVolumenSlider().getValue()-100);
		audioLevels.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void changeFromLevelToMenuMusic() {
		audioLevels.stop();
		audio.setFramePosition(0);
		FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue((float)options.getVolumenSlider().getValue()-100);
		audio.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void changeFromLevelToCreditsMusic() {
		if (audioLevels.isActive()) {
			audioLevels.stop();
		}
		else if (audio.isActive()) {
			audio.stop();
		}
		audioCredits.setFramePosition(0);
		FloatControl gainControl = (FloatControl) audioCredits.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue((float)options.getVolumenSlider().getValue()-100);
		audioCredits.loop(Clip.LOOP_CONTINUOUSLY);
	}
}
