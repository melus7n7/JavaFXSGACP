/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgacp.utilidades;

import javafx.scene.control.Alert;

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
}
