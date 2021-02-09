package dad.javafx.immunedefense.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Esta es la clase la cual va dirigida a la mayoría de torretas de este juego,
 * las cuales son las que tienen un coste.
 * 
 * @author Jose_Juan
 *
 */

public class CostTurret extends Turret {

	private IntegerProperty cost = new SimpleIntegerProperty();

	private IntegerProperty numberShots = new SimpleIntegerProperty();

	private IntegerProperty maxNumberShots = new SimpleIntegerProperty();

	/**
	 * Este constructor se usa para inicializar variables pero esta vez con valores
	 * pasados por parámetros.
	 * 
	 * @param damage         Usada para determinar el daño que hacen las torretas.
	 * @param fireRate       Es una variable dirigida a la cantidad de balas las
	 *                       cuales puede disparar una torreta por segundo, en caso
	 *                       de las torretas tipo daño constante, la misma será -1.
	 * @param cost           Determina el coste de las torretas.
	 * @param numberShots    En este caso determina la cantidad de disparos que
	 *                       lleva.
	 * @param maxNumberShots Esta variable se usa para determinar la cantidad máxima
	 *                       de disparos antes de que se destruya automaticamente.
	 */
	public CostTurret(int damage, int fireRate, int cost, int numberShots, int maxNumberShots) {
		super(damage, fireRate);
		this.cost.set(cost);
		this.numberShots.set(numberShots);
		this.maxNumberShots.set(maxNumberShots);
	}

	public final IntegerProperty costProperty() {
		return this.cost;
	}

	public final int getCost() {
		return this.costProperty().get();
	}

	public final void setCost(final int cost) {
		this.costProperty().set(cost);
	}

	public final IntegerProperty numberShotsProperty() {
		return this.numberShots;
	}

	public final int getNumberShots() {
		return this.numberShotsProperty().get();
	}

	public final void setNumberShots(final int numberShots) {
		this.numberShotsProperty().set(numberShots);
	}

	public final IntegerProperty maxNumberShotsProperty() {
		return this.maxNumberShots;
	}

	public final int getMaxNumberShots() {
		return this.maxNumberShotsProperty().get();
	}

	public final void setMaxNumberShots(final int maxNumberShots) {
		this.maxNumberShotsProperty().set(maxNumberShots);
	}

}
