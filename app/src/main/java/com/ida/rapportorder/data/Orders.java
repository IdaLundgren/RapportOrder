package com.ida.rapportorder.data;

import com.ida.rapportorder.R;

import java.util.ArrayList;
import java.util.Date;
/**
 * POJO
 */

public class Orders {
    private String orderid;
    private String customer;
    private Date created;
    private String created_by;
    private boolean active;
    private String teamid;
    private String vehiclenr;
    private int color_res;

    public Orders(String orderid, String customer, Date created, String created_by, boolean active, String teamid, String vehiclenr, int color_res) {
        this.orderid = orderid;
        this.customer = customer;
        this.created = created;
        this.created_by = created_by;
        this.active = active;
        this.teamid = teamid;
        this.vehiclenr = vehiclenr;
        this.color_res = color_res;
    }

    public Orders() {
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCreated() {
        return created.toString();
    }

    //public void setCreated(String created) {
    //    this.created = created;
    //}

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    public String getVehiclenr() {
        return vehiclenr;
    }

    public void setVehiclenr(String vehiclenr) {
        this.vehiclenr = vehiclenr;
    }

    public int getColor_res() {
        if(color_res == 1){
            return R.drawable.green_drawable;
        }else if(color_res == 2){
            return R.drawable.red_drawable;
        }else if(color_res == 3){
            return R.drawable.blue_drawable;
        }else if(color_res == 4){
            return R.drawable.yellow_drawable;
        }
        return color_res;
    }

    public void setColor_res(int color_res) {
        this.color_res = color_res;
    }
}
