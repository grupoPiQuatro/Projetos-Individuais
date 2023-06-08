/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import connection.ConnectionAzure;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import models.Computador;
import models.UserLogin;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author faculdade
 */
public class App {

    public static void main(String[] args) throws IOException {
        Scanner leitor = new Scanner(System.in);
        
        connection.ConnectionAzure conexao = new ConnectionAzure();
        JdbcTemplate con = conexao.getConnection();
        
        Boolean validacao = true;
        while (validacao) {

            System.out.println("Digite o Login:");
            String login = leitor.nextLine();
            System.out.println("Digite a Senha:");
            String senha = leitor.nextLine();

            List<UserLogin> usuarios = con.query(String.format("select top 1 * from usuario where email = '%s' and senha = '%s'", login, senha), new BeanPropertyRowMapper(UserLogin.class));

            if (!usuarios.isEmpty()) {
                validacao = false;
                System.out.println("Usuário altenticado");
                CadastrarPc cp = new CadastrarPc();
                cp.verificarPc();
            }else{
                System.out.println("Email ou senha incorretos");
            }
        }
    }
}
