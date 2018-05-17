package com.ida.rapportorder.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderRow {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("startdate")
    @Expose
    private String startdate;

    @SerializedName("enddate")
    @Expose
    private String enddate;

    @SerializedName("starttime")
    @Expose
    private String starttime;

    @SerializedName("endtime")
    @Expose
    private String endtime;

    @SerializedName("lunch_break_in_min")
    @Expose
    private int lunch_break_in_min;

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("extra_equipment")
    @Expose
    private int extra_equipment;

    @SerializedName("extra_equipment_in_min")
    @Expose
    private int extra_equipment_in_min;

    @SerializedName("order")
    @Expose
    private Order order;


    public int getId() {
        return id;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public String getStarttime() {
        return starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public int getLunch_break_in_min() {
        return lunch_break_in_min;
    }

    public String getComment() {
        return comment;
    }

    public int getExtra_equipment() {
        return extra_equipment;
    }

    public int getExtra_equipment_in_min() {
        return extra_equipment_in_min;
    }

    public Order getOrder() {
        return order;
    }
}
