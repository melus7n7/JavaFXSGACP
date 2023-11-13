/*
*Autor: Mongeote Tlachy Daniel, Martínez Aguilar Sulem
*Fecha de creación: 12/11/2023
*Fecha de modificación: 12/11/2023
*Descripción: POJO del usuario
*/
package javafxsgacp.modelo.pojo;

import java.util.Date;

public class Usuario {
    private String noPersonal;
    private String correoElectronico;
    private int tipoUsuario;
    private String fechaNacimiento;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private byte[] firmaDigital;

    public Usuario() {
    }

    public String getNoPersonal() {
        return noPersonal;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public String getFechaNacimiento() {
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

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
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
