package dad.javafx.immunedefense.model;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Virus extends Sprite {

	private boolean chocado;
	private Clip virusDie;
	private InputStream ruta=getClass().getResourceAsStream("/SoundEffects/VirusDie.wav");
	private boolean salido;
	
	public Virus() {
		super("/mapImages/Virus Guille1.png");
		setHealth(3);
		
		chocado=false;
		salido=false;
	}
	
	@Override
	public void update(double time) {
		
		if (getPositionX() > 300 & chocado==false & salido==false) {
			setVelocityY(0);
			setVelocityX(this.getVelocityX()*1.001);

		}
		
		
  		if (getPositionY() <= 5) {
			setVelocityY(this.getVelocityY()*-1);
		salido=true;
		
		}
  		
  		if (getPositionY() >= 505) {
			setVelocityY(this.getVelocityY()*-1);
		salido=true;
		
		}
  		
		
		super.update(time);
	}
	
	public void impact(Bullet bullet) {
		System.out.println("impacto!");
		
		setHealth(getHealth() - bullet.getTurret().getDamage());
		
		bullet.kill();

		// comprueba si el virus está muerto
		if (getHealth() <= 0) {
			SoundEffects.VirusDie();
			kill();
		}		
		
	}
	
	public void chocqueMuro(Muro muro) {
		System.out.println("Choque!");
		
	chocado=true;
		setVelocityY(0);
		setVelocityX(-70);
		muro.setHealth(muro.getHealth()-1);
		
	}
	
	public void choqueTorreta (Turret turret) {
		
		
		//setHealth(getHealth() - 1);
		kill();
		turret.kill();

		// comprueba si el virus está muerto
		/*
		if (getHealth() <= 0) {
			kill();
		}		
		*/
	}
	
	
}
