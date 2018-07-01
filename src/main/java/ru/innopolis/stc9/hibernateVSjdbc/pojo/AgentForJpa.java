package ru.innopolis.stc9.hibernateVSjdbc.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AgentForJpa
{
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int salary;
    private Date birthday;
    //@OneToOne(optional=false, cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    //private AgentBookForJpa book;

    public AgentForJpa() { }

    public AgentForJpa(String name, int salary, Date birthday)
    {
        this.name = name;
        this.salary = salary;
        this.birthday = birthday;
    }

    public AgentForJpa(int id, String name, int salary, Date birthday)
    {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.birthday = birthday;
    }

    public int      getId() {
        return id;
    }
    public String   getName() {
        return name;
    }
    public int      getSalary() {
        return salary;
    }
    public Date     getBirthday() {
        return birthday;
    }
    //public AgentBookForJpa getBook() { return book; }

    public void     setId(int id) {
        this.id = id;
    }
    public void     setName(String name) {
        this.name = name;
    }
    public void     setSalary(int salary) {
        this.salary = salary;
    }
    public void     setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    //public void     setBook(AgentBookForJpa book) { this.book = book; }
/*
    public AgentBookForJpa getBook() { return book; }

    public void setBook(AgentBookForJpa book)    {
        book.setAgent(this);
        this.book = book;
    }
*/


}
