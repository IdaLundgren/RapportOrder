package com.ida.rapportorder.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ida.rapportorder.R;

import java.sql.Struct;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * POJO
 */

public class Order implements Parcelable{
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("active")
    @Expose
    private int active = 0;

    @SerializedName("approved")
    @Expose
    private int approved = 1;

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

    @SerializedName("start_date")
    @Expose
    private String start_date;

    public Order() {
    }

    protected Order(Parcel in) {
        id = in.readInt();
        active = in.readInt();
        approved = in.readInt();
        created_at = in.readString();
        message_to_employer = in.readString();
        price_per_hour = in.readDouble();
        price_per_extra = in.readDouble();
        customer_name = in.readString();
        start_date = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getSetCreatedDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date).toString();
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

    public String getStart_date() {
        String str = start_date;
        String[] splited = str.split("\\s+");
        start_date = splited[0];
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
