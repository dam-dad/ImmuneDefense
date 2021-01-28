package dad.immuneDefense.turrets.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Esta es la clase de la cual heredan las demás torretas, las cuales van
 * a aparecer en este juego.
 * 
 * @author Jose_Juan
 *
 */
public class Turret
{
    
    private IntegerProperty damage;
    
    /**
     * Este es el constructor vacío de la clase Turrets, la cual no se piden datos,
     * pero se inicializan las variables.
     */
    public Turret() {
    	// Incializar Variables
    	this.damage = new SimpleIntegerProperty();
    }
    
    /**
     * Este es el constructor de la clase Turrets, en el cual se pide los distintos datos para así crear una torreta.
     * 
     * @param damage Es una variable la cual tiene en cuenta el daño que puede hacer
     * a enemigos las torretas.
     */
    public Turret(int damage)
    {
    	// Incializar Variables
    	this.damage = new SimpleIntegerProperty();
    	
        //Añadir Valores;
        this.damage.set(damage);
    }

	public IntegerProperty getDamage() {
		return damage;
	}
}

