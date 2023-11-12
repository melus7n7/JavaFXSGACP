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
import javafxsgacp.modelo.pojo.Usuario;
import javafxsgacp.utilidades.Constantes;

/**
 *
 * @author sulem
 */
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
}
