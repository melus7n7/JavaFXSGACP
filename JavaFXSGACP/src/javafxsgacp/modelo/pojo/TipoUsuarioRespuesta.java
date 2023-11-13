/*
*Autor: Mongeote Tlachy Daniel
*Fecha de creación: 12/11/2023
*Fecha de modificación: 12/11/2023
*Descripción: POJO de los tipos de usuarios
*/
package javafxsgacp.modelo.pojo;

import java.util.ArrayList;
import javafxsgacp.utilidades.Constantes;

public class TipoUsuarioRespuesta {
    private Constantes codigoRespuesta;
    private ArrayList<TipoUsuario> usuarios;

    public TipoUsuarioRespuesta() {
    }

    public TipoUsuarioRespuesta(Constantes codigoRespuesta, ArrayList<TipoUsuario> usuarios) {
        this.codigoRespuesta = codigoRespuesta;
        this.usuarios = usuarios;
    }

    public Constantes getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(Constantes codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public ArrayList<TipoUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<TipoUsuario> usuarios) {
        this.usuarios = usuarios;
    }
}
