package dad.javafx.immunedefense.enemies;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Enemy {

	private DoubleProperty velocityX, velocityY;
	
	private IntegerProperty health;
	
	public Enemy(double velocityX, double velocityY, int health) {
		this.velocityX = new SimpleDoubleProperty(velocityX);;
		this.velocityY = new SimpleDoubleProperty(velocityY);
		this.health = new SimpleIntegerProperty(health);
	}

	public DoubleProperty getVelocityX() {
		return velocityX;
	}

	public DoubleProperty getVelocityY() {
		return velocityY;
	}

	public IntegerProperty getHealth() {
		return health;
	}
}
