package com.ida.rapportorder.model.pojo;

import com.google.gson.annotations.Expose;

public class Vehicle {
    @Expose
    private int id;

    @Expose
    private String name;

    @Expose
    private String type;

    @Expose
    private String added_at;

    @Expose
    private String vehicle_nr;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVehicle_nr() {
        return vehicle_nr;
    }

    public void setVehicle_nr(String vehicle_nr) {
        this.vehicle_nr = vehicle_nr;
    }

    public String getAdded_at() {
        return added_at;
    }

    public void setAdded_at(String added_at) {
        this.added_at = added_at;
    }

    @Override
    public String toString() {
        return name + " " + vehicle_nr.toString();
    }
}
