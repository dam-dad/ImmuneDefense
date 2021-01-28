package dad.immuneDefense.Mapa;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import dad.immuneDefense.enemies.SpriteSencillo;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MapaSinTiled extends Application  {





public void start(Stage theStage) throws FileNotFoundException 
{
	   theStage.setTitle( "Canvas Example" );
       
	    Group root = new Group();
	    Scene theScene = new Scene( root );
	    theStage.setScene( theScene );
	         
	    Canvas canvas = new Canvas( 500, 400 );
	 
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
			  Image earth = new Image(inputstream);
			   gc.drawImage( earth, 0, 0 );
			
			   final SpriteSencillo virus = new SpriteSencillo();
				  FileInputStream inputstream2;
				
					inputstream2 = new FileInputStream("F:\\fp DAM\\segundo\\wilmer\\pygames\\bola.png");
							//"C:\\Users\\Usuario\\Desktop\\ImagenVirus.png");
					 Image im = new Image(inputstream2);
					   virus.setImage(im);
					   virus.setPositionX(0);
					   virus.setPositionY(0);
					
						   virus.setVelocityX(0.05);
					   
				 
				  
				
				  /* double px = 350 * Math.random() + 50;
				    double py = 350 * Math.random() + 50;
				    	  
			              
				    virus.setPositionX(px);
					   virus.setPositionY(py);
				   */
				   final long startNanoTime = System.nanoTime();
	   new AnimationTimer()
	   {
		   public void handle(long currentNanoTime)
		    {
			  
			   double elapsedTime = (currentNanoTime - startNanoTime) / 1000000000.0;
			   virus.update(elapsedTime);
			   if(virus.getPositionX()>50) {
				   virus.setVelocityY(0.05);
				   virus.setVelocityX(0);
			   }
		   
		   virus.render(gc);
		    }
	   }.start(); 
	   
	    theStage.show();
}
public static void main(String[] args) 
{
    launch(args);
}

}