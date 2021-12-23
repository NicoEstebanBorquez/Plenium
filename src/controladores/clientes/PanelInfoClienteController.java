package controladores.clientes;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelos.Clientes;
import modelos.ClientesDAO;
import modelos.Inmuebles;
import modelos.InmueblesDAO;
import controladores.inmuebles.PanelInfoInmuebleController;

public class PanelInfoClienteController extends PanelInfoInmuebleController implements Initializable {

    protected static int clienteSeleccionado;

    @FXML
    private Label labelReferencia, labelNombre, labelApellidos, labelNif, labelTelefono, labelEmail, labelUsuario,
            labelTipo, labelPresupuestoMin, labelPresupuestoMax, labelDormitoriosMin, labelDormitoriosMax,
            labelTerrazaBalcon, labelAparcamiento, labelPiscina, labelAscensor, labelPoblacion, labelProvincia;

    @FXML
    private TableView<Inmuebles> tablaInmuebles;

    @FXML
    private TableColumn colRef, colTipo, colNombre, colPrecio, colDormitorios, colPoblacion, colProvincia;

    private ObservableList<Inmuebles> listaObservable;

    public void cargarDatos(int seleccionado) {

        Clientes cliente = new ClientesDAO().obtenerPorId(seleccionado);

        labelReferencia.setText(Integer.toString(cliente.getIdCliente()));
        labelNombre.setText(cliente.getNombre());
        labelApellidos.setText(cliente.getApellidos());
        labelNif.setText(cliente.getNif());
        labelTelefono.setText(cliente.getTelefono());
        labelEmail.setText(cliente.getEmail());
        labelUsuario.setText(Integer.toString(cliente.getIdUsuario()));
        labelTipo.setText(cliente.getTipo());
        labelPresupuestoMin.setText(Integer.toString(cliente.getPresupuestoMin()));
        labelPresupuestoMax.setText(Integer.toString(cliente.getPresupuestoMax()));
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

    public void mostrarInmuebles() {
        //Tabla
        /*listaObservable = FXCollections.observableArrayList();
        this.colRef.setCellValueFactory(new PropertyValueFactory("idInmueble"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        this.colDormitorios.setCellValueFactory(new PropertyValueFactory("dormitorios"));
        this.colPoblacion.setCellValueFactory(new PropertyValueFactory("poblacion"));
        this.colProvincia.setCellValueFactory(new PropertyValueFactory("provincia"));

        Inmuebles inmueble = null;
        List<Inmuebles> lista = new InmueblesDAO().listar();
        Iterator iterador = lista.iterator();

        while (iterador.hasNext()) {
            inmueble = (Inmuebles) iterador.next();
            this.listaObservable.add(inmueble);
            this.tablaInmuebles.setItems(listaObservable);
        }*/
        
        //Tabla
        listaObservable = FXCollections.observableArrayList();
        this.colRef.setCellValueFactory(new PropertyValueFactory("idInmueble"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
       // this.colSuperficie.setCellValueFactory(new PropertyValueFactory("superficieTotal"));
        this.colDormitorios.setCellValueFactory(new PropertyValueFactory("dormitorios"));
        this.colPoblacion.setCellValueFactory(new PropertyValueFactory("poblacion"));
        this.colProvincia.setCellValueFactory(new PropertyValueFactory("provincia"));


        //Parámetros de la búsqueda: ----------------------------------------
        

        Clientes cliente = new ClientesDAO().obtenerPorId(clienteSeleccionado);
        
        int txt_PrecioMin = cliente.getPresupuestoMin();
        int txt_PrecioMax = cliente.getPresupuestoMax();
        int txt_dormitoriosMin = cliente.getDormitoriosMin();
        int txt_dormitoriosMax = cliente.getDormitoriosMax();
        //int txt_SuperficieMin = cliente.getSuperf..... ;
        //int txt_SuperficieMax = cliente.getSuperf..... ;
        String txt_poblacion = cliente.getPoblacion();
        String txt_provincia = cliente.getProvincia();
        String txt_tipo = cliente.getTipo();

       
        int precioMin = 0;
        int precioMax = 9999999;
        int dormitoriosMin = 0;
        int dormitoriosMax = 99;
        int superficieMin = 0;
        int superficieMax = 999;
        String poblacion = "%";
        String provincia = "%";
        String tipo = "%";
        
        //-----------------------------------------------------------------------------

        Inmuebles inmueble = null;
        List<Inmuebles> lista = new InmueblesDAO().listarBusquedaAvanzada(txt_PrecioMin, txt_PrecioMax, txt_dormitoriosMin, txt_dormitoriosMax, 0, 999, txt_poblacion, txt_provincia, txt_tipo);

        if (!lista.isEmpty()) {
            Iterator iterador = lista.iterator();

            while (iterador.hasNext()) {
                inmueble = (Inmuebles) iterador.next();
                this.listaObservable.add(inmueble);
                this.tablaInmuebles.setItems(listaObservable);
            }
        } else {
            
            /*EL ALERT APARECE ANTES DE ABRIR LA  VENTANA
            CAMBIARLO PARA QUE SÓLO SE MUESTRE EL MENSAJE:"No se han obtenido resultados" EN LA TABLA*/
            listaObservable.clear();
            this.tablaInmuebles.setItems(listaObservable);

            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Sin resultados");
            mensaje.setContentText("No se han obtenido resultados XXXXXXXXXX.");
            mensaje.showAndWait();
        }
        
    }

    @FXML
    public void seleccionar() throws IOException {
        Inmuebles inmuebleSeleccionado = this.tablaInmuebles.getSelectionModel().getSelectedItem();
        int seleccionado = inmuebleSeleccionado.getIdInmueble();

        //Se envía el id del inmueble seleccionado al controlador PanelInfoInmueblesController
        PanelInfoInmuebleController.inmuebleSeleccionado = seleccionado;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/inmuebles/panelInfoInmueble.fxml"));
        //Cargar el padre
        Parent padre = loader.load();
        Scene escena = new Scene(padre);
        Stage escenario = new Stage();
        escenario.setScene(escena);
        escenario.initStyle(StageStyle.UNDECORATED);
        escenario.setResizable(false);
        escenario.setTitle("Info inmueble");
        escenario.show();
    }

    public void cerrarInterfaz(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos(clienteSeleccionado);
        this.mostrarInmuebles();
    }

}
