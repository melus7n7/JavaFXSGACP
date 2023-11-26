/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsgacp.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxsgacp.JavaFXSGACP;
import javafxsgacp.modelo.pojo.Usuario;

/**
 * FXML Controller class
 *
 * @author monti
 */
public class FXMLMenuTrabajoDocenteController implements Initializable {

    @FXML
    private Label lblNombreAdministrativo;
    private Usuario personal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void inicializarPantallaTrabajoDocente(Usuario personal){
        this.personal = personal;
        lblNombreAdministrativo.setText(personal.toString());
    }


    @FXML
    private void clickImpartirEE(MouseEvent event) {
        Stage stage = (Stage) this.lblNombreAdministrativo.getScene().getWindow();
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSGACP.class.getResource("vistas/FXMLRegistrarImparticionEE.fxml"));
            Parent vista = accesoControlador.load();
            FXMLRegistrarImparticionEEController impartirEE = accesoControlador.getController();
            impartirEE.ingresarUsuario(personal);
            Scene scene = new Scene(vista);
            stage.setTitle("Registro de Impartir EE");
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex);
        }  
    }

    @FXML
    private void clickSalir(MouseEvent event) {
        Stage stage = (Stage) this.lblNombreAdministrativo.getScene().getWindow();
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSGACP.class.getResource("vistas/FXMLMenuPrincipal.fxml"));
            Parent vista = accesoControlador.load();
            FXMLMenuPrincipalController menuPersonal = accesoControlador.getController();
            menuPersonal.inicializarMenuAdministradores(personal);
            
            Scene scene = new Scene(vista);
            stage.setTitle("Menú Personal Administrativo");
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
