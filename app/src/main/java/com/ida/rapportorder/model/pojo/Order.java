package com.ida.rapportorder.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ida.rapportorder.R;

import java.util.Date;
/**
 * POJO
 */

public class Order {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("active")
    @Expose
    private int active;

    @SerializedName("approved")
    @Expose
    private int approved;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("message_to_customer")
    @Expose
    private String message_to_customer;

    @SerializedName("message_to_employer")
    @Expose
    private String message_to_employer;

    @SerializedName("customer")
    @Expose
    private Customer customer;

    @SerializedName("vehicle")
    @Expose
    private Vehicle vehicle;

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public String getCreated_at() {
        String str = created_at;
        String[] splited = str.split("\\s+");
        created_at = splited[0];
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage_to_customer() {
        return message_to_customer;
    }

    public void setMessage_to_customer(String message_to_customer) {
        this.message_to_customer = message_to_customer;
    }

    public String getMessage_to_employer() {
        return message_to_employer;
    }

    public void setMessage_to_employer(String message_to_employer) {
        this.message_to_employer = message_to_employer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
