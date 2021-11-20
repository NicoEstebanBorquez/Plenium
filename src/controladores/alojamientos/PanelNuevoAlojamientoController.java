package controladores.alojamientos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelos.Alojamiento;
import modelos.AlojamientoDAO;


public class PanelNuevoAlojamientoController implements Initializable {

    @FXML
    private Button btnCancelar, btnGuardar;

    @FXML
    private TextField txtNombre, txtCapacidad, txtDormitorios, txtBaños, txtTerraza, txtPiscina, txtAparcamiento, txtDireccion, txtPoblacion, txtProvincia, txtPropietario, txtUsuario;

    @FXML
    public void guardar(ActionEvent event) {

        String nombre = txtNombre.getText().trim();
        int capacidad = Integer.parseInt(txtCapacidad.getText().trim());
        int dormitorios = Integer.parseInt(txtDormitorios.getText().trim());
        int baños = Integer.parseInt(txtBaños.getText().trim());
        int terraza = Integer.parseInt(txtTerraza.getText().trim());
        int piscina = Integer.parseInt(txtPiscina.getText().trim());
        int aparcamiento = Integer.parseInt(txtAparcamiento.getText().trim());
        String direccion = txtDireccion.getText().trim();
        String poblacion = txtPoblacion.getText().trim();
        String provincia = txtProvincia.getText().trim();
        int propietario = Integer.parseInt(txtPropietario.getText().trim());
        int usuario = Integer.parseInt(txtUsuario.getText().trim());

        Alojamiento alojamiento = new Alojamiento(nombre, capacidad, dormitorios, baños, terraza, piscina, aparcamiento, direccion, poblacion, provincia, propietario, usuario);
        AlojamientoDAO alojamientoDAO = new AlojamientoDAO();
        int alojamientoInsertado = alojamientoDAO.insertar(alojamiento);

        if (alojamientoInsertado != 0) {
            Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
            mensaje.setTitle("Confirmación");
            mensaje.setContentText("Alojamiento guardado correctamente.");
            mensaje.show();
            this.cerrarInterfaz(event);
        } else {
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Error");
            mensaje.setContentText("Ha ocurrido un error al guardar el alojamiento.");
            mensaje.show();
            this.cerrarInterfaz(event);
        }
    }

    @FXML
    public void cancelar(ActionEvent event) {

        //PREGUNTAR POR CONFIRMACION
        this.cerrarInterfaz(event);
    }

    public void cerrarInterfaz(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
