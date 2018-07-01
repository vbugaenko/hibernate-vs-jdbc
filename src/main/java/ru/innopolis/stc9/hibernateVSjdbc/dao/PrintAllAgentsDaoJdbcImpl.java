package ru.innopolis.stc9.hibernateVSjdbc.dao;

import ru.innopolis.stc9.hibernateVSjdbc.Main;
import ru.innopolis.stc9.hibernateVSjdbc.connections.ConnectionManager;
import ru.innopolis.stc9.hibernateVSjdbc.connections.ConnectionManagerJDBCimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PrintAllAgentsDaoJdbcImpl {

    private static ConnectionManager connectionManager = ConnectionManagerJDBCimpl.getInstance();

    public static void main() {

        System.out.println("Agents:");
        printAllagents();
        System.out.println("Books: ");
        printAllBooks();
        System.out.println("Pages: ");
        printAllPages();
        System.out.println("Agents JPA:");
        printAllagentsJpa();
        System.out.println("Books JPA: ");
        printAllBooksJpa();
        System.out.println("Pages JPA: ");
        printAllPagesJpa();
    }

    public static void printAllagents() {

        try (
                Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM agent;");
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                {
                    System.out.println(
                            resultSet.getString(1)
                                    + " " + resultSet.getString(2)
                                    + " " + resultSet.getString(3)
                                    + " " + resultSet.getString(4)
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    private static void printAllBooks()
    {
        try (
                Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM agent;");
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                {
                    System.out.println(
                            resultSet.getString(1)
                                    + " " + resultSet.getString(2)
                                    + " " + resultSet.getString(3)
                            //+ " " + resultSet.getString(4)
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void printAllPages()
    {
        try (
                Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM page;");
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                {
                    System.out.println(
                            resultSet.getString(1)
                                    + " " + resultSet.getString(2)
                                    + " " + resultSet.getString(3)
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    public static void printAllagentsJpa() {

        try (
                Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM agentforjpa;");
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                {
                    System.out.println(
                            resultSet.getString(1)
                            + " " + resultSet.getString(2)
                            + " " + resultSet.getString(3)
                            + " " + resultSet.getString(4)
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    private static void printAllBooksJpa()
    {
        try (
                Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM agentbookforjpa;");
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                {
                    System.out.println(
                            resultSet.getString(1)
                            + " " + resultSet.getString(2)
                            + " " + resultSet.getString(3)
                            //+ " " + resultSet.getString(4)
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void printAllPagesJpa()
    {
        try (
                Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM pageforjpa;");
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                {
                    System.out.println(
                            resultSet.getString(1)
                            + " " + resultSet.getString(2)
                            + " " + resultSet.getString(3)
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
