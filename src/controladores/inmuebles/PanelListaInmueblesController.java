package controladores.inmuebles;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import modelos.Inmuebles;
import modelos.InmueblesDAO;
import modelos.SessionFactorySingleton;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/*
* Tengo que ver como se gestiona el SessionFactory, para que no se me cierre la sesión después de la consulta
*  
*
*
*
 */
public class PanelListaInmueblesController implements Initializable {

    @FXML
    private TableView<Inmuebles> tablaInmuebles;

    @FXML
    private TableColumn colRef, colNombre, colPrecio, colDormitorios, colBanos, colPoblacion, colProvincia;

    private ObservableList<Inmuebles> listaObservable;

    SessionFactory sf = SessionFactorySingleton.getSessionFactory();

    @FXML
    public void abrirNuevoInmueble(ActionEvent event) throws IOException {
        //CARGAR VISTA "NUEVO ALOJAMIENTO"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/inmuebles/PanelNuevoInmueble.fxml"));
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
        escenario.setTitle("Nuevo inmueble");
        escenario.show();
    }

    public void mostrarInmuebles() {
        //Tabla
        listaObservable = FXCollections.observableArrayList();
        this.colRef.setCellValueFactory(new PropertyValueFactory("idInmueble"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre")); 
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio")); 
        this.colDormitorios.setCellValueFactory(new PropertyValueFactory("dormitorios"));
        this.colBanos.setCellValueFactory(new PropertyValueFactory("banos"));
        this.colPoblacion.setCellValueFactory(new PropertyValueFactory("poblacion"));
        this.colProvincia.setCellValueFactory(new PropertyValueFactory("provincia"));

        /*
        *
        * Esto tengo que ver como meterlo en InmueblesDAO
        * De modo que al llamar a listar() me devuelve el List con los datos
        * Lo he metido aquí porque por algún motivo solo puedo usarlo una vez porque se me cierra la sesion
         */
        Inmuebles inmueble = null;
        InmueblesDAO inmuebleDAO = new InmueblesDAO();
        List<Inmuebles> lista = inmuebleDAO.listar();

        Iterator iterador = lista.iterator();
        while (iterador.hasNext()) {
            inmueble = (Inmuebles) iterador.next();
            this.listaObservable.add(inmueble);
            this.tablaInmuebles.setItems(listaObservable);
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

        /*
            *   Me faltaría ver como hacer para que la ventana Info Inmuebles fuese modal como la de Nuevo Inmuebles
            * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
         */
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.mostrarInmuebles();
    }

}
