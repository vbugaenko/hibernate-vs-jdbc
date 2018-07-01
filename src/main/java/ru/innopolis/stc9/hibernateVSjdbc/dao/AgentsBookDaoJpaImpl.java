package ru.innopolis.stc9.hibernateVSjdbc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.innopolis.stc9.hibernateVSjdbc.pojo.AgentBookForJpa;

public class AgentsBookDaoJpaImpl
{
    Configuration cfg = new Configuration()
            .configure("hibernate.cfg.xml");

    private SessionFactory sessionFactory=cfg.buildSessionFactory();

    public void insDel(AgentBookForJpa book)
    {
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transB = session.beginTransaction();
            session.save    ( book );
            transB.commit();
            session.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
