package dad.javafx.immunedefense.model;

public class Bullet extends Sprite {

	// torreta que disparó la bala
	private Turret turret;

	public Bullet() {
		super("/mapImages/Proyectile1.png");
	}

	public Turret getTurret() {
		return turret;
	}

	public void setTurret(Turret turret) {
		this.turret = turret;
	}

}
