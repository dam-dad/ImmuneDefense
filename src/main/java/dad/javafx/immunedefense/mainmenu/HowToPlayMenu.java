package dad.javafx.immunedefense.mainmenu;
	import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

	public class HowToPlayMenu  {
		
		private Image changeImg;

	    @FXML
	    private BorderPane view;

	    @FXML
	    private Label tituloL;

	    @FXML
	    private Button howB;

	    @FXML
	    private Button baseB;

	    @FXML
	    private Button turretB;

	    @FXML
	    private Button wallB;

	    @FXML
	    private Button virusB;

	    @FXML
	    private Button positionB;

	    @FXML
	    private Button winB;

	    @FXML
	    private Button volverB;
	    
	    @FXML
	    private ImageView imgIcon;

	    @FXML
	    private TextArea desText;
	    
		public HowToPlayMenu() throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/HowToPlayMenu.fxml"));
			loader.setController(this);
			loader.load();
		}
		

	    public BorderPane getView() {
			return view;
		}

		public Label getTituloL() {
			return tituloL;
		}


		public void setTituloL(Label tituloL) {
			this.tituloL = tituloL;
		}


		public Button getHowB() {
			return howB;
		}

		public Button getBaseB() {
			return baseB;
		}

		public Button getTurretB() {
			return turretB;
		}

		public Button getWallB() {
			return wallB;
		}

		public Button getVirusB() {
			return virusB;
		}

		public Button getPositionB() {
			return positionB;
		}

		public Button getWinB() {
			return winB;
		}

		public ImageView getImgIcon() {
			return imgIcon;
		}


		public void setImgIcon(ImageView imgIcon) {
			this.imgIcon = imgIcon;
		}


		public TextArea getDesText() {
			return desText;
		}


		public void setDesText(TextArea desText) {
			this.desText = desText;
		}
		
		public Button getVolverB() {
			return volverB;
		}

	    @FXML
	    void onHow(ActionEvent event) {
	    		tituloL.setText("¿Cómo se juega?");
	    		changeImg=new Image(getClass().getResourceAsStream("/Images/Logo.png"));
	    		imgIcon.setImage(changeImg);
	    		desText.textProperty().set("Immune Defense es un juego tower defense, en el que tendrás"
	    									+ " que resistir el \nataque de numerosos virus que quieren"
	    									+ " acabar con tu sistema inmunitario. \nPara defenderte de los"
	    									+ " numerosos ataques de estos pequeños, pero mortales \nseres, contarás con"
	    									+ " la ayuda de muros de plaquetas y torretas de anticuerpos \nque te ayudarán a proteger tu base.");
	    }

		@FXML
	    void onBase(ActionEvent event) {
			tituloL.setText("¿Qué es la base?");
			changeImg=new Image(getClass().getResourceAsStream("/mapImages/Base.png"));
			imgIcon.setImage(changeImg);
			desText.textProperty().set("La base es lo que debes proteger si quieres sobrevivir a la oleada viral.\n"
									  + "Esta, solo aguantará tres impactos asi que si te dejas dormir o no usas bien\n"
									  + " tus recursos, no tendrás nada que hacer.");
	    }


	    @FXML
	    void onPosition(ActionEvent event) {
	    	tituloL.setText("¿Qué son los posicionamientos?");
	    	changeImg=new Image(getClass().getResourceAsStream("/mapImages/wallPlacement.png"));
	    	imgIcon.setImage(changeImg);
	    	desText.textProperty().set("Los posicionamientos son los lugares donde colocarás las torretas o los\n"
	    			+ "muros. Para hacer uso de ellos y colocar tu sistema defensivo, primero\n"
	    			+ "selecciona el posicionamiento y luego selecciona la estructura que quieres\n "
	    			+ "colocar. Una vez se haya destruido la estructura que habías construido el\n"
	    			+ "posicionamiento volverá a aparecer y podrás construir otra estructura.\n"
	    			+ "RECUERDA: Las torretas y muros tienen un coste en sangre, no pensarias que\n"
	    			+ "te las ibamos a regalar, si no tienes esto en consideración,\n"
	    			+ " bueno... habrá sido un placer haberte tenido por aquí.");
	    }

	    @FXML
	    void onTurret(ActionEvent event) {
	    	tituloL.setText("¿Qué son las torretas?");
	    	changeImg=new Image(getClass().getResourceAsStream("/mapImages/Turret1.png"));
	    	imgIcon.setImage(changeImg);
	    	desText.textProperty().set("Las torretas son las estructuras encargadas de la eliminación de los virus.\n"
	    			+ "En estos momentos, tu organismo cuenta con dos tipos de torreta:\n"
	    			+ "Las torretas simples y las torretas cruzadas.\n"
	    			+ "Torreta Simple:\n"
	    			+ "Coste:");
	    }

	    @FXML
	    void onVirusB(ActionEvent event) {
	    	tituloL.setText("¿Qué son los virus?");
	    	changeImg=new Image(getClass().getResourceAsStream("/mapImages/Virus Guille1.png"));
	    	imgIcon.setImage(changeImg);
	    	desText.textProperty().set("");
	    }

	    @FXML
	    void onWall(ActionEvent event) {
	    	tituloL.setText("¿Que función tienen los muros?");
	    	changeImg=new Image(getClass().getResourceAsStream("/mapImages/muro.png"));
	    	imgIcon.setImage(changeImg);
	    	desText.textProperty().set("");
	    }

	    @FXML
	    void onWin(ActionEvent event) {
	    	tituloL.setText("¿Cómo ganar?");
	    	changeImg=new Image(getClass().getResourceAsStream("/mapImages/you_win.png"));
	    	imgIcon.setImage(changeImg);
	    	desText.textProperty().set("");
	    }

	}


