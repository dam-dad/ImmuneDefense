package dad.immuneDefense.mainMenu;

	import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

	public class MainMenuController {

	    @FXML
	    private BorderPane view;
	    @FXML
	    private Button ajustesB;

	    @FXML
	    private ToggleButton sonidoB;
	   
	    @FXML
	    private ImageView imagenSonido;

	    @FXML
	    private Button nuevaPartidaB;

	    @FXML
	    private Button cargarB;

	    @FXML
	    private Button masB;
	    
	    public MainMenuController() throws IOException{
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/Menus/MainMenuView.fxml"));
			loader.setController(this);
			loader.load();
		}
		public BorderPane getView() {
			return this.view;
		}

	    @FXML
	    void onDesactivarSonido(ActionEvent event) {

	    }

	    @FXML
	    void onLoadGame(ActionEvent event) {

	    }

	    @FXML
	    void onMoreB(ActionEvent event) {

	    }

	    @FXML
	    void onNewGame(ActionEvent event) {

	    }

	    @FXML
	    void onOptions(ActionEvent event) {

	    }

	}


