/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarciso.projeto.individual.tarciso;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.ProcessadorCacheLoader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Random;

/**
 *
 * @author 55119
 */
public class CapturarDados {

    private Looca looca;

    public CapturarDados() {
        this.looca = new Looca();
    }

    public Double usoAtualRam() {

        long memoriaRam = looca.getMemoria().getEmUso();
        Double ram = (double) memoriaRam;
        Double usoRam = Math.floor(((ram / (1024 * 1024 * 1024))) * 100) / 100;
        Double porceRam = usoRam * 100 / 8;

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

        Double usoConvertido = Math.floor((qtdUso / (1024 * 1024 * 1024)));
        Double totalBytesConvertido = Math.floor((qtdTotalBytes / (1024 * 1024 * 1024)));
        System.out.println(totalBytesConvertido);
        
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

    public Long ping() {
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

        return ping;
    }
}
