/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto.individual.nathan.david;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Nathan David
 */
public class ConexaoMySql {
    private JdbcTemplate connection;
    
    public ConexaoMySql(){
        BasicDataSource dataSource = new BasicDataSource();

        dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSource​.setUrl("jdbc:mysql://localhost/MonitorMind;");

        dataSource​.setUsername("MonitorMind");

        dataSource​.setPassword("monitormind");

        this.connection = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConnection() {
        return connection;
    }
}
