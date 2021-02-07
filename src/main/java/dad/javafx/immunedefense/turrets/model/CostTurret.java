package dad.javafx.immunedefense.turrets.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Esta es la clase la cual va dirigida a la mayoría de
 * torretas de este juego, las cuales son las que tienen 
 * un coste.
 * @author Jose_Juan
 *
 */

public class CostTurret extends Turret {
	
	private IntegerProperty damage;
	
	private DoubleProperty fireRate;
	
	private IntegerProperty cost;
	
	private IntegerProperty numberShots;
	
	private IntegerProperty maxNumberShots;
	
	/**
	 * Este es el constructor el cual se usa para inicializar variables pero
	 * sin darles valor.
	 */
	public CostTurret() {
		super();
		damage = new SimpleIntegerProperty();
		fireRate = new SimpleDoubleProperty();
		cost = new SimpleIntegerProperty();
		numberShots = new SimpleIntegerProperty();
		maxNumberShots = new SimpleIntegerProperty();
	}
	
	/**
	 * Este constructor se usa para inicializar variables pero esta vez con 
	 * valores pasados por parámetros.
	 * 
	 * @param damage Usada para determinar el daño que hacen las torretas.
	 * @param fireRate Es una variable dirigida a la cantidad de balas las cuales puede disparar una torreta por segundo, 
     * en caso de las torretas tipo daño constante, la misma será -1.
	 * @param cost Determina el coste de las torretas.
	 * @param numberShots En este caso determina la cantidad de disparos que lleva.
	 * @param maxNumberShots Esta variable se usa para determinar la cantidad máxima de disparos
	 * antes de que se destruya automaticamente.
	 */
	public CostTurret(int damage, int fireRate, int cost, int numberShots, int maxNumberShots) {
		super(damage,fireRate);
		this.damage = new SimpleIntegerProperty(damage);
		this.fireRate = new SimpleDoubleProperty(fireRate);
		this.cost = new SimpleIntegerProperty(cost);
		this.numberShots = new SimpleIntegerProperty(numberShots);
		this.maxNumberShots = new SimpleIntegerProperty(maxNumberShots);
	}

	public IntegerProperty getDamage() {
		return damage;
	}

	public DoubleProperty getFireRate() {
		return fireRate;
	}
	
	public IntegerProperty getCost() {
		return cost;
	}

	public IntegerProperty getNumberShots() {
		return numberShots;
	}

	public IntegerProperty getMaxNumberShots() {
		return maxNumberShots;
	}
}
