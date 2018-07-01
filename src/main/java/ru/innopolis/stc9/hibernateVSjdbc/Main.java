package ru.innopolis.stc9.hibernateVSjdbc;

import ru.innopolis.stc9.hibernateVSjdbc.dao.*;
import ru.innopolis.stc9.hibernateVSjdbc.pojo.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Test machine - measure time for insert operation by Jdbc vs Hibernate.
 *
 * TOTAL (10_000 | 100_000 agent)
 * - JDBC (by pack)                    :    148 | 1047
 * - JPA (different transactions)      : 26_253 | 280_244
 * - JPA (in one transaction)          :   1128 | 7002
 * - JPA (with relationships in one t) : 40_982 | 341_073
 *
 * @author Victor Bugaenko
 * @since 30.06.2018
 */

public class Main
{
    private static int limit=100_000;
    private static AgentsDaoJdbcImpl      aJdbc  = new AgentsDaoJdbcImpl();
    private static AgentsDaoJpaImpl       aJpa   = new AgentsDaoJpaImpl();
    private static AgentsBookDaoJdbcImpl  bJdbc  = new AgentsBookDaoJdbcImpl();
    private static AgentsForJpaDaoJpaImpl bJpa   = new AgentsForJpaDaoJpaImpl();
    private static AgentsBookDaoJpaImpl   bfJpa  = new AgentsBookDaoJpaImpl();

    public static void main(String[] args)
    {
        //new ClearDaoJdbcImpl().clear(); // drop data in tables with delete some tables
        test();
        //new PrintAllAgentsDaoJdbcImpl().main();
    }

    private static void test()
    {
        System.out.println("\n"+"TOTAL (agent)");
        //System.out.println("JDBC (pack)                  :  " + testJdbc()                     );
        //System.out.println("JPA  (separated transaction) :  " + testJpaSeparatedTransactions() );
        System.out.println("JPA  (one transaction)       :  " + testJpaOneTransactions()       );
        //System.out.println("JDBC - with relationships  :  " + testJdbcWithRelationships()    );
        //System.out.println("JPA  - with relationships    :  " + testJpaWithRelationships()     );
    }

    /**
     * Method not good work with limit > 100 because used new connection with DB each time.
     * that's why has been changed (insert in pack).
     */

    private static long testJdbc()
    {
        Set<Agent> agents = new HashSet<>();
        long time=0;
        for (int i = 1; i < limit; i++)
        {
            Agent agent = new Agent("name"+i, 1000+i-150, new Date());
            agents.add(agent);
            //time += aJdbc.insert(agent);
        }
        time += aJdbc.insert(agents);
        return time;
    }

    private static long testJpaSeparatedTransactions()
    {
        long time=0;
        Set<Agent> agents = new HashSet<>();
        for (int i = 1; i < limit; i++)
        {
            Agent agent = new Agent("name"+i, 1000+i-150, new java.sql.Date(new Date().getTime()));
            time += aJpa.insert(agent);
        }
        return time;
    }

    private static long testJpaOneTransactions()
    {
        long time=0;
        Set<Agent> agents = new HashSet<>();
        for (int i = 1; i < limit; i++)
        {
            Agent agent = new Agent("name"+i, 1000+i-150, new java.sql.Date(new Date().getTime()));
            agents.add(agent);
        }
        time += aJpa.insert(agents);
        return time;
    }

    /**
     * Method not work with limit > 10 because used new connection with DB each time.
     * that's why was ignored.
     */

    public static long testJdbcWithRelationships()
    {
        long startTime = System.currentTimeMillis();
        for (int i = 1; i < limit; i++)
        {
            Agent agent = new Agent("name"+i, 1000+i-150, new java.sql.Date(new Date().getTime()));
            aJdbc.insert( agent );
            AgentBook book = new AgentBook( agent.getId(), "Title: "+ agent.getName() );
            Set<Page> pages = book.getPage();
            pages.add(new Page("text "+i+1));
            pages.add(new Page("text "+i+2));
            pages.add(new Page("text "+i+3));
            book.setPage( pages );
            bJdbc.insertBook( book );
        }
        return System.currentTimeMillis() - startTime;
    }

    private static long testJpaWithRelationships()
    {
        long time=0;
        for (int i = 1; i < limit; i++)
        {
            AgentForJpa agent = new AgentForJpa("name"+i, 1000+i-150, new java.sql.Date(new Date().getTime()));
            time += bJpa.insDel( agent );
        }
        return time;
    }


}
