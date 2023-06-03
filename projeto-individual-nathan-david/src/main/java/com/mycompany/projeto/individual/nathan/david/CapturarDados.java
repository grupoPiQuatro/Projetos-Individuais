/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto.individual.nathan.david;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Nathan David
 */
public class CapturarDados {

    private Looca looca;

    public CapturarDados() {
        this.looca = new Looca();
    }

    public Double usoAtualRam() {

        long memoriaRam = looca.getMemoria().getEmUso();
        Double ram = (double) memoriaRam;
        Double usoRam = Math.floor(ram / (1024 * 1024 * 1024));
        Double porceRam = usoRam * 100 / 16;

        return porceRam;
    }

    public Double usoAtualCpu() {

        Double cpu = looca.getProcessador().getUso();

        return cpu;
    }

    public Double UsoAtualDisco() {
        List<Volume> volumes = looca.getGrupoDeDiscos().getVolumes();

        Double qtdDisponivelBytes = (double) volumes.get(0).getDisponivel();
        Double qtdTotalBytes = (double) volumes.get(0).getTotal();
        Double qtdUso = qtdTotalBytes - qtdDisponivelBytes;

        Double usoConvertido = Math.floor(qtdUso / (1024 * 1024 * 1024));
        Double totalBytesConvertido = Math.floor(qtdTotalBytes / (1024 * 1024 * 1024));

        if (totalBytesConvertido <= 120) {
            totalBytesConvertido = 30.0;
        } else if (totalBytesConvertido <= 256) {
            totalBytesConvertido = 256.0;
        } else if (totalBytesConvertido <= 512) {
            totalBytesConvertido = 512.0;
        } else {
            totalBytesConvertido = 1024.0;
        }
        Double discoPorce = usoConvertido * 100 / totalBytesConvertido;

        return discoPorce;
    }

    public Double ping() {
        String so = looca.getSistema().getSistemaOperacional();

        if (so.equalsIgnoreCase("Windows")) {
            
            Random rn = new Random();
            String host = "www.google.com"; // Especifique o host que você deseja pingar
            Long ping = null;
            try {
                InetAddress inetAddress = InetAddress.getByName(host);
                long startTime = System.currentTimeMillis();
                if (inetAddress.isReachable(5000)) { // Timeout de 5 segundos
                    long endTime = System.currentTimeMillis();
                    long pingTime = endTime - startTime;
                    ping = pingTime;
                } else {
                    System.out.println("Ping para " + host + " falhou.");
                    ping = rn.nextLong(25);
                }
            } catch (IOException e) {
                System.out.println("Ocorreu um erro durante o ping: " + e.getMessage());
            }
            
            return (double) ping;        
        } else {
            
            String command = "ping -c 4 google.com"; // Comando de ping para o Google

            try {
                Process process = Runtime.getRuntime().exec(command);

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                int exitCode = process.waitFor();
                return (double) exitCode;
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        return null;
    }
}
