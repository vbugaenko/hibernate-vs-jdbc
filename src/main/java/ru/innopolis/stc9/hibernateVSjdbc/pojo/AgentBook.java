package ru.innopolis.stc9.hibernateVSjdbc.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;


public class AgentBook
{
    @Id
    int id;
    int agent_id;
    String title;
    Set<Page> page= new HashSet<>();

    public AgentBook() {
    }

    public AgentBook(int agent_id, String title) {
        this.agent_id = agent_id;
        this.title = title;
    }

    public int getId()          { return id;        }
    public int getAgent_id()    { return agent_id;  }
    public String getTitle()    { return title;     }
    public Set<Page> getPage()  { return page;      }

    public void setId(int id)             { this.id = id;             }
    public void setAgent_id(int agent_id) {
        this.agent_id = agent_id;
    }
    public void setTitle(String title)    { this.title = title;       }
    public void setPage(Set<Page> page)   { this.page = page;         }

}
