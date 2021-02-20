package dad.javafx.immunedefense.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Moneda {

	private DoubleProperty	moneda = new SimpleDoubleProperty();
	
	
	public Moneda(){
		setMoneda(50);	
	}
	
	
	public final DoubleProperty monedaProperty() {
		return this.moneda;
	}

	public final double getmoneda() {
		return this.monedaProperty().get();
	}

	public final void setMoneda(final double moneda) {
		this.monedaProperty().set(moneda);
	}
	
	
}
