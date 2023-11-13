/*
*Autor: Mongeote Tlachy Daniel
*Fecha de creación: 12/11/2023
*Fecha de modificación: 12/11/2023
*Descripción: POJO del tipo de usuario
*/
package javafxsgacp.modelo.pojo;

public class TipoUsuario {
    private int idTipoUsuario;
    private String nombreTipo;

    public TipoUsuario() {
    }
    
    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }
}