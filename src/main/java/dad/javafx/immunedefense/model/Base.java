package dad.javafx.immunedefense.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Base extends Sprite {

	private DoubleProperty positionX = new SimpleDoubleProperty();
	private DoubleProperty positionY = new SimpleDoubleProperty();
	
	public Base() {
		super("/mapImages/Base1.png");
		setPositionX(740);
		setPositionY(50);
		setHealth(3);
		
	}
	

	

	
	
}