/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgacp.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                    "INNER JOIN ExperienciaEducativa ON ExperienciaEducativa.idExperienciaEducativa = ImparticionExperienciaEducativa.idExperienciaEducativa " +
                    "INNER JOIN ProgramaEducativo ON ExperienciaEducativa.idProgramaEducativo = ProgramaEducativo.idProgramaEducativo " +
                    "INNER JOIN Periodo ON Periodo.idPeriodo = imparticionexperienciaeducativa.idPeriodo " +
                    "INNER JOIN Seccion     ON Seccion.idSeccion = imparticionexperienciaeducativa.idSeccion " +
                    "INNER JOIN Bloque ON Bloque.idBloque = imparticionexperienciaeducativa.idBloque " +
                    "WHERE noPersonal = ?";
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
    
    public static Pair<Constantes, byte[]> recuperarArchivoConstancia(int idTrabajoDocente){
        Constantes respuesta;
        byte[] constancia;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT archivoConstancia FROM trabajodocente WHERE idTrabajoDocente = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idTrabajoDocente);
                ResultSet resultado = prepararSentencia.executeQuery();
                
                if(resultado.next()){
                    constancia = resultado.getBytes("archivoConstancia");
                    respuesta = Constantes.OPERACION_EXITOSA;
                }else{
                    constancia = null;
                    respuesta = Constantes.OPERACION_VACIA;
                }
                conexionBD.close();
            }catch(SQLException e){
                System.out.println(e);
                respuesta = Constantes.ERROR_CONSULTA;
                constancia = null;
            }
        }else{
            respuesta = Constantes.ERROR_CONEXION_BD;
            constancia = null;
        }
        return new Pair<>(respuesta, constancia);
    }
    
    public static Constantes guardarArchivoConstancia(byte[] archivo, int idTrabajoDocente, int idFirma){
        Constantes respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String sentencia = "UPDATE TrabajoDocente SET fechaConstancia = current_date(), archivoConstancia = ?, idFirma = ? "
                        + "WHERE idTrabajoDocente = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setBytes(1, archivo);
                prepararSentencia.setInt(3, idTrabajoDocente);
                prepararSentencia.setInt(2, idFirma);
                int filasAfectadas = prepararSentencia.executeUpdate();
                respuesta = (filasAfectadas <= 0) ? Constantes.OPERACION_VACIA : Constantes.OPERACION_EXITOSA;
                conexionBD.close();
            }catch (SQLException e){
                respuesta = Constantes.ERROR_CONSULTA;
                e.printStackTrace();
            }
        }else{
            respuesta = Constantes.ERROR_CONEXION_BD;
        }
        return respuesta;
    }
    
    public Constantes guardarTrabajoDocente(String noPersonal, int idExperienciaEducativa, int idSeccion, int idBloque, int idPeriodo){
        Connection conexion = ConexionBD.abrirConexionBD();
        if(conexion!=null){
            try{
                String consulta = "INSERT INTO TrabajoDocente (fechaRegistro, idTipoTrabajoDocente, noPersonal, idDirector, idFirma) VALUES (CURDATE(),3,?,1,1)";
                PreparedStatement sentencia= conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);   
                sentencia.setString(1, noPersonal);      
                int filasAfectadas  = sentencia.executeUpdate();
                if(filasAfectadas > 0){
                    ResultSet generatedKeys = sentencia.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int ultimoIdGenerado = generatedKeys.getInt(1);
                        Constantes respuesta = guardarImpartirEE(ultimoIdGenerado, idExperienciaEducativa, idSeccion, idBloque, idPeriodo);
                        return respuesta;
                    }
                    return Constantes.OPERACION_VACIA;
                }else{
                    return Constantes.OPERACION_VACIA;                    
                }

            }catch(SQLException ex){
                return Constantes.ERROR_CONSULTA;
            }
        }else{
            return Constantes.ERROR_CONEXION_BD;
        }
    }
    
    public Constantes guardarImpartirEE(int idTrabajoDocente, int idExperienciaEducativa, int idSeccion, int idBloque, int idPeriodo){
        Connection conexion = ConexionBD.abrirConexionBD();
        if(conexion!=null){
            try{
                String consulta = "INSERT INTO imparticionexperienciaeducativa "
                + "(idTrabajoDocente, idExperienciaEducativa, idSeccion, idBloque, idPeriodo) "
                + "values(?, ?, ?, ?, ?);";
                PreparedStatement sentencia= conexion.prepareStatement(consulta);   
                sentencia.setInt(1, idTrabajoDocente);      
                sentencia.setInt(2, idExperienciaEducativa);      
                sentencia.setInt(3, idSeccion);      
                sentencia.setInt(4, idBloque);      
                sentencia.setInt(5, idPeriodo);      
                int filasAfectadas  = sentencia.executeUpdate();
                if(filasAfectadas > 0){
                    return Constantes.OPERACION_EXITOSA;
                }else{
                    return Constantes.OPERACION_VACIA;                    
                }
            }catch(SQLException ex){
                return Constantes.ERROR_CONSULTA;
            }
        }else{
            return Constantes.ERROR_CONEXION_BD;
        }
    }
}
