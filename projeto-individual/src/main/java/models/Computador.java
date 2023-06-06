/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Faculdade
 */
public class Computador {
    private String hostName;
    private String sistemaOperacional;
    private String status; 
    private String mac;

    
    public Computador(String hostName, String sistemaOperacional, String status,String mac) {
        this.hostName = hostName;
        this.sistemaOperacional = sistemaOperacional;
        this.status = status;
        this.mac = mac;
    }
    
    public Computador() {
        
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    
    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
            
}
