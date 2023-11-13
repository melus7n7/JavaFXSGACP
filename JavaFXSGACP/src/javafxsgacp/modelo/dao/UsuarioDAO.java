/*
*Autor: Mongeote Tlachy Daniel, Martinez Aguilar Sulem
*Fecha de creación: 12/11/2023
*Fecha de modificación: 12/11/2023
*Descripción: Clase encargada de la comunicación con la BD, especificamente para manipular la información de los usuarios
*/
package javafxsgacp.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.util.Pair;
import javafxsgacp.modelo.ConexionBD;
import javafxsgacp.modelo.pojo.Usuario;
import javafxsgacp.utilidades.Constantes;

public class UsuarioDAO {
    public static Pair<Constantes,byte[]> recuperarFirmaDigital(Usuario docente){
        Constantes respuesta;
        byte[] firma;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT firmaDigital FROM usuario WHERE noPersonal = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, docente.getNoPersonal());
                ResultSet resultado = prepararSentencia.executeQuery();
                
                if(resultado.next()){
                    firma = resultado.getBytes("firmaDigital");
                    respuesta = Constantes.OPERACION_EXITOSA;
                }else{
                    firma = null;
                    respuesta = Constantes.OPERACION_VACIA;
                }
                conexionBD.close();
            }catch(SQLException e){
                System.out.println(e);
                respuesta = Constantes.ERROR_CONSULTA;
                firma = null;
            }
        }else{
            respuesta = Constantes.ERROR_CONEXION_BD;
            firma = null;
        }
        return new Pair<>(respuesta, firma);
    }
    
    public static Constantes guardarUsuario(Usuario usuarioNuevo) {
        Constantes respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String sentencia = "Insert into usuario(noPersonal, fechaNacimiento, nombre, apellidoPaterno, apellidoMaterno, correoElectronico, idTipoUsuario, firmaDigital) " +
                                    " VALUES (?, ? , ?, ?, ?, ?, ?, ?)";
                PreparedStatement prepararSentencia =  conexionBD.prepareStatement(sentencia);
                prepararSentencia.setString(1, usuarioNuevo.getNoPersonal());
                //prepararSentencia.setString(2, usuarioNuevo.getFechaNacimiento());
                prepararSentencia.setString(3, usuarioNuevo.getNombre());
                prepararSentencia.setString(4, usuarioNuevo.getApellidoPaterno());
                prepararSentencia.setString(5, usuarioNuevo.getApellidoMaterno());
                prepararSentencia.setString(6, usuarioNuevo.getCorreoElectronico());
                //prepararSentencia.setInt(7, usuarioNuevo.getTipoUsuario());
                prepararSentencia.setBytes(8, usuarioNuevo.getFirmaDigital());
                int filasAfectadas = prepararSentencia.executeUpdate();
                respuesta = Constantes.OPERACION_EXITOSA;
                conexionBD.close();
            } catch (SQLException e) {
                e.printStackTrace();
                respuesta = Constantes.ERROR_CONSULTA;
            }
        } else {
            respuesta = Constantes.ERROR_CONEXION_BD;
        }
        return respuesta;
    }
}
