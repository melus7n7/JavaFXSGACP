/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsgacp.controladores;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafxsgacp.JavaFXSGACP;
import javafxsgacp.modelo.dao.TipoTrabajoDocenteDAO;
import javafxsgacp.modelo.pojo.TipoTrabajoDocente;
import javafxsgacp.utilidades.Constantes;
import javafxsgacp.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author sulem
 */
public class FXMLCreacionConstanciasController implements Initializable {

    private ObservableList<TipoTrabajoDocente> tiposTrabajoDocentes;
    
    @FXML
    private Label lblNombreDocente;
    @FXML
    private AnchorPane ancPaneInformacionConstancia;
    @FXML
    private ComboBox<TipoTrabajoDocente> cmbBoxTiposRegistro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ancPaneInformacionConstancia.setVisible(false);
        cargarTiposTrabajo();
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

    private void cargarTiposTrabajo(){
        Pair<Constantes,ArrayList<TipoTrabajoDocente>> respuesta = TipoTrabajoDocenteDAO.recuperarTiposTrabajo();
        Constantes respuestaConsulta = respuesta.getKey();
        ArrayList<TipoTrabajoDocente> listaTiposTrabajo = respuesta.getValue();
        switch(respuestaConsulta){
            case OPERACION_EXITOSA:
                tiposTrabajoDocentes = FXCollections.observableArrayList();
                tiposTrabajoDocentes.addAll(listaTiposTrabajo);
                cmbBoxTiposRegistro.setItems(tiposTrabajoDocentes);
                break;
            case ERROR_CONEXION_BD:
                Utilidades.mostrarDialogoSimple("Error de conexión", "Error en la conexión con la base de datos", Alert.AlertType.ERROR);
                break;
            case ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error de consulta", "Error en la consulta con la base de datos", Alert.AlertType.ERROR);
                break;
        }
    }
    
}
