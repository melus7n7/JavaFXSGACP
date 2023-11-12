/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgacp.modelo.pojo;

import java.util.Date;

/**
 *
 * @author sulem
 */
public class TrabajoDocente {
    private int idTrabajoDocente;
    private TipoTrabajoDocente tipoTrabajo;
    private Usuario docente;
    private Director director;
    private Date fechaExpedicionConstancia;
    private Date fechaRegistro;
    private byte[] archivoConstancia;

    public TrabajoDocente() {
    }

    public int getIdTrabajoDocente() {
        return idTrabajoDocente;
    }

    public TipoTrabajoDocente getTipoTrabajo() {
        return tipoTrabajo;
    }

    public Usuario getDocente() {
        return docente;
    }

    public Director getDirector() {
        return director;
    }

    public Date getFechaExpedicionConstancia() {
        return fechaExpedicionConstancia;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public byte[] getArchivoConstancia() {
        return archivoConstancia;
    }

    public void setIdTrabajoDocente(int idTrabajoDocente) {
        this.idTrabajoDocente = idTrabajoDocente;
    }

    public void setTipoTrabajo(TipoTrabajoDocente tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    public void setDocente(Usuario docente) {
        this.docente = docente;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public void setFechaExpedicionConstancia(Date fechaExpedicionConstancia) {
        this.fechaExpedicionConstancia = fechaExpedicionConstancia;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setArchivoConstancia(byte[] archivoConstancia) {
        this.archivoConstancia = archivoConstancia;
    }
    
    
}
