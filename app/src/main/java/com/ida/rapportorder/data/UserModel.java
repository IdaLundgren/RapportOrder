package com.ida.rapportorder.data;

import java.io.Serializable;

public class UserModel {
    private String firstname;
    private String lastname;
    private String cellphone;
    private String email;
    private String uid;
    private String role = "driver";

    public UserModel() {

    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }

    public String getRole() {
        return role;
    }
}
