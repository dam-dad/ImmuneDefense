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
    private Canvas canvasMenu;
    
    @FXML
    private ImageView vida;

    @FXML
    private ImageView puntosParaComprar;

    @FXML
    void onBoton1Action(ActionEvent event) {

    }
    
    private MapaSinTiledApp mapa;
    
	public PruebaMenu() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/PanelJuegoFX.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		
		mapa = new MapaSinTiledApp();
		
		//probar ambas
		vistaBorderPane.setCenter(mapa.getCanvas());    
		canvasMenu=mapa.getCanvas();
		
		
		
		/*MapaSinTiled mapa = new MapaSinTiled();
		 Canvas canvas=mapa.getCanvas();
		*/
	}

	public final BorderPane getVistaBorderPane() {
		return vistaBorderPane;
	}

	public HBox getHBox_Buttons() {
		return HBox_Buttons;
	}

	public Button getBoton1() {
		return boton1;
	}

	public Canvas getCanvasMenu() {
		return canvasMenu;
	}
	
	public void setCanvasMenu(Canvas canvasMenu) {
		this.canvasMenu = canvasMenu;
	}

	public ImageView getVida() {
		return vida;
	}

	public ImageView getPuntosParaComprar() {
		return puntosParaComprar;
	}

	public MapaSinTiledApp getMapa() {
		return mapa;
	}

}
