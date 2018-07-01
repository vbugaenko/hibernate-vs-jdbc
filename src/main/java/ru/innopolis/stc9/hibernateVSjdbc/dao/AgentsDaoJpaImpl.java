package ru.innopolis.stc9.hibernateVSjdbc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.innopolis.stc9.hibernateVSjdbc.pojo.Agent;

import java.util.Set;

public class AgentsDaoJpaImpl
{
    Configuration cfg = new Configuration()
            .configure("hibernate.cfg.xml");

    private SessionFactory sessionFactory=cfg.buildSessionFactory();

    public long insert(Agent agent)
    {
        long startTime = System.currentTimeMillis();
        try (Session session = sessionFactory.getCurrentSession())
        {
            Transaction trans = session.beginTransaction();
            session.save(agent);
            trans.commit();
            session.close();
        }
        catch(Exception e){e.printStackTrace();}
        return System.currentTimeMillis() - startTime;
    }

    public long delete(Agent agent)
    {
        long startTime = System.currentTimeMillis();
        try (Session session = sessionFactory.getCurrentSession())
        {
            Transaction trans = session.beginTransaction();
            session.delete(agent);
            trans.commit();
            session.close();
        }
        catch(Exception e){e.printStackTrace();}
        return System.currentTimeMillis() - startTime;
    }

    public long insDel(Agent agent)
    {
        long startTime = System.currentTimeMillis();
        try (Session session = sessionFactory.getCurrentSession())
        {
            Transaction trans = session.beginTransaction();
            session.save(agent);
            session.delete(agent);
            trans.commit();
            session.close();
        }
        catch(Exception e){e.printStackTrace();}
        return System.currentTimeMillis() - startTime;
    }

    public long insert(Set<Agent> agents)
    {
        long startTime = System.currentTimeMillis();
        try (Session session = sessionFactory.getCurrentSession())
        {
            Transaction trans = session.beginTransaction();
            for (Agent a : agents)
            session.save(a);
            trans.commit();
            session.close();
        }
        catch(Exception e){e.printStackTrace();}
        return System.currentTimeMillis() - startTime;
    }
}
