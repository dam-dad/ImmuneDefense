package dad.javafx.immunedefense.model;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffects {
	private static InputStream ruta;
	private static Clip effect;
	
	public static void VirusDie(){
		try {
			ruta=SoundEffects.class.getResourceAsStream("/SoundEffects/VirusDie.wav");
			effect=AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(ruta));
			effect.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void Shoot() {
		try {
			ruta=SoundEffects.class.getResourceAsStream("/SoundEffects/Shoot.wav");
			effect=AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(ruta));
			effect.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void Win() {
		try {
			ruta=SoundEffects.class.getResourceAsStream("/SoundEffects/Win.wav");
			effect=AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(ruta));
			effect.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void GameOver() {
		try {
			ruta=SoundEffects.class.getResourceAsStream("/SoundEffects/GameOver.wav");
			effect=AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(ruta));
			effect.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
