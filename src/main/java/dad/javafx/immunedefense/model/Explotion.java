package dad.javafx.immunedefense.model;



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