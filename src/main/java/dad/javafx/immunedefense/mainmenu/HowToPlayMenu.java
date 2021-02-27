package dad.javafx.immunedefense.mainmenu;
	import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextArea;
	import javafx.scene.layout.BorderPane;

	public class HowToPlayMenu  {

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
	    private Label imgIcon;

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

		public Label getImgIcon() {
			return imgIcon;
		}


		public void setImgIcon(Label imgIcon) {
			this.imgIcon = imgIcon;
		}


		public TextArea getDesText() {
			return desText;
		}


		public void setDesText(TextArea desText) {
			this.desText = desText;
		}


		@FXML
	    void onBase(ActionEvent event) {

	    }

	    @FXML
	    void onHow(ActionEvent event) {

	    }

	    @FXML
	    void onPosition(ActionEvent event) {

	    }

	    @FXML
	    void onTurret(ActionEvent event) {

	    }

	    @FXML
	    void onVirusB(ActionEvent event) {

	    }

	    @FXML
	    void onWall(ActionEvent event) {

	    }

	    @FXML
	    void onWin(ActionEvent event) {

	    }

	}


