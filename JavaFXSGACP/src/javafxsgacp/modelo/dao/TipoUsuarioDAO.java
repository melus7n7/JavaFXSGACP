/*
*Autor: Mongeote Tlachy Daniel
*Fecha de creación: 12/11/2023
*Fecha de modificación: 12/11/2023
*Descripción: DAO del tipo de usuario
*/
package javafxsgacp.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafxsgacp.modelo.ConexionBD;
import javafxsgacp.modelo.pojo.TipoUsuario;

public class TipoUsuarioDAO {
    public static List<TipoUsuario> obtenerTiposUsuarios(){
        List<TipoUsuario> tiposUsuarios = new ArrayList<>();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT * FROM tipousuario";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                while(resultado.next()){
                    TipoUsuario usuario = new TipoUsuario();
                    usuario.setIdTipoUsuario(resultado.getInt("idTipoUsuario"));
                    usuario.setNombreTipo(resultado.getString("nombreTipo"));
                    tiposUsuarios.add(usuario);
                }
                conexionBD.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return tiposUsuarios;
    }
}
