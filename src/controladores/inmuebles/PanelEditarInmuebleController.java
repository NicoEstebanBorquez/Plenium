package controladores.inmuebles;

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
import modelos.Inmuebles;
import modelos.InmueblesDAO;

public class PanelEditarInmuebleController implements Initializable {

    protected static int inmuebleSeleccionado;

    @FXML
    private TextField txtReferencia, txtNombre, txtDireccion, txtPoblacion, txtProvincia, txtCapacidad,
            txtDormitorios, txtBanos, txtTerraza, txtPiscina, txtAparcamiento, txtPropietario, txtUsuario;

    @FXML
    public void guardar(ActionEvent event) {
      /*  int referencia = Integer.parseInt(txtReferencia.getText().trim());
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

        Inmuebles inmueble = new Inmuebles(referencia, nombre, capacidad, dormitorios, baños, terraza, piscina, aparcamiento, direccion, poblacion, provincia, propietario, usuario);
        InmueblesDAO inmuebleDAO = new InmueblesDAO();
        int inmuebleActualizado = inmuebleDAO.actualizar(inmueble);

        if (inmuebleActualizado != 0) {
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Confirmación");
            mensaje.setContentText("Inmueble modificado correctamente.");
            Optional<ButtonType> resultado = mensaje.showAndWait();
            if (resultado.get() == ButtonType.OK) {
                this.cerrarInterfaz(event);
            }
        } else {
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Error");
            mensaje.setContentText("Ha ocurrido un error al modificar el inmueble.");
            mensaje.showAndWait();
            this.cerrarInterfaz(event);
        }*/
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
        Inmuebles inmueble = null;
        inmueble = new InmueblesDAO().obtenerPorId(seleccionado);

        txtReferencia.setText(Integer.toString(inmueble.getIdInmueble()));
        txtNombre.setText(inmueble.getNombre());
        txtDireccion.setText(inmueble.getDireccion());
        txtPoblacion.setText(inmueble.getPoblacion());
        txtProvincia.setText(inmueble.getProvincia());
       // txtCapacidad.setText(Integer.toString(inmueble.getCapacidad()));
        txtDormitorios.setText(Integer.toString(inmueble.getDormitorios()));
        txtBanos.setText(Integer.toString(inmueble.getBanos()));
        txtTerraza.setText(Integer.toString(inmueble.getTerraza()));
        txtPiscina.setText(Integer.toString(inmueble.getPiscina()));
        txtAparcamiento.setText(Integer.toString(inmueble.getAparcamiento()));
        txtPropietario.setText(Integer.toString(inmueble.getIdPropietario()));
        txtUsuario.setText(Integer.toString(inmueble.getIdUsuario()));
        txtPoblacion.setText(inmueble.getPoblacion());

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
