package org.launchcode.springfilterbasedauth.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Opportunity {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String location;

    @NotNull
    private String author;

    @ManyToOne
    private Category category;

    public Opportunity(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Opportunity() {}

    public int getId() { return id; }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
