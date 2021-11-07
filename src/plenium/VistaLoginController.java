package plenium;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class VistaLoginController implements Initializable {

    @FXML
    private TextField txtUsuario, txtContrasena;
    
    
    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaGeneral.fxml"));

            //Cargar el padre
            Parent padre = loader.load();
            
            //VistaInicioController controlador = loader.getController();
            Scene escena = new Scene(padre);
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setResizable(false);
            escenario.setTitle("Vista General");
            escenario.show();
            
            //Cierra la ventana de Login
            ((Node)(event.getSource())).getScene().getWindow().hide();

        /*
        if (!txtUsuario.getText().equals("") && !txtContrasena.getText().equals("")) {

            //COMPROBAR AQUI USUARIO Y CONTRASEÑA > DAR ACCESO
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaGeneral.fxml"));

            //Cargar el padre
            Parent padre = loader.load();
            
            //VistaInicioController controlador = loader.getController();
            Scene escena = new Scene(padre);
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setResizable(false);
            escenario.setTitle("Vista General");
            escenario.show();
            
            //Cierra la ventana de Login
            ((Node)(event.getSource())).getScene().getWindow().hide();

        } else {
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Error al iniciar sesión");
            mensaje.setContentText("Debe introducir usuario y contraseña");
            mensaje.show();
        }*/
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
