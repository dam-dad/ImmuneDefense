package dad.javafx.immunedefense.model;

import dad.javafx.immunedefense.map.GameController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

public class Sprite {


	private GameController game;

	
	
	private ObjectProperty<Image> image = new SimpleObjectProperty<>();
	private DoubleProperty positionX = new SimpleDoubleProperty();
	private DoubleProperty positionY = new SimpleDoubleProperty();
	private DoubleProperty velocityX = new SimpleDoubleProperty();
	private DoubleProperty velocityY = new SimpleDoubleProperty();
	private DoubleProperty width = new SimpleDoubleProperty();
	private DoubleProperty height = new SimpleDoubleProperty();
	private DoubleProperty health = new SimpleDoubleProperty();

	public Sprite(String urlImage) {
		setImage(new Image(urlImage));
		setWidth(getImage().getWidth());
		setHeight(getImage().getHeight());
	}

	public void update(double time) {
		setPositionX(getPositionX() + getVelocityX() * time);
		setPositionY(getPositionY() + getVelocityY() * time);
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(getImage(), getPositionX(), getPositionY());
		gc.setStroke(Color.YELLOW);
		gc.setLineWidth(5);
		gc.setLineCap(StrokeLineCap.ROUND);
		gc.rect(getPositionX(), getPositionY(), getWidth(), getHeight());
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(getPositionX(), getPositionY(), getWidth(), getHeight());
	}

	public boolean intersects(Sprite s) {
		return s.getBoundary().intersects(this.getBoundary());
	}

	public final ObjectProperty<Image> imageProperty() {
		return this.image;
	}

	public final Image getImage() {
		return this.imageProperty().get();
	}

	public final void setImage(final Image image) {
		this.imageProperty().set(image);
	}

	public final DoubleProperty positionXProperty() {
		return this.positionX;
	}

	public final double getPositionX() {
		return this.positionXProperty().get();
	}

	public final void setPositionX(final double positionX) {
		this.positionXProperty().set(positionX);
	}

	public final DoubleProperty positionYProperty() {
		return this.positionY;
	}

	public final double getPositionY() {
		return this.positionYProperty().get();
	}

	public final void setPositionY(final double positionY) {
		this.positionYProperty().set(positionY);
	}

	public final DoubleProperty velocityXProperty() {
		return this.velocityX;
	}

	public final double getVelocityX() {
		return this.velocityXProperty().get();
	}

	public final void setVelocityX(final double velocityX) {
		this.velocityXProperty().set(velocityX);
	}

	public final DoubleProperty velocityYProperty() {
		return this.velocityY;
	}

	public final double getVelocityY() {
		return this.velocityYProperty().get();
	}

	public final void setVelocityY(final double velocityY) {
		this.velocityYProperty().set(velocityY);
	}

	public final DoubleProperty widthProperty() {
		return this.width;
	}

	public final double getWidth() {
		return this.widthProperty().get();
	}

	public final void setWidth(final double width) {
		this.widthProperty().set(width);
	}

	public final DoubleProperty heightProperty() {
		return this.height;
	}

	public final double getHeight() {
		return this.heightProperty().get();
	}

	public final void setHeight(final double height) {
		this.heightProperty().set(height);
	}

	public final DoubleProperty healthProperty() {
		return this.health;
	}

	public final double getHealth() {
		return this.healthProperty().get();
	}

	public final void setHealth(final double health) {
		this.healthProperty().set(health);
	}

	public GameController getGame() {
		return game;
	}

	public void setGame(GameController game) {
		this.game = game;
		this.getGame().getSprites().add(this);
	}
	
	
	public void kill() {
	
		getGame().getSprites().remove(this);
		
		/*
		if (elegir==1) {
			getGameSupervivencia().getSprites().remove(this);
			}
			*/
	}

}
