package org.launchcode.springfilterbasedauth.models.forms;

import javax.validation.constraints.NotNull;

public class CreateForm {

    @NotNull
    private String title;

    @NotNull
    private String details;


    public CreateForm() {}



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
