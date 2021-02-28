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
import dad.javafx.immunedefense.model.Explotion;
import dad.javafx.immunedefense.model.Moneda;
import dad.javafx.immunedefense.model.Muro;
import dad.javafx.immunedefense.model.SoundEffects;
import dad.javafx.immunedefense.model.Sprite;
import dad.javafx.immunedefense.model.Turret;
import dad.javafx.immunedefense.model.Virus;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * @author Christian,Adrian,Guillermo,Jose_Juan
 * Esta clase está dirigida a todos los eventos y acciones que pasan en el juego.
 */
public class GameController extends AnimationTimer implements Initializable {

	//loader que inicializa un FXML concreto, el cual viene determinado por
	//la variable nivel, la cual determina en cualo nivel está apareciendo en pantalla
	FXMLLoader loader;
	private int nivel;

	//Botones los cuales van a aparecer en el nivel fácil, aunque algunos de ellos
	//son reutilizados en los siguientes niveles.

	@FXML
	private Button botonLugarTorreta3;

	@FXML
	private Button botonLugarTorreta4;

	@FXML
	private Button botonLugarTorreta5;

	@FXML
	private Button botonLugarTorreta6;

	@FXML
	private Button botonLugarTorreta7;

	//Variables dirigidas a calcular el tiempo que se ha pasado y así calcular las monedas las cuales obtiene el usuario.
	private double timeVirus = 0.0;
	private double lastNanoTime;

	//Variables dirigidas a la Cuenta Atrás de Arriba a la izquierda, mostrando así cuanto tiempo falta para ganar el nivel.
	private Timeline timeline;
	private int startTime = 180;
	private IntegerProperty timeSeconds;

	//Variable dirigida a las monedas que se obtienen por la cantidad de tiempo que está la base viva.
	private double timeCoins = 0.0;

	//Lista de Sprites los cuales van a ser usados.
	private List<Sprite> sprites = new ArrayList<>();
	private Background background;

	private Base base;

	// Variables sacadas del FXML

	@FXML
	private StackPane view;

	@FXML
	private Canvas canvas;
	
	@FXML
	private AnchorPane gameOver;

	@FXML
	private Pane backgroundGameOverPane;

	// menu de arriba
	@FXML
	private Label etiquetaTiempo;

	@FXML
	private ImageView vida;

	@FXML
	private Label etiquetaDinero;

	private Moneda moneda = new Moneda();

	// boton reiniciar
	@FXML
	private Button botonReiniciar;

	// Placements turrets y muros
	@FXML
	private Button botonLugarTorreta1;

	@FXML
	private Button botonLugarTorreta2;

	@FXML
	private Button botonCoordenadasMuro;

	@FXML
	private Button botonCoordenadasMuro1;

	// Boton el cual añade una vida más por un cierto precio

	@FXML
	private Button botonVida;

	// YouWinPane dedicado a la pantalla que sale cuando se gana el juego

	@FXML
	private AnchorPane youWinPane;

	@FXML
	private Button continuarButton;
	
	@FXML
    private Button buttonCreditos;
	
	@FXML
	private Pane backgroundYouWinPane;

	@FXML
	private Button volverMenuButton;

	// Variable booleana importante de uso diferenciador entre si un placement es de muro o no.
	boolean esMuro;

	// coordenadas
	private double x;

	private double y;

	//Lista de Botones dirigidos al despliegue de torreta y muros sobre el campo de batalla, respectivamente.
	List<Button> botonesTorretas = new ArrayList<>();

	List<Button> botonesMuros = new ArrayList<>();
	
