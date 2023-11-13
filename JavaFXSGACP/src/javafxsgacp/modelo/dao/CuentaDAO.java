/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgacp.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.util.Pair;
import javafxsgacp.modelo.ConexionBD;
import javafxsgacp.modelo.pojo.TipoUsuario;
import javafxsgacp.modelo.pojo.Usuario;
import javafxsgacp.utilidades.Constantes;

/**
 *
 * @author monti
 */
public class CuentaDAO {
    public Pair<Constantes, Usuario> recuperarUsuario(String CorreoElectronico, String Contraseña)
    {
        Connection conexion = ConexionBD.abrirConexionBD();
        if(conexion!=null){
            try{
                String consulta = "SELECT cuenta.correoElectronico, cuenta.contraseña, usuario.noPersonal, usuario.nombre, "+
                "usuario.apellidoPaterno, usuario.apellidoMaterno, tipoUsuario.idTipoUsuario, tipoUsuario.nombreTipo " +
                "from cuenta " +
                "INNER JOIN usuario on cuenta.correoElectronico=usuario.correoElectronico and (cuenta.correoElectronico= ? or cuenta.contraseña= ? )"+
                "INNER JOIN tipoUsuario on tipoUsuario.idTipoUsuario=usuario.idTipoUsuario;";
                PreparedStatement sentencia= conexion.prepareStatement(consulta); 
                sentencia.setString(1, CorreoElectronico);                
                sentencia.setString(2, Contraseña);    
                ResultSet filasAfectadas  = sentencia.executeQuery();
                while(filasAfectadas.next())
                {
                    Usuario usuario = new Usuario();
                    TipoUsuario tipoUsuario = new TipoUsuario();
                    usuario.setCorreoElectronico(filasAfectadas.getString("correoElectronico"));
                    usuario.setContraseña(filasAfectadas.getString("contraseña"));
                    usuario.setNoPersonal(filasAfectadas.getString("noPersonal"));
                    usuario.setNombre(filasAfectadas.getString("nombre"));
                    usuario.setApellidoPaterno(filasAfectadas.getString("apellidoPaterno"));
                    usuario.setApellidoMaterno(filasAfectadas.getString("apellidoMaterno"));
                    tipoUsuario.setIdTipoUsuario(filasAfectadas.getInt("idTipoUsuario"));
                    tipoUsuario.setNombreTipo(filasAfectadas.getString("nombreTipo"));
                    usuario.setTipoUsuario(tipoUsuario);
                    if(usuario.getContraseña().equals(Contraseña) && usuario.getCorreoElectronico().equals(CorreoElectronico)){
                        return new Pair<>(Constantes.OPERACION_EXITOSA, usuario);                    
                    } else if(usuario.getCorreoElectronico().equals(CorreoElectronico)){
                        return new Pair<>(Constantes.OPERACION_EXITOSA, usuario);                    
                    }
                }
                return new Pair<>(Constantes.OPERACION_VACIA, null);                    

            }catch(SQLException ex){
                return new Pair<>(Constantes.ERROR_CONSULTA, null);
            }
        }else{
            return new Pair<>(Constantes.ERROR_CONEXION_BD, null);
        }
    }
}
