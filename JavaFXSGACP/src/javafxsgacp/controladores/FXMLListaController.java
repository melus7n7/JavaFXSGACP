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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafxsgacp.JavaFXSGACP;
import javafxsgacp.modelo.dao.ExperienciaDAO;
import javafxsgacp.modelo.dao.ProgramaEducativoDAO;
import javafxsgacp.modelo.dao.UsuarioDAO;
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
public class FXMLListaController implements Initializable {

    @FXML
    private Label lblTitulo;
    private Usuario personal;
    private ArrayList<Usuario> respuestaUsuarios;
    private ArrayList<ProgramaEducativo> respuestaProgramasEducativos;
    private ArrayList<ExperienciaEducativa> respuestaExperienciasEducativas;
    private ObservableList<Usuario> usuarios;
    private ObservableList<ProgramaEducativo> programasEducativos;
    private ObservableList<ExperienciaEducativa> experienciasEducativas;
    private Periodo valorPeriodo;
    private ProgramaEducativo programa;
    private ExperienciaEducativa experienciaEducativa;
    private Usuario docente;
    private Seccion seccion;
    private Bloque bloque;
    private boolean esDocente;
    private boolean esProgramaEducativo;
    private boolean esExperienciaEducativa;
    @FXML
    private TableColumn tblColumnNombre;
    @FXML
    private TableView<Usuario> tblView;
    @FXML
    private TableView<ProgramaEducativo> tblViewProgramaEducativo;
    @FXML
    private TableColumn tblColumnProgramaEducativo;
    @FXML
    private TableView<ExperienciaEducativa> tblViewExperienciaEducativa;
    @FXML
    private TableColumn tblColumnExperienciaEducativa;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void inicializarDatos(Usuario personal){
        this.personal=personal;
    }
    
