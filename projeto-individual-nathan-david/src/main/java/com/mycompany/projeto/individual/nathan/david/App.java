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
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConnection();
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
        
        CadastrarPc cp = new CadastrarPc();
        cp.verificarPc();
    }
}
