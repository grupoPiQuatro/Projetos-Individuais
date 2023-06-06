/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 *
 * @author faculdade
 */
public class ConnectionAzure {
    private JdbcTemplate connection;
    
     public ConnectionAzure(){
        BasicDataSource dataSource = new BasicDataSource();

        dataSource​.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        dataSource​.setUrl("jdbc:sqlserver://servidor-monitor-mind.database.windows.net:1433;"
                + "database=bd-monitor-mind;"
                + "user=admin-monitor-mind@servidor-monitor-mind;"
                + "password=#Gfgrupo4;"
                + "encrypt=true;trustServerCertificate=false;"
                + "hostNameInCertificate=*.database.windows.net;"
                + "loginTimeout=30;");

        dataSource​.setUsername("admin-monitor-mind");

        dataSource​.setPassword("#Gfgrupo4");

        this.connection = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConnection() {
        return connection;
    }
    public static void main(String[] args) {
        
    }
}
