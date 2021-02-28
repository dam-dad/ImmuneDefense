package dad.javafx.immunedefense.model;

public class Muro extends Sprite {

	
	private double time = 0;
	
	public Muro() {
		super("/mapImages/muro.png");
		setVelocityX(0);
		setVelocityY(0);
		setHealth(3);
	}

	@Override
	public void update(double timeDiff) {
		time += timeDiff;
		
	if(time>20 | this.getHealth()<1 ) {
		
		this.kill();
		
		
		time=0;	
	}
					
		super.update(time);
	}
	
}
