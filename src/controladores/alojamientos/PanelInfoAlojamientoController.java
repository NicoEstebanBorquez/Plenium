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
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelos.Alojamiento;
import modelos.AlojamientoDAO;

public class PanelInfoAlojamientoController implements Initializable {

    protected static int alojamientoSeleccionado;
    
    @FXML
    private Label labelReferencia, labelNombre, labelDireccion, labelPoblacion, labelProvincia, labelCapacidad,
            labelDormitorios, labelBanos, labelTerraza, labelPiscina, labelAparcamiento, labelPropietario, labelUsuario;

    

    public void cargarDatos(int seleccionado) {
        Alojamiento alojamiento = null;
        alojamiento = new AlojamientoDAO().obtenerPorId(seleccionado);

        labelReferencia.setText(Integer.toString(alojamiento.getIdAlojamiento()));
        labelNombre.setText(alojamiento.getNombre()); 
        labelDireccion.setText(alojamiento.getDireccion());
        labelPoblacion.setText(alojamiento.getPoblacion());
        labelProvincia.setText(alojamiento.getProvincia());
        labelCapacidad.setText(Integer.toString(alojamiento.getCapacidad()));
        labelDormitorios.setText(Integer.toString(alojamiento.getDormitorios()));
        labelBanos.setText(Integer.toString(alojamiento.getBanos()));
        labelTerraza.setText(Integer.toString(alojamiento.getTerraza()));
        labelPiscina.setText(Integer.toString(alojamiento.getPiscina()));
        labelAparcamiento.setText(Integer.toString(alojamiento.getAparcamiento()));
        labelPropietario.setText(Integer.toString(alojamiento.getIdPropietario()));
        labelUsuario.setText(Integer.toString(alojamiento.getIdUsuario()));
        labelPoblacion.setText(alojamiento.getPoblacion());        
        
    }

    @FXML
    public void editarAlojamiento(ActionEvent event) throws IOException{
        //Se envía el id del alojamiento seleccionado al controlador PanelEditarAlojamientoController
        PanelEditarAlojamientoController.alojamientoSeleccionado = alojamientoSeleccionado;
        
        
        //CARGAR VISTA "NUEVO ALOJAMIENTO"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/alojamientos/PanelEditarAlojamiento.fxml"));
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
        escenario.initStyle(StageStyle.UNDECORATED);
        escenario.setResizable(false);
        escenario.setTitle("Editar alojamiento");
        escenario.show();
    }
    
    
    @FXML
    public void eliminarAlojamiento(){
        System.out.println("ELIMINAR");
    }

     @FXML
    public void cerrar(ActionEvent event){
        this.cerrarInterfaz(event);
    }
    
    public void cerrarInterfaz(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos(alojamientoSeleccionado);
    }

}
