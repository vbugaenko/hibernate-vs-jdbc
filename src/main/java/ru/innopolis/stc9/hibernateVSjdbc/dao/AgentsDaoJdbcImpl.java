package ru.innopolis.stc9.hibernateVSjdbc.dao;

import ru.innopolis.stc9.hibernateVSjdbc.pojo.Agent;
import ru.innopolis.stc9.hibernateVSjdbc.connections.ConnectionManager;
import ru.innopolis.stc9.hibernateVSjdbc.connections.PoolConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;

public class AgentsDaoJdbcImpl {
    private ConnectionManager connection = new PoolConnection();
    private static final String INSERT_SQL = "INSERT INTO agent(name, salary, birthday) VALUES (?, ?, ?) RETURNING id;";
    private static final String DELETE_SQL = "DELETE FROM agent WHERE id = ?;";

    public long insert(Agent agent) {

        long startTime = System.currentTimeMillis();
        try (PreparedStatement statement = connection.getConnection().prepareStatement(INSERT_SQL)) {
            statement.setString(1, agent.getName());
            statement.setInt(2, agent.getSalary());
            statement.setDate(3, new java.sql.Date(agent.getBirthday().getTime()));
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Integer id = result.getInt("id");
                    if (id != null) {
                        agent.setId(result.getInt("id"));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return System.currentTimeMillis() - startTime;
    }

    public long insert(Set<Agent> agents) {
        long startTime = System.currentTimeMillis();
        final String AGENTS_INSERT_Template = "INSERT INTO agent(name, salary, birthday) VALUES ";
        StringBuilder str = new StringBuilder();
        str.append(AGENTS_INSERT_Template);
        for (Agent a : agents)
        {
            str.append("(");
            str.append("'");
            str.append(a.getName());
            str.append("'");
            str.append(", ");
            str.append(a.getSalary());
            str.append(", ");
            str.append("'");
            str.append(new java.sql.Date(a.getBirthday().getTime()));
            str.append(" +10'), ");
        }
        str.setLength(str.length() - 2);
        str.append(";");
        try (PreparedStatement statement = connection.getConnection().prepareStatement(str.toString()) )
        {
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return System.currentTimeMillis() - startTime;
    }

}
