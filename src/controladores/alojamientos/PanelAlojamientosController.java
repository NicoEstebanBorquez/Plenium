package controladores.alojamientos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PanelAlojamientosController implements Initializable {

    @FXML
    private Button btnNuevoAlojamiento;

    @FXML
    public void abrirNuevoAlojamiento(ActionEvent event) throws IOException {
        System.out.println("ABRIR INTERFAZ NUEVO ALOJAMIENTO");

        //CARGAR VISTA "NUEVO ALOJAMIENTO"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/alojamientos/PanelNuevoAlojamiento.fxml"));
        //Cargar el padre
        Parent padre = loader.load();
        //VistaInicioController controlador = loader.getController();
        Scene escena = new Scene(padre);
        Stage escenario = new Stage();
        escenario.setScene(escena);
        
        //Haciendo que la ventana sea modal
        escenario.initModality(Modality.WINDOW_MODAL);
        escenario.initOwner(((Node) event.getSource()).getScene().getWindow());
        
        //Elimina la barra superior con el boton de cerrar 
        //escenario.initStyle(StageStyle.UNDECORATED);
        
        escenario.setResizable(false);
        escenario.setTitle("Nuevo alojamiento");
        escenario.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
