package dad.javafx.immunedefense.map;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dad.javafx.immunedefense.enemies.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MapaSinTiledApp extends Application {

	private PruebaMenu vista;

	private Canvas canvas;
	
	//Virus Components
	private Sprite virus;
	
	//Torreta Components
	private Sprite torreta;
	
	//Balas Components
	private Sprite bala;
	
	//Current Time
	private long startNanoTime;
	
	
	public void start(Stage theStage) throws IOException {
		theStage.setTitle("Canvas Example");
		theStage.setResizable(false);
		
		vista = new PruebaMenu();
		
		vista.setCanvas_center(canvas);
		
		Group root = new Group();
		Scene theScene = new Scene(root);
		
		Scene sceneCanvas = new Scene(vista.getVistaBorderPane());
		
		theStage.setScene(theScene);

		canvas = new Canvas(800, 600);
		root.getChildren().add(canvas);	
		
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		/*
		 * gc.setFill( Color.RED ); gc.setStroke( Color.BLACK ); gc.setLineWidth(2);
		 * Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
		 * gc.setFont( theFont ); gc.fillText( "Hello, World!", 60, 50 ); gc.strokeText(
		 * "Hello, World!", 60, 50 );
		 */

		final Image earth = new Image("/mapImages/Terrain.png");

		// Virus moviendose
		virus = new Sprite("/mapImages/Virus Guille.png");
		virus.setPositionX(0);
		virus.setPositionY(0);
		virus.setVelocityX(20);
		virus.setWidth(200);
		virus.setHeight(200);
		virus.setHealth(3);

		// Torreta quieta
		torreta = new Sprite("/mapImages/Turret.png");
		torreta.setPositionX(50);
		torreta.setPositionY(200);
		torreta.setHeight(200);
		torreta.setWidth(200);

		// Balas torreta
		bala = new Sprite("/mapImages/Proyectile.png");
		bala.setHeight(1);
		bala.setWidth(1);
		bala.setPositionX(50);
		bala.setPositionY(300);
		bala.setVelocityX(120);
		bala.setHeight(200);
		bala.setWidth(200);

		startNanoTime = System.nanoTime();
		
		List<Sprite> sprites = new ArrayList<>();
		sprites.add(torreta);
		sprites.add(virus);
		sprites.add(bala);
		
		
		new AnimationTimer() {
			double time = 0.0; 			
			
			public void handle(long currentNanoTime) {
				double timeDiff = (currentNanoTime - startNanoTime) / 1000000000.0; // time difference between frames in seconds
				
				//List<Sprite> balasLista = new ArrayList<>();
				
				time += timeDiff;
				if (time > 2) {
					Sprite bala = new Sprite("/mapImages/Proyectile.png");
					bala.setHeight(1);
					bala.setWidth(1);
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

				//moviemiento del viruzaso
				if (virus.getPositionX() > 290) {
					virus.setVelocityY(16);
					virus.setVelocityX(0);
				}

				if (virus.getPositionY() >= 200) {
					virus.setVelocityY(0);
					virus.setVelocityX(10);
				}
				
				
				//choque de bala con virus
				
				for(int i=2;i<sprites.size();i++) {
					
					if(virus.intersects(sprites.get(i))==true & virus.getHealth()==1) {
						sprites.remove(virus);
						sprites.remove(i);
						virus.setHealth(0);
						
						//modo cutre de que no entorpezca el paso
						virus.setPositionX(-1);
						virus.setPositionY(-1);
					}
					
					if(virus.intersects(sprites.get(i))==true & virus.getHealth()>1) {
						virus.setHealth(virus.getHealth()-1);
						
						//modo cutre de que no entorpezca el paso
						sprites.get(i).setPositionX(-5);
						sprites.get(i).setPositionY(-5);
												
						sprites.remove(i);
						
						System.out.println("ME DEBILITO");
					}
					
				}
				
				//metodo para que dejen de renderizar las balas cuando salgan (solo por el lado de la izquierda funciona)
				for(int i=0;i<sprites.size();i++) {
					
					if(sprites.get(i).getPositionX()>canvas.getWidth()) {
						sprites.remove(i);
						System.out.println("PUERTO RICO ME LO REGALO");
						
					}
					
				}
				
				sprites.stream().forEach(s -> s.update(timeDiff));
				sprites.stream().forEach(s -> s.render(gc));
				
				
				
				startNanoTime = currentNanoTime;
			}
		}.start();

		theStage.show();
	}

	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public final Canvas getCanvas() {
		return canvas;
	}
}