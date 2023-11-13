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
import javafxsgacp.modelo.ConexionBD;
import javafxsgacp.modelo.pojo.TipoUsuario;
import javafxsgacp.modelo.pojo.TipoUsuarioRespuesta;
import javafxsgacp.utilidades.Constantes;

public class TipoUsuarioDAO {
    public static TipoUsuarioRespuesta obtenerInformacionFacultad() {
        TipoUsuarioRespuesta respuesta = new TipoUsuarioRespuesta();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        respuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        if (conexionBD != null) {
            try {
                String consulta = "SELECT * FROM TipoUsuario"; 
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList<TipoUsuario> tipoUsuariosConsulta = new ArrayList();
                while (resultado.next())
                {
                    TipoUsuario tipoUsuarios = new TipoUsuario();
                    tipoUsuarios.setIdTipoUsuario(resultado.getInt("idTipoUsuario"));
                    tipoUsuarios.setNombreTipo(resultado.getString("nombreTipo"));
                    tipoUsuariosConsulta.add(tipoUsuarios);
                }
                respuesta.setUsuarios(tipoUsuariosConsulta);
                conexionBD.close();
            } catch (SQLException e) {
                e.printStackTrace();
                respuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else {
            respuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION_BD);
        }
        return respuesta;
    }
}
