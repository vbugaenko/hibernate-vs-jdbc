package ru.innopolis.stc9.hibernateVSjdbc.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PageForJpa
{
    @Id
    @GeneratedValue
    int id;
    @ManyToOne(optional=false, cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    AgentBookForJpa book;
    String text;

    public PageForJpa() { }

    public PageForJpa(String text) {
        this.text = text;
    }

    public PageForJpa(AgentBookForJpa book, String text) {
        this.book = book;
        this.text = text;
    }

    public int getId()      { return id;      }
    public String getText() { return text;    }

    public AgentBookForJpa getBook() {  return book; }

    public void setId(int id)                   { this.id = id;           }
    public void setText(String text)            { this.text = text;       }
    public void setBook(AgentBookForJpa book)   { this.book = book; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageForJpa that = (PageForJpa) o;
        return id == that.id &&
                Objects.equals(book, that.book) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, book, text);
    }
}
