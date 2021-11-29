package controladores.clientes;

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
import modelos.Clientes;
import modelos.ClientesDAO;

public class PanelInfoClienteController implements Initializable {

    protected static int clienteSeleccionado;
    
    @FXML
    private Label labelReferencia, labelNombre, labelApellidos, labelDni, labelTelefono, labelEmail, labelUsuario;

    
    public void cargarDatos(int seleccionado) {
        Clientes cliente = null;
        cliente = new ClientesDAO().obtenerPorId(seleccionado);

        labelReferencia.setText(Integer.toString(cliente.getIdCliente()));
        labelNombre.setText(cliente.getNombre());       
        labelApellidos.setText(cliente.getApellidos());
        labelDni.setText(cliente.getDni());
        labelTelefono.setText(cliente.getTelefono());
        labelEmail.setText(cliente.getEmail());
        labelUsuario.setText(Integer.toString(cliente.getIdUsuario()));
    }

    @FXML
    public void editarCliente(ActionEvent event) throws IOException{
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
    public void eliminarCliente(){
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
        cargarDatos(clienteSeleccionado);
    }

}
