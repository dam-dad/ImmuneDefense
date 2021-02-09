package dad.javafx.immunedefense.model;

public class Virus extends Sprite {

	public Virus() {
		super("/mapImages/Virus Guille.png");
		setHealth(3);
	}
	
	@Override
	public void update(double time) {
		
		if (getPositionX() > 290) {
			setVelocityY(16);
			setVelocityX(0);
		}

		if (getPositionY() >= 200) {
			setVelocityY(0);
			setVelocityX(10);
		}
		
		super.update(time);
	}
	
}
