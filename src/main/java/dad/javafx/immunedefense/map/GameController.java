package dad.javafx.immunedefense.map;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import dad.javafx.immunedefense.model.Background;
import dad.javafx.immunedefense.model.Base;
import dad.javafx.immunedefense.model.Bullet;
import dad.javafx.immunedefense.model.Muro;
import dad.javafx.immunedefense.model.Sprite;
import dad.javafx.immunedefense.model.Turret;
import dad.javafx.immunedefense.model.Virus;
import javafx.animation.AnimationTimer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameController extends AnimationTimer implements Initializable {

	// model
	
	private double time = 0.0; 
	private double lastNanoTime;
	
		
	private List<Sprite> sprites = new ArrayList<>();
	private Background background; 
	
	private Base base;

	// view

	@FXML
	private StackPane view;

	@FXML
	private Canvas canvas;
	
   
        //menu de arriba
    @FXML
    private Label etiquetaTiempo;

    @FXML
    private ImageView vida;
   
    //boton reiniciar
    @FXML
    private Button botonReiniciar;
    
    
    //Placements turrets
    @FXML
    private Button botonLugarTorreta1;
    
    @FXML
    private Button botonLugarTorreta2;
    
    @FXML
    private Button botonCoordenadasMuro;
    
    
    //coordenadas
     private double x;
    
    private double y;
    
    
    //lista botones
    List<Button> botones = new ArrayList<>();
    
    
    @FXML
    void OnVolverAlMenuAction(ActionEvent event) {
//poner para volver al incio
    }
    
    //prueba fallida pero pienso que tiene que ser esta
    @FXML
    void tienequeDRAgDrop(DragEvent event) {
    	System.out.print("SOLTADO");
    }

    @FXML
    void MOUSEdragg(MouseEvent event) {
    		
   /*
    	
    	x=event.getSceneX();
    	
    	y=event.getSceneY();
    */	
    
    }
    
    @FXML
    void Colocar(ActionEvent event) {

    	Turret torreta = new Turret(1, 0.25);
    	torreta.setPositionX(x);
    	torreta.setPositionY(y);
       	torreta.setGame(this);

    	
    	
    }
    
    @FXML
    void OnMuroAction(ActionEvent event) {
    	if(x!=0 & y!=0 ) {	
    	Muro muro = new Muro();
    	muro.setPositionX(x);
    	muro.setPositionY(y);
    	
    	
    	
    	List<Muro> PLacementMuros = new ArrayList<>(getSprites(Muro.class));
		boolean colocar=true;
		
		for (Muro muros : PLacementMuros) {
    	if(muro.intersects(muros)) {
    		colocar=false;
        	
    	}
		}
		if (colocar==true) {
			muro.setGame(this);
			//botonCoordenadasMuro.setVisible(false);
			//botonCoordenadasMuro.setDisable(true);
		}
		x=0;
    	y=0;
        }
    	   	
    	}
    
    
    
    @FXML
    void obtenerCoordenadas(MouseEvent event) {

    	Bounds boundsInScreen = botonLugarTorreta1.localToScene(botonLugarTorreta1.getBoundsInLocal());
    	int xRedondeo=(int)boundsInScreen.getMinX();
    	int yRedondeo=(int)boundsInScreen.getMinY();
    	
   	 x=xRedondeo;
    	
    	y=yRedondeo;
    	    
   	
   	System.out.println(x);
   	System.out.println(y);
    	//x=event.getSceneX();
    	
    	//y=event.getSceneY();
    	    	
    	
    	//coordenadas buenas
    	//x=551.0;
    	//y=127.0;
    }
    
    @FXML
    void obtenerCoordenadas2(MouseEvent event) {
    	Bounds boundsInScreen = botonLugarTorreta2.localToScene(botonLugarTorreta2.getBoundsInLocal());
    	
    	
       	int xRedondeo=(int)boundsInScreen.getMinX();
    	int yRedondeo=(int)boundsInScreen.getMinY();
    	
   	 x=xRedondeo;
    	
    	y=yRedondeo;
    	    
   	
   	System.out.println(x);
   	System.out.println(y);
     	
    }
  
    @FXML
    void obtenerCoordenadas3(MouseEvent event) {
    	Bounds boundsInScreen = botonCoordenadasMuro.localToScene(botonCoordenadasMuro.getBoundsInLocal());
    	
    	
       	int xRedondeo=(int)boundsInScreen.getMinX();
    	int yRedondeo=(int)boundsInScreen.getMinY();
    	
   	 x=xRedondeo;
    	
    	y=yRedondeo;
    	    
   	
   	System.out.println(x);
   	System.out.println(y);
     	
    }
      
    
    @FXML
    void onColocarTorreta(ActionEvent event) {

        if(x!=0 & y!=0 ) {	
    	Turret torreta = new Turret(1, 0.25);
    	torreta.setPositionX(x);
    	torreta.setPositionY(y);
    		
    	
		List<Turret> PLacementTorretas = new ArrayList<>(getSprites(Turret.class));
		boolean colocar=true;
		//comprobar si ya hay una torreta
		for (Turret turret : PLacementTorretas) {
    	if(torreta.intersects(turret)) {
    		colocar=false;
        	
    	}
		}
		if (colocar==true) {
       	torreta.setGame(this);
       	
       	//bucle con lista de todos los botones y les pillamos las coordenadas el que coincida con las que tenemos lo hace invisible
       	
    	for (Button boton : botones) {

			// comprobar el redondeo
    		//System.out.println((int)boton.localToScene(boton.getBoundsInLocal()).getMinX());
     		//System.out.println((int)boton.localToScene(boton.getBoundsInLocal()).getMinY());
			if (x==(int)boton.localToScene(boton.getBoundsInLocal()).getMinX()-1& y==(int)boton.localToScene(boton.getBoundsInLocal()).getMinY()-1) {
				boton.setVisible(false);
			}
    	}
       	
		}
		x=0;
    	y=0;
        }
		
    }
    
	public GameController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/PanelJuegoFX.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {

		spritesPrincipales();
		
		lastNanoTime = System.nanoTime();
		
		
		//intento de poner el tiempo pero no recuerdo como bindearlo
		etiquetaTiempo.setText(lastNanoTime+"");
		//textProperty().bindBidirectional(tiempoMenu+"");
		
		
		vida.setImage(new Image("/mapImages/vida.png"));
		
		
		//quitar los bordes del boton
		botonLugarTorreta1.setPadding(new Insets(-1,-1,-1,-1));
		botonLugarTorreta2.setPadding(new Insets(-1,-1,-1,-1));
		
	//cambia el fondo del boton
		botonCoordenadasMuro.setStyle("-fx-background-color: #00ff00");
		
		//añadir botones a la lista
		botones.add(botonLugarTorreta1);
		botones.add(botonLugarTorreta2);
		botones.add(botonCoordenadasMuro);
		
		start(); // inicia el animationtimer
		
	}

	private void spritesPrincipales() {
		
		background = new Background();
		background.setPositionX(0);
		background.setPositionY(0);
		background.setWidth(canvas.getWidth());
		background.setHeight(canvas.getHeight());
		background.setGame(this);

		Virus rhinitis = new Virus();
		rhinitis.setPositionX(0);
		rhinitis.setPositionY(0);
		rhinitis.setVelocityX(50);
		rhinitis.setVelocityY(25);
		//rhinitis.setWidth(200);
		//rhinitis.setHeight(200);
		rhinitis.setGame(this);
		
		
		 base = new Base();
			base.setGame(this);
		
/*
		Turret turret = new Turret(1, 0.5);
		turret.setPositionX(50);
		turret.setPositionY(200);
		turret.setHeight(200);
		turret.setWidth(200);
		turret.setGame(this);
*/
	}
	
	public <T extends Sprite> List<T> getSprites(Class<T> type) {
		return sprites.stream().filter(s -> s.getClass().equals(type)).map(s -> type.cast(s)).collect(Collectors.toList());
	}
	
	public List<Sprite> getSprites() {
		return sprites;
	}

	public Parent getView() {
		return view;
	}

	@Override
	public void handle(long now) {
		final GraphicsContext gc = canvas.getGraphicsContext2D();

		double timeDiff = (now - lastNanoTime) / 1000000000.0; // time difference between frames in
																			// seconds


		List<Virus> safeViruses = new ArrayList<>(getSprites(Virus.class));
		List<Bullet> safeBullets = new ArrayList<>(getSprites(Bullet.class));
		List<Muro> safeMuros = new ArrayList<>(getSprites(Muro.class));
		List<Turret> safeTorretas = new ArrayList<>(getSprites(Turret.class));
		
		//Se comprueba las coliciones de los virus con cosas
		for (Virus virus : safeViruses) { 

			//balas
			for (Bullet bullet : safeBullets) {

				// comprueba si colisiona la bala y el virus
				if (bullet.intersects(virus)) {
					// la bala impacta en el virus
					virus.impact(bullet);
				}
				
			}
			//muros
			for (Muro muro : safeMuros) {

				// comprueba si colisiona el murp y el virus
				if (muro.intersects(virus)) {
					// el muro impacta en el virus
					virus.chocqueMuro(muro);
				}
				
			}
			//torretas
			for (Turret turret : safeTorretas) {

				// comprueba si colisiona el murp y el virus
				if (turret.intersects(virus)) {
					// el muro impacta en el virus
					virus.choqueTorreta(turret);
				}
				
			}
			//base
			if(base.intersects(virus)) {
				if(base.getHealth()>0) {
				base.setHealth(base.getHealth()-1);
				virus.kill();
				vida.setImage(new Image("/mapImages/vida2.png"));
				
				}
				if(base.getHealth()<1) {
				 gc.setFill( Color.RED );
				    gc.setStroke( Color.BLACK );
				    gc.setLineWidth(2);
				    Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
				    gc.setFont( theFont );
				    gc.fillText( "GAME OVER!", 60, 50 );
				    gc.strokeText( "GAME OVER!", 60, 50 );
				    botonReiniciar.setVisible(true);
				    botonReiniciar.setDisable(false);
				    
				    stop();
				}
			
			}
			
		}
		
		// metodo para que dejen de renderizar las balas cuando salgan (no funciona por todos los lados creo)
		for (Bullet bullet : safeBullets) {
			
			if (!bullet.intersects(background)) {
				System.out.println("bala perdida");
				bullet.kill(); // bala perdida
			}
			
		}
		// metodo para que dejen de renderizar los virus al salir del background
	for (Virus virus : safeViruses) {
			
			if (!virus.intersects(background)) {
				System.out.println("virus perdido");
				virus.kill(); 
			}
			
		}
		
		
		
		List<Sprite> safeSprites = new ArrayList<>(sprites);
		safeSprites.stream().forEach(s -> s.update(timeDiff));
		safeSprites.stream().forEach(s -> s.render(gc));
		
		//virus saliendo todo el rato
		time += timeDiff;
	if(time>5) {
		
		Random r = new Random();
	
		int posicionRamdon = r.nextInt(400-1) + 1;
		int velocidadRamdon = r.nextInt(80-20) + 20;
		
	
		Virus corona = new Virus();
		corona.setPositionX(0);
		corona.setPositionY(r.nextInt(100-1) + 1);
		corona.setVelocityX(r.nextInt(80-20) + 20);
		corona.setVelocityY(r.nextInt(80-20) + 20);
		//rhinitis.setWidth(200);
		//rhinitis.setHeight(200);
		corona.setGame(this);
		time = 0.0;
		
	}
	
	//hacer visible el placement de nuevo
	
	for (Button boton : botones) {
		if(boton.isVisible()==false ) {
			visiblePlacement(boton);
		}
	}
	

		lastNanoTime = now;

	}
	
	
	//metodo visible el placement
public void  visiblePlacement(Button boton) {
	
	Bounds boundsInScreen = boton.localToScene(boton.getBoundsInLocal());
	
	//pongo esto por que me rondeo el hijo de la gran puta
int posX=(int) boundsInScreen.getMinX()-1;
int posY=(int) boundsInScreen.getMinY()-1;
	boolean colocar=true;

	List<Turret> safeTorretas = new ArrayList<>(getSprites(Turret.class));
	
	for (Turret turret : safeTorretas) {

	if(turret.getPositionX()==posX & turret.getPositionY()== posY  ) {
		colocar=false;
		
	}
	}
	if (colocar==true) {
		
		boton.setVisible(true);
	}
	
	
	
	
}
	

}
