package dad.javafx.immunedefense.model;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffects {
	private static InputStream ruta;
	private static Clip virusDie;
	
	public static void VirusDie(){
		try {
			ruta=SoundEffects.class.getResourceAsStream("/SoundEffects/VirusDie.wav");
			virusDie=AudioSystem.getClip();
			virusDie.open(AudioSystem.getAudioInputStream(ruta));
			virusDie.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
