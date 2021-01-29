package dad.immuneDefense.Mapa;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import dad.immuneDefense.enemies.SpriteSencillo;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MapaSinTiled extends Application  {

private PruebaMenu vista;
	
	
	
public Canvas canvas;


public final Canvas getCanvas() {
	return canvas;
}

private void draw(Canvas canvas) {
    int width = (int) canvas.getWidth();
    int height = (int) canvas.getHeight();
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.clearRect(0, 0, width, height);
    gc.setStroke(Color.RED);
    gc.strokeLine(0, 0, width, height);
    gc.strokeLine(0, height, width, 0);
    gc.setFill(Color.BLUE);
    gc.fillOval(-30, -30, 60, 60);
    gc.fillOval(-30 + width, -30, 60, 60);
    gc.fillOval(-30, -30 + height, 60, 60);
    gc.fillOval(-30 + width, -30 + height, 60, 60);
}


public void start(Stage theStage) throws IOException 
{
	   theStage.setTitle( "Canvas Example" );
       
	   //NUeEVO
	   vista = new PruebaMenu();
	   		Scene scene = new Scene(vista.getVistaBorderPane());
	   
	    Group root = new Group();
	    Scene theScene = new Scene( root );
	    theStage.setScene( scene );
	         
	     canvas = new Canvas( 500, 400 );
	     
	     PruebaMenu.getPaneCanvas().getChildren().add(canvas);
	     canvas.widthProperty().bind(PruebaMenu.getPaneCanvas().widthProperty());
	     canvas.heightProperty().bind(PruebaMenu.getPaneCanvas().heightProperty());
	  
	     
	    root.getChildren().add( canvas );
	         
	    final GraphicsContext gc = canvas.getGraphicsContext2D();
	     /*    
	    gc.setFill( Color.RED );
	    gc.setStroke( Color.BLACK );
	    gc.setLineWidth(2);
	    Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
	    gc.setFont( theFont );
	    gc.fillText( "Hello, World!", 60, 50 );
	    gc.strokeText( "Hello, World!", 60, 50 );
	     */
	    
			FileInputStream inputstream = new FileInputStream("C:\\Users\\Usuario\\eclipse-workspace\\JuegoFX\\src\\main\\resources\\CELL-970x400.png");
			  final Image earth = new Image(inputstream);
			  
			//Virus moviendose
			   final SpriteSencillo virus = new SpriteSencillo();
				  FileInputStream inputstreamVirus = new FileInputStream("C:\\Users\\Usuario\\Desktop\\Fvbjf7Z.png");
					 Image im = new Image(inputstreamVirus);
					   virus.setImage(im);
					   virus.setPositionX(0);
					   virus.setPositionY(0);
					   virus.setVelocityX(0.05);
					   
					   //Torreta quieta
						   final SpriteSencillo torreta = new SpriteSencillo();
						   FileInputStream	   inputstreamTorreta = new FileInputStream("C:\\Users\\Usuario\\Desktop\\ImagenVirus.png");
						   Image imageTorreta = new Image(inputstreamTorreta);
						   torreta.setImage(imageTorreta);
						   torreta.setPositionX(50);
						   torreta.setPositionY(200);
						   
						   //disparo torreta
						   final SpriteSencillo bala = new SpriteSencillo();
							  FileInputStream inputstreambala = new FileInputStream("C:\\Users\\Usuario\\Desktop\\anticuerpos.jpg");
								 Image imagenBala = new Image(inputstreambala);
								 bala.setHeight(1);
								 bala.setWidth(1);
								 bala.setImage(imagenBala);
								 bala.setPositionX(50);
								 bala.setPositionY(200);
								 bala.setVelocityX(0.1);
								 
								
								 
			
				   final long startNanoTime = System.nanoTime();
	   new AnimationTimer()
	   {
		   public void handle(long currentNanoTime) 
		    {
			  
			   gc.drawImage( earth, 0, 0 );

			   double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
			   
		
			   if(virus.getPositionX()>201) {
				   virus.setVelocityY(0.5);
				   virus.setVelocityX(0);
			   }
			   
			   if(virus.getPositionY()>201) {
				   virus.setVelocityY(0);
				   virus.setVelocityX(0.09);
			   }
		
			   
			   
			bala.render(gc);   
		   torreta.render(gc);
		   virus.render(gc);
		 
		
		  virus.update(t);
		  bala.update(t);
		 
		    }
	   }.start(); 
	   
	    theStage.show();
}
public static void main(String[] args) 
{
    launch(args);
}

}