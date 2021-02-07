package dad.javafx.immunedefense.enemies;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Enemy {

	private DoubleProperty positionX, positionY, velocityX, velocityY, width, height;
	
	public Enemy(double positionX, double positionY, double velocityX, double velocityY, double width, double height) {
		this.positionX = new SimpleDoubleProperty(positionX);
		this.positionY = new SimpleDoubleProperty(positionY);;
		this.velocityX = new SimpleDoubleProperty(velocityX);;
		this.velocityY = new SimpleDoubleProperty(velocityY);
		this.width = new SimpleDoubleProperty(width);
		this.height = new SimpleDoubleProperty(height);
	}

	public DoubleProperty getPositionX() {
		return positionX;
	}

	public void setPositionX(DoubleProperty positionX) {
		this.positionX = positionX;
	}

	public DoubleProperty getPositionY() {
		return positionY;
	}

	public void setPositionY(DoubleProperty positionY) {
		this.positionY = positionY;
	}

	public DoubleProperty getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(DoubleProperty velocityX) {
		this.velocityX = velocityX;
	}

	public DoubleProperty getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(DoubleProperty velocityY) {
		this.velocityY = velocityY;
	}

	public DoubleProperty getWidth() {
		return width;
	}

	public void setWidth(DoubleProperty width) {
		this.width = width;
	}

	public DoubleProperty getHeight() {
		return height;
	}

	public void setHeight(DoubleProperty height) {
		this.height = height;
	}

	
}
