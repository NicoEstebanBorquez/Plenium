package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class VistaGeneralController implements Initializable {

    @FXML
    private AnchorPane PanelContenido;

    @FXML
    public void cargarPanel_PanelControl(ActionEvent event) {
        cargarPanel("PanelControl.fxml");
    }

    // INMUEBLES
    @FXML
    public void cargarPanel_ListaInmuebles(ActionEvent Ftarevent) {
        cargarPanel("inmuebles/PanelListaInmuebles.fxml");
    }

    @FXML
    public void cargarPanel_AnadirInmueble(ActionEvent event) {
        cargarPanel("inmuebles/PanelAnadirInmueble.fxml");
    }
    // CLIENTES
    @FXML
    public void cargarPanel_ListaClientes(ActionEvent event) {
        cargarPanel("clientes/PanelListaClientes.fxml");
    }

    @FXML
    public void cargarPanel_AnadirCliente(ActionEvent event) {
        cargarPanel("clientes/PanelAnadirCliente.fxml");
    }
    

    // AGENDA
    @FXML
    public void cargarPanel_ConsultarAgenda(ActionEvent event) {
        cargarPanel("agenda/PanelConsultarAgenda.fxml");
    }

    // TAREAS
    @FXML
    public void cargarPanel_ConsultarTareas(ActionEvent event) {
        cargarPanel("tareas/PanelConsultarTareas.fxml");
    }

    private void cargarPanel(String panel) {
        //Eliminamos la vista que hubiese cargada previamente en la
        //sección habilitada al respecto en la vista principal, 
        //que en este caso es VBxox con fx:id="contenido"
        PanelContenido.getChildren().clear();
        //Obtenemos la ubicación de la vista(panel) que vamos a cargar
        URL url = getClass().getResource("/vistas/" + panel);

        try {
            //La clase FXMLLoader, permite cargar un recurso en un nodo, 
            //pasándole la ubicación del recurso que deseamos cargar.

            //Cargamos la vista(panel) en un nodo pasándole la ubicación del recurso
            Node nodo = FXMLLoader.load(url);

            //Añadimos el nodo al panel con fx:id="contenido" en la vista
            //principal. De esta manera cargamos el panel pasado como argumento
            //en esa sección de la vista principal, mostrándose el mismo.
            PanelContenido.getChildren().add(nodo);

            //Mensaje por consola cerciorándonos de que el panel se ha cargado
            //correctamente
        } catch (Exception ex) {
            System.err.println("No se ha cargado: " + panel + "\n" + ex.toString());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarPanel("PanelControl.fxml");
    }

}
