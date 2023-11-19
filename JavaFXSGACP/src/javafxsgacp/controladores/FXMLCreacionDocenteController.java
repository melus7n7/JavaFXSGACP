/*
*Autor: Mongeote Tlachy Daniel
*Fecha de creación: 05/11/2023
*Fecha de modificación: 05/11/2023
*Descripción: Controlador de la vista de la creación de los docentes
*/
package javafxsgacp.controladores;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxsgacp.modelo.dao.TipoUsuarioDAO;
import javafxsgacp.modelo.dao.UsuarioDAO;
import javafxsgacp.modelo.pojo.TipoUsuario;
import javafxsgacp.modelo.pojo.Usuario;
import javafxsgacp.utilidades.Constantes;
import javafxsgacp.utilidades.Utilidades;

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
    private ComboBox<TipoUsuario> cbTipoUsuario;
    
    private ObservableList<TipoUsuario> usuarios;
    String estiloError = "-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 2;";
    String estiloNormal = "-fx-border-width: 0;";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarios = FXCollections.observableArrayList();
        cargarInformacionTiposUsuarios();
    }    

    @FXML
    private void clicGuardarRegistroDocente(ActionEvent event) {
        validarCamposRegistro();
    }
    
    @FXML
    private void clicRegresar(MouseEvent event) {
        cerrarVentana();
    }
    
    @FXML
    private void clicCancelarRegistroDocente(ActionEvent event) {
        cerrarVentana();
    }
    
    private void cerrarVentana(){
        Stage escenarioActual = (Stage) tfNombre.getScene().getWindow();
        escenarioActual.close();
    }
    
    private void cargarInformacionTiposUsuarios(){
        List<TipoUsuario> resultado = TipoUsuarioDAO.obtenerTiposUsuarios();
        usuarios.addAll(resultado);
        cbTipoUsuario.setItems(usuarios);
    }
    
    private void validarCamposRegistro(){
        establecerEstiloNormal();
        boolean datosValidos = true;
        String numPersonal = tfNumPersonal.getText();
        int posicionUsuario = cbTipoUsuario.getSelectionModel().getSelectedIndex();
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
        
        if(posicionUsuario == -1){
            cbTipoUsuario.setStyle(estiloError);
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
        
        if(datosValidos){
            Usuario usuarioValidado = new Usuario();
            usuarioValidado.setNoPersonal(numPersonal);
            usuarioValidado.setNombre(nombre);
            usuarioValidado.setApellidoPaterno(apellidoPaterno);
            usuarioValidado.setApellidoMaterno(apellidoMaterno);
            usuarioValidado.setCorreoElectronico(correoPrincipal);
            usuarioValidado.setCorreoElectronicoAlterno(correoAlterno);
            usuarioValidado.setContraseña(contrasenia);
            usuarioValidado.setFechaNacimiento(fechaNacimiento.toString());
            TipoUsuario tipoUsuario = cbTipoUsuario.getSelectionModel().getSelectedItem();
            usuarioValidado.setIdTipoUsuario(tipoUsuario.getIdTipoUsuario());
            registrarUsuario(usuarioValidado);
        }
    }
    
    private void registrarUsuario(Usuario usuarioRegistro){
        Constantes respuesta = UsuarioDAO.guardarUsuario(usuarioRegistro);
        switch(respuesta){
            case ERROR_CONEXION_BD:
                Utilidades.mostrarDialogoSimple("Error de conexión", "Error en la conexión con la base de datos", Alert.AlertType.ERROR);
                break;
            case ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error de consulta", "Error en la consulta con la base de datos", Alert.AlertType.ERROR);
                break;
            case OPERACION_EXITOSA:
                Utilidades.mostrarDialogoSimple("Usuario registrado",
                        "La información del usuario fue guardada correctamente",
                        Alert.AlertType.INFORMATION);
                cerrarVentana();
                break;
        }
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