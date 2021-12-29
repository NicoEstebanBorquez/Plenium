package controladores.inmuebles;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import modelos.Inmuebles;
import modelos.InmueblesDAO;
import modelos.Oficinas;
import modelos.OficinasDAO;
import modelos.Usuarios;
import modelos.UsuariosDAO;

public class PanelInfoInmuebleController implements Initializable {

    // nombre, tipo, precio, superficieUtil, superficieTotal, direccion, poblacion, provincia, 
    // fechaPublicacion, dormitorios, banos, amueblado, semiAmueblado, noAmueblado, cocinaEquipada, aireAcondicionado, 
    // calefaccionCentral, gasNatural, placasSolares, armariosEmpotrados, terrazaBalcon, piscina, aparcamiento, ascensor, 
    // trastero, jardin, propietario, usuario
    protected static int inmuebleSeleccionado;

    @FXML
    private Label labelReferencia, labelNombre, labelTipo, labelPrecio, labelSuperficieUtil, labelSuperficieTotal, labelDireccion, labelPoblacion, labelProvincia,
            labelFechaPublicacion, labelDormitorios, labelBanos, labelAmueblado, labelSemiAmueblado, labelNoAmueblado, labelCocinaEquipada, labelAireAcondicionado,
            labelCalefaccionCentral, labelGasNatural, labelPlacasSolares, labelArmariosEmpotrados, labelTerrazaBalcon, labelPiscina, labelAparcamiento, labelAscensor,
            labelTrastero, labelJardin, labelPropietarioNombre, labelPropietarioApellidos, labelPropietarioNif, labelPropietarioTelefono, labelPropietarioEmail;

    @FXML 
    private Label labelAgente, labelContactoTelefono, labelContactoEmail, labelOficina, labelOficinaTelefono, labelOficinaEmail;
    
    
    public void cargarDatos(int seleccionado) {
        
        //Datos del inmueble
        Inmuebles inmueble = null;
        inmueble = new InmueblesDAO().obtenerPorId(seleccionado);

        labelReferencia.setText(Integer.toString(inmueble.getIdInmueble()));
        labelNombre.setText(inmueble.getNombre());
        labelTipo.setText(inmueble.getTipo());
        labelPrecio.setText(Integer.toString(inmueble.getPrecio()));
        labelSuperficieUtil.setText(Double.toString(inmueble.getSuperficieUtil()));
        labelSuperficieTotal.setText(Double.toString(inmueble.getSuperficieTotal()));
        labelDireccion.setText(inmueble.getDireccion());
        labelPoblacion.setText(inmueble.getPoblacion());
        labelProvincia.setText(inmueble.getProvincia());
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
        labelPropietarioNombre.setText(inmueble.getPropietarioNombre());
        labelPropietarioApellidos.setText(inmueble.getPropietarioApellidos());
        labelPropietarioNif.setText(inmueble.getPropietarioNif());
        labelPropietarioTelefono.setText(inmueble.getPropietarioTelefono());
        labelPropietarioEmail.setText(inmueble.getPropietarioEmail());

        
        //Datos del agente
        Usuarios usuario = null;
        usuario = new UsuariosDAO().obtenerPorId(inmueble.getIdUsuario());
        labelAgente.setText(usuario.getNombre() + " " + usuario.getApellidos());
        labelContactoTelefono.setText(usuario.getTelefono());
        labelContactoEmail.setText(usuario.getEmail());
        
        
        //Datos de la oficina
        Oficinas oficina = null;
        oficina = new OficinasDAO().obtenerPorId(usuario.getIdOficina());
        labelOficina.setText(oficina.getNombre());
        labelOficinaTelefono.setText(oficina.getTelefono());
        labelOficinaEmail.setText(oficina.getEmail());
        
    }

    @FXML
    public void editarInmueble(ActionEvent event) throws IOException {
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
    public void eliminarInmueble(ActionEvent event) {

        int inmuebleEliminado = 0;

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION );
        confirmacion.setHeaderText("Confirmación");
        confirmacion.setContentText("El inmueble será eliminado.\n¿Desea continuar?");
        Optional<ButtonType> resultadoConfirmacion = confirmacion.showAndWait();
        if (resultadoConfirmacion.get() == ButtonType.OK) {
            InmueblesDAO inmuebleDAO = new InmueblesDAO();
            inmuebleEliminado = inmuebleDAO.eliminar(inmuebleSeleccionado);

            if (inmuebleEliminado != 0) {
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Confirmación");
                mensaje.setContentText("Inmueble eliminado correctamente.");
                Optional<ButtonType> resultado = mensaje.showAndWait();
                if (resultado.get() == ButtonType.OK) {
                    this.cerrarInterfaz(event);
                }
            } else {
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setTitle("Error");
                mensaje.setContentText("Ha ocurrido un error al eliminar el inmueble.");
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
        cargarDatos(inmuebleSeleccionado);
    }

}
