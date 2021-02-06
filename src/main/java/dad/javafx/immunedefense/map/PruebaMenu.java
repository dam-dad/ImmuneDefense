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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class PruebaMenu implements Initializable {
	
    @FXML
    private BorderPane vistaBorderPane;

    @FXML
    private HBox HBox_Buttons;

    @FXML
    private Button boton1;

    @FXML
    private ImageView vida;

    @FXML
    private ImageView puntosParaComprar;

    @FXML
    void onBoton1Action(ActionEvent event) {

    }
    
    private MapaSinTiledApp mapa;

    @FXML
    private Canvas canvasMenu;
    
	public PruebaMenu() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/PanelJuegoFX.fxml"));
		loader.setController(this);

		loader.load();

	}

	public void initialize(URL location, ResourceBundle resources) {
		
		//probar ambas
		vistaBorderPane.setCenter(mapa.getCanvas());    
		canvasMenu=mapa.getCanvas();
		
		
		/*MapaSinTiled mapa = new MapaSinTiled();
		 Canvas canvas=mapa.getCanvas();
		
		 paneCanvas.getChildren().add(canvas);
		 
		   canvas.widthProperty().bind(paneCanvas.widthProperty());
		    canvas.heightProperty().bind(paneCanvas.heightProperty());
*/
	}

	public final BorderPane getVistaBorderPane() {
		return vistaBorderPane;
	}






}
