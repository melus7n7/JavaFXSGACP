/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgacp.modelo.pojo;

/**
 *
 * @author sulem
 */
public class Director {
    private int idDirector;
    private String nombreDirector;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public Director() {
    }

    public int getIdDirector() {
        return idDirector;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
    
}
