package configuration;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Nico on 28/9/2016.
 */

@Component
public class Singleton_Sql_Connection {


    private static Singleton_Sql_Connection singletonSql_connection = null;
    private String connectionUrl;
    private Connection connect = null;

    private Singleton_Sql_Connection() {
        connectionUrl = "jdbc:sqlserver://;database=DB_Pronostico;integratedSecurity=true;";

        try {
            connect = DriverManager.getConnection(connectionUrl);
            System.out.println("Conected to DB!");
        } catch (SQLException ex) {
            System.out.println("Error! It's not possible connect to DB");
        }
    }

    public static Singleton_Sql_Connection getInstance() throws SQLException {
        if (singletonSql_connection == null || singletonSql_connection.getConnect().isClosed()) {
            singletonSql_connection = new Singleton_Sql_Connection();
        }
        return singletonSql_connection;
    }

    public Connection getConnect() {
        return connect;
    }
}
