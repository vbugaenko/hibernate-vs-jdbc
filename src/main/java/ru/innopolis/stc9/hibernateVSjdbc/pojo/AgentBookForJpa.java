package ru.innopolis.stc9.hibernateVSjdbc.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AgentBookForJpa
{
    @Id
    @GeneratedValue
    private int     id;
    //@OneToOne(optional=false),  mappedBy="book")
    private int     agent_id;
    private String  title;
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Set<PageForJpa> pages= new HashSet<PageForJpa>();

    
    public AgentBookForJpa() { }

    public AgentBookForJpa(String title)
    {
        this.title = title;
    }

    public AgentBookForJpa(int agent_id, String title, Set<PageForJpa> pages)
    {
        this.agent_id = agent_id;
        this.title = title;
        this.pages=pages;
    }

    public void addPage(PageForJpa page)
    {
        pages.add( page );
    }

    public Integer getId()                      { return id;       }
    public String  getTitle()                   { return title;    }

    public Set<PageForJpa> getPages()           { return pages;    }
    public void setPage(Set<PageForJpa> pages)  { this.pages = pages;       }

    public int getagent_id()                    { return agent_id; }

    public void setId(int id)                   { this.id = id;             }
    public void setTitle(String title)          { this.title = title;       }

    public void setagent_id(int agent_id)       { this.agent_id = agent_id; }



}
