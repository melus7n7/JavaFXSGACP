/*
*Autor: Mongeote Tlachy Daniel, Martínez Aguilar Sulem
*Fecha de creación: 12/11/2023
*Fecha de modificación: 12/11/2023
*Descripción: POJO de la cuenta (correo electrónico)
*/
package javafxsgacp.modelo.pojo;

public class Cuenta {
    private String correoElectronico;
    private String contrasena;
    private String correoElectronicoAlterno;

    public Cuenta() {
    }

    public Cuenta(String correoElectronico, String contrasena, String correoElectronicoAlterno) {
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.correoElectronicoAlterno = correoElectronicoAlterno;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreoElectronicoAlterno() {
        return correoElectronicoAlterno;
    }

    public void setCorreoElectronicoAlterno(String correoElectronicoAlterno) {
        this.correoElectronicoAlterno = correoElectronicoAlterno;
    }
}
