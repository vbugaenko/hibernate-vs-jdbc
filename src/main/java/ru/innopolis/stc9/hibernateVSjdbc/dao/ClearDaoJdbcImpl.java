package ru.innopolis.stc9.hibernateVSjdbc.dao;

import ru.innopolis.stc9.hibernateVSjdbc.connections.ConnectionManager;
import ru.innopolis.stc9.hibernateVSjdbc.connections.ConnectionManagerJDBCimpl;

import java.sql.PreparedStatement;

public class ClearDaoJdbcImpl
{
    private static ConnectionManager connectionManager = ConnectionManagerJDBCimpl.getInstance();
    private static final String DELETE_ALL_AGENT = "DELETE FROM agent;";
    private static final String DELETE_ALL_BOOK  = "DELETE FROM agentbook;";
    private static final String DELETE_ALL_PAGE  = "DELETE FROM page;";
    private static final String DELETE_ALL_AGENT_forJPA = "DROP TABLE IF EXISTS agentforjpa CASCADE;";
    private static final String DELETE_ALL_BOOK_forJPA  = "DROP TABLE IF EXISTS agentbookforjpa CASCADE;";
    private static final String DELETE_ALL_PAGE_forJPA  = "DROP TABLE IF EXISTS pageforjpa CASCADE;";
    private static final String DELETE_ALL_c_forJPA  = "DROP TABLE IF EXISTS agentbookforjpa_pageforjpa CASCADE;";


    public void clear()
    {
        try (
                PreparedStatement statement1 = connectionManager.getConnection().prepareStatement(DELETE_ALL_AGENT);
                PreparedStatement statement2 = connectionManager.getConnection().prepareStatement(DELETE_ALL_BOOK);
                PreparedStatement statement3 = connectionManager.getConnection().prepareStatement(DELETE_ALL_PAGE);
                PreparedStatement statement4 = connectionManager.getConnection().prepareStatement(DELETE_ALL_AGENT_forJPA);
                PreparedStatement statement5 = connectionManager.getConnection().prepareStatement(DELETE_ALL_BOOK_forJPA);
                PreparedStatement statement6 = connectionManager.getConnection().prepareStatement(DELETE_ALL_PAGE_forJPA);
                PreparedStatement statement7 = connectionManager.getConnection().prepareStatement(DELETE_ALL_c_forJPA);
        )
        {
            statement1.executeUpdate();
            statement2.executeUpdate();
            statement3.executeUpdate();
            statement4.executeUpdate();
            statement5.executeUpdate();
            statement6.executeUpdate();
            statement7.executeUpdate();
        } catch (Exception e) { }
    }
}


