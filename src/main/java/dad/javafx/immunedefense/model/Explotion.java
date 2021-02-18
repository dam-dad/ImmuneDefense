package dad.javafx.immunedefense.model;

import javafx.scene.image.Image;

public class Explotion extends Sprite {

	
	private double time = 0;
	
	public Explotion() {
		super("/mapImages/explotion0.png");
		setVelocityX(0);
		setVelocityY(0);
	}

	@Override
	public void update(double timeDiff) {
		time += timeDiff;
		if( time <=0.25) {
			this.setImage(new Image("/mapImages/explotion1.png"));
		}
		
		
		if(time>0.25 & time <=0.5) {
			this.setImage(new Image("/mapImages/expltion2.png"));
		}
		
	if(time>0.5) {
		
		this.kill();
		
		
		
	}
					
		super.update(time);
	}
}