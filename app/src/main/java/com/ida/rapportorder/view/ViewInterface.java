package com.ida.rapportorder.view;


import com.ida.rapportorder.data.Orders;

import java.util.List;

public interface ViewInterface {
    void startDetailActivity(String dateAndTime, String message, int colorResource);
    void setUpAdapterAndView(List<Orders> listOfData);
}
