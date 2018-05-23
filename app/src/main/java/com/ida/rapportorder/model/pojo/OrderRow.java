package com.ida.rapportorder.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class OrderRow implements Parcelable{
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


    protected OrderRow(Parcel in) {
        id = in.readInt();
        startdate = in.readString();
        enddate = in.readString();
        starttime = in.readString();
        endtime = in.readString();
        lunch_break_in_min = in.readInt();
        comment = in.readString();
        extra_equipment = in.readInt();
        extra_equipment_in_min = in.readInt();
        order = in.readParcelable(Order.class.getClassLoader());
    }

    public static final Creator<OrderRow> CREATOR = new Creator<OrderRow>() {
        @Override
        public OrderRow createFromParcel(Parcel in) {
            return new OrderRow(in);
        }

        @Override
        public OrderRow[] newArray(int size) {
            return new OrderRow[size];
        }
    };

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

    public String getShortDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = startdate;
        String[] splited = str.split("-");
        String newString = splited[2];
        return newString;
    }

    public String getShortMonthName(){
        Calendar cal = Calendar.getInstance();
        String month =  new SimpleDateFormat("MMM").format(cal.getTime());
        return month.substring(0, 1).toUpperCase() + month.substring(1);
    }
    public String getShortdayName() {
        String dayOfWeek = "";
        try {
            Date date = new SimpleDateFormat("yyyy-M-d").parse(startdate);
            dayOfWeek = new SimpleDateFormat("EEEE", Locale.getDefault()).format(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1,3);
    }
    public int getDayOfWeek(){
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-M-d").parse(startdate);
        }catch (ParseException e){
            e.printStackTrace();
        }


        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek - 1;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(startdate);
        dest.writeString(enddate);
        dest.writeString(starttime);
        dest.writeString(endtime);
        dest.writeInt(lunch_break_in_min);
        dest.writeString(comment);
        dest.writeInt(extra_equipment);
        dest.writeInt(extra_equipment_in_min);
        dest.writeParcelable(order, flags);
    }
}
