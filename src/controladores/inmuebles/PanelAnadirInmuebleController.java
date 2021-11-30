package controladores.inmuebles;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import modelos.Inmuebles;
import modelos.InmueblesDAO;

public class PanelAnadirInmuebleController implements Initializable {

    @FXML
    private TextField txtNombre, txtTipo, txtPrecio, txtSuperficieConstruida, txtSuperficieTotal, txtDireccion, txtPoblacion, txtProvincia, txtCategoria,
            txtFechaPublicacion, txtDormitorios, txtBanos, txtAmueblado, txtSemiAmueblado, txtNoAmueblado, txtCocinaEquipada, txtAireAcondicionado,
            txtCalefaccionCentral, txtGasNatural, txtPlacasSolares, txtArmariosEmpotrados, txtTerrazaBalcon, txtPiscina, txtAparcamiento, txtAscensor,
            txtTrastero, txtJardin, txtPropietario, txtUsuario;

    @FXML
    public void guardar(ActionEvent event) {
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
        int propietario = Integer.parseInt(txtPropietario.getText().trim());
        int usuario = Integer.parseInt(txtUsuario.getText().trim());

        Inmuebles inmueble = new Inmuebles(nombre, tipo, precio, superficieConstruida, superficieTotal, direccion, poblacion, provincia, categoria, fechaPublicacion, dormitorios, 
                banos, amueblado, semiAmueblado, noAmueblado, cocinaEquipada, aireAcondicionado, calefaccionCentral, gasNatural, placasSolares, armariosEmpotrados, terrazaBalcon, 
                piscina, aparcamiento, ascensor, trastero, jardin, propietario, usuario);
        
        InmueblesDAO inmuebleDAO = new InmueblesDAO();
        int inmuebleInsertado = inmuebleDAO.insertar(inmueble);

        if (inmuebleInsertado != 0) {
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Confirmación");
            mensaje.setContentText("Inmueble guardado correctamente.");
            Optional<ButtonType> resultado = mensaje.showAndWait();
            if (resultado.get() == ButtonType.OK) {
                //this.cerrarInterfaz(event);
                //Aqui en vez de cerrar la ventana debería ir al Panel de Control o a la lista de inmubles
                this.limpiarCampos();
            }
        } else {
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Error");
            mensaje.setContentText("Ha ocurrido un error al guardar el inmueble.");
            mensaje.showAndWait();
            //this.cerrarInterfaz(event);
            this.limpiarCampos();
        }
    }

    @FXML
    public void cancelar(ActionEvent event) {

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setHeaderText("Confirmación");
        confirmacion.setContentText("Se perderán los cambios no guardados.\n¿Desea continuar?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.get() == ButtonType.OK) {
            //this.cerrarInterfaz(event);
            this.limpiarCampos();
        }
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtTipo.setText("");
        txtPrecio.setText("");
        txtSuperficieConstruida.setText("");
        txtSuperficieTotal.setText("");
        txtDireccion.setText("");
        txtPoblacion.setText("");
        txtProvincia.setText("");
        txtCategoria.setText("");
        txtFechaPublicacion.setText("");
        txtDormitorios.setText("");
        txtBanos.setText("");
        txtAmueblado.setText("");
        txtSemiAmueblado.setText("");
        txtNoAmueblado.setText("");
        txtCocinaEquipada.setText("");
        txtAireAcondicionado.setText("");
        txtCalefaccionCentral.setText("");
        txtGasNatural.setText("");
        txtPlacasSolares.setText("");
        txtArmariosEmpotrados.setText("");
        txtTerrazaBalcon.setText("");
        txtPiscina.setText("");
        txtAparcamiento.setText("");
        txtAscensor.setText("");
        txtTrastero.setText("");
        txtJardin.setText("");
        txtPropietario.setText("");
        txtUsuario.setText("");
    }

    /*public void cerrarInterfaz(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }*/
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
