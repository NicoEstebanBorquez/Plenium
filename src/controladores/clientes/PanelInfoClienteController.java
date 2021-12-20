package controladores.clientes;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelos.Clientes;
import modelos.ClientesDAO;
import modelos.ClientesDAO;

public class PanelInfoClienteController implements Initializable {

    protected static int clienteSeleccionado;

    @FXML
    private Label labelReferencia, labelNombre, labelApellidos, labelNif, labelTelefono, labelEmail, labelUsuario,
            labelTipo, labelPresupuestoMin, labelPresupuestoMax, labelDormitoriosMin, labelDormitoriosMax,
            labelTerrazaBalcon, labelAparcamiento, labelPiscina, labelAscensor, labelPoblacion, labelProvincia;

    public void cargarDatos(int seleccionado) {
        Clientes cliente = null;
        cliente = new ClientesDAO().obtenerPorId(seleccionado);

        labelReferencia.setText(Integer.toString(cliente.getIdCliente()));
        labelNombre.setText(cliente.getNombre());
        labelApellidos.setText(cliente.getApellidos());
        labelNif.setText(cliente.getNif());
        labelTelefono.setText(cliente.getTelefono());
        labelEmail.setText(cliente.getEmail());
        labelUsuario.setText(Integer.toString(cliente.getIdUsuario()));
        labelTipo.setText(Integer.toString(cliente.getTipo()));
        labelPresupuestoMin.setText(Double.toString(cliente.getPresupuestoMin()));
        labelPresupuestoMax.setText(Double.toString(cliente.getPresupuestoMax()));
        labelDormitoriosMin.setText(Integer.toString(cliente.getDormitoriosMin()));
        labelDormitoriosMax.setText(Integer.toString(cliente.getDormitoriosMax()));
        labelTerrazaBalcon.setText(Integer.toString(cliente.getTerrazaBalcon()));
        labelAparcamiento.setText(Integer.toString(cliente.getAparcamiento()));
        labelPiscina.setText(Integer.toString(cliente.getPiscina()));
        labelAscensor.setText(Integer.toString(cliente.getAscensor()));
        labelPoblacion.setText(cliente.getPoblacion());
        labelProvincia.setText(cliente.getProvincia());

    }

    @FXML
    public void editarCliente(ActionEvent event) throws IOException {
        //Se envía el id del cliente seleccionado al controlador PanelEditarClienteController
        PanelEditarClienteController.clienteSeleccionado = clienteSeleccionado;

        //CARGAR VISTA "NUEVO ALOJAMIENTO"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/clientes/PanelEditarCliente.fxml"));
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
        escenario.setTitle("Editar cliente");
        escenario.show();
    }

    @FXML
    public void eliminarCliente(ActionEvent event) {
      int clienteEliminado = 0;

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setHeaderText("Confirmación");
        confirmacion.setContentText("El cliente será eliminado.\n¿Desea continuar?");
        Optional<ButtonType> resultadoConfirmacion = confirmacion.showAndWait();
        if (resultadoConfirmacion.get() == ButtonType.OK) {
            ClientesDAO clienteDAO = new ClientesDAO();
            clienteEliminado = clienteDAO.eliminar(clienteSeleccionado);

            if (clienteEliminado != 0) {
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Confirmación");
                mensaje.setContentText("Cliente eliminado correctamente.");
                Optional<ButtonType> resultado = mensaje.showAndWait();
                if (resultado.get() == ButtonType.OK) {
                    this.cerrarInterfaz(event);
                }
            } else {
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setTitle("Error");
                mensaje.setContentText("Ha ocurrido un error al eliminar el cliente.");
                mensaje.showAndWait();
                this.cerrarInterfaz(event);
            }
        }
    }

    @FXML
    public void cerrar(ActionEvent event) {
        this.cerrarInterfaz(event);
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