	/**
	 * setOnAction dirigido a el caso en que el usuario hay decidido colocar una torreta doble, la
	 * cual dispara horizontalmente.
	 * @param event
	 */
	@FXML
	void ColocarTorretaDoble(ActionEvent event) {
		
		/*
		 * If dedicado a la comprobación de el placement elegido por el usuario en primera Instancia,
		 * y de la solvencia del mismo (refiriendome así a si tiene o no la cantidad necesaria para
		 * desplegar la torreta en cuestión).
		 */
		if (x != 0 & y != 0 & esMuro == false & moneda.getmoneda() > 7) {
			//Aquí se crea la torreta en cuestión
			Turret torreta = new Turret(1, 0.25, 2);
			torreta.setPositionX(x);
			torreta.setPositionY(y);
			
			/*
			 * En esta List, es donde se almacenan los placements, los cuales van a ser usados en el nivel en cuestión y 
			 * filtrados por el método getSprites, el cual mostraremos después.
			 */
			List<Turret> PLacementTorretas = new ArrayList<>(getSprites(Turret.class));
			boolean colocar = true;
			
			/*
			 * Foreach dedicado a encontrar el placement el cual ha sido elegido por el usuario, mediante la variable colocar.
			 */
			for (Turret turret : PLacementTorretas) {
				if (torreta.intersects(turret)) {
					colocar = false;

				}
			}
			
			/*
			 * En el caso de que la variable colocar sea true, se determina el controlador de la torreta (setGame(this)) y se cobra
			 * el coste de la torreta.
			 */
			if (colocar == true) {
				torreta.setGame(this);
				moneda.setMoneda(moneda.getmoneda() - 7);
				
				/*
				 *For each dedicado a determinat si el placement en cuestión existe, y en el caso que si, 
				 *el mismo placement se vuelve invisible
				 */
				for (Button boton : botonesTorretas) {

					// Método para determinar el botón en cuestión mediante el uso de bounds, a la vez de 
					if (x == (int) boton.localToScene(boton.getBoundsInLocal()).getMinX() - 1
							& y == (int) boton.localToScene(boton.getBoundsInLocal()).getMinY() - 1) {
						boton.setVisible(false);
					}
				}

			}
			x = 0;
			y = 0;

		}

	}

	/**
	 * seOnAction dirigido al despliegue de muros en sus respectivo lugar, decisión tomada por el usuario.
	 * @param event
	 */
	@FXML
	void OnMuroAction(ActionEvent event) {
		/*
		 * Condicionante dedicado a la comprobación de el placement elegido por el usuario en primera Instancia,
		 * y de la solvencia del mismo (refiriendome así a si tiene o no la cantidad necesaria para
		 * desplegar el muro en cuestión).
		 */
		if (x != 0 & y != 0 & esMuro == true & moneda.getmoneda() > 35) {
			
			//Aquí se declara el nuevo muro
			Muro muro = new Muro();
			muro.setPositionX(x);
			muro.setPositionY(y);
			
			/*
			 * Lista dedicada a guardar todos los placements los cuales estén dedicados a colocar muros,
			 * el cual utiliza el método getSprites, para filtrar entre todos los placements que hay.
			 */
			List<Muro> PLacementMuros = new ArrayList<>(getSprites(Muro.class));
			boolean colocar = true;

			/*
			 * Foreach dedicado a buscar el placements de muro el cual el jugador a elegido en primera instancia.
			*/
			for (Muro muros : PLacementMuros) {
				if (muro.intersects(muros)) {
					colocar = false;

				}
			}
			
			/*
			 * En el caso de que sea correcta las coordenadas del placement de muro, seteamos el controlador del Sprite del muro, 
			 * le cobramos el coste del msimo y determinamos en que placement va a ser colocada la torreta. 
			 */
			if (colocar == true) {
				muro.setGame(this);
				moneda.setMoneda(moneda.getmoneda() - 35);
				//Dar Invisibilidad a el placement elegido en concreto
				for (Button boton : botonesMuros) {
					//Condicionante para determinar el placement elegido
					if (x == (int) boton.localToScene(boton.getBoundsInLocal()).getMinX() - 1
							& y == (int) boton.localToScene(boton.getBoundsInLocal()).getMinY() - 1) {
						boton.setVisible(false);
					}
				}

			}
			x = 0;
			y = 0;
		}

	}
	/**
	 * Método determinado para saber las coordenadas del botón elegido, mediante el uso de ActionEvents.
	 * @param event
	 */

	@FXML
	void obtenerCoordenadas(ActionEvent event) {
		
		//Aquí se obtiene el botón el cual ha sido pulsado
		Button botonPulsado = (Button) event.getSource();

		/*
		 * En esta parte se obtienen los bounds del boton pulsado mediante el método localToScene. Eso sí,
		 * para obtener la x e y pertinentes se redondean a enteros las coordenadas de los bounds en cuestión.
		 */
		Bounds coordenadas = botonPulsado.localToScene(botonPulsado.getBoundsInLocal());
		int xRedondeo = (int) coordenadas.getMinX();
		int yRedondeo = (int) coordenadas.getMinY();

		//Aquí se declaran las coordenadas x e y, ya redondeadas.
		x = xRedondeo;

		y = yRedondeo;

		esMuro = false;

		/*
		 * Foreach usado para determinar si el placement seleccionado es muro o no, cual variable usada antes en el
		 * despliegue o no de los muros.
		 */
		for (Button boton : botonesMuros) {
			if (boton == botonPulsado) {
				esMuro = true;
				
			}
		}
	}

