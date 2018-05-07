package com.ida.rapportorder.model.pojo;

import com.google.gson.annotations.Expose;

public class User {
    @Expose
    private int id;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String cellphone;
    @Expose
    private String email;
    @Expose
    private String role;

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserSign(){
        String sign = Character.toString(firstname.charAt(0));
        sign += Character.toString(lastname.charAt(0));
        return sign;
    }
}
