package com.ida.rapportorder.view;


import com.ida.rapportorder.model.pojo.Order;

import java.util.List;

public interface ViewInterface {
    void startDetailActivity(String dateAndTime, String message, int colorResource);
    void setUpAdapterAndView(List<Order> listOfData);
}
