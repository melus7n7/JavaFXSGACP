/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxsgacp.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxsgacp.JavaFXSGACP;
import javafxsgacp.modelo.pojo.Usuario;
import javafxsgacp.utilidades.Utilidades;

/**
 *
 * @author sulem
 */
public class FXMLMenuPrincipalController implements Initializable {

    private Usuario personal;
    @FXML
    private Label lblNombreDocente;
    @FXML
    private Label clickSalir;

        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void inicializarMenuAdministradores(Usuario administrador){
        String nombreCompleto = administrador.getNombre().toUpperCase() + " " + administrador.getApellidoPaterno().toUpperCase() +
                " " + administrador.getApellidoMaterno().toUpperCase();
        lblNombreDocente.setText(nombreCompleto);
        personal = administrador;
    }


    @FXML
    private void clicIrRegistrarDocente(MouseEvent event) {
        Stage escenarioDocentes = new Stage();
        Scene esceneAdminDocentes = Utilidades.inicializarEscena("vistas/FXMLCreacionDocente.fxml");
        escenarioDocentes.setScene(esceneAdminDocentes);
        escenarioDocentes.setTitle("Administración de Docentes");
        escenarioDocentes.initModality(Modality.APPLICATION_MODAL);
        escenarioDocentes.showAndWait();

    }

    @FXML
    private void clickSalir(MouseEvent event) {
        Stage stage = (Stage) this.lblNombreDocente.getScene().getWindow();  
        Parent root;
        try {
            root = FXMLLoader.load(JavaFXSGACP.class.getResource("vistas/FXMLInicioSesion.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Inicio Sesion");
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
