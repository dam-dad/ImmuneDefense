package dad.javafx.immunedefense.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Esta es la clase de la cual heredan las demás torretas, las cuales van a
 * aparecer en este juego.
 * 
 * @author Jose_Juan
 *
 */
public class Turret extends Sprite {
	
	private double timeShoot = 0;
	
	private double lifeTime = 0;

	private IntegerProperty damage = new SimpleIntegerProperty();
	private DoubleProperty fireRate = new SimpleDoubleProperty();
	private ListProperty<Bullet> firedBullets = new SimpleListProperty<>(FXCollections.observableArrayList());

	/**
	 * Este es el constructor de la clase Turrets, en el cual se pide los distintos
	 * datos para así crear una torreta.
	 * 
	 * @param damage   Es una variable la cual tiene en cuenta el daño que puede
	 *                 hacer a enemigos las torretas.
	 * @param fireRate Es una variable dirigida a la cantidad de balas las cuales
	 *                 puede disparar una torreta por segundo, en caso de las
	 *                 torretas tipo daño constante, la misma será -1.
	 */
	public Turret(int damage, double fireRate) {
		super("/mapImages/Turret1.png");
		this.damage.set(damage);
		this.fireRate.set(fireRate);
	}

	public final IntegerProperty damageProperty() {
		return this.damage;
	}

	public final int getDamage() {
		return this.damageProperty().get();
	}

	public final void setDamage(final int damage) {
		this.damageProperty().set(damage);
	}

	public final DoubleProperty fireRateProperty() {
		return this.fireRate;
	}

	public final double getFireRate() {
		return this.fireRateProperty().get();
	}

	public final void setFireRate(final double fireRate) {
		this.fireRateProperty().set(fireRate);
	}

	public final ListProperty<Bullet> firedBulletsProperty() {
		return this.firedBullets;
	}

	public final ObservableList<Bullet> getFiredBullets() {
		return this.firedBulletsProperty().get();
	}

	public final void setFiredBullets(final ObservableList<Bullet> firedBullets) {
		this.firedBulletsProperty().set(firedBullets);
	}
	
	@Override
	public void update(double timeDiff) {
		lifeTime += timeDiff;
		timeShoot += timeDiff;
		if (timeShoot > 2) {
			Bullet bullet = new Bullet();
			bullet.setTurret(this);
			bullet.setPositionX(this.getPositionX() + (this.getWidth() / 2));
			bullet.setPositionY(this.getPositionY() + (this.getHeight() / 2));
			bullet.setVelocityX(120);
			bullet.setGame(getGame());
			
			Bullet bulletIZQUIERDA = new Bullet();
			bulletIZQUIERDA.setTurret(this);
			bulletIZQUIERDA.setPositionX(this.getPositionX() + (this.getWidth() / 2));
			bulletIZQUIERDA.setPositionY(this.getPositionY() + (this.getHeight() / 2));
			bulletIZQUIERDA.setVelocityX(-80);
			bulletIZQUIERDA.setGame(getGame());
			
			
			Bullet bulletARRIBA = new Bullet();
			bulletARRIBA.setTurret(this);
			bulletARRIBA.setPositionX(this.getPositionX() + (this.getWidth() / 2));
			bulletARRIBA.setPositionY(this.getPositionY() + (this.getHeight() / 2));
			bulletARRIBA.setVelocityY(-80);
			bulletARRIBA.setGame(getGame());
			
			Bullet bulletABAJO = new Bullet();
			bulletABAJO.setTurret(this);
			bulletABAJO.setPositionX(this.getPositionX() + (this.getWidth() / 2));
			bulletABAJO.setPositionY(this.getPositionY() + (this.getHeight() / 2));
			bulletABAJO.setVelocityY(80);
			bulletABAJO.setGame(getGame());
			
			
			timeShoot = 0.0;
		
			
		}
		
		if(lifeTime>7) {
			
			this.kill();
			
			
			lifeTime=0;	
		}
		
		super.update(lifeTime);
		super.update(timeShoot);
	}

}
