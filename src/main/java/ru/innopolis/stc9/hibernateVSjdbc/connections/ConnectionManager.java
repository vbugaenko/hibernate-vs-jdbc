package ru.innopolis.stc9.hibernateVSjdbc.connections;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager
{
    Connection getConnection() throws SQLException;
}
