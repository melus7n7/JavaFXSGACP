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
import javafxsgacp.modelo.pojo.ExperienciaEducativa;
import javafxsgacp.modelo.pojo.Periodo;
import javafxsgacp.modelo.pojo.ProgramaEducativo;
import javafxsgacp.utilidades.Constantes;

/**
 *
 * @author monti
 */
public class ExperienciaDAO {
    public Pair<Constantes, ArrayList<ExperienciaEducativa>> recuperarExperienciasEducativas(ProgramaEducativo programa, Periodo periodo){
        Connection conexion = ConexionBD.abrirConexionBD();
        ArrayList<ExperienciaEducativa> listaExperienciasEducativas = new ArrayList<ExperienciaEducativa>();
        if(conexion!=null){
            try{
                String consulta = "Select ExperienciaEducativa.idExperienciaEducativa, nombre, creditos, hsm " +
                "from ExperienciaEducativa " +
                "inner join programaEducativo on programaEducativo.idProgramaEducativo=experienciaeducativa.idProgramaEducativo " +
                "inner join periodoexperienciaeducativa on periodoexperienciaeducativa.idExperienciaEducativa=experienciaeducativa.idExperienciaEducativa " +
                "inner join periodo on periodo.idPeriodo=periodoexperienciaeducativa.idPeriodo where programaEducativo.idProgramaEducativo=? and periodo.idperiodo=?;";
                PreparedStatement sentencia= conexion.prepareStatement(consulta);   
                sentencia.setInt(1, programa.getIdProgramaEducativo());                
                sentencia.setInt(2, periodo.getIdPeriodo());  
                ResultSet filasAfectadas  = sentencia.executeQuery();
                while(filasAfectadas.next())
                {
                    ExperienciaEducativa experiencia = new ExperienciaEducativa();
                    experiencia.setIdExperienciaEducativa(filasAfectadas.getInt("idExperienciaEducativa"));
                    experiencia.setNombre(filasAfectadas.getString("nombre"));
                    experiencia.setCreditos(filasAfectadas.getInt("creditos"));
                    experiencia.setHsm(filasAfectadas.getInt("hsm"));
                    listaExperienciasEducativas.add(experiencia);
                }
                if(listaExperienciasEducativas.isEmpty()){
                    return new Pair<>(Constantes.OPERACION_VACIA, null);
                }else{
                    return new Pair<>(Constantes.OPERACION_EXITOSA, listaExperienciasEducativas);                    
                }

            }catch(SQLException ex){
                return new Pair<>(Constantes.ERROR_CONSULTA, null);
            }
        }else{
            return new Pair<>(Constantes.ERROR_CONEXION_BD, null);
        }
    }
}
