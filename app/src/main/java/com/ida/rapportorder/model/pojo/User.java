package com.ida.rapportorder.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class User implements Parcelable{
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

    protected User(Parcel in) {
        id = in.readInt();
        firstname = in.readString();
        lastname = in.readString();
        cellphone = in.readString();
        email = in.readString();
        role = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(cellphone);
        dest.writeString(email);
        dest.writeString(role);
    }
}
