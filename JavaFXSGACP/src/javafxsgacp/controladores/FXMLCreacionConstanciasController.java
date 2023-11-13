/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsgacp.controladores;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafxsgacp.JavaFXSGACP;
import javafxsgacp.interfaces.INotificacionTrabajoDocenteDatosConstancia;
import javafxsgacp.modelo.dao.FirmaFacultadoDAO;
import javafxsgacp.modelo.dao.TipoTrabajoDocenteDAO;
import javafxsgacp.modelo.dao.TrabajoDocenteDAO;
import javafxsgacp.modelo.dao.UsuarioDAO;
import javafxsgacp.modelo.pojo.FirmaFacultad;
import javafxsgacp.modelo.pojo.ImparticionExperienciaEducativa;
import javafxsgacp.modelo.pojo.TipoTrabajoDocente;
import javafxsgacp.modelo.pojo.TrabajoDocente;
import javafxsgacp.modelo.pojo.Usuario;
import javafxsgacp.utilidades.Constancia;
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
    private FirmaFacultad firmaFacultad;
    private LocalDate fechaInicioRango;
    private LocalDate fechaFinRango;
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
    @FXML
    private DatePicker dtPickerFechaInicio;
    @FXML
    private DatePicker dtPickerFechaFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        trabajoActual = null;
        mostrarTrabajosConConstancias = false;
        ancPaneInformacionConstancia.setVisible(false);
        cargarTiposTrabajo();
        cmbBoxTiposRegistro.valueProperty().addListener(new ChangeListener<TipoTrabajoDocente>(){
            @Override
            public void changed(ObservableValue<? extends TipoTrabajoDocente> observable, TipoTrabajoDocente oldValue, TipoTrabajoDocente newValue) {
                if (newValue != null){
                    trabajoActual = null;
                    trabajosDocenteEE = null;
                    vBoxListaTrabajos.getChildren().clear();
                    ancPaneInformacionConstancia.setVisible(false);
                    cargarTrabajosDocente(newValue);
                }
            }
        });
    }
    
    @FXML
    private void clicAjustarFecha(ActionEvent event) {
        fechaInicioRango = dtPickerFechaInicio.getValue();
        fechaFinRango = dtPickerFechaFin.getValue();
        if((fechaInicioRango != null || fechaFinRango != null) && trabajosDocenteEE != null){
            cargarTrabajosDocenteImpartirEE();
            if(trabajoActual != null && !estaDentroRango(trabajoActual)){
                ancPaneInformacionConstancia.setVisible(false);
            }
        }
    }
    
    public void inicializarPantalla(Usuario docente){
        this.docente = docente;
        prepararDatosVentana();
        recuperarFirmaDigital();
    }
    
    private void prepararDatosVentana(){
        String nombreCompleto = "Docente: " + docente.getNombre().toUpperCase() + " " +
                docente.getApellidoPaterno().toUpperCase() + " " + docente.getApellidoMaterno().toUpperCase();
        lblNombreDocente.setText(nombreCompleto);
    }
    
    private void recuperarFirmaDigital(){
        Pair<Constantes, FirmaFacultad> respuestaConsulta = FirmaFacultadoDAO.recuperarFirmaDigitalFacultad();
        FirmaFacultad firma = respuestaConsulta.getValue();
        Constantes respuesta = respuestaConsulta.getKey();
        switch(respuesta){
            case OPERACION_EXITOSA:
                firmaFacultad = firma;
                break;
            case OPERACION_VACIA:
                Utilidades.mostrarDialogoSimple("Firma digital no válida", "Consulte con el personal administrativo", Alert.AlertType.WARNING);
                salirAMenuPrincipalDocente();
                break;
            case ERROR_CONEXION_BD:
            case ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error de conexión", "Error para conectarse a la base de datos", Alert.AlertType.ERROR);
                salirAMenuPrincipalDocente();
                break;
        }
    }
    
    @FXML
    private void clickRegresarMenu(MouseEvent event) {
        salirAMenuPrincipalDocente();
    }
    
    private void salirAMenuPrincipalDocente(){
        Stage escenarioBase = (Stage) lblNombreDocente.getScene().getWindow();
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSGACP.class.getResource("vistas/FXMLMenuPrincipalDocentes.fxml"));
            Parent vista = accesoControlador.load();
            FXMLMenuPrincipalDocentesController menuDocentes = accesoControlador.getController();
            menuDocentes.inicializarPantallaDocente(docente);
            
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
        ancPaneInformacionConstancia.setVisible(trabajoActual != null);
        
        for (int i=0; i<trabajosDocenteEE.size(); i++){
            if(trabajoActual != null && trabajoActual.getIdTrabajoDocente() == trabajosDocenteEE.get(i).getIdTrabajoDocente()){
                trabajoActual = trabajosDocenteEE.get(i);
            }else{
                if(!mostrarTrabajosConConstancias && trabajosDocenteEE.get(i).getFechaExpedicionConstancia() != null){
                    continue;
                }
            }
            if(!estaDentroRango(trabajosDocenteEE.get(i))){
                continue;
            }
            FXMLLoader fmxlLoaderTrabajoEE = new FXMLLoader();
            fmxlLoaderTrabajoEE.setLocation(JavaFXSGACP.class.getResource("vistas/FXMLTrabajoDocenteImpartirEE.fxml"));
            try{
                Pane pane = fmxlLoaderTrabajoEE.load();
                FXMLTrabajoDocenteImpartirEEController elementoEnLista = fmxlLoaderTrabajoEE.getController();
                boolean estaraSeleccionado = trabajoActual != null && trabajoActual.getIdTrabajoDocente() == trabajosDocenteEE.get(i).getIdTrabajoDocente();
                elementoEnLista.inicializarTrabajoEE(trabajosDocenteEE.get(i), this, estaraSeleccionado);
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
    
    private boolean estaDentroRango(TrabajoDocente trabajo){
        boolean fechaInicioValido = true;
        boolean fechaFinValido = true;
        LocalDate fecharegistro = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(trabajo.getFechaRegistro()));
        if(fechaInicioRango != null ){
            fechaInicioValido = fecharegistro.isAfter(fechaInicioRango);
        }
        if(fechaFinRango != null){
            fechaFinValido = fecharegistro.isBefore(fechaFinRango);
        }
        return fechaInicioValido && fechaFinValido;
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
        String cssArchivo = JavaFXSGACP.class.getResource("estilos/fxmlcreacionconstancias.css").toExternalForm();
        nchPaneFormatoConstancia.getStyleClass().clear();
        nchPaneFormatoConstancia.getStylesheets().add(cssArchivo);
        nchPaneFormatoConstancia.getStyleClass().add("constanciaEE");
        
        if(trabajoActual.getFechaExpedicionConstancia() != null){
            lblFechaExpedicion.setText(Utilidades.formatoFechaEscrito(trabajoActual.getFechaExpedicionConstancia()));
            lblNombreConstancia.setText(nombreArchivoConstancia());
            
            nchPaneArchivoPDF.getStyleClass().clear();
            nchPaneArchivoPDF.getStylesheets().add(cssArchivo);
            nchPaneArchivoPDF.getStyleClass().add("anchorPanePDF");
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
        ImparticionExperienciaEducativa trabajoEE = (ImparticionExperienciaEducativa) trabajoActual;
        if(trabajoEE != null && trabajoEE.getFechaExpedicionConstancia() == null){
            generarConstanciaEE(trabajoEE);
        }else{
            recuperarConstancia();
        }
    }
    
    private void generarConstanciaEE(ImparticionExperienciaEducativa trabajoEE){
        byte[] archivoConstancia = Constancia.generarConstanciaImpartirEE(trabajoEE, docente, firmaFacultad);
        if(archivoConstancia != null){
            guardarConstanciaBD(archivoConstancia);
        }else{
            Utilidades.mostrarDialogoSimple("Error al generar la constancia", "No se ha podido generar correctamente,"
                    + " inténtelo más tarde", 
                    Alert.AlertType.ERROR);
        }
    }
    private void guardarConstanciaBD(byte[] archivo){
        Constantes respuesta = TrabajoDocenteDAO.guardarArchivoConstancia(archivo, trabajoActual.getIdTrabajoDocente(), firmaFacultad.getIdFirma());
        switch(respuesta){
            case OPERACION_EXITOSA:
                Utilidades.mostrarDialogoSimple("Constancia Generada y Guardada", "La constancia de "
                    +  trabajoActual.getTipoTrabajo().getNombreTrabajo() + " ha sido generada correctamente", 
                    Alert.AlertType.INFORMATION);
                recuperarTrabajosDocenteImpartirEE();
                mostrarInformacionConstancia();
                break;
            case OPERACION_VACIA:
                Utilidades.mostrarDialogoSimple("Error al guardar la constancia", "No se ha podido guardar "
                        + " en la base de datos correctamente", 
                    Alert.AlertType.WARNING);
                break;
            case ERROR_CONEXION_BD:
            case ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error con la base de datos", "Hubo un error con la conexión de la base de datos", 
                    Alert.AlertType.ERROR);
        }
    }
    
    private void recuperarConstancia(){
        Pair<Constantes, byte[]> respuestaConsulta = TrabajoDocenteDAO.recuperarArchivoConstancia(trabajoActual.getIdTrabajoDocente());
        Constantes respuesta = respuestaConsulta.getKey();
        byte[] archivoConstancia = respuestaConsulta.getValue();
        switch(respuesta){
            case OPERACION_EXITOSA:
                descargarConstancia(archivoConstancia);
                break;
            case OPERACION_VACIA:
                Utilidades.mostrarDialogoSimple("Constancia no encontrada", "No se encontró la constancia", Alert.AlertType.WARNING);
                break;
            case ERROR_CONEXION_BD:
            case ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error con la base de datos", "Hubo un error con la conexión de la base de datos", 
                    Alert.AlertType.ERROR);
        }
    }
    
    private void descargarConstancia(byte[] constancia){
        DirectoryChooser dialogoSeleccionDireccion = new DirectoryChooser();
        dialogoSeleccionDireccion.setTitle("Seleccione la carpeta para descargar el documento");
        Stage escenarioBase = (Stage)lblNombreDocente.getScene().getWindow();
        File direccionElegida = dialogoSeleccionDireccion.showDialog(escenarioBase);
        
        if(direccionElegida != null){
            try (FileOutputStream stream = new FileOutputStream(direccionElegida + "/" + nombreArchivoConstancia() + ".pdf")) {
                stream.write(constancia);
                stream.close();
                Utilidades.mostrarDialogoSimple("Constancia descargada", "Se descargó la constancia en la ruta "+ direccionElegida, Alert.AlertType.INFORMATION);
            }catch(IOException e){
                System.out.println(e);
                Utilidades.mostrarDialogoSimple("Error al descargar constancia", "Ocurrió un error al descargar la constancia", Alert.AlertType.ERROR);
            }
        }
    }
    
    private String nombreArchivoConstancia(){
        return "CONSTANCIA-" + trabajoActual.getTipoTrabajo().getNombreTrabajo() + "-" +
                    trabajoActual.getFechaExpedicionConstancia();
    }
    
}
