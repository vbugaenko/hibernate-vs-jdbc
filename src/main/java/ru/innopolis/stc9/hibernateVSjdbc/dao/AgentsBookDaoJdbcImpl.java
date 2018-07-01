package ru.innopolis.stc9.hibernateVSjdbc.dao;

import ru.innopolis.stc9.hibernateVSjdbc.connections.ConnectionManager;
import ru.innopolis.stc9.hibernateVSjdbc.connections.ConnectionManagerJDBCimpl;
import ru.innopolis.stc9.hibernateVSjdbc.pojo.AgentBook;
import ru.innopolis.stc9.hibernateVSjdbc.pojo.Page;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AgentsBookDaoJdbcImpl
{
    private static ConnectionManager connectionManager = ConnectionManagerJDBCimpl.getInstance();
    private static final String INSERT_SQL_B = "INSERT INTO agentbook (agent_id, title) VALUES (?, ?) RETURNING id;";
    private static final String DELETE_SQL_B = "DELETE FROM agentbook WHERE id = ?;";

    public AgentBook insertBook(AgentBook book)
    {
        try (PreparedStatement statement = connectionManager.getConnection().prepareStatement(INSERT_SQL_B))
        {
            statement.setInt        (1, book.getAgent_id() );
            statement.setString     (2, book.getTitle()    );
            try (ResultSet result = statement.executeQuery())
            {
                if (result.next())
                {
                    Integer id = result.getInt("id");
                        if (id !=null)
                        {
                            book.setId(id);
                            for( Page p : book.getPage() )
                                new PagesDaoJdbcImpl().insertPages( book.getId(), p );
                        }
                        else
                        {
                            System.out.println("sql return null for book");
                        }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return book;
    }

    public boolean deleteBook(AgentBook book)
    {
        try (PreparedStatement statement = connectionManager.getConnection().prepareStatement(DELETE_SQL_B))
        {
            statement.setInt(1, book.getId());
            new PagesDaoJdbcImpl().deletePages(book.getId());
            return statement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
