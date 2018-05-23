package com.ida.rapportorder.model.callback;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ida.rapportorder.model.pojo.Order;
import com.ida.rapportorder.model.pojo.OrderRow;
import com.ida.rapportorder.model.pojo.Result;
import com.ida.rapportorder.model.pojo.User;
import com.ida.rapportorder.model.pojo.Vehicle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderService {

    @GET("orders")
    Call<List<Order>>getAllOrders();

    @FormUrlEncoded
    @POST("create_order")
    Call<Result> insertOrder (
            @Field("created_at") String created_at,
            @Field("created_by") String uid,
            @Field("message_to_employer") String message_to_employer,
            @Field("price_per_hour") String price_per_hour,
            @Field("price_per_extra") String price_per_extra,
            @Field("customer_name") String customer_name,
            @Field("vehicle_id") String vid,
            @Field("start_date") String start_date
    );

    @GET("vehicles")
    Call<List<Vehicle>> getAllVehicles();

    @GET("orderrows/{id}")
    Call<List<OrderRow>> getOrderRows(@Path("id") int id);

    @FormUrlEncoded
    @POST("login")
    Call<Result> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("create_orderrow")
    Call<Result> inserOrderrow(
            @Field("startdate") String startdate,
            @Field("enddate") String enddate,
            @Field("starttime") String starttime,
            @Field("endtime") String endtime,
            @Field("lunch_break_in_min") String lunchBreak,
            @Field("comment") String comment,
            @Field("extra_equipment") String extra,
            @Field("extra_equipment_in_min") String extra_time,
            @Field("order_id") String order_id
    );
}
