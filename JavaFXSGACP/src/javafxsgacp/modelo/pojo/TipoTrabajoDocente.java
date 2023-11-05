/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgacp.modelo.pojo;

/**
 *
 * @author sulem
 */
public class TipoTrabajoDocente {
    private int idTipoTrabajoDocente;
    private String nombreTrabajo;

    public TipoTrabajoDocente() {
    }

    public int getIdTipoTrabajoDocente() {
        return idTipoTrabajoDocente;
    }

    public String getNombreTrabajo() {
        return nombreTrabajo;
    }

    public void setIdTipoTrabajoDocente(int idTipoTrabajoDocente) {
        this.idTipoTrabajoDocente = idTipoTrabajoDocente;
    }

    public void setNombreTrabajo(String nombreTrabajo) {
        this.nombreTrabajo = nombreTrabajo;
    }

    @Override
    public String toString() {
        return nombreTrabajo;
    }
    
}
