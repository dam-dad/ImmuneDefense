package dad.javafx.immunedefense.map;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class PruebaMenu implements Initializable {
	
    @FXML
    private  BorderPane vistaBorderPane;

    @FXML
    private Button boton1;
    
    @FXML
    private Canvas canvas_center;
    
    @FXML
    void onBoton1Action(ActionEvent event) {

    }

	public PruebaMenu() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/PanelJuegoFX.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public final BorderPane getVistaBorderPane() {
		return vistaBorderPane;
	}

	public Canvas getCanvas_center() {
		return canvas_center;
	}

	public void setCanvas_center(Canvas canvas_center) {
		this.canvas_center = canvas_center;
	}
	
}
