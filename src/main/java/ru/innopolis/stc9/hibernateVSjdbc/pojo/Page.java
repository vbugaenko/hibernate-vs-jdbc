package ru.innopolis.stc9.hibernateVSjdbc.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

public class Page
{
    @Id
    @GeneratedValue
    int id;
    int book_id;
    String text;


    public Page() {
    }

    public Page(String text) {
        this.text = text;
    }

    public Page(int book_id, String text) {
        this.book_id = book_id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return id == page.id &&
                Objects.equals(text, page.text);
    }

    public int hashCode() {

        return Objects.hash(id, text);
    }
}
