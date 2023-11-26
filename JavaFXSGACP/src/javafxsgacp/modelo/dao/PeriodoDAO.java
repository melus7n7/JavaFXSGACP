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
import javafxsgacp.modelo.pojo.Periodo;
import javafxsgacp.utilidades.Constantes;

/**
 *
 * @author monti
 */
public class PeriodoDAO {
    public Pair<Constantes, ArrayList<Periodo>> recuperarPeriodos(){
        Connection conexion = ConexionBD.abrirConexionBD();
        ArrayList<Periodo> listaPeriodos = new ArrayList<Periodo>();
        
        if(conexion!=null){
            try{
                String consulta = "Select idPeriodo, nombrePeriodo, fechaInicio, fechaFinal from Periodo;";
                PreparedStatement sentencia= conexion.prepareStatement(consulta);   
                ResultSet filasAfectadas  = sentencia.executeQuery();
                while(filasAfectadas.next())
                {
                    Periodo periodo = new Periodo();
                    periodo.setIdPeriodo(filasAfectadas.getInt("idPeriodo"));
                    periodo.setNombrePeriodo(filasAfectadas.getString("nombrePeriodo"));
                    periodo.setFechaInicio(filasAfectadas.getDate("fechaInicio"));
                    periodo.setFechaFinal(filasAfectadas.getDate("fechaFinal"));
                    listaPeriodos.add(periodo);
                }
                if(listaPeriodos.isEmpty()){
                    return new Pair<>(Constantes.OPERACION_VACIA, null);
                }else{
                    return new Pair<>(Constantes.OPERACION_EXITOSA, listaPeriodos);                    
                }

            }catch(SQLException ex){
                return new Pair<>(Constantes.ERROR_CONSULTA, null);
            }
        }else{
            return new Pair<>(Constantes.ERROR_CONEXION_BD, null);
        }
    }
}
