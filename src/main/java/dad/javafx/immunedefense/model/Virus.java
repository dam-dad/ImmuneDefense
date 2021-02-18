package dad.javafx.immunedefense.model;

public class Virus extends Sprite {

	private boolean chocado;
	
	
	public Virus() {
		super("/mapImages/Virus Guille1.png");
		setHealth(3);
		
		chocado=false;
	}
	
	@Override
	public void update(double time) {
		
		if (getPositionX() > 300 & chocado==false) {
			setVelocityY(0);
			setVelocityX(this.getVelocityX()*1.005);
		}
/*
		if (getPositionY() >= 200) {
			setVelocityY(0);
			setVelocityX(10);
		}
		*/
		super.update(time);
	}
	
	public void impact(Bullet bullet) {
		System.out.println("impacto!");
		
		setHealth(getHealth() - bullet.getTurret().getDamage());
		
		bullet.kill();

		// comprueba si el virus está muerto
		if (getHealth() <= 0) {
			kill();
		}		
		
	}
	
	public void chocqueMuro(Muro muro) {
		System.out.println("Choque!");
		
	chocado=true;
		setVelocityY(70);
		setVelocityX(0);
		
		
	}
	
	public void choqueTorreta (Turret turret) {
		
		
		setHealth(getHealth() - 1);
		
		turret.kill();

		// comprueba si el virus está muerto
		if (getHealth() <= 0) {
			kill();
		}		
		
	}
	
	
}
