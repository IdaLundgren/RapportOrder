package com.ida.rapportorder.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ida.rapportorder.model.callback.OrdersApi;
import com.ida.rapportorder.model.helper.Constants;
import com.ida.rapportorder.model.pojo.Order;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestManager {

    private OrdersApi mOrdersApi;
    public OrdersApi getOrderFromApi(){
        if(mOrdersApi == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HHTP.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            mOrdersApi = retrofit.create(OrdersApi.class);
        }
        return mOrdersApi;
    }
}
