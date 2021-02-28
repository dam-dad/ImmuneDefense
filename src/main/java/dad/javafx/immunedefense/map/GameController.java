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

public class GameController extends AnimationTimer implements Initializable {

	// que nivel

	FXMLLoader loader;
	private int nivel;

	// botones nivel facil

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

	// model

	private double timeVirus = 0.0;
	private double lastNanoTime;

	private Timeline timeline;
	private int startTime = 4;
	private IntegerProperty timeSeconds;

	private double timeCoins = 0.0;

	private List<Sprite> sprites = new ArrayList<>();
	private Background background;

	private Base base;

	// view

	@FXML
	private StackPane view;

	@FXML
	private Canvas canvas;

	@FXML
	private AnchorPane gameOver;
	
	@FXML
    private Button botonReintentar;

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

	// Boton mas vida

	@FXML
	private Button botonVida;

	// YouWinPane

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

	// selector torreta-muro
	boolean esMuro;

	// coordenadas
	private double x;

	private double y;

	// lista botones
	List<Button> botonesTorretas = new ArrayList<>();

	List<Button> botonesMuros = new ArrayList<>();

	@FXML
	void ColocarTorretaDoble(ActionEvent event) {

		if (x != 0 & y != 0 & esMuro == false & moneda.getmoneda() > 7) {
			Turret torreta = new Turret(1, 0.25, 2);
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
				moneda.setMoneda(moneda.getmoneda() - 7);
				// hacer invisible el placement
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
	void OnMuroAction(ActionEvent event) {
		if (x != 0 & y != 0 & esMuro == true & moneda.getmoneda() > 35) {
			Muro muro = new Muro();
			muro.setPositionX(x);
			muro.setPositionY(y);

			List<Muro> PLacementMuros = new ArrayList<>(getSprites(Muro.class));
			boolean colocar = true;

			for (Muro muros : PLacementMuros) {
				if (muro.intersects(muros)) {
					colocar = false;

				}
			}
			if (colocar == true) {
				muro.setGame(this);
				moneda.setMoneda(moneda.getmoneda() - 35);
				// hacer invisible el placement
				for (Button boton : botonesMuros) {

					// comprobar el redondeo

					System.out.println((int) boton.localToScene(boton.getBoundsInLocal()).getMinX() + "boton");
					System.out.println((int) boton.localToScene(boton.getBoundsInLocal()).getMinY() + "boton");
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
	void obtenerCoordenadas(ActionEvent event) {

		Button botonPulsado = (Button) event.getSource();

		Bounds coordenadas = botonPulsado.localToScene(botonPulsado.getBoundsInLocal());
		int xRedondeo = (int) coordenadas.getMinX();
		int yRedondeo = (int) coordenadas.getMinY();

		x = xRedondeo;

		y = yRedondeo;

		esMuro = false;

		for (Button boton : botonesMuros) {
			if (boton == botonPulsado) {
				esMuro = true;
			}
		}

		// x=event.getSceneX();

		// y=event.getSceneY();

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

	public GameController(int level) {
		this.nivel = level;
		// this.nivel = 0;
		try {
			switch (nivel) {
			// nivel facil
			case 0:
				loader = new FXMLLoader(getClass().getResource("/Menus/PanelJuegoFXFacil.fxml"));
				loader.setController(this);
				loader.load();
				break;

			// nivel normal
			case 1:
				loader = new FXMLLoader(getClass().getResource("/Menus/PanelJuegoFXFNormal.fxml"));
				loader.setController(this);
				loader.load();

				botonLugarTorreta5.setVisible(false);
				botonLugarTorreta5.setDisable(true);
				botonLugarTorreta6.setVisible(false);
				botonLugarTorreta6.setDisable(true);
				botonLugarTorreta7.setVisible(false);
				botonLugarTorreta7.setDisable(true);
				break;

			// nivel que ya tenemos hecho el chungo
			case 2:
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/PanelJuegoFX.fxml"));
				loader.setController(this);
				loader.load();

				botonLugarTorreta5.setVisible(false);
				botonLugarTorreta5.setDisable(true);
				botonLugarTorreta6.setVisible(false);
				botonLugarTorreta6.setDisable(true);
				botonLugarTorreta7.setVisible(false);
				botonLugarTorreta7.setDisable(true);
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initialize(URL location, ResourceBundle resources) {

		backgroundGameOverPane.setStyle("-fx-background-color: #000000");
		backgroundYouWinPane.setStyle("-fx-background-color: #000000");

		timeSeconds = new SimpleIntegerProperty(startTime);

		timeline = new Timeline(new KeyFrame(Duration.seconds(startTime + 1), new KeyValue(timeSeconds, 0)));

		timeline.setCycleCount(1);
		timeline.setOnFinished(e -> {
			SoundEffects.Win();
			youWinPane.setVisible(true);
			System.out.println("La animación terminó");
			stop();
		});
		timeline.play();

		// System.out.println(timeSeconds.get());

		timeSeconds.addListener((obv, ov, nv) -> {
			if (ov != nv) {
				etiquetaTiempo.setText("Tiempo -> " + nv);
				// System.out.println(nv);
			}
		});

		spritesPrincipales();

		lastNanoTime = System.nanoTime();

		// intento de poner el tiempo pero no recuerdo como bindearlo
		// etiquetaTiempo.setText(lastNanoTime+"");
		// textProperty().bindBidirectional(tiempoMenu+"");
		// etiquetaDinero.textProperty().bind(new SimpleStringProperty("")
		// .concat(moneda.getmoneda()));

		vida.setImage(new Image("/mapImages/3vidas.png"));

		// quitar los bordes del boton
		botonLugarTorreta1.setPadding(new Insets(-1, -1, -1, -1));
		botonLugarTorreta2.setPadding(new Insets(-1, -1, -1, -1));
		botonLugarTorreta3.setPadding(new Insets(-1, -1, -1, -1));
		botonLugarTorreta4.setPadding(new Insets(-1, -1, -1, -1));

		if (nivel == 0) {
			botonLugarTorreta3.setPadding(new Insets(-1, -1, -1, -1));
			botonLugarTorreta4.setPadding(new Insets(-1, -1, -1, -1));
			botonLugarTorreta5.setPadding(new Insets(-1, -1, -1, -1));
			botonLugarTorreta6.setPadding(new Insets(-1, -1, -1, -1));
			botonLugarTorreta7.setPadding(new Insets(-1, -1, -1, -1));

		}

		// añadir botones a la lista
		botonesTorretas.add(botonLugarTorreta1);
		botonesTorretas.add(botonLugarTorreta2);
		botonesTorretas.add(botonLugarTorreta3);
		botonesTorretas.add(botonLugarTorreta4);

		if (nivel == 0) {

			botonesTorretas.add(botonLugarTorreta5);
			botonesTorretas.add(botonLugarTorreta6);
			botonesTorretas.add(botonLugarTorreta7);
		}

		botonesTorretas.add(botonLugarTorreta3);
		botonesTorretas.add(botonLugarTorreta4);

		botonesMuros.add(botonCoordenadasMuro);
		botonesMuros.add(botonCoordenadasMuro1);

		start(); // inicia el animationtimer

	}

	private void spritesPrincipales() {
		// segun nivel
		background = new Background();
		if (nivel == 1) {
			background.setImage(new Image("/mapImages/mapa1.png"));
		}
		if (nivel == 0) {
			background.setImage(new Image("/mapImages/mapa2.png"));
		}

		background.setPositionX(0);
		background.setPositionY(0);
		background.setWidth(canvas.getWidth());
		background.setHeight(canvas.getHeight());
		background.setGame(this);

		base = new Base();
		base.setGame(this);

	}

	public <T extends Sprite> List<T> getSprites(Class<T> type) {
		return sprites.stream().filter(s -> s.getClass().equals(type)).map(s -> type.cast(s))
				.collect(Collectors.toList());
	}

	public List<Sprite> getSprites() {
		return sprites;
	}

	public Parent getView() {
		return view;
	}
	
	public Button getBotonReintentar() {
		return botonReintentar;
	}
	
	public Button getBotonReiniciar() {
		return botonReiniciar;
	}
	
	public Button getContinuarButton() {
		return continuarButton;
	}

	public Button getBotonReiniciarGanar() {
		return volverMenuButton;
	}
	
	public Button getButtonCreditos() {
		return buttonCreditos;
	}

	public void setButtonCreditos(Button buttonCreditos) {
		this.buttonCreditos = buttonCreditos;
	}

	public void setBotonReintentar(Button botonReintentar) {
		this.botonReintentar = botonReintentar;
	}

	public void setBotonReiniciar(Button botonReiniciar) {
		this.botonReiniciar = botonReiniciar;
	}

	public void setContinuarButton(Button continuarButton) {
		this.continuarButton = continuarButton;
	}

	public void setVolverMenuButton(Button volverMenuButton) {
		this.volverMenuButton = volverMenuButton;
	}

	public int getNivel() {
		return nivel;
	}

	public Canvas getCanvas() {
		return canvas;
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

		// Se comprueba las coliciones de los virus con cosas
		for (Virus virus : safeViruses) {

			// balas
			for (Bullet bullet : safeBullets) {

				// comprueba si colisiona la bala y el virus
				if (bullet.intersects(virus)) {
					// la bala impacta en el virus
					virus.impact(bullet);
					Explotion expltion = new Explotion();
					expltion.setPositionX(virus.getPositionX());
					expltion.setPositionY(virus.getPositionY());
					expltion.setGame(this);
					moneda.setMoneda(moneda.getmoneda() + 5);
				}

			}
			// muros
			for (Muro muro : safeMuros) {

				// comprueba si colisiona el murp y el virus
				if (muro.intersects(virus)) {
					// el muro impacta en el virus
					virus.chocqueMuro(muro);
				}

			}
			// torretas
			for (Turret turret : safeTorretas) {

				// comprueba si colisiona el murp y el virus
				if (turret.intersects(virus)) {
					// el muro impacta en el virus
					virus.choqueTorreta(turret);
				}

			}
			// base
			if (base.intersects(virus)) {

				base.setHealth(base.getHealth() - 1);
				virus.kill();
				ponerImagenVida();

				if (base.getHealth() == 3) {
					botonVida.setDisable(false);
				}

				if (base.getHealth() < 1) {
					timeline.stop();
					SoundEffects.GameOver();
					botonReintentar.setDisable(false);
					botonReiniciar.setVisible(true);
					botonReiniciar.setDisable(false);
					gameOver.setVisible(true);
					stop();
				}

			}

		}

		// metodo para que dejen de renderizar las balas cuando salgan
		for (Bullet bullet : safeBullets) {

			if (!bullet.intersects(background)) {
				bullet.kill(); // bala perdida
			}

		}
		// metodo para que dejen de renderizar los virus al salir del background
		for (Virus virus : safeViruses) {

			if (!virus.intersects(background)) {
				virus.kill();
			}

		}

		List<Sprite> safeSprites = new ArrayList<>(sprites);
		safeSprites.stream().forEach(s -> s.update(timeDiff));
		safeSprites.stream().forEach(s -> s.render(gc));

		// virus saliendo todo el rato
		timeVirus += timeDiff;
		if (timeVirus > 6) {

			Random r = new Random();

			int posicionRamdon = r.nextInt(350 - 50) + 50;
			//int velocidadRamdon = r.nextInt(80 - 20) + 20;

			Virus corona = new Virus();

			if (nivel == 2) {
				corona.setPositionX(0);
				corona.setPositionY(posicionRamdon);
				if (posicionRamdon > 175) {
					corona.setVelocityY(-(r.nextInt(70 - 30) + 30));
				} else {
					corona.setVelocityY((r.nextInt(70 - 30) + 30));
				}
				corona.setVelocityX(r.nextInt(80 - 20) + 20);
			}

			if (nivel == 1) {
				int caminoRamdom = (int) (Math.random() * 3) + 1;
				corona.setSalido(true);

				if (caminoRamdom == 1) {

					corona.setPositionX(0);
					corona.setPositionY(300);
					corona.setVelocityY(0);
					corona.setVelocityX(r.nextInt(70 - 20) + 20);
				}
				if (caminoRamdom == 3) {

					corona.setPositionX(370);
					corona.setPositionY(0);
					corona.setVelocityY(r.nextInt(50 - 20) + 20);
					corona.setVelocityX(0);
				}
				if (caminoRamdom == 2) {

					corona.setPositionX(370);
					corona.setPositionY(510);
					corona.setVelocityY((r.nextInt(50 - 20) + 20) * -1);
					corona.setVelocityX(0);
				}
			}

			if (nivel == 0) {
				int caminoRamdom = (int) (Math.random() * 2) + 1;
				if (caminoRamdom == 1) {
					corona.setPositionX(0);
					corona.setPositionY(150);
					corona.setVelocityY(0);
					corona.setVelocityX(r.nextInt(100 - 70) + 70);
				}
				if (caminoRamdom == 2) {
					corona.setPositionX(0);
					corona.setPositionY(445);
					corona.setVelocityY(0);
					corona.setVelocityX(r.nextInt(100 - 70) + 70);
				}

			}

			corona.setGame(this);
			timeVirus = 0.0;

		}

		// hacer visible el placement de nuevo

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
		// el lory money
		timeCoins += timeDiff;
		if (timeCoins > 1) {

			moneda.setMoneda(moneda.getmoneda() + 1);
			timeCoins = 0;
			etiquetaDinero.setText("Moneda: " + moneda.getmoneda());

		}
		// tiempo real cuenta atras

		lastNanoTime = now;

	}

	// metodo visible el placement
	public void visiblePlacementTorreta(Button boton) {

		Bounds coordenadas = boton.localToScene(boton.getBoundsInLocal());

		int posX = (int) coordenadas.getMinX() - 1;
		int posY = (int) coordenadas.getMinY() - 1;
		boolean colocar = true;

		List<Turret> safeTorretas = new ArrayList<>(getSprites(Turret.class));

		for (Turret turret : safeTorretas) {

			if (turret.getPositionX() == posX & turret.getPositionY() == posY) {
				colocar = false;

			}
		}
		if (colocar == true) {

			boton.setVisible(true);
		}

	}

	public void visiblePlacementMuro(Button boton) {

		Bounds coordenadas = boton.localToScene(boton.getBoundsInLocal());

		// pongo esto por que me rondea
		int posX = (int) coordenadas.getMinX() - 1;
		int posY = (int) coordenadas.getMinY() - 1;
		boolean colocar = true;

		List<Muro> safeMuros = new ArrayList<>(getSprites(Muro.class));

		for (Muro muro : safeMuros) {

			if (muro.getPositionX() == posX & muro.getPositionY() == posY) {
				colocar = false;

			}
		}
		if (colocar == true) {

			boton.setVisible(true);
		}

	}

//cambiar la imagen de la vida
	public void ponerImagenVida() {
		if (base.getHealth() == 4) {

			vida.setImage(new Image("/mapImages/vida2.png"));

		}

		if (base.getHealth() == 3) {

			vida.setImage(new Image("/mapImages/3vidas.png"));

		}

		if (base.getHealth() == 2) {
			vida.setImage(new Image("/mapImages/2vidas.png"));
		}

		if (base.getHealth() == 1) {

			vida.setImage(new Image("/mapImages/1vida.png"));
		}
		if (base.getHealth() == 0) {

			vida.setImage(new Image("/mapImages/0vidas.png"));
		}

	}

}
