package com.ida.rapportorder.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Ida on 2018-04-25.
 */

public interface StartOrderListInterface {
    LiveData<List<Orders>> getAllOrders();
    LiveData<List<Orders>> getAllOrdersByUserId(String userId);
}
