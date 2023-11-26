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
import javafxsgacp.modelo.pojo.Bloque;
import javafxsgacp.modelo.pojo.Seccion;
import javafxsgacp.utilidades.Constantes;

/**
 *
 * @author monti
 */
public class BloqueDAO {
    public Pair<Constantes, ArrayList<Bloque>> recuperarBloques(ExperienciaEducativa experiencia){
        Connection conexion = ConexionBD.abrirConexionBD();
        ArrayList<Bloque> listaBloques = new ArrayList<Bloque>();
        if(conexion!=null){
            try{
                String consulta = "select bloque.idBloque, nombreBloque " +
                "from bloque " +
                "inner join bloqueexperienciaeducativa on bloqueexperienciaeducativa.idBloque=bloque.idBloque " +
                "inner join experienciaeducativa on experienciaeducativa.idExperienciaEducativa= bloqueexperienciaeducativa.idExperienciaEducativa " +
                "where experienciaeducativa.idExperienciaEducativa=?;";
                PreparedStatement sentencia= conexion.prepareStatement(consulta);   
                sentencia.setInt(1, experiencia.getIdExperienciaEducativa());      
                ResultSet filasAfectadas  = sentencia.executeQuery();
                while(filasAfectadas.next())
                {
                    Bloque bloque = new Bloque();
                    bloque.setIdBloque(filasAfectadas.getInt("idBloque"));
                    bloque.setNombreBloque(filasAfectadas.getString("nombreBloque"));
                    listaBloques.add(bloque);
                }
                if(listaBloques.isEmpty()){
                    return new Pair<>(Constantes.OPERACION_VACIA, null);
                }else{
                    return new Pair<>(Constantes.OPERACION_EXITOSA, listaBloques);                    
                }

            }catch(SQLException ex){
                return new Pair<>(Constantes.ERROR_CONSULTA, null);
            }
        }else{
            return new Pair<>(Constantes.ERROR_CONEXION_BD, null);
        }
    }
    
    public Pair<Constantes, ArrayList<Seccion>> recuperarSecciones(Bloque bloque){
        Connection conexion = ConexionBD.abrirConexionBD();
        ArrayList<Seccion> listaSecciones = new ArrayList<Seccion>();
        if(conexion!=null){
            try{
                String consulta = "SELECT Seccion.idSeccion, nombreSeccion " +
                "from bloque " +
                "inner join seccionbloque on seccionbloque.idbloque=bloque.idBloque " +
                "inner join seccion on seccionbloque.idSeccion=seccion.idSeccion where bloque.idBloque=?;";
                PreparedStatement sentencia= conexion.prepareStatement(consulta);   
                sentencia.setInt(1, bloque.getIdBloque());      
                ResultSet filasAfectadas  = sentencia.executeQuery();
                while(filasAfectadas.next())
                {
                    Seccion seccion = new Seccion();
                    seccion.setIdSeccion(filasAfectadas.getInt("idSeccion"));
                    seccion.setNombreSeccion(filasAfectadas.getString("nombreSeccion"));
                    listaSecciones.add(seccion);
                }
                if(listaSecciones.isEmpty()){
                    return new Pair<>(Constantes.OPERACION_VACIA, null);
                }else{
                    return new Pair<>(Constantes.OPERACION_EXITOSA, listaSecciones);                    
                }

            }catch(SQLException ex){
                return new Pair<>(Constantes.ERROR_CONSULTA, null);
            }
        }else{
            return new Pair<>(Constantes.ERROR_CONEXION_BD, null);
        }
    }
}
