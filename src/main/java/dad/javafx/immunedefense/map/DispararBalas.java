package dad.javafx.immunedefense.map;

import dad.javafx.immunedefense.enemies.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class DispararBalas extends Thread{
	
	private Sprite bala;
	
	private Sprite virus;
	
	private GraphicsContext gc;
	
	private double t;
	
	public DispararBalas(Sprite bala, Sprite virus,GraphicsContext gc, double t) {
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
