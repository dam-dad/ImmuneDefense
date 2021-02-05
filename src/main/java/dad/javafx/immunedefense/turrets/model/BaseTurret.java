package dad.javafx.immunedefense.turrets.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;

/**
 * Esta es una clase dirigida a las torretas las cuales protegen la base,
 * pero que no tienen límite de disparos. Aunque las torretas quedaran destrozadas si la
 * base es destrozada también, debido a que las mismas no tienen vida.
 * 
 * @author Jose_Juan
 *
 */

public class BaseTurret extends Turret {
	
	private IntegerProperty damage;
	
	private BooleanProperty base_exists;
	
	private IntegerProperty fireRate;
	
	/**
	 * Este es el constructor vacío que inicializa  las variables,
	 * pero no les da ningún valor.
	 */
	public BaseTurret() {
		super();
		base_exists = new SimpleBooleanProperty();
		damage = new SimpleIntegerProperty();
		fireRate = new SimpleIntegerProperty();
	}
	
	/**
	 * Este constructor es el usado para inicializar las variables,
	 * esta vez con valores. Además que usa las siguientes variables:
	 * 
	 * @param damage La cual representa el daño de la torreta.
	 * @param base_exists Representando si la base existe o no.
	 * @param fireRate Es una variable dirigida a la cantidad de balas las cuales puede disparar una torreta por segundo, 
     * en caso de las torretas tipo daño constante, la misma será -1.
	 */
	public BaseTurret(int damage, int fireRate, boolean base_exists) {
		super(damage,fireRate);
		this.base_exists = new SimpleBooleanProperty(base_exists);
		this.damage = new SimpleIntegerProperty(damage);
		this.fireRate = new SimpleIntegerProperty(fireRate);
	}
	
	public IntegerProperty getDamage() {
		return damage;
	}
	
	public IntegerProperty getFireRate() {
		return fireRate;
	}
	
	public BooleanProperty getBase_exists() {
		return base_exists;
	}
	
	
}
