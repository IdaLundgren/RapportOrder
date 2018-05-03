package com.ida.rapportorder.model.callback;

import com.ida.rapportorder.model.pojo.Order;

import java.util.List;

/**
 * Created by Ida on 2018-05-03.
 */

public interface OrderFetchListener {
    void onDeliverAllOrders(List<Order> orders);
    void onDeliverOrder (Order order);
    void onHideDialog();
}
