package com.ida.rapportorder.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ida.rapportorder.model.callback.OrderService;
import com.ida.rapportorder.model.helper.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestManager {

    private OrderService mOrderService;
    public OrderService getOrderFromApi(){
        if(mOrderService == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HHTP.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            mOrderService = retrofit.create(OrderService.class);
        }
        return mOrderService;
    }
}