	@FXML
	void onColocarTorretaCuadruple(ActionEvent event) {

		if (x != 0 & y != 0 & esMuro == false & moneda.getmoneda() > 15) {
			Turret torreta = new Turret(1, 0.25, 4);
			torreta.setPositionX(x);
			torreta.setPositionY(y);

			List<Turret> PLacementTorretas = new ArrayList<>(getSprites(Turret.class));
			boolean colocar = true;
			// comprobar si ya hay una torreta
			for (Turret turret : PLacementTorretas) {
				if (torreta.intersects(turret)) {
					colocar = false;

				}
			}
			if (colocar == true) {
				torreta.setGame(this);
				moneda.setMoneda(moneda.getmoneda() - 15);
				// bucle con lista de todos los botones y les pillamos las coordenadas el que
				// coincida con las que tenemos lo hace invisible

				for (Button boton : botonesTorretas) {

					// comprobar el redondeo
					if (x == (int) boton.localToScene(boton.getBoundsInLocal()).getMinX() - 1
							& y == (int) boton.localToScene(boton.getBoundsInLocal()).getMinY() - 1) {
						boton.setVisible(false);
					}
				}

			}
			x = 0;
			y = 0;
		}

	}

	@FXML
	void onMasUnaVida(ActionEvent event) {
		if (moneda.getmoneda() > 50 & base.getHealth() < 4) {

			base.setHealth(base.getHealth() + 1);
			moneda.setMoneda(moneda.getmoneda() - 50);
			ponerImagenVida();

			if (base.getHealth() == 4) {

				botonVida.setDisable(true);
			}

		}
	}
	
