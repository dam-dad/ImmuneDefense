package dad.javafx.immunedefense.mainmenu;

	import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
	import javafx.scene.control.Slider;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.BorderPane;

	public class OptionController {

	    @FXML
	    private BorderPane view;

	    @FXML
	    private Slider volumenSlider;

	    @FXML
	    private TextField volumenText;

	    @FXML
	    private ComboBox<?> pantallaCombo;
	    
	    public OptionController() throws IOException{
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/Menus/OptionMenu.fxml"));
			loader.setController(this);
			loader.load();
		}
		public BorderPane getView() {
			return this.view;
		}
	}


