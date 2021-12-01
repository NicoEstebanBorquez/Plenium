package controladores.inmuebles;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelos.Inmuebles;
import modelos.InmueblesDAO;

public class PanelEditarInmuebleController implements Initializable {

    protected static int inmuebleSeleccionado;

    @FXML
    private TextField txtReferencia, txtNombre, txtTipo, txtPrecio, txtSuperficieConstruida, txtSuperficieTotal, txtDireccion, txtPoblacion, txtProvincia, txtCategoria,
            txtFechaPublicacion, txtDormitorios, txtBanos, txtAmueblado, txtSemiAmueblado, txtNoAmueblado, txtCocinaEquipada, txtAireAcondicionado,
            txtCalefaccionCentral, txtGasNatural, txtPlacasSolares, txtArmariosEmpotrados, txtTerrazaBalcon, txtPiscina, txtAparcamiento, txtAscensor,
            txtTrastero, txtJardin, txtPropietarioNombre, txtPropietarioApellidos, txtPropietarioNif, txtPropietarioTelefono, txtPropietarioEmail, txtUsuario;

    @FXML
    public void guardar(ActionEvent event) {
        int referencia = Integer.parseInt(txtReferencia.getText().trim());
        String nombre = txtNombre.getText().trim();
        int tipo = Integer.parseInt(txtTipo.getText().trim());
        double precio = Double.parseDouble(txtPrecio.getText().trim());
        double superficieConstruida = Double.parseDouble(txtSuperficieConstruida.getText().trim());
        double superficieTotal = Double.parseDouble(txtSuperficieTotal.getText().trim());
        String direccion = txtDireccion.getText().trim();
        String poblacion = txtPoblacion.getText().trim();
        String provincia = txtProvincia.getText().trim();
        int categoria = Integer.parseInt(txtCategoria.getText().trim());
        Date fechaPublicacion = Date.valueOf(txtFechaPublicacion.getText().trim());
        int dormitorios = Integer.parseInt(txtDormitorios.getText().trim());
        int banos = Integer.parseInt(txtBanos.getText().trim());
        int amueblado = Integer.parseInt(txtAmueblado.getText().trim());
        int semiAmueblado = Integer.parseInt(txtSemiAmueblado.getText().trim());
        int noAmueblado = Integer.parseInt(txtNoAmueblado.getText().trim());
        int cocinaEquipada = Integer.parseInt(txtCocinaEquipada.getText().trim());
        int aireAcondicionado = Integer.parseInt(txtAireAcondicionado.getText().trim());
        int calefaccionCentral = Integer.parseInt(txtCalefaccionCentral.getText().trim());
        int gasNatural = Integer.parseInt(txtGasNatural.getText().trim());
        int placasSolares = Integer.parseInt(txtPlacasSolares.getText().trim());
        int armariosEmpotrados = Integer.parseInt(txtArmariosEmpotrados.getText().trim());
        int terrazaBalcon = Integer.parseInt(txtTerrazaBalcon.getText().trim());
        int piscina = Integer.parseInt(txtPiscina.getText().trim());
        int aparcamiento = Integer.parseInt(txtAparcamiento.getText().trim());
        int ascensor = Integer.parseInt(txtAscensor.getText().trim());
        int trastero = Integer.parseInt(txtTrastero.getText().trim());
        int jardin = Integer.parseInt(txtJardin.getText().trim());
        String propietarioNombre = txtPropietarioNombre.getText().trim();
        String propietarioApellidos = txtPropietarioApellidos.getText().trim();
        String propietarioNif = txtPropietarioNif.getText().trim();
        String propietarioTelefono = txtPropietarioTelefono.getText().trim();
        String propietarioEmail = txtPropietarioEmail.getText().trim();
        int usuario = Integer.parseInt(txtUsuario.getText().trim());

        Inmuebles inmueble = new Inmuebles(referencia, nombre, tipo, precio, superficieConstruida, superficieTotal, direccion, poblacion, provincia, categoria, fechaPublicacion, dormitorios,
                banos, amueblado, semiAmueblado, noAmueblado, cocinaEquipada, aireAcondicionado, calefaccionCentral, gasNatural, placasSolares, armariosEmpotrados, terrazaBalcon,
                piscina, aparcamiento, ascensor, trastero, jardin, propietarioNombre, propietarioApellidos, propietarioNif, propietarioTelefono, propietarioEmail, usuario);

        InmueblesDAO inmuebleDAO = new InmueblesDAO();
        int inmuebleActualizado = inmuebleDAO.actualizar(inmueble);

        if (inmuebleActualizado != 0) {
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Confirmación");
            mensaje.setContentText("Inmueble modificado correctamente.");
            Optional<ButtonType> resultado = mensaje.showAndWait();
            if (resultado.get() == ButtonType.OK) {
                this.cerrarInterfaz(event);
            }
        } else {
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Error");
            mensaje.setContentText("Ha ocurrido un error al modificar el inmueble.");
            mensaje.showAndWait();
            this.cerrarInterfaz(event);
        }
    }

