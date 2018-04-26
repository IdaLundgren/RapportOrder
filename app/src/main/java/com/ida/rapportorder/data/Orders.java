package com.ida.rapportorder.data;

import java.util.ArrayList;

/**
 * POJO
 */

public class Orders {
    private String orderid;
    private ArrayList<String> customer;
    private String created;
    private String created_by;
    private boolean active;
    private String teamid;
    private int colorResource;
    private String dateAndTime;
    private String message;

    public Orders(int colorResource, String dateAndTime, String message) {
        this.colorResource = colorResource;
        this.dateAndTime = dateAndTime;
        this.message = message;
    }

    public int getColorResource() {
        return colorResource;
    }

    public void setColorResource(int colorResource) {
        this.colorResource = colorResource;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
