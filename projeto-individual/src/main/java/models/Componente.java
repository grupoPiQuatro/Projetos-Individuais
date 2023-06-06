/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Faculdade
 */
public class Componente {
    private Integer idComponente;
    private Double numeroChave;
    private String unidadeMedida;
    private Integer fkTipo;

    public Componente(Integer idComponente, Double numeroChave, String unidadeMedida, Integer fkTipo) {
        this.idComponente = idComponente;
        this.numeroChave = numeroChave;
        this.unidadeMedida = unidadeMedida;
        this.fkTipo = fkTipo;
    }

    public Componente() {
    }

    public Integer getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Integer idComponente) {
        this.idComponente = idComponente;
    }

    public Double getNumeroChave() {
        return numeroChave;
    }

    public void setNumeroChave(Double numeroChave) {
        this.numeroChave = numeroChave;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Integer getFkTipo() {
        return fkTipo;
    }

    public void setFkTipo(Integer fkTipo) {
        this.fkTipo = fkTipo;
    }

    @Override
    public String toString() {
        return "Componente{" + "idComponente=" + idComponente + ", numeroChave=" + numeroChave + ", unidadeMedida=" + unidadeMedida + ", fkTipo=" + fkTipo + '}';
    }
    
   
}