    @FXML
    public void cancelar(ActionEvent event) {
        Alert confirmacion = new Alert(AlertType.CONFIRMATION);
        confirmacion.setHeaderText("Confirmación");
        confirmacion.setContentText("Se perderán los cambios no guardados.\n¿Desea continuar?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.get() == ButtonType.OK) {
            this.cerrarInterfaz(event);
        }
    }

    public void cargarDatos(int seleccionado) {
        Inmuebles inmueble = null;
        inmueble = new InmueblesDAO().obtenerPorId(seleccionado);

        txtReferencia.setText(Integer.toString(inmueble.getIdInmueble()));
        txtNombre.setText(inmueble.getNombre());
        txtTipo.setText(Integer.toString(inmueble.getTipo()));
        txtPrecio.setText(Double.toString(inmueble.getPrecio()));
        txtSuperficieConstruida.setText(Double.toString(inmueble.getSuperficieConstruida()));
        txtSuperficieTotal.setText(Double.toString(inmueble.getSuperficieTotal()));
        txtDireccion.setText(inmueble.getDireccion());
        txtPoblacion.setText(inmueble.getPoblacion());
        txtProvincia.setText(inmueble.getProvincia());
        txtCategoria.setText(Integer.toString(inmueble.getCategoria()));
        txtFechaPublicacion.setText(String.valueOf(inmueble.getFechaPublicacion()));
        txtDormitorios.setText(Integer.toString(inmueble.getDormitorios()));
        txtBanos.setText(Integer.toString(inmueble.getBanos()));
        txtAmueblado.setText(Integer.toString(inmueble.getAmueblado()));
        txtSemiAmueblado.setText(Integer.toString(inmueble.getSemiAmueblado()));
        txtNoAmueblado.setText(Integer.toString(inmueble.getNoAmueblado()));
        txtCocinaEquipada.setText(Integer.toString(inmueble.getCocinaEquipada()));
        txtAireAcondicionado.setText(Integer.toString(inmueble.getAireAcondicionado()));
        txtCalefaccionCentral.setText(Integer.toString(inmueble.getCalefaccionCentral()));
        txtGasNatural.setText(Integer.toString(inmueble.getGasNatural()));
        txtPlacasSolares.setText(Integer.toString(inmueble.getPlacasSolares()));
        txtArmariosEmpotrados.setText(Integer.toString(inmueble.getArmariosEmpotrados()));
        txtTerrazaBalcon.setText(Integer.toString(inmueble.getTerrazaBalcon()));
        txtPiscina.setText(Integer.toString(inmueble.getPiscina()));
        txtAparcamiento.setText(Integer.toString(inmueble.getAparcamiento()));
        txtAscensor.setText(Integer.toString(inmueble.getAscensor()));
        txtTrastero.setText(Integer.toString(inmueble.getTrastero()));
        txtJardin.setText(Integer.toString(inmueble.getJardin()));
        txtPropietarioNombre.setText(inmueble.getPropietarioNombre());
        txtPropietarioApellidos.setText(inmueble.getPropietarioApellidos());
        txtPropietarioNif.setText(inmueble.getPropietarioNif());
        txtPropietarioTelefono.setText(inmueble.getPropietarioTelefono());
        txtPropietarioEmail.setText(inmueble.getPropietarioEmail());
        txtUsuario.setText(Integer.toString(inmueble.getIdUsuario()));
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
