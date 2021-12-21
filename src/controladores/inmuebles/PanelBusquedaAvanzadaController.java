package controladores.inmuebles;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelos.Inmuebles;
import modelos.InmueblesDAO;

public class PanelBusquedaAvanzadaController implements Initializable {

    @FXML
    private TextField txtRef, txtPrecioMin, txtPrecioMax, txtDormitoriosMin, txtTipo, txtProvincia, txtPoblacion;

    @FXML
    private TableView<Inmuebles> tablaInmuebles;

    @FXML
    private TableColumn colRef, colNombre, colPrecio, colDormitorios, colBanos, colPoblacion, colProvincia;

    private ObservableList<Inmuebles> listaObservable;

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

        Inmuebles inmueble = null;
        List<Inmuebles> lista = new InmueblesDAO().listar();
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
    }

    @FXML
    public void buscar() {

        int inmuebleBuscado = Integer.parseInt(this.txtRef.getText().trim());
        //Tabla
        listaObservable = FXCollections.observableArrayList();
        this.colRef.setCellValueFactory(new PropertyValueFactory("idInmueble"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        this.colDormitorios.setCellValueFactory(new PropertyValueFactory("dormitorios"));
        this.colBanos.setCellValueFactory(new PropertyValueFactory("banos"));
        this.colPoblacion.setCellValueFactory(new PropertyValueFactory("poblacion"));
        this.colProvincia.setCellValueFactory(new PropertyValueFactory("provincia"));

        Inmuebles inmueble = new InmueblesDAO().obtenerPorId(inmuebleBuscado);

        this.listaObservable.add(inmueble);
        this.tablaInmuebles.setItems(listaObservable);
    }

    @FXML
    public void actualizar() {
        //this.mostrarInmuebles();
    }

    @FXML
    public void limpiar() {
        this.txtRef.setText("");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.mostrarInmuebles();
    }

}
