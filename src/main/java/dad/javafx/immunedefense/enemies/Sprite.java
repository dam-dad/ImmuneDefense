package dad.javafx.immunedefense.enemies;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {

	private Image image;
	private double positionX;
	private double positionY;
	private double velocityX;
	private double velocityY;
	private double width;
	private double height;
	private int health;

	public Sprite(String urlImage) {
		setImage(new Image(urlImage));
	}
	
	public void update(double time) {
		positionX += velocityX * time;
		positionY += velocityY * time;
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(image, positionX, positionY);
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX, positionY, width, height);
	}

	public boolean intersects(Sprite s) {
		return s.getBoundary().intersects(this.getBoundary());
	}

	public final Image getImage() {
		return image;
	}

	public final void setImage(Image image) {
		this.image = image;
	}

	public final double getPositionX() {
		return positionX;
	}

	public final void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public final double getPositionY() {
		return positionY;
	}

	public final void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public final double getVelocityX() {
		return velocityX;
	}

	public final void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public final double getVelocityY() {
		return velocityY;
	}

	public final void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	public final double getWidth() {
		return width;
	}

	public final void setWidth(double width) {
		this.width = width;
	}

	public final double getHeight() {
		return height;
	}

	public final void setHeight(double height) {
		this.height = height;
	}

	public final int getHealth() {
		return health;
	}

	public final void setHealth(int health) {
		this.health = health;
	}
}
