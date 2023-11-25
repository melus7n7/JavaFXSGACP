/*
*Autor: Mongeote Tlachy Daniel, Martinez Aguilar Sulem
*Fecha de creación: 12/11/2023
*Fecha de modificación: 19/11/2023
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
                    String sentenciaCuenta = "INSERT INTO Cuenta(correoElectronico, contraseña, correoElectronicoAlterno) VALUES (?, ?, ?)";
                    PreparedStatement prepararSentenciaCuenta = conexionBD.prepareStatement(sentenciaCuenta);
                    prepararSentenciaCuenta.setString(1, usuarioNuevo.getCorreoElectronico());
                    prepararSentenciaCuenta.setString(2, usuarioNuevo.getContraseña());
                    prepararSentenciaCuenta.setString(3, usuarioNuevo.getCorreoElectronicoAlterno());
                    prepararSentenciaCuenta.executeUpdate();

                    String sentenciaUsuario = "INSERT INTO Usuario(noPersonal, fechaNacimiento, nombre, apellidoPaterno, apellidoMaterno, correoElectronico, idTipoUsuario) " +
                                            "VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement prepararSentenciaUsuario = conexionBD.prepareStatement(sentenciaUsuario);
                    prepararSentenciaUsuario.setString(1, usuarioNuevo.getNoPersonal());
                    prepararSentenciaUsuario.setString(2, usuarioNuevo.getFechaNacimiento());
                    prepararSentenciaUsuario.setString(3, usuarioNuevo.getNombre());
                    prepararSentenciaUsuario.setString(4, usuarioNuevo.getApellidoPaterno());
                    prepararSentenciaUsuario.setString(5, usuarioNuevo.getApellidoMaterno());
                    prepararSentenciaUsuario.setString(6, usuarioNuevo.getCorreoElectronico());
                    prepararSentenciaUsuario.setInt(7, usuarioNuevo.getIdTipoUsuario());
                    prepararSentenciaUsuario.executeUpdate();

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
