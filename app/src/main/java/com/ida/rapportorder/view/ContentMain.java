package com.ida.rapportorder.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.ida.rapportorder.R;
import com.ida.rapportorder.controller.RestManager;
import com.ida.rapportorder.model.adapter.ItemClickListener;
import com.ida.rapportorder.model.adapter.StartOrderListAdapter;
import com.ida.rapportorder.model.pojo.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentMain extends AppCompatActivity implements ItemClickListener{
    private StartOrderListAdapter mStartOrderListAdapter;
    private List<Order> mListOfData;
    private LayoutInflater mLayoutInflater;
    private RecyclerView mRecyclerView;
    private TextView mTextViewlblHeader;
    private RestManager mRestManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_main);
        setUpViewAndAdapter();

        mTextViewlblHeader = findViewById(R.id.lbl_start_list_header);
        mLayoutInflater = getLayoutInflater();
        mRestManager = new RestManager();
        Call<List<Order>> listcall = mRestManager.getOrderFromApi().getAllOrders();
        listcall.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if(response.isSuccessful()){
                    List<Order> orderList = response.body();

                    for (int i = 0; i < orderList.size(); i++) {
                        Order order = orderList.get(i);
                        mStartOrderListAdapter.addOrder(order);
                    }
                }else{
                    Log.d("Lista", "funkar inte!" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("Lista", t.toString());
            }
        });
    }
    private void setUpViewAndAdapter(){
        mRecyclerView = findViewById(R.id.rec_list_driver_activity_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mStartOrderListAdapter = new StartOrderListAdapter(this);
        mRecyclerView.setAdapter(mStartOrderListAdapter);
        DividerItemDecoration dividerItem = new DividerItemDecoration(
                mRecyclerView.getContext(),
                layoutManager.getOrientation()
        );

        dividerItem.setDrawable(ContextCompat.getDrawable(
                ContentMain.this,
                R.drawable.divider_grey
        ));

        mRecyclerView.addItemDecoration(
                dividerItem
        );
    }

    @Override
    public void onClick(int position) {
        Order selectedOrder = mStartOrderListAdapter.getSelectedOrder(position);
        int orderId = selectedOrder.getId();

    }
}
