package org.acme.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "joke")
public class Joke implements Serializable {

    @Id
    @Column(name = "joke-id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String text;
    String author;
    @OneToMany(mappedBy = "rating")
    List<Rating> ratingList = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    public Joke() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", rating='" + ratingList.size() + '\'' +
                '}';
    }
}
