/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto.individual.nathan.david;

import com.github.britooo.looca.api.core.Looca;
import java.io.IOException;

/**
 *
 * @author Nathan David
 */
public class Utilitarios {

    public Utilitarios() {
    }
    
    public void limparShell() throws IOException, InterruptedException {
        Looca looca = new Looca();
        String so = looca.getSistema().getSistemaOperacional();
        
        if (so.equalsIgnoreCase("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
        
    }
}
