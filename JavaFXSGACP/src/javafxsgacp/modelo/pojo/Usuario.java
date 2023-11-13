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
public class Usuario {
    private String noPersonal;
    private String correoElectronico;
    private String contraseña;
    private TipoUsuario tipoUsuario;
    private Date fechaNacimiento;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private byte[] firmaDigital;

    public Usuario() {
    }
    
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public String getNoPersonal() {
        return noPersonal;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public byte[] getFirmaDigital() {
        return firmaDigital;
    }

    public void setNoPersonal(String noPersonal) {
        this.noPersonal = noPersonal;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setFirmaDigital(byte[] firmaDigital) {
        this.firmaDigital = firmaDigital;
    }
    
    
}
