package dad.immuneDefense.Mapa;

import dad.immuneDefense.enemies.SpriteSencillo;
import javafx.scene.canvas.GraphicsContext;

public class DispararBalas extends Thread{
	
	private SpriteSencillo bala;
	
	private SpriteSencillo virus;
	
	private GraphicsContext gc;
	
	private double t;
	
	public DispararBalas(SpriteSencillo bala, SpriteSencillo virus,GraphicsContext gc, double t) {
		this.bala = bala;
		this.virus = virus;
		this.gc = gc;
		this.t = t;
	}
	
	public void run() {
		virus.render(gc);
		bala.render(gc);
		
		virus.update(t);
		bala.update(t);
		if (bala.intersects(virus)) {
			currentThread().interrupt();
			System.out.println(currentThread().isInterrupted());
		}
	}

}
