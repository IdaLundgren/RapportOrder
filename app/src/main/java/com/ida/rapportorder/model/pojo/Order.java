package com.ida.rapportorder.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ida.rapportorder.R;

import java.sql.Struct;
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

    @SerializedName("message_to_employer")
    @Expose
    private String message_to_employer;

    @SerializedName("price_per_hour")
    @Expose
    private double price_per_hour;

    @SerializedName("price_per_extra")
    @Expose
    private double price_per_extra;

    @SerializedName("customer_name")
    @Expose
    private String customer_name;

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

    public String getMessage_to_employer() {
        return message_to_employer;
    }

    public void setMessage_to_employer(String message_to_employer) {
        this.message_to_employer = message_to_employer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getPrice_per_hour() {
        return price_per_hour;
    }

    public void setPrice_per_hour(double price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    public double getPrice_per_extra() {
        return price_per_extra;
    }

    public void setPrice_per_extra(double price_per_extra) {
        this.price_per_extra = price_per_extra;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
}
