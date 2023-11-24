/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgacp.utilidades;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafxsgacp.JavaFXSGACP;

/**
 *
 * @author sulem
 */
public class Utilidades {
    public static void mostrarDialogoSimple (String titulo, String mensaje, Alert.AlertType tipo){
        Alert alertaSimple = new Alert (tipo);
        alertaSimple.setTitle(titulo);
        alertaSimple.setContentText(mensaje);
        alertaSimple.setHeaderText(null);
        alertaSimple.setAlertType(tipo);
        alertaSimple.showAndWait();
    }
    
    public static Scene inicializarEscena(String ruta) {
        Scene escena = null;
        try
        {
            Parent vista = FXMLLoader.load(JavaFXSGACP.class.getResource(ruta));
            escena = new Scene(vista);
        } catch (IOException ex)
        {
            System.err.println("ERROR: " + ex.getMessage());
        }
        return escena;
    }
    
    public static boolean correoValido(String correo){
        if(correo != null && !correo.isEmpty()){
            Pattern patronCorreo = Pattern.compile("[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9.]+");
            Matcher matchPatron = patronCorreo.matcher(correo); 
            return matchPatron.matches();
        } else {
            return false;
        }
    }
    
    public static boolean caracteresValidos(String texto) {
        String regex = "^[a-zA-ZÃ¡Ã©Ã­Ã³ÃºÃ�Ã‰Ã�Ã“ÃšÃ±Ã‘Ã¼Ãœ\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }
    
    public static String formatoFechaEscrito(Date fecha){
        String pattern = "dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String patternDos = "MMMM yyyy";
        SimpleDateFormat simpleDateFormatDos = new SimpleDateFormat(patternDos);
        return simpleDateFormat.format(fecha) + " de " + simpleDateFormatDos.format(fecha);
    }
    
    
}
