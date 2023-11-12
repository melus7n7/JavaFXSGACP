/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgacp.modelo.pojo;

/**
 *
 * @author sulem
 */
public class ImparticionExperienciaEducativa extends TrabajoDocente{
    private int idImpartirExperienciaEducativa;
    private ExperienciaEducativa experienciaEducativa;

    public ImparticionExperienciaEducativa() {
    }

    public int getIdImpartirExperienciaEducativa() {
        return idImpartirExperienciaEducativa;
    }

    public ExperienciaEducativa getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public void setIdImpartirExperienciaEducativa(int idImpartirExperienciaEducativa) {
        this.idImpartirExperienciaEducativa = idImpartirExperienciaEducativa;
    }

    public void setExperienciaEducativa(ExperienciaEducativa experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
    }
    
    
}
