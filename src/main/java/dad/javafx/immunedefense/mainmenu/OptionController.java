package dad.javafx.immunedefense.mainmenu;

	import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
	import javafx.scene.control.Slider;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

	public class OptionController  implements Initializable {

	    @FXML
	    private BorderPane view;

	    @FXML
	    private Slider volumenSlider;

	    @FXML
	    private TextField volumenText;
	    
	    @FXML
	    private Button backOption;
	    
	    public OptionController() throws IOException{
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/Menus/OptionMenu.fxml"));
			loader.setController(this);
			loader.load();
		}
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
		}

	    
		public BorderPane getView() {
			return this.view;
		}

		public Slider getVolumenSlider() {
			return volumenSlider;
		}

		public TextField getVolumenText() {
			return volumenText;
		}
	    
		public Button getBackOption() {
			return backOption;
		}	
		
	}