    public void recuperarDocentes(ProgramaEducativo programa, Periodo periodo, ExperienciaEducativa experienciaEducativa, Bloque bloque, Seccion seccion, Usuario docente){
        this.programa=programa;
        this.valorPeriodo=periodo;
        this.experienciaEducativa=experienciaEducativa;
        this.bloque=bloque;
        this.seccion=seccion;
        this.docente=docente;
        lblTitulo.setText("Lista docentes");
        tblView.setVisible(true);
        tblViewProgramaEducativo.setVisible(false);
        tblViewExperienciaEducativa.setVisible(false);
        esDocente = true;
        tblColumnNombre.setCellValueFactory(new PropertyValueFactory("datosDocente"));
        tblColumnNombre.setText("Docentes");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Pair<Constantes, ArrayList<Usuario>> pairRespuesta = usuarioDAO.recuperarDocentes();
        Constantes respuestaConstante = pairRespuesta.getKey();
        switch (respuestaConstante) {
            case OPERACION_EXITOSA:
                respuestaUsuarios = pairRespuesta.getValue();
                usuarios = FXCollections.observableArrayList();
                usuarios.addAll(respuestaUsuarios);
                tblView.setItems(usuarios);
            break;
            case OPERACION_VACIA:
                Utilidades.mostrarDialogoSimple("Docentes no encontrados", "El sistema no cuenta con Docentes actualmente", Alert.AlertType.WARNING);
                break;
            case ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error de conexión", "Ha ocurrido un error de conexión a la base de datos", Alert.AlertType.ERROR);
                break;
            case ERROR_CONEXION_BD:
               Utilidades.mostrarDialogoSimple("Error de conexión", "Ha ocurrido un error de conexión a la base de datos", Alert.AlertType.ERROR);
                break;
        }
    }
    
    
    public void recuperarProgramasEducativos(ProgramaEducativo programa, Periodo valorPeriodo){
        this.valorPeriodo=valorPeriodo;
        lblTitulo.setText("Lista Programas Educativos");
        tblView.setVisible(false);
        tblViewExperienciaEducativa.setVisible(false);
        tblViewProgramaEducativo.setVisible(true);
        this.programa=programa;
        esProgramaEducativo = true;
        esDocente = false;
        tblColumnProgramaEducativo.setCellValueFactory(new PropertyValueFactory("nombreProgramaEducativo"));
        tblColumnProgramaEducativo.setText("Programas Educativos");
        ProgramaEducativoDAO programaEducativoDAO = new ProgramaEducativoDAO();
        Pair<Constantes, ArrayList<ProgramaEducativo>> pairRespuesta = programaEducativoDAO.recuperarProgramasEducativos();
        Constantes respuestaConstante = pairRespuesta.getKey();
        switch (respuestaConstante) {
            case OPERACION_EXITOSA:
                respuestaProgramasEducativos = pairRespuesta.getValue();
                programasEducativos = FXCollections.observableArrayList();
                programasEducativos.addAll(respuestaProgramasEducativos);
                tblViewProgramaEducativo.setItems(programasEducativos);
            break;
            case OPERACION_VACIA:
                Utilidades.mostrarDialogoSimple("Docentes no encontrados", "El sistema no cuenta con Docentes actualmente", Alert.AlertType.WARNING);
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
    private void clickRegresarRegistrarTrabajo(MouseEvent event) {
        if(esProgramaEducativo){
            Regresar(valorPeriodo);
        } else if(esExperienciaEducativa){
            Regresar(experienciaEducativa);
        } else if(esDocente){
            Regresar(docente);
            //Regresar TODO y mostrar todo y el mismo
        }
    }
    private void Regresar(Usuario docente){
        ContinuarRegistro(this.docente);
    }
   
    private void Regresar(Periodo periodo){
        ContinuarRegistro(this.programa);
    }
    
    private void ContinuarRegistro(ProgramaEducativo programaEducativo){
        Stage stage = (Stage) this.lblTitulo.getScene().getWindow();
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSGACP.class.getResource("vistas/FXMLRegistrarImparticionEE.fxml"));
            Parent vista = accesoControlador.load();
            FXMLRegistrarImparticionEEController impartirEE = accesoControlador.getController();
            impartirEE.ingresarUsuario(personal);
            impartirEE.ingresarPeriodo(programaEducativo, this.valorPeriodo);
            Scene scene = new Scene(vista);
            stage.setTitle("Registro de Impartir EE");
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    private void Regresar(ExperienciaEducativa experienciaEducativa){
        ContinuarRegistro(experienciaEducativa);
    }
    private void ContinuarRegistro(ExperienciaEducativa experienciaEducativa){
        Stage stage = (Stage) this.lblTitulo.getScene().getWindow();
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSGACP.class.getResource("vistas/FXMLRegistrarImparticionEE.fxml"));
            Parent vista = accesoControlador.load();
            FXMLRegistrarImparticionEEController impartirEE = accesoControlador.getController();
            impartirEE.ingresarUsuario(personal);
            impartirEE.ingresarExperienciaEducativa(this.programa, this.valorPeriodo, experienciaEducativa);
            Scene scene = new Scene(vista);
            stage.setTitle("Registro de Impartir EE");
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }


    @FXML
    private void clickContinuar(MouseEvent event) {
        int posicionDocente = tblView.getSelectionModel().getSelectedIndex();
        int posicionProgramaEducativo = tblViewProgramaEducativo.getSelectionModel().getSelectedIndex();
        int posicionExperienciaEducativa = tblViewExperienciaEducativa.getSelectionModel().getSelectedIndex();
        if(posicionDocente!=-1 && esDocente){
            ContinuarRegistro(tblView.getSelectionModel().getSelectedItem());
        }else if(posicionProgramaEducativo!=-1 && esProgramaEducativo){
            ContinuarRegistro(tblViewProgramaEducativo.getSelectionModel().getSelectedItem());
        }else if(posicionExperienciaEducativa!=-1 && esExperienciaEducativa){
            ContinuarRegistro(tblViewExperienciaEducativa.getSelectionModel().getSelectedItem());
        }
        else{
            Utilidades.mostrarDialogoSimple("Selecciona un valor de la lista", "Selecciona un valor para el registro", Alert.AlertType.WARNING);
        }
    }
    private void ContinuarRegistro(Usuario docente){
        Stage stage = (Stage) this.lblTitulo.getScene().getWindow();
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSGACP.class.getResource("vistas/FXMLRegistrarImparticionEE.fxml"));
            Parent vista = accesoControlador.load();
            FXMLRegistrarImparticionEEController impartirEE = accesoControlador.getController();
            impartirEE.ingresarUsuario(personal);
            impartirEE.ingresarDocente(programa, valorPeriodo, experienciaEducativa, bloque, seccion, docente);
            Scene scene = new Scene(vista);
            stage.setTitle("Registro de Impartir EE");
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    
    
    public void recuperarExperienciasEducativas(ProgramaEducativo programa, Periodo periodo, ExperienciaEducativa experienciaEducativa){
        this.valorPeriodo=periodo;
        this.programa=programa;
        this.experienciaEducativa=experienciaEducativa;
        lblTitulo.setText("Lista Experiencias Educativas");
        tblView.setVisible(false);
        tblViewExperienciaEducativa.setVisible(true);
        tblViewProgramaEducativo.setVisible(false);
        esExperienciaEducativa = true;
        tblColumnExperienciaEducativa.setCellValueFactory(new PropertyValueFactory("datosExperienciaEducativa"));
        tblColumnExperienciaEducativa.setText("Programas Educativos");
        ExperienciaDAO experienciaDAO = new ExperienciaDAO();
        Pair<Constantes, ArrayList<ExperienciaEducativa>> pairRespuesta = experienciaDAO.recuperarExperienciasEducativas(programa, periodo);
        Constantes respuestaConstante = pairRespuesta.getKey();
        switch (respuestaConstante) {
            case OPERACION_EXITOSA:
                respuestaExperienciasEducativas = pairRespuesta.getValue();
                experienciasEducativas = FXCollections.observableArrayList();
                experienciasEducativas.addAll(respuestaExperienciasEducativas);
                tblViewExperienciaEducativa.setItems(experienciasEducativas);
            break;
            case OPERACION_VACIA:
                Utilidades.mostrarDialogoSimple("Experiencias Educativas no encontradas", "El sistema no cuenta con Experiencias Educativas en el programa "+
                        programa.getNombreProgramaEducativo()+" con el periodo "+periodo.getNombrePeriodo(), Alert.AlertType.WARNING);
                break;
            case ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error de conexión", "Ha ocurrido un error de conexión a la base de datos", Alert.AlertType.ERROR);
                break;
            case ERROR_CONEXION_BD:
               Utilidades.mostrarDialogoSimple("Error de conexión", "Ha ocurrido un error de conexión a la base de datos", Alert.AlertType.ERROR);
                break;
        }
    }
}
