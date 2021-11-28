package controladores.clientes;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import modelos.Clientes;
import modelos.ClientesDAO;

public class PanelAnadirClienteController implements Initializable {

    @FXML
    private TextField txtNombre, txtApellidos, txtDni, txtTelefono, txtEmail, txtDireccion, txtUsuario;

    @FXML
    public void guardar(ActionEvent event) {
        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String dni = txtDni.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String email = txtEmail.getText().trim();
        String direccion = txtDireccion.getText().trim();
        int usuario = Integer.parseInt(txtUsuario.getText().trim());

        Clientes cliente = new Clientes(nombre, apellidos, dni, telefono, email, direccion, usuario);
        ClientesDAO clienteDAO = new ClientesDAO();
        int clienteInsertado = clienteDAO.insertar(cliente);

        if (clienteInsertado != 0) {
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Confirmación");
            mensaje.setContentText("Cliente guardado correctamente.");
            Optional<ButtonType> resultado = mensaje.showAndWait();
            if (resultado.get() == ButtonType.OK) {
                //this.cerrarInterfaz(event);
                //Aqui en vez de cerrar la ventana debería ir al Panel de Control o a la lista de inmubles
                this.limpiarCampos();
            }
        } else {
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Error");
            mensaje.setContentText("Ha ocurrido un error al guardar el cliente.");
            mensaje.showAndWait();
            //this.cerrarInterfaz(event);
            this.limpiarCampos();
        }
    }

    @FXML
    public void cancelar(ActionEvent event) {

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setHeaderText("Confirmación");
        confirmacion.setContentText("Se perderán los cambios no guardados.\n¿Desea continuar?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.get() == ButtonType.OK) {
            //this.cerrarInterfaz(event);
            this.limpiarCampos();
        }
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtDni.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
        txtUsuario.setText("");
    }

    /*public void cerrarInterfaz(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
