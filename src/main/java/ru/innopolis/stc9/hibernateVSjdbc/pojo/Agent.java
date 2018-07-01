package ru.innopolis.stc9.hibernateVSjdbc.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Agent
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Integer id;
    private String  name;
    private int     salary;
    private Date    birthday;

    public Agent() { }

    public Agent(String name, int salary, Date birthday)
    {
        this.name = name;
        this.salary = salary;
        this.birthday = birthday;
    }

    public Agent(int id, String name, int salary, Date birthday)
    {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.birthday = birthday;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setName(String name) {
        this.name = name;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
