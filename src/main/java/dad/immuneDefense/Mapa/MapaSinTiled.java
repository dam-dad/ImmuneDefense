package dad.immuneDefense.Mapa;

import java.io.FileInputStream;
import java.io.IOException;

import dad.immuneDefense.enemies.SpriteSencillo;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MapaSinTiled extends Application {

	private PruebaMenu vista;

	private Canvas canvas;
	
	//Virus Components
	private SpriteSencillo virus;
	private FileInputStream inputStreamVirus;
	private Image virusImage;
	
	//Torreta Components
	private SpriteSencillo torreta;
	private FileInputStream inputStreamTorreta;
	private Image imageTorreta;
	
	//Balas Components
	private SpriteSencillo bala;
	private FileInputStream inputStreamBala;
	private Image imageBala;
	
	//Current Time
	private long startNanoTime;
	
	//Proceso FireRate
	private DispararBalas fireRate;
	
	
	public void start(Stage theStage) throws IOException {
		theStage.setTitle("Canvas Example");

		/*
		 * //NUeEVO vista = new PruebaMenu(); Scene scene = new
		 * Scene(vista.getVistaBorderPane());
		 */
		Group root = new Group();
		Scene theScene = new Scene(root);
		theStage.setScene(theScene);

		canvas = new Canvas(1920, 1080);

		/*
		 * PruebaMenu.getPaneCanvas().getChildren().add(canvas);
		 * canvas.widthProperty().bind(PruebaMenu.getPaneCanvas().widthProperty());
		 * canvas.heightProperty().bind(PruebaMenu.getPaneCanvas().heightProperty());
		 * 
		 * 
		 * // redraw when resized canvas.widthProperty().addListener(event ->
		 * draw(canvas)); canvas.heightProperty().addListener(event -> draw(canvas));
		 * draw(canvas);
		 */

		root.getChildren().add(canvas);

		final GraphicsContext gc = canvas.getGraphicsContext2D();
		/*
		 * gc.setFill( Color.RED ); gc.setStroke( Color.BLACK ); gc.setLineWidth(2);
		 * Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
		 * gc.setFont( theFont ); gc.fillText( "Hello, World!", 60, 50 ); gc.strokeText(
		 * "Hello, World!", 60, 50 );
		 */

		FileInputStream inputstream = new FileInputStream("src\\main\\resources\\mapImages\\Terrain.png");
		final Image earth = new Image(inputstream);

		// Virus moviendose
		virus = new SpriteSencillo();
		inputStreamVirus = new FileInputStream("src\\main\\resources\\mapImages\\Virus Guille.png");
		virusImage = new Image(inputStreamVirus);
		virus.setImage(virusImage);
		virus.setPositionX(600);
		virus.setPositionY(300);
		virus.setVelocityX(0.05);
		virus.setWidth(200);
		virus.setHeight(200);

		// Torreta quieta
		torreta = new SpriteSencillo();
		inputStreamTorreta = new FileInputStream("src\\main\\resources\\mapImages\\Turret.png");
		imageTorreta = new Image(inputStreamTorreta);
		torreta.setImage(imageTorreta);
		torreta.setPositionX(50);
		torreta.setPositionY(200);
		torreta.setHeight(200);
		torreta.setWidth(200);

		// Balas torreta
		bala = new SpriteSencillo();
		inputStreamBala = new FileInputStream("src\\main\\resources\\mapImages\\Proyectile.png");
		imageBala = new Image(inputStreamBala);
		bala.setHeight(1);
		bala.setWidth(1);
		bala.setImage(imageBala);
		bala.setPositionX(50);
		bala.setPositionY(300);
		bala.setVelocityX(0.2);
		bala.setHeight(200);
		bala.setWidth(200);

		startNanoTime = System.nanoTime();
		
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				
				gc.drawImage(earth, 0, 0);

				double t = (currentNanoTime - startNanoTime) / 1000000000.0;
				/*
				if (virus.getPositionX() > 201) {
					virus.setVelocityY(0.5);
					virus.setVelocityX(0);
				}

				if (virus.getPositionY() > 201) {
					virus.setVelocityY(0);
					virus.setVelocityX(0.09);
				}
				*/
				
				torreta.render(gc);
				fireRate = new DispararBalas(bala,virus,gc,t);
				fireRate.run();
				
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