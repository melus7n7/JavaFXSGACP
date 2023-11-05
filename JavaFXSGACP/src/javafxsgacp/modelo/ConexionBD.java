/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgacp.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sulem
 */
public class ConexionBD {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String nombreBase = "sgacp";
    private static String hostname = "localhost";
    private static String port = "3306";
    private static String usuario = "adminSGACP";
    private static String password = "1234";
    private static String urlConexion = "jdbc:mysql://" + hostname + ":" + port + "/" + nombreBase + "?allowPublicKeyRetrieval=true&useSSL=false";
    
    public static Connection abrirConexionBD(){
        Connection conexion = null;
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(urlConexion, usuario, password);
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Error de conexion con BD:" + ex.getMessage());
            ex.printStackTrace();
        }
        return conexion;
    }
}
