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
import javafxsgacp.utilidades.Utilidades;

/**
 *
 * @author sulem
 */
public class FXMLMenuPrincipalController implements Initializable {

        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickGenerarConstancia(MouseEvent event) {
        //Ir Inicio Sesion
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
}
