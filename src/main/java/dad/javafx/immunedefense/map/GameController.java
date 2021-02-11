package dad.javafx.immunedefense.map;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dad.javafx.immunedefense.model.Bullet;
import dad.javafx.immunedefense.model.Sprite;
import dad.javafx.immunedefense.model.Turret;
import dad.javafx.immunedefense.model.Virus;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class GameController extends AnimationTimer implements Initializable {

	// model
	
	private double time = 0.0; 
	private double lastNanoTime;

	// Lista de Torretas
	private List<Turret> turrets;

	// Lista de Enemigos distintos
	private List<Virus> enemies;

	// Lista Sprites
	private List<Sprite> sprites;

	private Image earth = new Image("/mapImages/Terrain.png");

	// view

	@FXML
	private StackPane view;

	@FXML
	private Canvas canvas;

	public GameController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/PanelJuegoFX.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {

		spritesPrincipales();
		
		lastNanoTime = System.nanoTime();
		start(); // inicia el animationtimer
		
	}

	private void spritesPrincipales() {

		Virus rhinitis = new Virus();
		rhinitis.setPositionX(0);
		rhinitis.setPositionY(0);
		rhinitis.setVelocityX(20);
		rhinitis.setWidth(200);
		rhinitis.setHeight(200);

		enemies = new ArrayList<Virus>();
		enemies.add(rhinitis);

		// Torreta quieta
		Turret turret = new Turret(1, 0.5);
		turret.setPositionX(50);
		turret.setPositionY(200);
		turret.setHeight(200);
		turret.setWidth(200);

		turrets = new ArrayList<Turret>();
		turrets.add(turret);

		sprites = new ArrayList<>();
		sprites.add(turret);
		sprites.add(rhinitis);

	}

	public Parent getView() {
		return view;
	}

	@Override
	public void handle(long now) {
		final GraphicsContext gc = canvas.getGraphicsContext2D();

		double timeDiff = (now - lastNanoTime) / 1000000000.0; // time difference between frames in
																			// seconds

		// List<Sprite> balasLista = new ArrayList<>();

		time += timeDiff;
		if (time > 2) {
			Bullet bala = new Bullet();
			bala.setPositionX(50);
			bala.setPositionY(300);
			bala.setVelocityX(120);
			bala.setHeight(200);
			bala.setWidth(200);
			sprites.add(bala);

			time = 0.0;
		}

		// draw background
		gc.drawImage(earth, 0, 0);


		// choque de bala con virus

//		for (int i = 2; i <= sprites.size() - 1; i++) {
//
//			if (virus.intersects(sprites.get(i)) == true & virus.getHealth() == 1) {
//				sprites.remove(virus);
//				sprites.remove(i);
//
//				virus.setHealth(0);
//
//				// modo cutre de que no entorpezca el paso
//				virus.setPositionX(-1);
//				virus.setPositionY(-1);
//			}
//
//			if (virus.intersects(sprites.get(i)) == true & virus.getHealth() > 1) {
//				virus.setHealth(virus.getHealth() - 1);
//
//				// modo cutre de que no entorpezca el paso
//
//				sprites.get(i).setPositionX(-5);
//				sprites.get(i).setPositionY(-5);
//
//				sprites.remove(i);
//
//				System.out.println("ME DEBILITO");
//			}
//		}

		 //metodo para que dejen de renderizar las balas cuando salgan (no funciona por
		 //todos los lados creo)
//		List<Sprite> toBeReleased = new ArrayList<>();
//		sprites.stream().forEach(action);
//		for (int i = 0; i < sprites.size(); i++) {
//
//			if (sprites.get(i).getPositionX() > canvas.getWidth()
//					| sprites.get(i).getPositionY() > canvas.getHeight()) {
//				sprites.remove(i);
//				System.out.println("PUERTO RICO ME LO REGALO");
//
//			}
//			// Método a probar para que funcione por los lados restantes
//			else if (sprites.get(i).getPositionX() <= -sprites.get(i).getWidth() / 2
//					| sprites.get(i).getPositionY() <= -sprites.get(i).getHeight() / 2) {
//				sprites.remove(i);
//				System.out.println("DOMINICANA YA REPLICÓ");
//			}
//
//		}
		
		sprites.stream().forEach(s -> s.update(timeDiff));
		sprites.stream().forEach(s -> s.render(gc));

		lastNanoTime = now;

	}

}
