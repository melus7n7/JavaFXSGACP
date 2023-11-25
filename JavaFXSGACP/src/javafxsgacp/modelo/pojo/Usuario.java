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
    private String fechaNacimiento;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correoElectronico;
    private int idTipoUsuario;
    private String correoElectronicoAlterno;
    private String contraseña;

    public Usuario() {
    }

    public Usuario(String noPersonal, String fechaNacimiento, String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, int idTipoUsuario, String correoElectronicoAlterno, String contraseña) {
        this.noPersonal = noPersonal;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoElectronico = correoElectronico;
        this.idTipoUsuario = idTipoUsuario;
        this.correoElectronicoAlterno = correoElectronicoAlterno;
        this.contraseña = contraseña;
    }

    public String getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(String noPersonal) {
        this.noPersonal = noPersonal;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getCorreoElectronicoAlterno() {
        return correoElectronicoAlterno;
    }

    public void setCorreoElectronicoAlterno(String correoElectronicoAlterno) {
        this.correoElectronicoAlterno = correoElectronicoAlterno;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
