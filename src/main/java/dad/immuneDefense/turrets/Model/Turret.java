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
    
    private IntegerProperty fireRate;
    
    /**
     * Este es el constructor vacío de la clase Turrets, la cual no se piden datos,
     * pero se inicializan las variables.
     */
    public Turret() {
    	// Incializar Variables
    	this.damage = new SimpleIntegerProperty();
    	this.fireRate = new SimpleIntegerProperty();
    }
    
    /**
     * Este es el constructor de la clase Turrets, en el cual se pide los distintos datos para así crear una torreta.
     * 
     * @param damage Es una variable la cual tiene en cuenta el daño que puede hacer
     * a enemigos las torretas.
     * @param fireRate Es una variable dirigida a la cantidad de balas las cuales puede disparar una torreta por segundo, 
     * en caso de las torretas tipo daño constante, la misma será -1.
     */
    public Turret(int damage, int fireRate)
    {
    	// Incializar Variables
    	this.damage = new SimpleIntegerProperty();
    	this.fireRate = new SimpleIntegerProperty();
    	
        //Añadir Valores;
        this.damage.set(damage);
        this.fireRate.set(fireRate);
    }

	public IntegerProperty getDamage() {
		return damage;
	}
	
	public IntegerProperty getFireRate() {
		return fireRate;
	}
}

