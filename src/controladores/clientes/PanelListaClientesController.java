package controladores.clientes;

import controladores.VistaGeneralController;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelos.Clientes;
import modelos.ClientesDAO;
import modelos.SessionFactorySingleton;
import org.hibernate.SessionFactory;

public class PanelListaClientesController implements Initializable  {

    @FXML
    private TableView<Clientes> tablaClientes;

    @FXML
    private TableColumn colRef, colNombre, colTelefono, colEmail;

    private ObservableList<Clientes> listaObservable;

    SessionFactory sf = SessionFactorySingleton.getSessionFactory();

    @FXML
    public void abrirNuevoCliente() {
        
    }

    public void mostrarClientes() {
        //Tabla
        listaObservable = FXCollections.observableArrayList();
        this.colRef.setCellValueFactory(new PropertyValueFactory("idCliente"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        this.colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        /*
        *
        * Esto tengo que ver como meterlo en ClientesDAO
        * De modo que al llamar a listar() me devuelve el List con los datos
        * Lo he metido aquí porque por algún motivo solo puedo usarlo una vez porque se me cierra la sesion
         */
        Clientes cliente = null;
        ClientesDAO clienteDAO = new ClientesDAO();
        List<Clientes> lista = clienteDAO.listar();

        Iterator iterador = lista.iterator();
        while (iterador.hasNext()) {
            cliente = (Clientes) iterador.next();
            this.listaObservable.add(cliente);
            this.tablaClientes.setItems(listaObservable);
        }
    }

    @FXML
    public void seleccionar() throws IOException  {
        Clientes clienteSeleccionado = this.tablaClientes.getSelectionModel().getSelectedItem();
        int seleccionado = clienteSeleccionado.getIdCliente();

        //Se envía el id del cliente seleccionado al controlador PanelInfoClientesController
        PanelInfoClienteController.clienteSeleccionado = seleccionado;

        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/clientes/panelInfoCliente.fxml"));
        //Cargar el padre
        Parent padre = loader.load();
        Scene escena = new Scene(padre);
        Stage escenario = new Stage();
        escenario.setScene(escena);
        escenario.initStyle(StageStyle.UNDECORATED);
        escenario.setResizable(false);
        escenario.setTitle("Info cliente");
        escenario.show();

        /*
            *   Me faltaría ver como hacer para que la ventana Info Clientes fuese modal como la de Nuevo Clientes
            * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
         */
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.mostrarClientes();
    }
}
