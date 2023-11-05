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

/**
 * FXML Controller class
 *
 * @author sulem
 */
public class FXMLMenuPrincipalDocentesController implements Initializable {

    @FXML
    private Label lblNombreDocente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickGenerarConstancia(MouseEvent event) {
        Stage escenarioBase = (Stage) lblNombreDocente.getScene().getWindow();
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSGACP.class.getResource("vistas/FXMLCreacionConstancias.fxml"));
            Parent vista = accesoControlador.load();
            FXMLCreacionConstanciasController creacionConstancias = accesoControlador.getController();
            
            escenarioBase.setScene(new Scene (vista));
            escenarioBase.setTitle("Generación Constancias");
            escenarioBase.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void clickSalir(MouseEvent event) {
        //Ir Inicio Sesion
    }
    
}
