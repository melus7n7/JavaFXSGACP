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
public class FirmaFacultad {
    private int idFirma;
    private Date fechaVencimiento;
    private Date fechaCreacion;
    private byte[] archivoFirma;

    public FirmaFacultad() {
    }

    public int getIdFirma() {
        return idFirma;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public byte[] getArchivoFirma() {
        return archivoFirma;
    }

    public void setIdFirma(int idFirma) {
        this.idFirma = idFirma;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setArchivoFirma(byte[] archivoFirma) {
        this.archivoFirma = archivoFirma;
    }
    
}