	/**
	 * Constructor de la clase GameController, y que determina a su vez el nivel, a su vez el FXML, el cual va a ser cargado.
	 * @param level Variable la cual determina el nivel al cual se va a acceder.
	 */
	public GameController(int level) {
		//variable dirigida a almacenar el nivel en el cual se está trabajando.
		this.nivel = level;
		
		//Try-catch para atrapar las IOException a la hora de cargar los FXML
		try {
			switch (nivel) {
			//Cargando FXML del nivel fácil
			case 0:
				loader = new FXMLLoader(getClass().getResource("/Menus/PanelJuegoFXFacil.fxml"));
				loader.setController(this);
				loader.load();
				break;

			//Cargando FXML del nivel normal
			case 1:
				loader = new FXMLLoader(getClass().getResource("/Menus/PanelJuegoFXFNormal.fxml"));
				loader.setController(this);
				loader.load();
				
				//Desabilitando e invisibilizando botones los cuales no van a ser utilizados en este nivel
				botonLugarTorreta5.setVisible(false);
				botonLugarTorreta5.setDisable(true);
				botonLugarTorreta6.setVisible(false);
				botonLugarTorreta6.setDisable(true);
				botonLugarTorreta7.setVisible(false);
				botonLugarTorreta7.setDisable(true);
				break;

			//Cargando FXML del nivel dificil
			case 2:
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/PanelJuegoFX.fxml"));
				loader.setController(this);
				loader.load();
				
				//Desabilitando e invisibilizando botones los cuales no van a ser utilizados en este nivel
				botonLugarTorreta5.setVisible(false);
				botonLugarTorreta5.setDisable(true);
				botonLugarTorreta6.setVisible(false);
				botonLugarTorreta6.setDisable(true);
				botonLugarTorreta7.setVisible(false);
				botonLugarTorreta7.setDisable(true);
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Método el cual actúa como constructor, lo único que en este caso después de haber cargado el FXML.
	 */

	public void initialize(URL location, ResourceBundle resources) {
		
		//Determinar el color del fondo de las pantallas finales, en el caso de que pierda o no el usuario respectivamente. 
		backgroundGameOverPane.setStyle("-fx-background-color: #000000");
		backgroundYouWinPane.setStyle("-fx-background-color: #000000");
		
		/*
		 * Método para hacer posible la cuenta Atrás mediante l uso de un TimeLine para el uso de KeyFrames.
		 */
		timeSeconds = new SimpleIntegerProperty(startTime);
		
		/*
		 *En este caso se crea el timeline con un Keyframe, el cual hace que cada vez que pase un segundo, la variable timeSeconds cambie de valor a
		 *cuanto falte para llegar a 0, y cuando llegue a 0 se finaliza la transición, en cual caso habrás ganado y se parará el juego. Eso sí después 
		 *se usa un listener para que cada vez que baje un segundo, se muestre en la etiqueta arriba a la Izquierda.
		 *
		 */
		//Aquí se declara el Timeline con los KeyFrames
		timeline = new Timeline(new KeyFrame(Duration.seconds(startTime + 1), new KeyValue(timeSeconds, 0)));

		//Se repite solo una vez
		timeline.setCycleCount(1);
		
		//Evento para cuando sea 0 timeSeconds
		timeline.setOnFinished(e -> {
			SoundEffects.Win();
			youWinPane.setVisible(true);
			stop();
		});
		
		//Se inicia el contador
		timeline.play();

		//Listener el cual cambia el valor de la etiqueta tiempo.
		timeSeconds.addListener((obv, ov, nv) -> {
			if (ov != nv) {
				etiquetaTiempo.setText("Tiempo -> " + nv);
				
			}
		});

		//método en el que se determinan los SpritesPrincipales del Proyecto
		spritesPrincipales();

		lastNanoTime = System.nanoTime();
		
		//Declarando las vidas al principio de la partida
		vida.setImage(new Image("/mapImages/3vidas.png"));

		//En este caso se quitan los bordes del botón.
		botonLugarTorreta1.setPadding(new Insets(-1, -1, -1, -1));
		botonLugarTorreta2.setPadding(new Insets(-1, -1, -1, -1));
		botonLugarTorreta3.setPadding(new Insets(-1, -1, -1, -1));
		botonLugarTorreta4.setPadding(new Insets(-1, -1, -1, -1));
		
		//En el caso de que sea el nivel 0, se realizan también con estos botones
		if (nivel == 0) {
			botonLugarTorreta3.setPadding(new Insets(-1, -1, -1, -1));
			botonLugarTorreta4.setPadding(new Insets(-1, -1, -1, -1));
			botonLugarTorreta5.setPadding(new Insets(-1, -1, -1, -1));
			botonLugarTorreta6.setPadding(new Insets(-1, -1, -1, -1));
			botonLugarTorreta7.setPadding(new Insets(-1, -1, -1, -1));

		}

		//Se añaden los placements a la lista, y en el caso de que el nivel sea 0, se añadirán unos cuántos más
		botonesTorretas.add(botonLugarTorreta1);
		botonesTorretas.add(botonLugarTorreta2);
		botonesTorretas.add(botonLugarTorreta3);
		botonesTorretas.add(botonLugarTorreta4);

		//Este es el caso de que el nivel sea 0 a la hora de añadir los placements a la lista.
		if (nivel == 0) {

			botonesTorretas.add(botonLugarTorreta5);
			botonesTorretas.add(botonLugarTorreta6);
			botonesTorretas.add(botonLugarTorreta7);
		}
		
		botonesTorretas.add(botonLugarTorreta3);
		botonesTorretas.add(botonLugarTorreta4);
		
		//Aquí se añaden los placements de muros
		botonesMuros.add(botonCoordenadasMuro);
		botonesMuros.add(botonCoordenadasMuro1);

		start(); // inicia la animación del juego, la cual va a ser pintada sobre un Canvas

	}
	
	/**
	 * Método Utilizado para inicializar algunos de los Sprites Principales del proyecto, tales como la base y el fondo.
	 */
	private void spritesPrincipales() {
		/*
		 * En esta parte se declaran los atributos del fondo, el cual el único dinámico es la imagen, cuya variable dependiente es
		 * el nivel en el que se está.
		 */
		background = new Background();
		if (nivel == 1) {
			background.setImage(new Image("/mapImages/mapa1.png"));
		}
		else if (nivel == 0) {
			background.setImage(new Image("/mapImages/mapa2.png"));
		}
		
		//Aquí se declaran los atributos del Sprite del fondo
		background.setPositionX(0);
		background.setPositionY(0);
		background.setWidth(canvas.getWidth());
		background.setHeight(canvas.getHeight());
		background.setGame(this);
		
		//Aquí se declaran los atributos del Sprite de la base
		base = new Base();
		base.setGame(this);

	}
	
	/**
	 * Método para devolver una lista Sprites la cual sea de la clase T, la cual sí o sí tiene que ser Sprite, por que
	 * hereda de ella, además de devolver solo los que son de ese tipo
	 * @param <T> Tipo de Sprite
	 * @param type clase en cuestión, cuales Sprites se están buscando.
	 * @return La lista de Sprites, en concreto del tipo T.
	 */
	public <T extends Sprite> List<T> getSprites(Class<T> type) {
		return sprites.stream().filter(s -> s.getClass().equals(type)).map(s -> type.cast(s))
				.collect(Collectors.toList());
	}

	/**
	 * @return lista de sprites completa.
	 */
	public List<Sprite> getSprites() {
		return sprites;
	}
	
	/**
	 * @return vista Principal de la ventana del juego.
	 */
	public Parent getView() {
		return view;
	}
	
	/**
	 * @return boton el cual te retorna al menú principal, y que aparece cuando pierdes.
	 */
	public Button getBotonReiniciar() {
		return botonReiniciar;
	}
	
	/**
	 * @return boton Continuar, el cual aparece cuando ganas.
	 */
	public Button getContinuarButton() {
		return continuarButton;
	}
	
	/**
	 * @return boton de volver al menu, el cual aparece cuando ganas.
	 */
	public Button getBotonReiniciarGanar() {
		return volverMenuButton;
	}
	
	/**
	 * @return boton de volver al menu el cual aparece en los créditos solo.
	 */
	public Button getButtonCreditos() {
		return buttonCreditos;
	}

	/**
	 * @return nivel en el cual se está.
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * @return Canvas en el cual se está dibujando todos los sprites.
	 */
	public Canvas getCanvas() {
		return canvas;
	}
	
	
	/**
	 * Metodo en donde ocurren las animaciones las cuales aparecen en pantalla.
	 */
	@Override
	public void handle(long now) {
		//Intermediario el cual gestiona las llamadas para dibujar en el canvas.
		final GraphicsContext gc = canvas.getGraphicsContext2D();

		//tiempo que tarda en dibujarse un frame.
		double timeDiff = (now - lastNanoTime) / 1000000000.0;
		
		//Listas dedicadas a los Sprites los cuales se están utilizando para así evitar Excepciones inesperadas.
		List<Virus> safeViruses = new ArrayList<>(getSprites(Virus.class));
		List<Bullet> safeBullets = new ArrayList<>(getSprites(Bullet.class));
		List<Muro> safeMuros = new ArrayList<>(getSprites(Muro.class));
		List<Turret> safeTorretas = new ArrayList<>(getSprites(Turret.class));

		// Se comprueba las coliciones de los Sprites de Virus, con otros Sprites.
		for (Virus virus : safeViruses) {

			// En este caso con los Sprites Balas
			for (Bullet bullet : safeBullets) {

				// Comprobación de la colisión de la bala y el virus
				if (bullet.intersects(virus)) {
					// Impacto de la bala en el virus, lo que concluye en un incremento de saldo, entre otros.
					virus.impact(bullet);
					Explotion expltion = new Explotion();
					expltion.setPositionX(virus.getPositionX());
					expltion.setPositionY(virus.getPositionY());
					expltion.setGame(this);
					moneda.setMoneda(moneda.getmoneda() + 5);
				}

			}
			
			// Colisión con muros
			for (Muro muro : safeMuros) {

				// Comprobación de colisión entre el virus y el muro
				if (muro.intersects(virus)) {
					//metodo para este tipo de casos
					virus.chocqueMuro(muro);
				}

			}
			// Colisión con Torretas
			for (Turret turret : safeTorretas) {

				// Comprobación de la colisión entre la torreta en cuestión y el virus
				if (turret.intersects(virus)) {
					//metodo para este tipo de casos
					virus.choqueTorreta(turret);
				}

			}
			// Colisión con la Base
			if (base.intersects(virus)) {
				//Se baja la vida de la base
				base.setHealth(base.getHealth() - 1);
				virus.kill();
				//Se cambia la imagen de la vida
				ponerImagenVida();
				
				/*
				 * Condicionante en el caso de que la base tenga las 3 vidas completas, lo cual da lugar 
				 * a la inhablitación del botón de compra de vidas.
				 */
				if (base.getHealth() == 3) {
					botonVida.setDisable(false);
				}
				
				/*
				 * Condición en la cual se cumple que la base sea igual a 0 o menos, en ese caso se para la cuentaAtrás,
				 * se muestra la pantalla de GameOver y se para la animación.
				 */
				if (base.getHealth() < 1) {
					timeline.stop();
					SoundEffects.GameOver();
					botonReiniciar.setVisible(true);
					gameOver.setVisible(true);
					stop();
				}

			}

		}

		// metodo para que dejen de renderizar las balas cuando no estén intersectados con el Sprite del fondo
		for (Bullet bullet : safeBullets) {
			//Condicionante en el cual se cumple que una bala ha salido del mapa.
			if (!bullet.intersects(background)) {
				bullet.kill(); // bala perdida
			}

		}
		// metodo para que dejen de renderizar los virus al salir de la pantalla.
		for (Virus virus : safeViruses) {
			//Condicionante en el cual un virus desaparece si el virus salió del mapa.
			if (!virus.intersects(background)) {
				virus.kill();
			}

		}
		
		/*
		 * En este caso se añaden a la lista de Sprites que se van a usar, la lista de todos los Sprites declarados anteriormente,
		 * además de actualizar su estado y renderizarlos en el canvas.
		 */
		List<Sprite> safeSprites = new ArrayList<>(sprites);
		safeSprites.stream().forEach(s -> s.update(timeDiff));
		safeSprites.stream().forEach(s -> s.render(gc));

		//Despliegue de Virus cada cierto tiempo
		timeVirus += timeDiff;
		if (timeVirus > 6) {
			
			/*
			 * En esta parte se genera la posición a la cual irán dirigidos los Virus, la cual es aleatoria o no,
			 * dependiendo del nivel en el que estemos.
			 */
			Random r = new Random();
			
			int posicionRamdon = r.nextInt(350 - 50) + 50;

			//Aquí se declara el Sprite del Virus
			Virus corona = new Virus();
			
			
			switch (nivel) {
			/**
			 * En el caso del nivel más fácil, se escoge un número entre 1 y 2 para saber si el
			 * sprite va a ir por el camino 1 o el camino 2, y depende del resultado se configura 
			 * el Sprite del Virus.
			 */
			case 0:
				int caminoRandom = (int) (Math.random() * 2) + 1;
				switch (caminoRandom) {
				//camino 1
				case 1:
					corona.setPositionX(0);
					corona.setPositionY(150);
					corona.setVelocityY(0);
					corona.setVelocityX(r.nextInt(100 - 70) + 70);
				break;
				//camino 2
				case 2:
					corona.setPositionX(0);
					corona.setPositionY(445);
					corona.setVelocityY(0);
					corona.setVelocityX(r.nextInt(100 - 70) + 70);
				break;
				}
			break;
			/*
			 * En el caso del nivel medio, se escoge un número entre 1 y 3, para así determinar por qué camino va a ir, 
			 * y así hacer configuraciones cuales valores dependan de alietoriedad.
			 */
			case 1:
				int caminoRandom1 = (int) (Math.random() * 3) + 1;
				corona.setSalido(true);
				switch (caminoRandom1) {
				case 1:
					corona.setPositionX(0);
					corona.setPositionY(300);
					corona.setVelocityY(0);
					corona.setVelocityX(r.nextInt(70 - 20) + 20);
				break;
				
				case 2:
					corona.setPositionX(370);
					corona.setPositionY(510);
					corona.setVelocityY((r.nextInt(50 - 20) + 20) * -1);
					corona.setVelocityX(0);
				break;
				
				case 3:
					corona.setPositionX(370);
					corona.setPositionY(0);
					corona.setVelocityY(r.nextInt(50 - 20) + 20);
					corona.setVelocityX(0);
				break;
				}
			break;
			/*
			 * En el caso del que sea el nivel más difícil, no hay caminos, osea que los virus vienen de la posición 0, 
			 * pero no tienen un movimiento estructurado, he ahí el porque de su configuración simple, solo que en este caso,
			 * cuando el virus está a cierto rango de la base aumenta la velocidad, en otros términos es un Kamikaze sin ser un
			 * rap, o un Quimiotanque turbo (quién entienda entendió).
			 */
			case 2:
				corona.setPositionX(0);
				corona.setPositionY(posicionRamdon);
				if (posicionRamdon > 175) {
					corona.setVelocityY(-(r.nextInt(70 - 30) + 30));
				} else {
					corona.setVelocityY((r.nextInt(70 - 30) + 30));
				}
				corona.setVelocityX(r.nextInt(80 - 20) + 20);
			break;
			}
			
			//Aquí se configura el controlador del Sprite del Virus.
			corona.setGame(this);
			timeVirus = 0.0;

		}

		/*
		 * Los siguientes foreach comprueban el estado de las torretas,
		 * y se vuelven los placement visibles en el caso de que ya no estén
		 */
		for (Button boton : botonesMuros) {
			if (boton.isVisible() == false) {
				visiblePlacementMuro(boton);
			}
		}

		for (Button boton : botonesTorretas) {
			if (boton.isVisible() == false) {
				visiblePlacementTorreta(boton);
			}
		}
		// En esta parte se añade 1 moneda por cada segundo que se lleva en el juego.
		timeCoins += timeDiff;
		if (timeCoins > 1) {

			moneda.setMoneda(moneda.getmoneda() + 1);
			timeCoins = 0;
			etiquetaDinero.setText("Moneda: " + moneda.getmoneda());

		}
		
		// tiempo real cuenta atras
		lastNanoTime = now;

	}

	/**
	 * Método para volver visible un placement en el caso de que la torreta misma no exista,
	 * debido a que hay un método de este tipo para muros, el cual es el siguente en la lista.
	 * @param boton placement el cual se quiere comprobar.
	 */
	public void visiblePlacementTorreta(Button boton) {
		
		//coordenadas del boton en cuestión
		Bounds coordenadas = boton.localToScene(boton.getBoundsInLocal());
		
		
		//En esta parte se almacenan las coordenadas x e y.
	
		int posX = (int) coordenadas.getMinX() - 1;
		int posY = (int) coordenadas.getMinY() - 1;
		boolean colocar = true;

		//En esta parte se guardan todas las torretas que existen en este juego
		List<Turret> safeTorretas = new ArrayList<>(getSprites(Turret.class));
		
		/*
		 * Aquí se comprueba si la torreta la cual se está buscando, sigue existiendo o no,
		 * en alguno de los placements para torretas que existen.
		 */
		for (Turret turret : safeTorretas) {
			//Condición en el caso que el placement se encuentre
			if (turret.getPositionX() == posX & turret.getPositionY() == posY) {
				colocar = false;
			}
		}
		//Condición en caso de que la torreta no esté.
		if (colocar == true) {
			boton.setVisible(true);
		}

	}

	/**
	 * Método para volver visible un placement en el caso que ya no exista el Sprite Muro, el cual
	 * había sido colocado.
	 * @param boton placement el cual se quiere comprobar.
	 */
	public void visiblePlacementMuro(Button boton) {
		
		//coordenadas del boton en cuestión
		Bounds coordenadas = boton.localToScene(boton.getBoundsInLocal());

		//En esta parte se almacenan las coordenadas x e y.
		int posX = (int) coordenadas.getMinX() - 1;
		int posY = (int) coordenadas.getMinY() - 1;
		boolean colocar = true;

		//En esta parte se guardan todas los muros que existen en este juego
		List<Muro> safeMuros = new ArrayList<>(getSprites(Muro.class));

		/*
		 * Aquí se comprueba si el muro la cual se está buscando, sigue existiendo o no,
		 * en alguno de los placements para torretas que existen.
		 */
		for (Muro muro : safeMuros) {
			//Condición en el caso que el placement se encuentre
			if (muro.getPositionX() == posX & muro.getPositionY() == posY) {
				colocar = false;

			}
		}
		//Condición en caso de que el muro no esté.
		if (colocar == true) {

			boton.setVisible(true);
		}

	}

	/**
	 * Método para cambiar la imagen de la vida, dependiendo de la cantidad de la vida que
	 * tenga la base en ese momento.
	 */
	public void ponerImagenVida() {
		switch ((int) base.getHealth()) {
		case 0:
			vida.setImage(new Image("/mapImages/0vidas.png"));
		break;
		
		case 1:
			vida.setImage(new Image("/mapImages/1vida.png"));
		break;
		
		case 2:
			vida.setImage(new Image("/mapImages/2vidas.png"));
		break;
		
		case 3:
			vida.setImage(new Image("/mapImages/3vidas.png"));
		break;
		
		case 4:
			vida.setImage(new Image("/mapImages/4vidas.png"));
		break;
		}
	}

}
