package controladores.inmuebles;

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
import modelos.Inmuebles;
import modelos.InmueblesDAO;

public class PanelInfoInmuebleController implements Initializable {

    protected static int inmuebleSeleccionado;
    
    @FXML
    private Label labelReferencia, labelNombre, labelDireccion, labelPoblacion, labelProvincia, labelCapacidad,
            labelDormitorios, labelBanos, labelTerraza, labelPiscina, labelAparcamiento, labelPropietario, labelUsuario;

    public void cargarDatos(int seleccionado) {
        Inmuebles inmueble = null;
        inmueble = new InmueblesDAO().obtenerPorId(seleccionado);

        labelReferencia.setText(Integer.toString(inmueble.getIdInmueble()));
        labelNombre.setText(inmueble.getNombre()); 
        labelDireccion.setText(inmueble.getDireccion());
        labelPoblacion.setText(inmueble.getPoblacion());
        labelProvincia.setText(inmueble.getProvincia());
       // labelCapacidad.setText(Integer.toString(inmueble.getCapacidad()));
        labelDormitorios.setText(Integer.toString(inmueble.getDormitorios()));
        labelBanos.setText(Integer.toString(inmueble.getBanos()));
        labelTerraza.setText(Integer.toString(inmueble.getTerraza()));
        labelPiscina.setText(Integer.toString(inmueble.getPiscina()));
        labelAparcamiento.setText(Integer.toString(inmueble.getAparcamiento()));
        labelPropietario.setText(Integer.toString(inmueble.getIdPropietario()));
        labelUsuario.setText(Integer.toString(inmueble.getIdUsuario()));
        labelPoblacion.setText(inmueble.getPoblacion());        
        
    }

    @FXML
    public void editarInmueble(ActionEvent event) throws IOException{
        //Se envía el id del inmueble seleccionado al controlador PanelEditarInmuebleController
        PanelEditarInmuebleController.inmuebleSeleccionado = inmuebleSeleccionado;
        
        
        //CARGAR VISTA "NUEVO ALOJAMIENTO"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/inmuebles/PanelEditarInmueble.fxml"));
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
        escenario.setTitle("Editar inmueble");
        escenario.show();
    }
    
    
    @FXML
    public void eliminarInmueble(){
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
        cargarDatos(inmuebleSeleccionado);
    }

}
