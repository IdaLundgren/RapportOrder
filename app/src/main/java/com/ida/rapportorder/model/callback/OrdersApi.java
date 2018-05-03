package com.ida.rapportorder.model.callback;

import com.ida.rapportorder.model.pojo.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OrdersApi {

    @GET("/orders_getAll.php")
    Call<List<Order>>getAllOrders();
}
