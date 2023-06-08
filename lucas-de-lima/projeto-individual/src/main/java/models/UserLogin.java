/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author lukas
 */
public class UserLogin {
    private String email;
    private String senha;
    private String fkEmpresa;
    
    public UserLogin(String email, String senha, String fkEmpresa){
        this.email = email;
        this.senha = senha;
        this.fkEmpresa = fkEmpresa;
    }
    
    public UserLogin() {
    }

    public String getLogin() {
        return email;
    }

    public void setLogin(String email) {
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
        return "UserLogin{" + "email=" + email + ", senha=" + senha + ", fkEmpresa=" + fkEmpresa + '}';
    }
    
    
}
