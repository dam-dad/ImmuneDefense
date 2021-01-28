package dad.immuneDefense.turrets.Model;

import javafx.beans.property.IntegerProperty;
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
	
	private IntegerProperty cost;
	
	private IntegerProperty numberShots;
	
	private IntegerProperty maxNumberShots;
	
	/**
	 * Este es el constructor el cual se usa para inicializar variables pero
	 * sin darles valor.
	 */
	public CostTurret() {
		super();
	}
	
	/**
	 * Este constructor se usa para inicializar variables pero esta vez con 
	 * valores pasados por parámetros.
	 * 
	 * @param damage Usada para determinar el daño que hacen las torretas.
	 * @param cost Determina el coste de las torretas.
	 * @param numberShots En este caso determina la cantidad de disparos que lleva.
	 * @param maxNumberShots Esta variable se usa para determinar la cantidad máxima de disparos
	 * antes de que se destruya automaticamente.
	 */
	public CostTurret(int damage, int cost, int numberShots, int maxNumberShots) {
		super(damage);
		this.damage = new SimpleIntegerProperty(damage);
		this.cost = new SimpleIntegerProperty(cost);
		this.numberShots = new SimpleIntegerProperty(numberShots);
		this.maxNumberShots = new SimpleIntegerProperty(maxNumberShots);
	}

	public IntegerProperty getDamage() {
		return damage;
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
