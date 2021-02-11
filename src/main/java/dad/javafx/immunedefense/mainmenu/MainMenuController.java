package dad.javafx.immunedefense.mainmenu;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MainMenuController implements Initializable {
	// SubControllers
	OptionController options = new OptionController();
	// Sonido
	private Clip audio;
	private InputStream ruta;
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
	private Button cargarB;

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
		
		try {
			audio = AudioSystem.getClip();
			audio.open(AudioSystem.getAudioInputStream(ruta));
			
			FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);
			audio.loop(Clip.LOOP_CONTINUOUSLY);
			
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		/*
		MediaPlayer mediaPlayer;
		
		final String s = "D:\\2_DAM_Alternativa\\DAD\\ImmuneDefense\\src\\main\\resources\\SoundTrack\\MainMenu.mp3";
		File archivo = new File(s);
		
		Media h = new Media(Paths.get(s).toUri().toString());
		mediaPlayer = new MediaPlayer(h);
		mediaPlayer.play();
		*/
		sonidoB.setOnAction(event -> {
			if (sonidoB.isSelected()) {
				audio.stop();
				
				imagenSonido.setImage(offMusic);
			} else {
				audio.loop(Clip.LOOP_CONTINUOUSLY);
				
				imagenSonido.setImage(onMusic);
			}
		});
		
		
	}

	@FXML
	void onLoadGame(ActionEvent event) {

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

	public Button getCargarB() {
		return cargarB;
	}

	public Button getMasB() {
		return masB;
	}
}
