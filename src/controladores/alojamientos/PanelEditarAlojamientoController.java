package controladores.alojamientos;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
    public void guardar(ActionEvent event) {
        int referencia = Integer.parseInt(txtReferencia.getText().trim());
        String nombre = txtNombre.getText().trim();
        int capacidad = Integer.parseInt(txtCapacidad.getText().trim());
        int dormitorios = Integer.parseInt(txtDormitorios.getText().trim());
        int baños = Integer.parseInt(txtBanos.getText().trim());
        int terraza = Integer.parseInt(txtTerraza.getText().trim());
        int piscina = Integer.parseInt(txtPiscina.getText().trim());
        int aparcamiento = Integer.parseInt(txtAparcamiento.getText().trim());
        String direccion = txtDireccion.getText().trim();
        String poblacion = txtPoblacion.getText().trim();
        String provincia = txtProvincia.getText().trim();
        int propietario = Integer.parseInt(txtPropietario.getText().trim());
        int usuario = Integer.parseInt(txtUsuario.getText().trim());

        Alojamiento alojamiento = new Alojamiento(referencia, nombre, capacidad, dormitorios, baños, terraza, piscina, aparcamiento, direccion, poblacion, provincia, propietario, usuario);
        AlojamientoDAO alojamientoDAO = new AlojamientoDAO();
        int alojamientoActualizado = alojamientoDAO.actualizar(alojamiento);

        if (alojamientoActualizado != 0) {
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Confirmación");
            mensaje.setContentText("Alojamiento modificado correctamente.");
            Optional<ButtonType> resultado = mensaje.showAndWait();
            if (resultado.get() == ButtonType.OK) {
                this.cerrarInterfaz(event);
            }
        } else {
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Error");
            mensaje.setContentText("Ha ocurrido un error al modificar el alojamiento.");
            mensaje.showAndWait();
            this.cerrarInterfaz(event);
        }
    }

    @FXML
    public void cancelar(ActionEvent event) {
        Alert confirmacion = new Alert(AlertType.CONFIRMATION);
        confirmacion.setHeaderText("Confirmación");
        confirmacion.setContentText("Se perderán los cambios no guardados.\n¿Desea continuar?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.get() == ButtonType.OK) {
            this.cerrarInterfaz(event);
        }
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
