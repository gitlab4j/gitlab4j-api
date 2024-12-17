package org.gitlab4j.api.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Email implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the email.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The email address.
     */
    @JsonProperty("email")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
