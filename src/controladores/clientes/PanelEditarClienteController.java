package controladores.clientes;

import controladores.clientes.*;
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
import modelos.Clientes;
import modelos.ClientesDAO;

public class PanelEditarClienteController implements Initializable {

    protected static int clienteSeleccionado;

    @FXML
    private TextField txtReferencia, txtNombre, txtApellidos, txtDni, txtTelefono, txtEmail, txtDireccion, txtUsuario;

    @FXML
    public void guardar(ActionEvent event) {
        int referencia = Integer.parseInt(txtReferencia.getText().trim());
        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String dni = txtDni.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String email = txtEmail.getText().trim();
        String direccion = txtDireccion.getText().trim();
        int usuario = Integer.parseInt(txtUsuario.getText().trim());

        Clientes cliente = new Clientes(referencia, nombre, apellidos, dni, telefono, email, direccion, usuario);
        ClientesDAO clienteDAO = new ClientesDAO();
        int clienteActualizado = clienteDAO.actualizar(cliente);

        if (clienteActualizado != 0) {
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Confirmación");
            mensaje.setContentText("Cliente modificado correctamente.");
            Optional<ButtonType> resultado = mensaje.showAndWait();
            if (resultado.get() == ButtonType.OK) {
                this.cerrarInterfaz(event);
            }
        } else {
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Error");
            mensaje.setContentText("Ha ocurrido un error al modificar el cliente.");
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
        Clientes cliente = null;
        cliente = new ClientesDAO().obtenerPorId(seleccionado);

        txtReferencia.setText(Integer.toString(cliente.getIdCliente()));
        txtNombre.setText(cliente.getNombre());
        txtApellidos.setText(cliente.getApellidos());
        txtDni.setText(cliente.getDni());
        txtTelefono.setText(cliente.getTelefono());
        txtEmail.setText(cliente.getEmail());
        txtDireccion.setText(cliente.getDireccion());
        txtUsuario.setText(Integer.toString(cliente.getIdUsuario()));
    }

    public void cerrarInterfaz(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos(clienteSeleccionado);
    }

}
