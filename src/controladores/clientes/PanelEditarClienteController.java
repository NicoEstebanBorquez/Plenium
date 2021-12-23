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
    private TextField txtReferencia, txtNombre, txtApellidos, txtNif, txtTelefono, txtEmail, txtUsuario,
            txtTipo, txtPresupuestoMin, txtPresupuestoMax, txtDormitoriosMin, txtDormitoriosMax,
            txtTerrazaBalcon, txtAparcamiento, txtPiscina, txtAscensor, txtPoblacion, txtProvincia;

    @FXML
    public void guardar(ActionEvent event) {
        int referencia = Integer.parseInt(txtReferencia.getText().trim());
        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String nif = txtNif.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String email = txtEmail.getText().trim();
        int usuario = Integer.parseInt(txtUsuario.getText().trim());
        String tipo = txtTipo.getText().trim();
        int presupuestoMin = Integer.parseInt(txtPresupuestoMin.getText().trim());
        int presupuestoMax = Integer.parseInt(txtPresupuestoMax.getText().trim());
        int dormitoriosMin = Integer.parseInt(txtDormitoriosMin.getText().trim());
        int dormitoriosMax = Integer.parseInt(txtDormitoriosMax.getText().trim());
        int terrazaBalcon = Integer.parseInt(txtTerrazaBalcon.getText().trim());
        int aparcamiento = Integer.parseInt(txtAparcamiento.getText().trim());
        int piscina = Integer.parseInt(txtPiscina.getText().trim());
        int ascensor = Integer.parseInt(txtAscensor.getText().trim());
        String poblacion = txtPoblacion.getText().trim();
        String provincia = txtProvincia.getText().trim();

        Clientes cliente = new Clientes(referencia, nombre, apellidos, nif, telefono, email, tipo, presupuestoMin, presupuestoMax, dormitoriosMin, dormitoriosMax, terrazaBalcon, aparcamiento, piscina, ascensor, poblacion, provincia, usuario);
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
        txtNif.setText(cliente.getNif());
        txtTelefono.setText(cliente.getTelefono());
        txtEmail.setText(cliente.getEmail());
        txtUsuario.setText(Integer.toString(cliente.getIdUsuario()));
        txtTipo.setText(cliente.getTipo());
        txtPresupuestoMin.setText(Integer.toString(cliente.getPresupuestoMin()));
        txtPresupuestoMax.setText(Integer.toString(cliente.getPresupuestoMax()));
        txtDormitoriosMin.setText(Integer.toString(cliente.getDormitoriosMin()));
        txtDormitoriosMax.setText(Integer.toString(cliente.getDormitoriosMax()));
        txtTerrazaBalcon.setText(Integer.toString(cliente.getTerrazaBalcon()));
        txtAparcamiento.setText(Integer.toString(cliente.getAparcamiento()));
        txtPiscina.setText(Integer.toString(cliente.getPiscina()));
        txtAscensor.setText(Integer.toString(cliente.getAscensor()));
        txtPoblacion.setText(cliente.getPoblacion());
        txtProvincia.setText(cliente.getProvincia());
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
