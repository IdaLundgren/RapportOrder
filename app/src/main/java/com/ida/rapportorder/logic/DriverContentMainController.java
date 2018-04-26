package com.ida.rapportorder.logic;


import com.ida.rapportorder.data.DataSourceInterface;
import com.ida.rapportorder.data.Orders;
import com.ida.rapportorder.view.ViewInterface;

public class DriverContentMainController {

    private ViewInterface view;
    private DataSourceInterface dataSource;

    public DriverContentMainController(ViewInterface view, DataSourceInterface dataSource) {
        this.view = view;
        this.dataSource = dataSource;

        getListFromDataSource();
    }
    public void getListFromDataSource(){
        view.setUpAdapterAndView(
                dataSource.getListOfData()
        );
    }
    public void onListItemClick(Orders order){
        view.startDetailActivity(
                order.getDateAndTime(),
                order.getMessage(),
                order.getColorResource()
        );
    }
}
