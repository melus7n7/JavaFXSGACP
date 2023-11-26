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
import javafxsgacp.modelo.pojo.ProgramaEducativo;
import javafxsgacp.utilidades.Constantes;

/**
 *
 * @author monti
 */
public class ProgramaEducativoDAO {
    public Pair<Constantes, ArrayList<ProgramaEducativo>> recuperarProgramasEducativos(){
        Connection conexion = ConexionBD.abrirConexionBD();
        ArrayList<ProgramaEducativo> listaProgramasEducativos = new ArrayList<ProgramaEducativo>();
        
        if(conexion!=null){
            try{
                String consulta = "SELECT idProgramaEducativo, nombreProgramaEducativo FROM ProgramaEducativo;";
                PreparedStatement sentencia= conexion.prepareStatement(consulta);   
                ResultSet filasAfectadas  = sentencia.executeQuery();
                while(filasAfectadas.next())
                {
                    ProgramaEducativo programa = new ProgramaEducativo();
                    programa.setIdProgramaEducativo(filasAfectadas.getInt("idProgramaEducativo"));
                    programa.setNombreProgramaEducativo(filasAfectadas.getString("nombreProgramaEducativo"));
                    listaProgramasEducativos.add(programa);
                }
                if(listaProgramasEducativos.isEmpty()){
                    return new Pair<>(Constantes.OPERACION_VACIA, null);
                }else{
                    return new Pair<>(Constantes.OPERACION_EXITOSA, listaProgramasEducativos);                    
                }

            }catch(SQLException ex){
                return new Pair<>(Constantes.ERROR_CONSULTA, null);
            }
        }else{
            return new Pair<>(Constantes.ERROR_CONEXION_BD, null);
        }
    }
}
