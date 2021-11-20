package controladores.alojamientos;

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
import modelos.Alojamiento;
import modelos.AlojamientoDAO;
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
public class PanelAlojamientosController implements Initializable {

    @FXML
    private TableView<Alojamiento> tablaAlojamientos;

    @FXML
    private TableColumn colRef, colAlojamiento, colClase, colCapacidad, colPoblacion, colProvincia;

    private ObservableList<Alojamiento> listaObservable;

    SessionFactory sf = SessionFactorySingleton.getSessionFactory();

    @FXML
    public void abrirNuevoAlojamiento(ActionEvent event) throws IOException {
        System.out.println("ABRIR INTERFAZ NUEVO ALOJAMIENTO");

        //CARGAR VISTA "NUEVO ALOJAMIENTO"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/alojamientos/PanelNuevoAlojamiento.fxml"));
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
        //escenario.initStyle(StageStyle.UNDECORATED);
        escenario.setResizable(false);
        escenario.setTitle("Nuevo alojamiento");
        escenario.show();
    }

    public void mostrarAlojamientos() {
        //Tabla
        listaObservable = FXCollections.observableArrayList();
        this.colRef.setCellValueFactory(new PropertyValueFactory("idAlojamiento"));
        this.colAlojamiento.setCellValueFactory(new PropertyValueFactory("nombre"));
        //this.colClase.setCellValueFactory(new PropertyValueFactory("idAlojamiento"));
        this.colCapacidad.setCellValueFactory(new PropertyValueFactory("capacidad"));
        this.colPoblacion.setCellValueFactory(new PropertyValueFactory("poblacion"));
        this.colProvincia.setCellValueFactory(new PropertyValueFactory("provincia"));

        /*
        *
        * Esto tengo que ver como meterlo en AlojamientoDAO
        * De modo que al llamar a listar() me devuelve el List con los datos
        * Lo he metido aquí porque por algún motivo solo puedo usarlo una vez porque se me cierra la sesion
        */
        //Datos
        Alojamiento alojamiento = null;
        List<Alojamiento> lista = null;

        Session sesion = sf.openSession();
        Transaction transaccion = sesion.beginTransaction();

        Query query = sesion.createQuery("FROM Alojamiento");
        lista = query.list();

        Iterator iterador = lista.iterator();
        while (iterador.hasNext()) {
            alojamiento = (Alojamiento) iterador.next();
            this.listaObservable.add(alojamiento);
            this.tablaAlojamientos.setItems(listaObservable);
        }
        sesion.close();
    }

    @FXML
    public void seleccionar() throws IOException {
        Alojamiento alojamientoSeleccionado = this.tablaAlojamientos.getSelectionModel().getSelectedItem();
        int seleccionado = alojamientoSeleccionado.getIdAlojamiento();
        
        //Se envía el id del alojamiento seleccionado al controlador PanelInfoAlojamientoController
        PanelInfoAlojamientoController.alojamientoSeleccionado = seleccionado;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/alojamientos/panelInfoAlojamiento.fxml"));
        //Cargar el padre
        Parent padre = loader.load();
        Scene escena = new Scene(padre);
        Stage escenario = new Stage();
        escenario.setScene(escena);
        escenario.setResizable(false);
        escenario.setTitle("Info alojamiento");
        escenario.show();

        /*
            *   Me faltaría ver como hacer para que la ventana Info Alojamiento fuese modal como la de Nuevo Alojamiento
            *
            *
            *
         */
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.mostrarAlojamientos();
    }

}
