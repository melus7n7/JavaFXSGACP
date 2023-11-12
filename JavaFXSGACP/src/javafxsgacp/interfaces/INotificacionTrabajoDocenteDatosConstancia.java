/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package javafxsgacp.interfaces;

import javafxsgacp.controladores.FXMLTrabajoDocenteImpartirEEController;
import javafxsgacp.modelo.pojo.ImparticionExperienciaEducativa;

/**
 *
 * @author sulem
 */
public interface INotificacionTrabajoDocenteDatosConstancia {
    public void cargarDatosConstanciaImpartirEE(ImparticionExperienciaEducativa trabajo, FXMLTrabajoDocenteImpartirEEController elementoSeleccionado);
}
