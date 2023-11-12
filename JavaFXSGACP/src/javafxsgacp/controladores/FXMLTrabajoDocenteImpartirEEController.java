/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsgacp.controladores;

import java.awt.Insets;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafxsgacp.interfaces.INotificacionTrabajoDocenteDatosConstancia;
import javafxsgacp.modelo.pojo.ImparticionExperienciaEducativa;

/**
 * FXML Controller class
 *
 * @author sulem
 */
public class FXMLTrabajoDocenteImpartirEEController implements Initializable {

    private ImparticionExperienciaEducativa trabajo;
    private INotificacionTrabajoDocenteDatosConstancia notificacion;
    
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
    @FXML
    private AnchorPane nchPaneTrabajo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void inicializarTrabajoEE(ImparticionExperienciaEducativa trabajo, INotificacionTrabajoDocenteDatosConstancia notificacion, boolean estaSeleccionado){
        this.trabajo = trabajo;
        this.notificacion = notificacion;
        if(estaSeleccionado){
            nchPaneTrabajo.setStyle("-fx-background-color: #5084d0; -fx-border-color: #08a141; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 10");
        }
        cargarDatos();
    }
    
    public void establecerColorPorDefecto(){
        if(trabajo.getFechaExpedicionConstancia() != null){
            nchPaneTrabajo.setStyle("-fx-background-color: #90b4e0; -fx-border-color: #034f75; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 10");
        }else{
            nchPaneTrabajo.setStyle("-fx-background-color: #bacfe8; -fx-border-color: #034f75; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 10");
        }
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
            nchPaneTrabajo.setStyle("-fx-background-color: #bacfe8; -fx-border-color: #034f75; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 10");
            lblTieneConstancia.setText("Sin constancia");
        }
    }

    @FXML
    private void clickSeleccionarRegistro(MouseEvent event) {
        nchPaneTrabajo.setStyle("-fx-background-color: #5084d0; -fx-border-color: #08a141; -fx-border-width: 3; -fx-border-radius: 10; -fx-background-radius: 10");
        notificacion.cargarDatosConstanciaImpartirEE(trabajo, this);
    }
}
