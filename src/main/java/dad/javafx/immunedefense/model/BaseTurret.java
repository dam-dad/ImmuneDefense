package dad.javafx.immunedefense.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Esta es una clase dirigida a las torretas las cuales protegen la base, pero
 * que no tienen límite de disparos. Aunque las torretas quedaran destrozadas si
 * la base es destrozada también, debido a que las mismas no tienen vida.
 * 
 * @author Jose_Juan
 *
 */

public class BaseTurret extends Turret {

	private BooleanProperty base_exists = new SimpleBooleanProperty();

	/**
	 * Este constructor es el usado para inicializar las variables, esta vez con
	 * valores. Además que usa las siguientes variables:
	 * 
	 * @param damage      La cual representa el daño de la torreta.
	 * @param base_exists Representando si la base existe o no.
	 * @param fireRate    Es una variable dirigida a la cantidad de balas las cuales
	 *                    puede disparar una torreta por segundo, en caso de las
	 *                    torretas tipo daño constante, la misma será -1.
	 */
	public BaseTurret(int damage, double fireRate, boolean base_exists) {
		super(damage, fireRate);
		this.base_exists.set(base_exists);
	}

	public final BooleanProperty base_existsProperty() {
		return this.base_exists;
	}

	public final boolean isBase_exists() {
		return this.base_existsProperty().get();
	}

	public final void setBase_exists(final boolean base_exists) {
		this.base_existsProperty().set(base_exists);
	}

}
