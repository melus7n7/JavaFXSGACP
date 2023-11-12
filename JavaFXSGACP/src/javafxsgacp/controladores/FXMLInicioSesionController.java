/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsgacp.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafxsgacp.modelo.dao.CuentaDAO;
import javafxsgacp.utilidades.Constantes;

/**
 * FXML Controller class
 *
 * @author monti
 */
public class FXMLInicioSesionController implements Initializable {

    @FXML
    private TextField txtFieldCorreoElectronico;
    @FXML
    private TextField txtFieldContraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                System.out.print("INICIALIZAR");

    }    

    @FXML
    private void clicContinuar(MouseEvent event) {
        System.out.print("CLIC");
        CuentaDAO cuenta = new CuentaDAO();
        Constantes resultado = cuenta.recuperarUsuario();
        switch (resultado) {
            case OPERACION_EXITOSA:
                System.out.print("OPERACION_EXITOSA");
                break;
            case OPERACION_VACIA:
                System.out.print("OPERACION_VACIA");
                break;
            case ERROR_CONSULTA:
                System.out.print("ERROR_CONSULTA");
                break;
            case ERROR_CONEXION_BD:
                System.out.print("ERROR_CONEXION_BD");

                break;
        }
    }

    @FXML
    private void clicSalir(MouseEvent event) {
                System.out.print("SALIR");
    }
    
}
