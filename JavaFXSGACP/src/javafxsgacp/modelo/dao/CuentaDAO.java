/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgacp.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafxsgacp.modelo.ConexionBD;
import javafxsgacp.utilidades.Constantes;

/**
 *
 * @author monti
 */
public class CuentaDAO {
    public Constantes recuperarUsuario()
    {
        Connection conexion = ConexionBD.abrirConexionBD();
        if(conexion!=null){
            try{
                String consulta = "SELECT cuenta.correoElectronico, usuario.noPersonal, usuario.nombre, usuario.apellidoPaterno, usuario.apellidoMaterno, idTipoUsuario " +
                "from cuenta " +
                "INNER JOIN usuario on cuenta.correoElectronico=usuario.correoElectronico and cuenta.correoElectronico= ? and cuenta.contraseña= ? ;";
                PreparedStatement sentencia= conexion.prepareStatement(consulta); 
                sentencia.setString(1, "Jacobobo@gmail.com");                
                sentencia.setString(2, "1234567890");    
                ResultSet filasAfectadas  = sentencia.executeQuery();
                if(filasAfectadas.next())
                {
                    System.out.print("Correo "+filasAfectadas.getString("correoElectronico"));
                    System.out.print("noPersonal "+filasAfectadas.getString("noPersonal"));
                    System.out.print("nombre "+filasAfectadas.getString("nombre"));
                    System.out.print("apellidoPaterno "+filasAfectadas.getString("apellidoPaterno"));
                    System.out.print("apellidoMaterno "+filasAfectadas.getString("apellidoMaterno"));
                    System.out.print("idTipoUsuario "+filasAfectadas.getInt("idTipoUsuario"));
                    return Constantes.OPERACION_EXITOSA;                    
                }else
                {
                    return Constantes.OPERACION_VACIA;                    
                }
                
            }catch(SQLException ex){
                return Constantes.ERROR_CONSULTA;
            }
        }else{
            return Constantes.ERROR_CONEXION_BD;
        }
    }
}
