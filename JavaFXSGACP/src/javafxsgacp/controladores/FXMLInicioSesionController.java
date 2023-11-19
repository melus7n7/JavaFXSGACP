/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsgacp.controladores;

import static com.sun.javafx.scene.control.skin.Utils.getResource;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafxsgacp.JavaFXSGACP;
import javafxsgacp.modelo.dao.CuentaDAO;
import javafxsgacp.modelo.pojo.Usuario;
import javafxsgacp.utilidades.Constantes;
import javafxsgacp.utilidades.Utilidades;
import javax.swing.BorderFactory;

/**
 * FXML Controller class
 *
 * @author monti
 */
public class FXMLInicioSesionController implements Initializable {

    @FXML
    private TextField txtFieldCorreoElectronico;
    @FXML
    private TextField txtFieldContraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    

    @FXML
    private void clicContinuar(MouseEvent event) {
        String correoElectronico;
        String contraseña;
        correoElectronico = txtFieldCorreoElectronico.getText();
        contraseña = txtFieldContraseña.getText();
        txtFieldCorreoElectronico.setStyle("-fx-text-box-border: black");
        txtFieldContraseña.setStyle("-fx-text-box-border: black");
        if(correoElectronico.isEmpty() && contraseña.isEmpty()){
            txtFieldCorreoElectronico.setStyle("-fx-text-box-border: red");
            txtFieldContraseña.setStyle("-fx-text-box-border: red");
        }else if(correoElectronico.isEmpty()){
            txtFieldCorreoElectronico.setStyle("-fx-text-box-border: red");
        }else{
            if(contraseña.isEmpty()){
                txtFieldContraseña.setStyle("-fx-text-box-border: red");
            }
            CuentaDAO cuenta = new CuentaDAO();
            Usuario usuario = new Usuario();
            Pair respuesta= new Pair<Constantes, Usuario>(Constantes.ERROR_CONEXION_BD, usuario);
            respuesta = cuenta.recuperarUsuario(correoElectronico, contraseña);
            Constantes respuestaConstante = (Constantes) respuesta.getKey();
            Usuario respuestaUsuario = (Usuario) respuesta.getValue();
            switch (respuestaConstante) {
                case OPERACION_EXITOSA:
                    if(respuestaUsuario.getContraseña().equals(contraseña)){
                        if(respuestaUsuario.getIdTipoUsuario()==1){
                            Utilidades.mostrarDialogoSimple("Usuario encontrado", "Ingresando a menú", Alert.AlertType.INFORMATION);
                            irMenuPrincipalAdministrativo();
                        }else if(respuestaUsuario.getIdTipoUsuario()==2){
                            irMenuPrincipalDocente(respuestaUsuario);
                        }
                    }else{
                        Utilidades.mostrarDialogoSimple("Contraseña invalida", "La contraseña no es correcta, verifique e inténtelo de nuevo", Alert.AlertType.WARNING);
                        txtFieldContraseña.setStyle("-fx-text-box-border: red");
                    }
                    break;
                case OPERACION_VACIA:
                    Utilidades.mostrarDialogoSimple("Usuario no encontrado", "El correo electrónico no se encuentra registrado en el sistema", Alert.AlertType.WARNING);
                    break;
                case ERROR_CONSULTA:
                    Utilidades.mostrarDialogoSimple("Error de conexión", "Ha ocurrido un error de conexión a la base de datos", Alert.AlertType.ERROR);
                    break;
                case ERROR_CONEXION_BD:
                   Utilidades.mostrarDialogoSimple("Error de conexión", "Ha ocurrido un error de conexión a la base de datos", Alert.AlertType.ERROR);
                    break;
            }
        }
    }

    @FXML
    private void clicSalir(MouseEvent event) {
        Stage stage = (Stage) this.txtFieldCorreoElectronico.getScene().getWindow();
        stage.close();
    }
    
    private void irMenuPrincipalAdministrativo(){
        Stage stage = (Stage) this.txtFieldCorreoElectronico.getScene().getWindow();  
        Parent root;
        try {
            root = FXMLLoader.load(JavaFXSGACP.class.getResource("vistas/FXMLMenuPrincipal.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Menú Personal Administrativo");
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
    
    private void irMenuPrincipalDocente(Usuario docente){
        Utilidades.mostrarDialogoSimple("Usuario encontrado", "Ingresando a menú", Alert.AlertType.INFORMATION);
        Stage stage = (Stage) this.txtFieldCorreoElectronico.getScene().getWindow();
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSGACP.class.getResource("vistas/FXMLMenuPrincipalDocentes.fxml"));
            Parent vista = accesoControlador.load();
            FXMLMenuPrincipalDocentesController menuDocente = accesoControlador.getController();
            menuDocente.inicializarPantallaDocente(docente);
            
            Scene scene = new Scene(vista);
            stage.setTitle("Menú Docente");
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex);
        }                        
    }
    
}
