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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.converter.IntegerStringConverter;
import javafxsgacp.JavaFXSGACP;
import javafxsgacp.modelo.dao.BloqueDAO;
import javafxsgacp.modelo.dao.PeriodoDAO;
import javafxsgacp.modelo.dao.TrabajoDocenteDAO;
import javafxsgacp.modelo.pojo.Bloque;
import javafxsgacp.modelo.pojo.ExperienciaEducativa;
import javafxsgacp.modelo.pojo.Periodo;
import javafxsgacp.modelo.pojo.ProgramaEducativo;
import javafxsgacp.modelo.pojo.Seccion;
import javafxsgacp.modelo.pojo.Usuario;
import javafxsgacp.utilidades.Constantes;
import javafxsgacp.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author monti
 */
public class FXMLRegistrarImparticionEEController implements Initializable {

    private Usuario personal;
    @FXML
    private Label lblHorasTitulo;
    @FXML
    private Label lblExperienciaEducativaTitulo;
    @FXML
    private Label lblBloqueTitulo;
    @FXML
    private Label lblSeccionTitulo;
    @FXML
    private Label lblCreditosTitulo;
    @FXML
    private ComboBox<Periodo> cmbBoxPeriodo;
    @FXML
    private ComboBox<Bloque> cmbBoxBloque;
    @FXML
    private ComboBox<Seccion> cmbBoxSeccion;
    @FXML
    private Label lblCantidadHoras;
    @FXML
    private Label lblCantidadCreditos;
    @FXML
    private Button btnExperienciaEducativa;
    @FXML
    private Label lblDocenteTitulo;
    @FXML
    private Button btnDocente;
    @FXML
    private Button btnProgramaEducativo;
    private ArrayList<Periodo> respuestaPeriodos;
    private ArrayList<Bloque> respuestaBloques;
    private ArrayList<Seccion> respuestaSecciones;
    private ProgramaEducativo programa;
    private Usuario docente;
    private Periodo periodo;
    private Bloque bloque;
    private Seccion seccion;
    private ExperienciaEducativa experienciaEducativa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recuperarPeriodos();
    }    
    
    private void recuperarPeriodos(){
        PeriodoDAO periodoDAO = new PeriodoDAO();
        Pair<Constantes, ArrayList<Periodo>> pairRespuesta = periodoDAO.recuperarPeriodos();
        Constantes respuestaConstante = pairRespuesta.getKey();
        switch (respuestaConstante) {
                case OPERACION_EXITOSA:
                    respuestaPeriodos = pairRespuesta.getValue();
                    cmbBoxPeriodo.getItems().addAll(respuestaPeriodos);
                break;
                case OPERACION_VACIA:
                    Utilidades.mostrarDialogoSimple("Periodos no encontrados", "El sistema no cuenta con Periodos actualmente", Alert.AlertType.WARNING);
                    regresar();
                    break;
                case ERROR_CONSULTA:
                    Utilidades.mostrarDialogoSimple("Error de conexión", "Ha ocurrido un error de conexión a la base de datos", Alert.AlertType.ERROR);
                    break;
                case ERROR_CONEXION_BD:
                   Utilidades.mostrarDialogoSimple("Error de conexión", "Ha ocurrido un error de conexión a la base de datos", Alert.AlertType.ERROR);
                    break;
        }
    }
    
    public void ingresarUsuario(Usuario personal){
        this.personal=personal;
        boolean visibilidad = false;
        visibilidadDesdeExperienciaEducativa(visibilidad);
        visibilidadDesdeBloque(visibilidad);
        visibilidadSeccion(visibilidad);
        visibilidadDocente(visibilidad);
    }
    
    private void visibilidadDesdeExperienciaEducativa(boolean visibilidad){
        lblExperienciaEducativaTitulo.setVisible(visibilidad);
        btnExperienciaEducativa.setVisible(visibilidad);
        lblCreditosTitulo.setVisible(visibilidad);
        lblCantidadCreditos.setVisible(visibilidad);
        lblCantidadHoras.setVisible(visibilidad);
        lblHorasTitulo.setVisible(visibilidad);
    }
    
    private void visibilidadDesdeBloque(boolean visibilidad){
        lblBloqueTitulo.setVisible(visibilidad);
        cmbBoxBloque.setVisible(visibilidad);
    }
    
    private void visibilidadSeccion(boolean visibilidad){
        lblSeccionTitulo.setVisible(visibilidad);
        cmbBoxSeccion.setVisible(visibilidad);
    }
    
    private void visibilidadDocente(boolean visibilidad){
        lblDocenteTitulo.setVisible(visibilidad);
        btnDocente.setVisible(visibilidad);
    }

    @FXML
    private void clickGuardarDatos(MouseEvent event) {
        if(seccion!=null && docente!=null && experienciaEducativa!=null){
            TrabajoDocenteDAO trabajoDAO = new TrabajoDocenteDAO();
            System.out.println(personal.getNoPersonal());
            Constantes respuesta = trabajoDAO.guardarTrabajoDocente(docente.getNoPersonal(), experienciaEducativa.getIdExperienciaEducativa(), seccion.getIdSeccion(), bloque.getIdBloque(),periodo.getIdPeriodo());
            System.out.println(respuesta);
            switch (respuesta) {
                case OPERACION_EXITOSA:
                    Utilidades.mostrarDialogoSimple("Formulario guardado", "Se ha guardado correctamente la informacion", Alert.AlertType.INFORMATION);
                    regresar();
                    break;
                case OPERACION_VACIA:
                    Utilidades.mostrarDialogoSimple("Error al guardar los datos", "Error al intentar guardar los datos", Alert.AlertType.WARNING);
                    break;
                case ERROR_CONSULTA:
                    Utilidades.mostrarDialogoSimple("Error de conexión", "Ha ocurrido un error de conexión a la base de datos", Alert.AlertType.ERROR);
                    break;
                case ERROR_CONEXION_BD:
                   Utilidades.mostrarDialogoSimple("Error de conexión", "Ha ocurrido un error de conexión a la base de datos", Alert.AlertType.ERROR);
                    break;
            }   
        }else{
            Utilidades.mostrarDialogoSimple("Error al guardar los datos", "¨Por favor complete todos los campos", Alert.AlertType.WARNING);
        }
    }
    

    @FXML
    private void clickListaDocente(MouseEvent event) {
        Stage stage = (Stage) this.lblExperienciaEducativaTitulo.getScene().getWindow();
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSGACP.class.getResource("vistas/FXMLLista.fxml"));
            Parent vista = accesoControlador.load();
            FXMLListaController lista = accesoControlador.getController();
            lista.inicializarDatos(personal);
            lista.recuperarDocentes(programa, periodo, experienciaEducativa, bloque, seccion, docente);
            Scene scene = new Scene(vista);
            stage.setTitle("Lista Docentes");
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void clickListaProgramaEducativo(MouseEvent event) {
        eliminarHastaProgramaEducativo();
        Periodo valorPeriodo = cmbBoxPeriodo.getValue();
        Stage stage = (Stage) this.lblExperienciaEducativaTitulo.getScene().getWindow();
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSGACP.class.getResource("vistas/FXMLLista.fxml"));
            Parent vista = accesoControlador.load();
            FXMLListaController lista = accesoControlador.getController();
            lista.inicializarDatos(personal);
            lista.recuperarProgramasEducativos(programa, valorPeriodo);
            Scene scene = new Scene(vista);
            stage.setTitle("Lista Programas Educativos");
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    private void eliminarHastaProgramaEducativo(){
        visibilidadDesdeExperienciaEducativa(false);
        visibilidadDesdeBloque(false);
        visibilidadDocente(false);
        eliminarHastaExperienciaEducativa();
        eliminarBloqueSeccion();
        //orrar Docente
    }
    
    private void eliminarHastaExperienciaEducativa(){
        experienciaEducativa=null;
        btnExperienciaEducativa.setText("+");
        lblCantidadCreditos.setText("");
        lblCantidadHoras.setText("");
        eliminarBloqueSeccion();
    }

    private void eliminarBloqueSeccion(){
        bloque = null;
        cmbBoxBloque.setValue(null);
        eliminarSeccion();
    }
    private void eliminarSeccion(){
        seccion=null;
        cmbBoxSeccion.setValue(null);
    }
    private void eliminarDocente(){
        docente=null;
        btnDocente.setText("+");
    }
    @FXML
    private void clickListaExperienciaEducativa(MouseEvent event) {
        Stage stage = (Stage) this.lblExperienciaEducativaTitulo.getScene().getWindow();
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSGACP.class.getResource("vistas/FXMLLista.fxml"));
            Parent vista = accesoControlador.load();
            FXMLListaController lista = accesoControlador.getController();
            lista.inicializarDatos(personal);
            lista.recuperarExperienciasEducativas(programa, periodo, experienciaEducativa);
            Scene scene = new Scene(vista);
            stage.setTitle("Lista Experiencias Educativas");
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    public void ingresarPeriodo(ProgramaEducativo programa, Periodo valorPeriodo){
        cmbBoxPeriodo.setValue(valorPeriodo);
        this.programa=programa;
        this.periodo=valorPeriodo;
        if(valorPeriodo!=null && programa!=null){
            btnProgramaEducativo.setText(programa.getNombreProgramaEducativo());
            visibilidadDesdeExperienciaEducativa(true);
        }else if(programa!=null){
            btnProgramaEducativo.setText(programa.getNombreProgramaEducativo());
        }else{
            eliminarHastaProgramaEducativo();
        }
    }
    
    public void ingresarExperienciaEducativa(ProgramaEducativo programa, Periodo valorPeriodo, ExperienciaEducativa experiencia){
        ingresarPeriodo(programa, valorPeriodo);
        this.experienciaEducativa=experiencia;
        if(experiencia!=null){
            ingresarExperienciaEducativa(experiencia);
            recuperarBloques();
        }else{
            eliminarHastaExperienciaEducativa();
            
        }
    }
    
    private void ingresarExperienciaEducativa(ExperienciaEducativa experiencia){
        visibilidadDesdeBloque(true);
        btnExperienciaEducativa.setText(experiencia.getNombre());
        lblCantidadCreditos.setText(experiencia.getCreditos()+"");
        lblCantidadHoras.setText(experiencia.getHsm()+"");
    }
    
    public void ingresarDocente(ProgramaEducativo programa, Periodo periodo, ExperienciaEducativa experienciaEducativa, Bloque bloque, Seccion seccion, Usuario docente){
        ingresarExperienciaEducativa(programa, periodo, experienciaEducativa);
        this.bloque=bloque;
        this.seccion=seccion;
        this.docente=docente;
        cmbBoxBloque.setValue(bloque);
        visibilidadSeccion(true);
        cmbBoxSeccion.setValue(seccion);
        if(docente!=null){
            visibilidadDocente(true);
            btnDocente.setVisible(true);
            btnDocente.setText(docente.toString());
        }
    }
    
    private void recuperarBloques(){
        BloqueDAO bloqueDAO = new BloqueDAO();
        Pair<Constantes, ArrayList<Bloque>> pairRespuesta = bloqueDAO.recuperarBloques(experienciaEducativa);
        Constantes respuestaConstante = pairRespuesta.getKey();
        switch (respuestaConstante) {
            case OPERACION_EXITOSA:
                respuestaBloques = pairRespuesta.getValue();
                cmbBoxBloque.getItems().addAll(respuestaBloques);
            break;
            case OPERACION_VACIA:
                Utilidades.mostrarDialogoSimple("Bloques no encontrados", "El sistema no tiene Bloques con dicha Experiencia Educativa", Alert.AlertType.WARNING);
                break;
            case ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error de conexión", "Ha ocurrido un error de conexión a la base de datos", Alert.AlertType.ERROR);
                break;
            case ERROR_CONEXION_BD:
               Utilidades.mostrarDialogoSimple("Error de conexión", "Ha ocurrido un error de conexión a la base de datos", Alert.AlertType.ERROR);
                break;
        }
    }

    @FXML
    private void clickSeleccionarPeriodo(ActionEvent event) {
        eliminarHastaProgramaEducativo();
        periodo = cmbBoxPeriodo.getValue();
        if(cmbBoxPeriodo.getValue()!=null && programa!=null){
            visibilidadDesdeExperienciaEducativa(true);
            visibilidadDesdeBloque(false);
            eliminarHastaExperienciaEducativa();
        }
    }

    @FXML
    private void clickSeleccionarBloque(ActionEvent event) {
        visibilidadSeccion(false);
        eliminarSeccion();
        visibilidadDocente(false);
        eliminarDocente();
        if(cmbBoxBloque.getValue()!=null ){
            bloque = cmbBoxBloque.getValue();
            recuperarSecciones(cmbBoxBloque.getValue());
        }
        //RecuperarSecciones si se selecciona un bloque eliminar y setter invisible seccion y btnDocente null y invisible
    }
    
    private void recuperarSecciones(Bloque bloque){
        BloqueDAO bloqueDAO = new BloqueDAO();
        Pair<Constantes, ArrayList<Seccion>> pairRespuesta = bloqueDAO.recuperarSecciones(bloque);
        Constantes respuestaConstante = pairRespuesta.getKey();
        switch (respuestaConstante) {
            case OPERACION_EXITOSA:
                respuestaSecciones = pairRespuesta.getValue();
                cmbBoxSeccion.getItems().addAll(respuestaSecciones);
                visibilidadSeccion(true);
            break;
            case OPERACION_VACIA:
                Utilidades.mostrarDialogoSimple("Secciones no encontradas", "El sistema no tiene Secciones en el Bloque seleccionado", Alert.AlertType.WARNING);
                break;
            case ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error de conexión", "Ha ocurrido un error de conexión a la base de datos", Alert.AlertType.ERROR);
                break;
            case ERROR_CONEXION_BD:
               Utilidades.mostrarDialogoSimple("Error de conexión", "Ha ocurrido un error de conexión a la base de datos", Alert.AlertType.ERROR);
                break;
        }
    }

    @FXML
    private void clickSeleccionarSeccion(ActionEvent event) {
        verificarSeccion();
    }
    private void verificarSeccion(){
        if(cmbBoxBloque.getValue()!=null && cmbBoxSeccion.getValue()!=null){
            seccion = cmbBoxSeccion.getValue();
            visibilidadDocente(true);
        }
    }

    @FXML
    private void clickRegresarMenuTrabajoDocente(MouseEvent event) {
        regresar();
    }
    
    private void regresar(){
        Stage stage = (Stage) this.lblExperienciaEducativaTitulo.getScene().getWindow();
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSGACP.class.getResource("vistas/FXMLMenuTrabajoDocente.fxml"));
            Parent vista = accesoControlador.load();
            FXMLMenuTrabajoDocenteController menuTrabajoDocente = accesoControlador.getController();
            menuTrabajoDocente.inicializarPantallaTrabajoDocente(personal);
            Scene scene = new Scene(vista);
            stage.setTitle("Menu Trabajo Docente");
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
