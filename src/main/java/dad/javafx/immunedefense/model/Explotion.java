package dad.javafx.immunedefense.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Explotion extends Sprite {

	
	private double time = 0;

	
	public Explotion() {
		super("/mapImages/explosion.gif");
		setVelocityX(0);
		setVelocityY(0);
	}

	@Override
	public void update(double timeDiff) {
		time += timeDiff;

	if(time>0.5) {
			this.kill();
	
	}
					
		super.update(time);
	}
}