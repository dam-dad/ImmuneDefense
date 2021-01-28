package dad.immuneDefense.turrets.Model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
	
	/**
	 * Este es el constructor vacío que inicializa  las variables,
	 * pero no les da ningún valor.
	 */
	public BaseTurret() {
		super();
		base_exists = new SimpleBooleanProperty();
		damage = new SimpleIntegerProperty();
	}
	
	/**
	 * Este constructor es el usado para inicializar las variables,
	 * esta vez con valores. Además que usa las siguientes variables:
	 * 
	 * @param damage La cual representa el daño de la torreta.
	 * @param base_exists Representando si la base existe o no.
	 */
	public BaseTurret(int damage, boolean base_exists) {
		super(damage);
		this.base_exists = new SimpleBooleanProperty(base_exists);
		this.damage = new SimpleIntegerProperty(damage);
	}
	
	public IntegerProperty getDamage() {
		return damage;
	}

	public BooleanProperty getBase_exists() {
		return base_exists;
	}
	
	
}
