/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsgacp.controladores;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafxsgacp.JavaFXSGACP;
import javafxsgacp.interfaces.INotificacionTrabajoDocenteDatosConstancia;
import javafxsgacp.modelo.dao.TipoTrabajoDocenteDAO;
import javafxsgacp.modelo.dao.TrabajoDocenteDAO;
import javafxsgacp.modelo.pojo.ImparticionExperienciaEducativa;
import javafxsgacp.modelo.pojo.TipoTrabajoDocente;
import javafxsgacp.modelo.pojo.TrabajoDocente;
import javafxsgacp.modelo.pojo.Usuario;
import javafxsgacp.utilidades.Constantes;
import javafxsgacp.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author sulem
 */
public class FXMLCreacionConstanciasController implements Initializable, INotificacionTrabajoDocenteDatosConstancia {

    private ObservableList<TipoTrabajoDocente> tiposTrabajoDocentes;
    private ArrayList<ImparticionExperienciaEducativa> trabajosDocenteEE;
    private ArrayList<FXMLTrabajoDocenteImpartirEEController> trabajosElementosEE;
    private Usuario docente;
    private TrabajoDocente trabajoActual;
    private boolean mostrarTrabajosConConstancias;
    
    @FXML
    private Label lblNombreDocente;
    @FXML
    private AnchorPane ancPaneInformacionConstancia;
    @FXML
    private ComboBox<TipoTrabajoDocente> cmbBoxTiposRegistro;
    @FXML
    private VBox vBoxListaTrabajos;
    @FXML
    private ScrollPane scrPaneTrabajos;
    @FXML
    private Label lblNombreConstancia;
    @FXML
    private AnchorPane nchPaneArchivoPDF;
    @FXML
    private Label lblFechaExpedicion;
    @FXML
    private AnchorPane nchPaneFormatoConstancia;
    @FXML
    private RadioButton rdButtonTrabajosConConstancias;
    @FXML
    private Button btnDescargar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarTrabajosConConstancias = false;
        ancPaneInformacionConstancia.setVisible(false);
        cargarTiposTrabajo();
        cmbBoxTiposRegistro.valueProperty().addListener(new ChangeListener<TipoTrabajoDocente>(){
            @Override
            public void changed(ObservableValue<? extends TipoTrabajoDocente> observable, TipoTrabajoDocente oldValue, TipoTrabajoDocente newValue) {
                if (newValue != null){
                    trabajosDocenteEE = null;
                    vBoxListaTrabajos.getChildren().clear();
                    ancPaneInformacionConstancia.setVisible(false);
                    cargarTrabajosDocente(newValue);
                }
            }
        });
    }
    
    public void inicializarPantalla(Usuario docente){
        this.docente = docente;
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
    
    private void cargarTrabajosDocente(TipoTrabajoDocente tipo){
        switch (tipo.getNombreTrabajo()) {
            case "Impartir Experiencia Educativa":
                recuperarTrabajosDocenteImpartirEE();
                break;
            default:
        }
    }
    
    private void recuperarTrabajosDocenteImpartirEE(){
        Pair<Constantes,ArrayList<ImparticionExperienciaEducativa>> respuesta = TrabajoDocenteDAO.recuperarTrabajosDocenteTipoImpartirEE(docente);
        Constantes respuestaConsulta = respuesta.getKey();
        ArrayList<ImparticionExperienciaEducativa> listaTrabajos = respuesta.getValue();
        switch(respuestaConsulta){
            case OPERACION_EXITOSA:
                trabajosDocenteEE = listaTrabajos;
                cargarTrabajosDocenteImpartirEE();
                break;
            case ERROR_CONEXION_BD:
                Utilidades.mostrarDialogoSimple("Error de conexión", "Error en la conexión con la base de datos", Alert.AlertType.ERROR);
                break;
            case ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error de consulta", "Error en la consulta con la base de datos", Alert.AlertType.ERROR);
                break;
        }
    }
    
    private void cargarTrabajosDocenteImpartirEE(){
        int altoVBox = 0;
        trabajosElementosEE = new ArrayList<>();
        vBoxListaTrabajos.getChildren().clear();
        ancPaneInformacionConstancia.setVisible(false);
        for (int i=0; i<trabajosDocenteEE.size(); i++){
            if(!mostrarTrabajosConConstancias && trabajosDocenteEE.get(i).getFechaExpedicionConstancia() != null){
                continue;
            }
            FXMLLoader fmxlLoaderTrabajoEE = new FXMLLoader();
            fmxlLoaderTrabajoEE.setLocation(JavaFXSGACP.class.getResource("vistas/FXMLTrabajoDocenteImpartirEE.fxml"));
            try{
                Pane pane = fmxlLoaderTrabajoEE.load();
                FXMLTrabajoDocenteImpartirEEController elementoEnLista = fmxlLoaderTrabajoEE.getController();
                elementoEnLista.inicializarTrabajoEE(trabajosDocenteEE.get(i), this);
                trabajosElementosEE.add(elementoEnLista);
                
                altoVBox += pane.getPrefHeight();
                vBoxListaTrabajos.setPrefHeight(altoVBox);
                vBoxListaTrabajos.getChildren().add(pane);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(vBoxListaTrabajos.getPrefHeight() < scrPaneTrabajos.getPrefHeight()){
            vBoxListaTrabajos.setPrefHeight(scrPaneTrabajos.getPrefHeight());
        }
    }

    @Override
    public void cargarDatosConstanciaImpartirEE(ImparticionExperienciaEducativa trabajo,FXMLTrabajoDocenteImpartirEEController elementoSeleccionado) {
        restaurarInformacionConstancia();
        trabajoActual = trabajo;
        for(FXMLTrabajoDocenteImpartirEEController elemento: trabajosElementosEE){
            if(elemento != elementoSeleccionado){
                elemento.establecerColorPorDefecto();
            }
        }
        mostrarInformacionConstancia();
    }
    
    private void mostrarInformacionConstancia(){
        ancPaneInformacionConstancia.setVisible(true);
        if(trabajoActual.getFechaExpedicionConstancia() != null){
            String nombreConstancia = "CONSTANCIA-" + trabajoActual.getTipoTrabajo().getNombreTrabajo() + "-" +
                    trabajoActual.getFechaExpedicionConstancia();
            
            lblFechaExpedicion.setText(trabajoActual.getFechaExpedicionConstancia().toString());
            lblNombreConstancia.setText(nombreConstancia);
            
            String cssArchivo = JavaFXSGACP.class.getResource("estilos/fxmlcreacionconstancias.css").toExternalForm();
            nchPaneArchivoPDF.getStyleClass().clear();
            nchPaneArchivoPDF.getStylesheets().add(cssArchivo);
            nchPaneArchivoPDF.getStyleClass().add("anchorPanePDF");
            
            nchPaneFormatoConstancia.getStyleClass().clear();
            nchPaneFormatoConstancia.getStylesheets().add(cssArchivo);
            nchPaneFormatoConstancia.getStyleClass().add("constanciaEE");
            btnDescargar.setText("Descargar");
        }else{
            btnDescargar.setText("Generar Constancia");
        }
    }
    
    private void restaurarInformacionConstancia(){
        lblFechaExpedicion.setText("-------");
        lblNombreConstancia.setText("-------");
        
        String cssArchivo = JavaFXSGACP.class.getResource("estilos/fxmlcreacionconstancias.css").toExternalForm();
        nchPaneArchivoPDF.getStyleClass().clear();
        nchPaneArchivoPDF.getStylesheets().add(cssArchivo);
        nchPaneArchivoPDF.getStyleClass().add("anchorPaneVacioPDF");
        
        nchPaneFormatoConstancia.getStyleClass().clear();
        nchPaneFormatoConstancia.getStylesheets().add(cssArchivo);
        nchPaneFormatoConstancia.getStyleClass().add("anchorPaneVacioPDF");
    }

    @FXML
    private void clickMostrarTrabajosConConstancias(ActionEvent event) {
        mostrarTrabajosConConstancias = rdButtonTrabajosConConstancias.isSelected();
        if(trabajosDocenteEE != null){
            cargarTrabajosDocenteImpartirEE();
        }
    }

    @FXML
    private void clicDescargar(ActionEvent event) {
        
    }
    
}
