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
	    }


	    @FXML
	    void onPosition(ActionEvent event) {
	    	tituloL.setText("¿Qué son los posicionamientos?");
	    	changeImg=new Image(getClass().getResourceAsStream("/mapImages/wallPlacement.png"));
	    	imgIcon.setImage(changeImg);
	    }

	    @FXML
	    void onTurret(ActionEvent event) {
	    	tituloL.setText("¿Qué son las torretas?");
	    	changeImg=new Image(getClass().getResourceAsStream("/mapImages/Turret1.png"));
	    	imgIcon.setImage(changeImg);
	    }

	    @FXML
	    void onVirusB(ActionEvent event) {
	    	tituloL.setText("¿Qué son los virus?");
	    	changeImg=new Image(getClass().getResourceAsStream("/mapImages/Virus Guille1.png"));
	    	imgIcon.setImage(changeImg);
	    }

	    @FXML
	    void onWall(ActionEvent event) {
	    	tituloL.setText("¿Que función tienen los muros?");
	    	changeImg=new Image(getClass().getResourceAsStream("/mapImages/muro.png"));
	    	imgIcon.setImage(changeImg);
	    }

	    @FXML
	    void onWin(ActionEvent event) {
	    	tituloL.setText("¿Cómo ganar?");
	    	changeImg=new Image(getClass().getResourceAsStream("/mapImages/you_win.png"));
	    	imgIcon.setImage(changeImg);
	    }

	}


