/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto.individual.nathan.david;

/**
 *
 * @author Nathan David
 */
public class Usuario {
    private String email;
    private String senha;
    private String fkEmpresa;
    
    public Usuario(String email, String senha, String fkEmpresa){
        this.email = email;
        this.senha = senha;
        this.fkEmpresa = fkEmpresa;
    }
    
    public Usuario() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(String fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", senha=" + senha + ", fkEmpresa=" + fkEmpresa + '}';
    }
}
