package ru.innopolis.stc9.hibernateVSjdbc.connections;

import org.postgresql.ds.PGConnectionPoolDataSource;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * This pool increase limit for tests connections (x2);
 */

public class PoolConnection implements ConnectionManager
{
    private PGConnectionPoolDataSource source;

    public PoolConnection() {
        source = new PGConnectionPoolDataSource();
        source.setServerName("localhost");
        source.setPortNumber(5432);
        source.setDatabaseName("students_control_db");
        source.setUser("st_contr_admin");
        source.setPassword("qwerty");
        source.setLoginTimeout(20);
        source.setSocketTimeout(20);
        //source.setMaxStatements(20);
    }

    public Connection getConnection() throws SQLException {
        return source.getConnection();
    }

    public void putConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
