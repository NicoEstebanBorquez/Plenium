package controladores.inmuebles;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import modelos.Inmuebles;
import modelos.InmueblesDAO;

public class PanelInfoInmuebleController implements Initializable {

        // nombre, tipo, precio, superficieConstruida, superficieTotal, direccion, poblacion, provincia, 
        // categoria, fechaPublicacion, dormitorios, banos, amueblado, semiAmueblado, noAmueblado, cocinaEquipada, aireAcondicionado, 
        // calefaccionCentral, gasNatural, placasSolares, armariosEmpotrados, terrazaBalcon, piscina, aparcamiento, ascensor, 
        // trastero, jardin, propietario, usuario
    
    protected static int inmuebleSeleccionado;
    
    @FXML
    private Label labelReferencia, labelNombre, labelTipo, labelPrecio, labelSuperficieConstruida, labelSuperficieTotal, labelDireccion, labelPoblacion, labelProvincia, labelCategoria,
            labelFechaPublicacion, labelDormitorios, labelBanos, labelAmueblado, labelSemiAmueblado, labelNoAmueblado, labelCocinaEquipada, labelAireAcondicionado,
            labelCalefaccionCentral, labelGasNatural, labelPlacasSolares, labelArmariosEmpotrados, labelTerrazaBalcon, labelPiscina, labelAparcamiento, labelAscensor,
            labelTrastero, labelJardin, labelPropietario, labelUsuario;


    public void cargarDatos(int seleccionado) {
        Inmuebles inmueble = null;
        inmueble = new InmueblesDAO().obtenerPorId(seleccionado);

        labelReferencia.setText(Integer.toString(inmueble.getIdInmueble()));
        labelNombre.setText(inmueble.getNombre()); 
        labelTipo.setText(Integer.toString(inmueble.getTipo()));
        labelPrecio.setText(Double.toString(inmueble.getPrecio()));
        labelSuperficieConstruida.setText(Double.toString(inmueble.getSuperficieConstruida()));
        labelSuperficieTotal.setText(Double.toString(inmueble.getSuperficieTotal()));
        labelDireccion.setText(inmueble.getDireccion());
        labelPoblacion.setText(inmueble.getPoblacion());
        labelProvincia.setText(inmueble.getProvincia());
        labelCategoria.setText(Integer.toString(inmueble.getCategoria()));
        labelFechaPublicacion.setText(String.valueOf(inmueble.getFechaPublicacion()));
        labelDormitorios.setText(Integer.toString(inmueble.getDormitorios()));
        labelBanos.setText(Integer.toString(inmueble.getBanos()));
        labelAmueblado.setText(Integer.toString(inmueble.getAmueblado()));
        labelSemiAmueblado.setText(Integer.toString(inmueble.getSemiAmueblado()));
        labelNoAmueblado.setText(Integer.toString(inmueble.getNoAmueblado()));
        labelCocinaEquipada.setText(Integer.toString(inmueble.getCocinaEquipada()));
        labelAireAcondicionado.setText(Integer.toString(inmueble.getAireAcondicionado()));
        labelCalefaccionCentral.setText(Integer.toString(inmueble.getCalefaccionCentral()));
        labelGasNatural.setText(Integer.toString(inmueble.getGasNatural()));
        labelPlacasSolares.setText(Integer.toString(inmueble.getPlacasSolares()));
        labelArmariosEmpotrados.setText(Integer.toString(inmueble.getArmariosEmpotrados()));   
        labelTerrazaBalcon.setText(Integer.toString(inmueble.getTerrazaBalcon()));
        labelPiscina.setText(Integer.toString(inmueble.getPiscina()));
        labelAparcamiento.setText(Integer.toString(inmueble.getAparcamiento()));
        labelAscensor.setText(Integer.toString(inmueble.getAscensor()));
        labelTrastero.setText(Integer.toString(inmueble.getTrastero()));
        labelJardin.setText(Integer.toString(inmueble.getJardin()));
        labelPropietario.setText(Integer.toString(inmueble.getIdPropietario()));
        labelUsuario.setText(Integer.toString(inmueble.getIdUsuario()));
     }

    @FXML
    public void editarInmueble(ActionEvent event) throws IOException{
        //Se envía el id del inmueble seleccionado al controlador PanelEditarInmuebleController
        PanelEditarInmuebleController.inmuebleSeleccionado = inmuebleSeleccionado;
        
        
        //CARGAR VISTA "NUEVO ALOJAMIENTO"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/inmuebles/PanelEditarInmueble.fxml"));
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
        escenario.setTitle("Editar inmueble");
        escenario.show();
    }
    
    
    @FXML
    public void eliminarInmueble(){
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
        cargarDatos(inmuebleSeleccionado);
    }

}
