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
import javafxsgacp.modelo.pojo.Bloque;
import javafxsgacp.modelo.pojo.Director;
import javafxsgacp.modelo.pojo.ExperienciaEducativa;
import javafxsgacp.modelo.pojo.ImparticionExperienciaEducativa;
import javafxsgacp.modelo.pojo.Periodo;
import javafxsgacp.modelo.pojo.ProgramaEducativo;
import javafxsgacp.modelo.pojo.Seccion;
import javafxsgacp.modelo.pojo.TipoTrabajoDocente;
import javafxsgacp.modelo.pojo.TrabajoDocente;
import javafxsgacp.modelo.pojo.Usuario;
import javafxsgacp.utilidades.Constantes;

/**
 *
 * @author sulem
 */
public class TrabajoDocenteDAO {
    public static Pair<Constantes, ArrayList<ImparticionExperienciaEducativa>> recuperarTrabajosDocenteTipoImpartirEE(Usuario docente){
        Constantes respuesta;
        ArrayList<ImparticionExperienciaEducativa> listaTrabajos = new ArrayList<>();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT trabajodocente.idTrabajoDocente, fechaRegistro, fechaConstancia, nombre, nombreProgramaEducativo, " +
                    "nombrePeriodo, nombreSeccion, nombreBloque, TrabajoDocente.idTipoTrabajoDocente, nombreTrabajo, " +
                    "nombreDirector, apellidoPaterno, apellidoMaterno, creditos, hsm " +
                    "FROM trabajodocente " +
                    "INNER JOIN Director ON Director.idDirector = trabajodocente.idDirector " +
                    "INNER JOIN TipoTrabajoDocente ON TipoTrabajoDocente.idTipoTrabajoDocente = TrabajoDocente.idTipoTrabajoDocente " +    
                    "INNER JOIN ImparticionExperienciaEducativa ON trabajoDocente.idTrabajoDocente = ImparticionExperienciaEducativa.idtrabajoDocente " +
                    "INNER JOIN ExperienciaEducativa ON ExperienciaEducativa.NRC = ImparticionExperienciaEducativa.NRC " +
                    "INNER JOIN ProgramaEducativo ON ExperienciaEducativa.idProgramaEducativo = ProgramaEducativo.idProgramaEducativo " +
                    "INNER JOIN PeriodoExperienciaEducativa ON PeriodoExperienciaEducativa.NRC = ExperienciaEducativa.NRC " +
                    "INNER JOIN Periodo ON Periodo.idPeriodo = PeriodoExperienciaEducativa.idPeriodo " +
                    "INNER JOIN SeccionExperienciaEducativa ON SeccionExperienciaEducativa.NRC = ExperienciaEducativa.NRC " +
                    "INNER JOIN Seccion ON Seccion.idSeccion = SeccionExperienciaEducativa.idSeccion " +
                    "INNER JOIN BloqueExperienciaEducativa ON BloqueExperienciaEducativa.NRC = ExperienciaEducativa.NRC " +
                    "INNER JOIN Bloque ON Bloque.idBloque = BloqueExperienciaEducativa.idBloque " +
                    "WHERE noPersonal = ? ";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, docente.getNoPersonal());
                ResultSet resultado = prepararSentencia.executeQuery();
                
                while(resultado.next()){
                    ImparticionExperienciaEducativa trabajo = new ImparticionExperienciaEducativa();
                    trabajo.setIdTrabajoDocente(resultado.getInt("idTrabajoDocente"));
                    trabajo.setFechaRegistro(resultado.getDate("fechaRegistro"));
                    trabajo.setFechaExpedicionConstancia(resultado.getDate("fechaConstancia"));
                    
                    TipoTrabajoDocente tipo = new TipoTrabajoDocente();
                    tipo.setIdTipoTrabajoDocente(resultado.getInt("idTipoTrabajoDocente"));
                    tipo.setNombreTrabajo(resultado.getString("nombreTrabajo"));
                    
                    ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
                    experienciaEducativa.setNombre(resultado.getString("nombre"));
                    experienciaEducativa.setHsm(resultado.getInt("hsm"));
                    experienciaEducativa.setCreditos(resultado.getInt("creditos"));
                    
                    ProgramaEducativo programaEducativo = new ProgramaEducativo();
                    programaEducativo.setNombreProgramaEducativo(resultado.getString("nombreProgramaEducativo"));
                    
                    Periodo periodo = new Periodo();
                    periodo.setNombrePeriodo(resultado.getString("nombrePeriodo"));
                    
                    Seccion seccion = new Seccion();
                    seccion.setNombreSeccion(resultado.getString("nombreSeccion"));
                    
                    Bloque bloque = new Bloque();
                    bloque.setNombreBloque(resultado.getString("nombreBloque"));
                    
                    Director director = new Director();
                    director.setNombreDirector(resultado.getString("nombreDirector"));
                    director.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    director.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                    
                    experienciaEducativa.setProgramaEducativo(programaEducativo);
                    experienciaEducativa.setPeriodo(periodo);
                    experienciaEducativa.setBloque(bloque);
                    experienciaEducativa.setSeccion(seccion);
                    trabajo.setExperienciaEducativa(experienciaEducativa);
                    trabajo.setTipoTrabajo(tipo);
                    trabajo.setDirector(director);
                    
                    listaTrabajos.add(trabajo);
                }
                respuesta = Constantes.OPERACION_EXITOSA;
                conexionBD.close();
            }catch(SQLException e){
                System.out.println(e);
                respuesta = Constantes.ERROR_CONSULTA;
                listaTrabajos = null;
            }
        }else{
            respuesta = Constantes.ERROR_CONEXION_BD;
            listaTrabajos = null;
        }
        return new Pair<>(respuesta, listaTrabajos);
    }
}
