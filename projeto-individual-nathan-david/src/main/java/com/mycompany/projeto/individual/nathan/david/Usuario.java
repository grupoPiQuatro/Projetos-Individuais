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
    private String login;
    private String senha;
    private String fkEmpresa;
    
    public Usuario(String login, String senha, String fkEmpresa){
        this.login = login;
        this.senha = senha;
        this.fkEmpresa = fkEmpresa;
    }
    
    public Usuario() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
}
