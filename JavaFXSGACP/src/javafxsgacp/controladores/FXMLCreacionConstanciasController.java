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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxsgacp.JavaFXSGACP;

/**
 * FXML Controller class
 *
 * @author sulem
 */
public class FXMLCreacionConstanciasController implements Initializable {

    @FXML
    private Label lblNombreDocente;
    @FXML
    private AnchorPane ancPaneInformacionConstancia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ancPaneInformacionConstancia.setVisible(false);
    }
    @FXML
    private void clickRegresarMenu(MouseEvent event) {
        Stage escenarioBase = (Stage) lblNombreDocente.getScene().getWindow();
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSGACP.class.getResource("vistas/FXMLMenuPrincipalDocentes.fxml"));
            Parent vista = accesoControlador.load();
            FXMLMenuPrincipalDocentesController menuDocentes = accesoControlador.getController();
            
            escenarioBase.setScene(new Scene (vista));
            escenarioBase.setTitle("Menú Principal Docentes");
            escenarioBase.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }    
    
}
