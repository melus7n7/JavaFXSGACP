/*
*Autor: Mongeote Tlachy Daniel
*Fecha de creación: 05/11/2023
*Fecha de modificación: 05/11/2023
*Descripción: Controlador de la vista de la creación de los docentes
*/
package javafxsgacp.controladores;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafxsgacp.utilidades.Utilidades;
import javax.imageio.ImageIO;

public class FXMLCreacionDocenteController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private TextField tfCorreoPrincipal;
    @FXML
    private TextField tfCorreoAlterno;
    @FXML
    private TextField tfContrasenia;
    @FXML
    private TextField tfNumPersonal;
    @FXML
    private ComboBox<?> cbTipoUsuario;
    @FXML
    private Button btnSeleccionarFirmaDigital;
    @FXML
    private ImageView imgFirmaDigital;
    
    private ObservableList<?> usuarios;
    private boolean esEdicion;
    private File archivoQR;
    String estiloError = "-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 2;";
    String estiloNormal = "-fx-border-width: 0;";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dpFechaNacimiento.setEditable(false);
    }    

    @FXML
    private void clicGuardarRegistroDocente(ActionEvent event) {
        validarCamposRegistro();
    }
    
    @FXML
    private void clicRegresar(MouseEvent event) {
        Stage escenarioBase = (Stage) tfNombre.getScene().getWindow();
        escenarioBase.close();
    }

    @FXML
    private void clicSeleccionarFirmaDigital(ActionEvent event) {
        FileChooser dialogoSeleccionImg = new FileChooser();
        dialogoSeleccionImg.setTitle("Selecciona una imagen");
        FileChooser.ExtensionFilter filtroDialogo = new FileChooser.ExtensionFilter("Archivos PNG (*.png)", "*.PNG");
        dialogoSeleccionImg.getExtensionFilters().add(filtroDialogo);
        Stage escenarioBase = (Stage) tfNombre.getScene().getWindow();
        archivoQR = dialogoSeleccionImg.showOpenDialog(escenarioBase);
        if(archivoQR != null){
            try {
                BufferedImage bufferImg = ImageIO.read(archivoQR);
                Image imagenDecodificada =SwingFXUtils.toFXImage(bufferImg, null);
                imgFirmaDigital.setImage(imagenDecodificada);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        btnSeleccionarFirmaDigital.setVisible(false);
    }
    
    private void validarCamposRegistro(){
        
        establecerEstiloNormal();
        boolean datosValidos = true;
        String numPersonal = tfNumPersonal.getText();
        //String tipoUsuario = combo
        String nombre = tfNombre.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        LocalDate fechaNacimiento = dpFechaNacimiento.getValue();
        String correoPrincipal = tfCorreoPrincipal.getText();
        String correoAlterno = tfCorreoAlterno.getText();
        String contrasenia = tfContrasenia.getText();
        
        if(numPersonal.length() != 9){
            tfNumPersonal.setStyle(estiloError);
            datosValidos = false;
        }
        
        if(nombre.isEmpty()){
            tfNombre.setStyle(estiloError);
            datosValidos = false;
        }
        
        if(apellidoPaterno.isEmpty()){
            tfApellidoPaterno.setStyle(estiloError);
            datosValidos = false;
        }
        
        if(apellidoMaterno.isEmpty()){
            tfApellidoMaterno.setStyle(estiloError);
            datosValidos = false;
        }
        
        if(fechaNacimiento == null){
            dpFechaNacimiento.setStyle(estiloError);
            datosValidos = false;
        }
        
        if(!Utilidades.correoValido(correoPrincipal)){
            tfCorreoPrincipal.setStyle(estiloError);
            datosValidos = false;
        }
        
        if(!Utilidades.correoValido(correoAlterno)){
            tfCorreoAlterno.setStyle(estiloError);
            datosValidos = false;
        }
        
        if(contrasenia.length() != 9){
            tfContrasenia.setStyle(estiloError);
            datosValidos = false;
        }
        /*
        if(datosValidos){
            Alumno alumnoValidado = new Alumno();
            alumnoValidado.setNombre(nombre);
            alumnoValidado.setApellidoPaterno(apellidoPaterno);
            alumnoValidado.setApellidoMaterno(apellidoMaterno);
            alumnoValidado.setMatricula(matricula);
            alumnoValidado.setCorreo(correo);
            alumnoValidado.setFechaNacimiento(fechaNacimiento.toString());
            alumnoValidado.setIdCarrera(carreras.get(posicionCarrera).getIdCarrera()); //estamos seguros que tiene algo
            try{
                if(esEdicion){
                    if(archivoFoto != null || alumnoEdicion.getFoto().length  > 0){
                        if(archivoFoto != null){
                            alumnoValidado.setFoto(Files.readAllBytes(archivoFoto.toPath()));
                        }else{
                            alumnoValidado.setFoto(alumnoEdicion.getFoto());
                        }
                        alumnoValidado.setIdAlumno(alumnoEdicion.getIdAlumno());
                        actualizarAlumno(alumnoValidado);
                    }else{
                        Utilidades.mostrarDialogoSimple("Error con el archivo",
                        "Hubo un error al intentar guardar la imagen, vuelva a seleccionar el archivo.",
                        Alert.AlertType.ERROR);
                    }
                }else{ //nuevo registro
                    //File -> byte[]
                    if(archivoFoto != null){
                        alumnoValidado.setFoto(Files.readAllBytes(archivoFoto.toPath())); //Files es una clase de utilidad que contiene metodos propios
                        registrarAlumno(alumnoValidado);
                    }else{
                        Utilidades.mostrarDialogoSimple("Selecciona una imagen",
                                "Para guardar el registro del alumno debes seleccionar su foto desde la opción Seleccionar foto",
                                Alert.AlertType.WARNING);
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
                Utilidades.mostrarDialogoSimple("Error con el archivo",
                        "Hubo un error al intentar guardar la imagen, vuelva a seleccionar el archivo.",
                        Alert.AlertType.ERROR);
            }
        }
        */
    }
    
    private void establecerEstiloNormal(){
        tfNumPersonal.setStyle(estiloNormal);
        cbTipoUsuario.setStyle(estiloNormal);
        tfNombre.setStyle(estiloNormal);
        tfApellidoPaterno.setStyle(estiloNormal);
        tfApellidoMaterno.setStyle(estiloNormal);
        dpFechaNacimiento.setStyle(estiloNormal);
        tfCorreoPrincipal.setStyle(estiloNormal);
        tfCorreoAlterno.setStyle(estiloNormal);
        tfContrasenia.setStyle(estiloNormal);
    }
}