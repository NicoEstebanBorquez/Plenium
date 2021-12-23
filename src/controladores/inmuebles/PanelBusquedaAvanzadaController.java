package controladores.inmuebles;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    private TextField txtPrecioMin, txtPrecioMax, txtDormitoriosMin, txtDormitoriosMax, txtSuperficieMin, txtSuperficieMax, txtTipo, txtPoblacion, txtProvincia;

    @FXML
    private TableView<Inmuebles> tablaInmuebles;

    @FXML
    private TableColumn colRef, colTipo, colNombre, colPrecio, colSuperficie, colDormitorios, colPoblacion, colProvincia;

    private ObservableList<Inmuebles> listaObservable;

    public void mostrarInmuebles() {
        //Tabla
        listaObservable = FXCollections.observableArrayList();
        this.colRef.setCellValueFactory(new PropertyValueFactory("idInmueble"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        this.colSuperficie.setCellValueFactory(new PropertyValueFactory("superficieTotal"));
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

        //Tabla
        listaObservable = FXCollections.observableArrayList();
        this.colRef.setCellValueFactory(new PropertyValueFactory("idInmueble"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        this.colSuperficie.setCellValueFactory(new PropertyValueFactory("superficieTotal"));
        this.colDormitorios.setCellValueFactory(new PropertyValueFactory("dormitorios"));
        this.colPoblacion.setCellValueFactory(new PropertyValueFactory("poblacion"));
        this.colProvincia.setCellValueFactory(new PropertyValueFactory("provincia"));

        Inmuebles inmueble = null;

        //Parámetros de la búsqueda: ----------------------------------------
        String txt_PrecioMin = txtPrecioMin.getText().trim();
        String txt_PrecioMax = txtPrecioMax.getText().trim();
        String txt_dormitoriosMin = txtDormitoriosMin.getText().trim();
        String txt_dormitoriosMax = txtDormitoriosMax.getText().trim();
        String txt_SuperficieMin = txtSuperficieMin.getText().trim();
        String txt_SuperficieMax = txtSuperficieMax.getText().trim();
        String txt_poblacion = txtPoblacion.getText().trim();
        String txt_provincia = txtProvincia.getText().trim();
        String txt_tipo = txtTipo.getText().trim();

        int precioMin = 0;
        int precioMax = 9999999;
        int dormitoriosMin = 0;
        int dormitoriosMax = 99;
        int superficieMin = 0;
        int superficieMax = 999;
        String poblacion = "%";
        String provincia = "%";
        String tipo = "%";

        if (!txt_PrecioMin.equals("")) {
            precioMin = Integer.parseInt(txt_PrecioMin);
        }

        if (!txt_PrecioMax.equals("")) {
            precioMax = Integer.parseInt(txt_PrecioMax);
        }

        if (!txt_dormitoriosMin.equals("")) {
            dormitoriosMin = Integer.parseInt(txt_dormitoriosMin);
        }

        if (!txt_dormitoriosMax.equals("")) {
            dormitoriosMax = Integer.parseInt(txt_dormitoriosMax);
        }

        if (!txt_SuperficieMin.equals("")) {
            superficieMin = Integer.parseInt(txt_SuperficieMin);
        }

        if (!txt_SuperficieMax.equals("")) {
            superficieMax = Integer.parseInt(txt_SuperficieMax);
        }

        if (!txt_poblacion.equals("")) {
            poblacion = txt_poblacion;
        }

        if (!txt_provincia.equals("")) {
            provincia = txt_provincia;
        }

        if (!txt_tipo.equals("")) {
            tipo = txt_tipo;
        }
        //-----------------------------------------------------------------------------

        List<Inmuebles> lista = new InmueblesDAO().listarBusquedaAvanzada(precioMin, precioMax, dormitoriosMin, dormitoriosMax, superficieMin, superficieMax, poblacion, provincia, tipo);

        if (!lista.isEmpty()) {
            Iterator iterador = lista.iterator();

            while (iterador.hasNext()) {
                inmueble = (Inmuebles) iterador.next();
                this.listaObservable.add(inmueble);
                this.tablaInmuebles.setItems(listaObservable);
            }
        } else {
            listaObservable.clear();
            this.tablaInmuebles.setItems(listaObservable);

            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Sin resultados");
            mensaje.setContentText("No se han obtenido resultados.");
            mensaje.showAndWait();
        }
    }

    @FXML
    public void actualizar() {
        //this.mostrarInmuebles();
    }

    @FXML
    public void limpiar() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.mostrarInmuebles();
    }

}
