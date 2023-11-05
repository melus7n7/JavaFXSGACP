/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgacp.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.util.Pair;
import javafxsgacp.modelo.ConexionBD;
import javafxsgacp.modelo.pojo.TipoTrabajoDocente;
import javafxsgacp.utilidades.Constantes;

/**
 *
 * @author sulem
 */
public class TipoTrabajoDocenteDAO {
    public static Pair<Constantes,ArrayList<TipoTrabajoDocente>> recuperarTiposTrabajo(){
        Constantes respuesta;
        ArrayList<TipoTrabajoDocente> listaTipos = new ArrayList<>();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT * FROM sgacp.tipotrabajodocente";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                
                while(resultado.next()){
                    TipoTrabajoDocente tipo = new TipoTrabajoDocente();
                    tipo.setIdTipoTrabajoDocente(resultado.getInt("idTipoTrabajoDocente"));
                    tipo.setNombreTrabajo(resultado.getString("nombreTrabajo"));
                    listaTipos.add(tipo);
                }
                respuesta = Constantes.OPERACION_EXITOSA;
                conexionBD.close();
            }catch(SQLException e){
                respuesta = Constantes.ERROR_CONSULTA;
                listaTipos = null;
            }
        }else{
            respuesta = Constantes.ERROR_CONEXION_BD;
            listaTipos = null;
        }
        return new Pair<>(respuesta, listaTipos);
    }
}
