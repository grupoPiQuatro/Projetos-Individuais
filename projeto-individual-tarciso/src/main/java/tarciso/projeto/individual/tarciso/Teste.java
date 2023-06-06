/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarciso.projeto.individual.tarciso;

import com.github.britooo.looca.api.core.Looca;
import java.util.Scanner;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author 55119
 */
public class Teste {

    public static void main(String[] args) {

        Timer timer = new Timer();
        CapturarDados dados = new CapturarDados();
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConnection();

        Double ping = (double) dados.ping();
        Double ram = dados.usoAtualRam();
        Double cpu = dados.usoAtualCpu();
        Double disco = dados.UsoAtualDisco();
        
        

        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                int azurePing = con.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'ms',current_timestamp,?)", ping, 337);
                int azureRam = con.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'gb',current_timestamp,?)", ram, 338);
                int azureCpu = con.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'%',current_timestamp,?)", cpu, 339);
                int azureArmazenamento = con.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'gb',current_timestamp,?)", disco, 340);
                System.out.println("Inserindo a cada 10 segundos...");
            }
        };

        timer.scheduleAtFixedRate(tarefa, 0, 10000);

    }
}
