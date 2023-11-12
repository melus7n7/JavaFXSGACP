/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsgacp.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafxsgacp.modelo.pojo.ImparticionExperienciaEducativa;

/**
 * FXML Controller class
 *
 * @author sulem
 */
public class FXMLTrabajoDocenteImpartirEEController implements Initializable {

    private ImparticionExperienciaEducativa trabajo;
    
    @FXML
    private Label lblProgramaEducativo;
    @FXML
    private Label lblExperienciaEducativa;
    @FXML
    private Label lblPeriodo;
    @FXML
    private Label lblBloqueSeccion;
    @FXML
    private Label lblFechaRegistro;
    @FXML
    private Label lblTieneConstancia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void inicializarTrabajoEE(ImparticionExperienciaEducativa trabajo){
        this.trabajo = trabajo;
        cargarDatos();
    }
    
    private void cargarDatos(){
        lblFechaRegistro.setText(trabajo.getFechaRegistro().toString());
        lblProgramaEducativo.setText(trabajo.getExperienciaEducativa().getProgramaEducativo().getNombreProgramaEducativo());
        lblExperienciaEducativa.setText(trabajo.getExperienciaEducativa().getNombre());
        lblPeriodo.setText(trabajo.getExperienciaEducativa().getPeriodo().getNombrePeriodo());
        
        String bloqueSeccion = trabajo.getExperienciaEducativa().getBloque().getNombreBloque() + " - " +
                trabajo.getExperienciaEducativa().getSeccion().getNombreSeccion();
        lblBloqueSeccion.setText(bloqueSeccion);
        
        if(trabajo.getFechaExpedicionConstancia() != null){
            lblTieneConstancia.setText("Con constancia");
        }else{
            lblTieneConstancia.setText("Sin constancia");
        }
    }
}
