package com.ida.rapportorder.model.callback;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ida.rapportorder.model.pojo.Order;
import com.ida.rapportorder.model.pojo.User;
import com.ida.rapportorder.model.pojo.Vehicle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OrderService {

    @GET("orders")
    Call<List<Order>>getAllOrders();

    @FormUrlEncoded
    @POST("/orders_createOrder.php")
    Call<Order> insertOrder (
            @Field("created_at") String created_at,
            @Field("created_by") String uid,
            @Field("message_to_employer") String message_to_employer,
            @Field("price_per_hour") String price_per_hour,
            @Field("price_per_extra") String price_per_extra,
            @Field("customer_name") String customer_name,
            @Field("vehicle_id") String vid
    );

    @GET("/vehicle_getAll.php")
    Call<List<Vehicle>> getAllVehicles();
}
