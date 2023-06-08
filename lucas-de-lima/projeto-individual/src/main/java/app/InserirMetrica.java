/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import connection.ConnectionAzure;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author faculdade
 */
public class InserirMetrica {

    private Looca looca;

    public InserirMetrica() {
        this.looca = new Looca();
    }

    public Double getUsoAtualDisco() {
        List<Volume> volumes = looca.getGrupoDeDiscos().getVolumes();

        long l = volumes.get(0).getDisponivel();
        long g = volumes.get(0).getTotal();

        Double response = Math.floor(((l / (1024 * 1024 * 1024))) * 100) / 100;
        Double response1 = Math.floor(((g / (1024 * 1024 * 1024))) * 100) / 100;

        if (response1 < 100) {
            response1 = 30.0;
        } else if (response1 < 256) {
            response1 = 256.0;
        } else if (response1 < 512) {
            response1 = 512.0;
        } else {
            response1 = 1024.0;
        }

        Double emUso = response1 - response;
        return emUso;
    }

    public Double getUsoAtualRam() {
        long ram = looca.getMemoria().getEmUso();
        Double r = (double) ram;
        Double usoRam = Math.floor(((r / (1024 * 1024 * 1024))) * 100) / 100;
        return usoRam;
    }

    public Double getUsoAtualCpu() {
        Double usoCpuCheio = looca.getProcessador().getUso();
        Double usoCpu = Math.floor(usoCpuCheio * 100) / 100;
        return usoCpu;
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
                ping = rn.nextLong(25);
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro durante o ping: " + e.getMessage());
        }

        return ping;
    }

    public Integer fkConfigRede() {
        ConnectionAzure conexao = new ConnectionAzure();
        JdbcTemplate con = conexao.getConnection();
        InfoPc infoPc = new InfoPc();

        String hostname = infoPc.hostName();

        return con.queryForObject("select c.idConfig from Config c\n"
                + "join Componente cp on cp.idComponente = c.fkComponente\n"
                + "where cp.fkTipo in (1) and c.fkComputador = ?;", Integer.class, hostname);
    }

    public Integer fkConfigRam() {
        ConnectionAzure conexao = new ConnectionAzure();
        JdbcTemplate con = conexao.getConnection();

        InfoPc infoPc = new InfoPc();
        String hostname = infoPc.hostName();

        return con.queryForObject("select c.idConfig from Config c join Componente cp on cp.idComponente = c.fkComponente where cp.fkTipo  in (2) and c.fkComputador = ?;", Integer.class, hostname);
    }

    public Integer fkConfigCpu() {
        ConnectionAzure conexao = new ConnectionAzure();
        JdbcTemplate con = conexao.getConnection();
        InfoPc infoPc = new InfoPc();

        String hostname = infoPc.hostName();

        return con.queryForObject("select c.idConfig from Config c\n"
                + "join Componente cp on cp.idComponente = c.fkComponente\n"
                + "where cp.fkTipo in (3) and c.fkComputador = ?;", Integer.class, hostname);
    }

    public Integer fkConfigArmazenamento() {
        ConnectionAzure conexao = new ConnectionAzure();
        JdbcTemplate con = conexao.getConnection();
        InfoPc infoPc = new InfoPc();

        String hostname = infoPc.hostName();

        return con.queryForObject("select c.idConfig from Config c\n"
                + "join Componente cp on cp.idComponente = c.fkComponente\n"
                + "where cp.fkTipo in (4,5) and c.fkComputador = ?;", Integer.class, hostname);
    }

    public void inserirMetrica() {
        InfoPc ip = new InfoPc();

        ConnectionAzure conexao = new ConnectionAzure();
        JdbcTemplate con = conexao.getConnection();

        Long rede = ping();
        Double ramAtual = getUsoAtualRam();
        Double totalRam = ip.qtdRam();
        Double ram = (ramAtual / totalRam) * 100;

        Double cpu = getUsoAtualCpu();
        Double discoAtual = getUsoAtualDisco();
        Double discoTotal = ip.qtdArmazenamento();
        Double disco = (discoAtual / discoTotal) * 100;

        int azurePing = con.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'ms',CONVERT(VARCHAR, DATEADD(HOUR, -3, GETDATE()), 120),?)", rede, fkConfigRede());
        int azureRam = con.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'%',CONVERT(VARCHAR, DATEADD(HOUR, -3, GETDATE()), 120),?)", ram, fkConfigRam());
        int azureCpu = con.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'%',CONVERT(VARCHAR, DATEADD(HOUR, -3, GETDATE()), 120),?)", cpu, fkConfigCpu());
        int azureArmazenamento = con.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'%',CONVERT(VARCHAR, DATEADD(HOUR, -3, GETDATE()), 120),?)", disco, fkConfigArmazenamento());

        System.out.println(String.format("""
                                          
                           Dados sendo inseridos
                           *************************
                                %d MS
                                %.2f %%  RAM
                                %.2f %%  CPU
                                %.2f %%  Armazenamento
                           *************************
                                          
                           """, rede, ram, cpu, disco));

    }
}
