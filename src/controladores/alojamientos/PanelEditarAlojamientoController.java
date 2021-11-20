package controladores.alojamientos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelos.Alojamiento;
import modelos.AlojamientoDAO;

public class PanelEditarAlojamientoController implements Initializable {

    protected static int alojamientoSeleccionado;
    
    @FXML
    private TextField txtReferencia, txtNombre, txtDireccion, txtPoblacion, txtProvincia, txtCapacidad,
            txtDormitorios, txtBanos, txtTerraza, txtPiscina, txtAparcamiento, txtPropietario, txtUsuario;

    @FXML
    public void guardar() {
        System.out.println("SELECCIONADO: " +alojamientoSeleccionado);
    }

    @FXML
    public void cancelar(ActionEvent event) {
        this.cerrarInterfaz(event);
    }

    public void cargarDatos(int seleccionado) {
        Alojamiento alojamiento = null;
        alojamiento = new AlojamientoDAO().obtenerPorId(seleccionado);

        txtReferencia.setText(Integer.toString(alojamiento.getIdAlojamiento()));
        txtNombre.setText(alojamiento.getNombre()); 
        txtDireccion.setText(alojamiento.getDireccion());
        txtPoblacion.setText(alojamiento.getPoblacion());
        txtProvincia.setText(alojamiento.getProvincia());
        txtCapacidad.setText(Integer.toString(alojamiento.getCapacidad()));
        txtDormitorios.setText(Integer.toString(alojamiento.getDormitorios()));
        txtBanos.setText(Integer.toString(alojamiento.getBanos()));
        txtTerraza.setText(Integer.toString(alojamiento.getTerraza()));
        txtPiscina.setText(Integer.toString(alojamiento.getPiscina()));
        txtAparcamiento.setText(Integer.toString(alojamiento.getAparcamiento()));
        txtPropietario.setText(Integer.toString(alojamiento.getIdPropietario()));
        txtUsuario.setText(Integer.toString(alojamiento.getIdUsuario()));
        txtPoblacion.setText(alojamiento.getPoblacion());        
        
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
