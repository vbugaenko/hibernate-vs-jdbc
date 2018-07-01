package ru.innopolis.stc9.hibernateVSjdbc.dao;

import ru.innopolis.stc9.hibernateVSjdbc.connections.ConnectionManager;
import ru.innopolis.stc9.hibernateVSjdbc.connections.ConnectionManagerJDBCimpl;
import ru.innopolis.stc9.hibernateVSjdbc.pojo.Page;

import java.sql.PreparedStatement;

public class PagesDaoJdbcImpl
{
    private static ConnectionManager connectionManager = ConnectionManagerJDBCimpl.getInstance();
    private static final String INSERT_SQL_P = "INSERT INTO page (book_id, text) VALUES (?, ?);";
    private static final String DELETE_SQL_P = "DELETE FROM page WHERE book_id = ?;";

    public void insertPages(int book_id, Page page)
    {
        try (PreparedStatement statement = connectionManager.getConnection().prepareStatement(INSERT_SQL_P))
        {
            statement.setInt(1, book_id);
            statement.setString(2, page.getText());
            statement.execute();
        } catch (Exception e) {e.printStackTrace(); }
    }

    public boolean deletePages(int book_id)
    {
        try (PreparedStatement statement = connectionManager.getConnection().prepareStatement(DELETE_SQL_P)) {
            statement.setInt(1, book_id);
            return statement.execute();
        } catch (Exception e) { e.printStackTrace();}
        return false;
    }

}
