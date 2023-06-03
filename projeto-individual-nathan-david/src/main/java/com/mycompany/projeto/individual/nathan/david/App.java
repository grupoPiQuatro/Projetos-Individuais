/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto.individual.nathan.david;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Nathan David
 */
public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
        CapturarDados dados = new CapturarDados();
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConnection();

        ConexaoMySql conexaoMySql = new ConexaoMySql();
        JdbcTemplate conMySql = conexaoMySql.getConnection();

        Scanner leitor = new Scanner(System.in);
        List<Usuario> user = new ArrayList();
        Boolean usuarioEncontrado = false;

        Utilitarios util = new Utilitarios();
        util.limparShell();

        do {
            System.out.println("Digite seu email:");
            String emailDigitado = leitor.nextLine();
            System.out.println("Digite sua senha:");
            String senhaDigitada = leitor.nextLine();

            user = con.query(String.format("select * from Usuario where email = '%s' and senha = '%s'", emailDigitado, senhaDigitada),
                    new BeanPropertyRowMapper(Usuario.class));
            Integer sizeUser = user.size();

            if (sizeUser > 0) {
                System.out.println("Login realizado com sucesso!");
                usuarioEncontrado = true;
                Thread.sleep(2000);
                util.limparShell();
            } else {
                System.out.println("Credenciais incorretas ou inexistentes!");
                Thread.sleep(2000);
                util.limparShell();
            }

        } while (usuarioEncontrado == false);

        while (true) {
            Double ping = (double) dados.ping();
            Double ram = dados.usoAtualRam();
            Double cpu = dados.usoAtualCpu();
            Double disco = dados.UsoAtualDisco();

            int azurePing = con.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'ms',CONVERT(VARCHAR, DATEADD(HOUR, -3, GETDATE()), 120),?)", ping, 1);
            int azureRam = con.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'GB',CONVERT(VARCHAR, DATEADD(HOUR, -3, GETDATE()), 120),?)", ram, 2);
            int azureCpu = con.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'%',CONVERT(VARCHAR, DATEADD(HOUR, -3, GETDATE()), 120),?)", cpu, 3);
            int azureArmazenamento = con.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'GB',CONVERT(VARCHAR, DATEADD(HOUR, -3, GETDATE()), 120),?)", disco, 4);

//                int localPing = conMySql.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'ms',current_timestamp,?)", ping, 1);
//                int localRam = conMySql.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'GB',current_timestamp,?)", ram, 2);
//                int localCpu = conMySql.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'%',current_timestamp,?)", cpu, 3);
//                int localArmazenamento = conMySql.update("insert into Metrica (valor, unidade,dtCaptura,fkConfig) values(?,'GB',current_timestamp,?)", disco, 4);
            System.out.println("Dados inseridos com sucesso!");

            for (int i = 15; i >= 0; i--) {
                System.out.println("Inserindo novamente em: " + i + " segundos");
                Thread.sleep(1000);
                util.limparShell();
            };
        }

    }
}
