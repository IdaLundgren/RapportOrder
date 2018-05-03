package com.ida.rapportorder.model.pojo;


import com.google.gson.annotations.Expose;

public class Customer {
    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private String adress;
    @Expose
    private String zipcode;
    @Expose
    private String state;
    @Expose
    private String contact_name;
    @Expose
    private String contact_phone_nr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_phone_nr() {
        return contact_phone_nr;
    }

    public void setContact_phone_nr(String contact_phone_nr) {
        this.contact_phone_nr = contact_phone_nr;
    }
}
