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
public class Periodo {
    private int idPeriodo;
    private String nombrePeriodo;
    private Date fechaInicio;
    private Date fechaFinal;

    public Periodo() {
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public String getNombrePeriodo() {
        return nombrePeriodo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public void setNombrePeriodo(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public String toString() {
        return nombrePeriodo + " (" + fechaInicio + "-" + fechaFinal + ")";
    }
    
    
}
