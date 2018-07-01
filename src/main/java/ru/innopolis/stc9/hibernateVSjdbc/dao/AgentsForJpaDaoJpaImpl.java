package ru.innopolis.stc9.hibernateVSjdbc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.innopolis.stc9.hibernateVSjdbc.pojo.AgentBookForJpa;
import ru.innopolis.stc9.hibernateVSjdbc.pojo.AgentForJpa;
import ru.innopolis.stc9.hibernateVSjdbc.pojo.PageForJpa;

import java.util.HashSet;
import java.util.Set;

public class AgentsForJpaDaoJpaImpl
{
    Configuration cfg = new Configuration()
            .configure("hibernate.cfg.xml");

    private SessionFactory sessionFactory=cfg.buildSessionFactory();

    public long insDel(AgentForJpa agent)
    {
        long startTime = System.currentTimeMillis();
        try (Session session = sessionFactory.openSession() )
        {
            Transaction trans = session.beginTransaction();
            session.save    ( agent );

            Set<PageForJpa> pages = new HashSet();

            AgentBookForJpa book = new AgentBookForJpa(agent.getId(), "\"Title: "+ agent.getName()+"\"", pages);
            session.save    ( book );

            PageForJpa p1 = new PageForJpa("text "+1) ;
            p1.setBook( book );
            PageForJpa p2 = new PageForJpa("text "+2) ;
            p2.setBook( book );
            PageForJpa p3 = new PageForJpa("text "+3) ;
            p3.setBook( book );

            pages.add ( p1 );
            pages.add ( p2 );
            pages.add ( p3 );

            book.setPage( pages );

            session.save ( p1 );
            session.save ( p2 );
            session.save ( p3 );

            int id_p1  = p1.getId();
            int id_p2  = p2.getId();
            int id_p3  = p3.getId();

            trans.commit();
            //agent.setBook( book );

            session.close();
        }
        catch(Exception e)
        {
            System.out.println("!!!");
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
        return System.currentTimeMillis() - startTime;
    }
}
