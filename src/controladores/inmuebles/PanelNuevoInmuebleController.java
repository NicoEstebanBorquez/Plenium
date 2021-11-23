package controladores.inmuebles;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelos.Inmuebles;
import modelos.InmueblesDAO;

public class PanelNuevoInmuebleController implements Initializable {

    @FXML
    private TextField txtNombre, txtCapacidad, txtDormitorios, txtBanos, txtTerraza, txtPiscina, txtAparcamiento, txtDireccion, txtPoblacion, txtProvincia, txtPropietario, txtUsuario;

    @FXML
    public void guardar(ActionEvent event) {
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

        Inmuebles inmueble = new Inmuebles(nombre, capacidad, dormitorios, baños, terraza, piscina, aparcamiento, direccion, poblacion, provincia, propietario, usuario);
        InmueblesDAO inmuebleDAO = new InmueblesDAO();
        int inmuebleInsertado = inmuebleDAO.insertar(inmueble);

        if (inmuebleInsertado != 0) {
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Confirmación");
            mensaje.setContentText("Inmueble guardado correctamente.");
            Optional<ButtonType> resultado = mensaje.showAndWait();
            if (resultado.get() == ButtonType.OK) {
                this.cerrarInterfaz(event);
            }
        } else {
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Error");
            mensaje.setContentText("Ha ocurrido un error al guardar el inmueble.");
            mensaje.showAndWait();
            this.cerrarInterfaz(event);
        }
    }

    @FXML
    public void cancelar(ActionEvent event) {

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setHeaderText("Confirmación");
        confirmacion.setContentText("Se perderán los cambios no guardados.\n¿Desea continuar?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.get() == ButtonType.OK) {
            this.cerrarInterfaz(event);
        }
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
