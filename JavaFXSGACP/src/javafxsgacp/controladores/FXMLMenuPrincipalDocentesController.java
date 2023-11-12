/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsgacp.controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafxsgacp.JavaFXSGACP;
import javafxsgacp.modelo.dao.FirmaFacultadoDAO;
import javafxsgacp.modelo.dao.TrabajoDocenteDAO;
import javafxsgacp.modelo.pojo.TrabajoDocente;
import javafxsgacp.modelo.pojo.Usuario;
import javafxsgacp.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author sulem
 */
public class FXMLMenuPrincipalDocentesController implements Initializable {

    private Usuario docente;
            
    @FXML
    private Label lblNombreDocente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Por Cambiar Con Inicio Sesion
            docente = new Usuario();
            docente.setNoPersonal("59715493");
            docente.setNombre("Sulem");
            docente.setApellidoPaterno("Martínez");
            docente.setApellidoMaterno("Aguilar");
            mostrarInformacionPantalla();
        //Fin Del Cambio
    }

    @FXML
    private void clickGenerarConstancia(MouseEvent event) {
        Stage escenarioBase = (Stage) lblNombreDocente.getScene().getWindow();
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSGACP.class.getResource("vistas/FXMLCreacionConstancias.fxml"));
            Parent vista = accesoControlador.load();
            FXMLCreacionConstanciasController creacionConstancias = accesoControlador.getController();
            creacionConstancias.inicializarPantalla(docente);
            
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
    
    private void mostrarInformacionPantalla(){
        String nombreCompleto = docente.getNombre().toUpperCase() + " " + docente.getApellidoPaterno().toUpperCase() +
                " " + docente.getApellidoMaterno().toUpperCase();
        lblNombreDocente.setText(nombreCompleto);
    }
}
