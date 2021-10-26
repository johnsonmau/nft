package com.asset.tracker.nft.model;

import javax.validation.constraints.*;

public class UserRegistrationDto {

    @NotEmpty(message = "Required")
    private String firstName;

    @NotEmpty(message = "Required")
    private String lastName;

    @Size(min = 8, message = "Must be a minimum of 8 characters long")
    private String password;

    @NotEmpty(message = "Required")
    private String confirmPassword;

    @NotEmpty(message = "Required")
    private String username;

    @NotEmpty(message = "Required")
    private String email;

    @NotEmpty(message = "Required")
    private String confirmEmail;

    private Boolean terms;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
