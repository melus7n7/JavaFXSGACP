/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgacp.modelo.pojo;

/**
 *
 * @author sulem
 */
public class ExperienciaEducativa {
    private int idExperienciaEducativa;
    private ProgramaEducativo programaEducativo;
    private Periodo periodo;
    private Seccion seccion;
    private Bloque bloque;
    private String nombre;
    private int creditos;
    private int hsm;

    public ExperienciaEducativa() {
    }

    public int getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public ProgramaEducativo getProgramaEducativo() {
        return programaEducativo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getHsm() {
        return hsm;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public Bloque getBloque() {
        return bloque;
    }
    

    public void setIdExperienciaEducativa(int idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public void setProgramaEducativo(ProgramaEducativo programaEducativo) {
        this.programaEducativo = programaEducativo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public void setHsm(int hsm) {
        this.hsm = hsm;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }
    
    
}
