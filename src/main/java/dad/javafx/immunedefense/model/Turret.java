package dad.javafx.immunedefense.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Esta es la clase de la cual heredan las demás torretas, las cuales van a
 * aparecer en este juego.
 * 
 * @author Jose_Juan
 *
 */
public class Turret extends Sprite {

	private IntegerProperty damage = new SimpleIntegerProperty();

	private DoubleProperty fireRate = new SimpleDoubleProperty();

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
		super("/mapImages/Turret.png");
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

}
