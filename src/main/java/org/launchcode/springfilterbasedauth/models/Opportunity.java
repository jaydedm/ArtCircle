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

    @ManyToOne
    private User user;

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

    public User getUid() {
        return user;
    }

    public void setUid(User user) {
        this.user = user;
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
