package org.launchcode.springfilterbasedauth.models.forms;

import org.launchcode.springfilterbasedauth.models.Category;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Opportunity {

    @Id
    @GeneratedValue
    private int id;

    @NotNull (message = "This cannot be empty")
    private String title;

    @NotNull (message = "This cannot be empty")
    private String description;

    @NotNull (message = "This cannot be empty")
    private String location;

    private String author;

    @ManyToOne
    private Category category;

    public Opportunity(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
