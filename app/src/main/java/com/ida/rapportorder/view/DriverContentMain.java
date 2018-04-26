package com.ida.rapportorder.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ida.rapportorder.R;
import com.ida.rapportorder.data.FakeDataSource;
import com.ida.rapportorder.data.Orders;
import com.ida.rapportorder.logic.DriverContentMainController;

import java.util.List;

public class DriverContentMain extends AppCompatActivity implements ViewInterface {
    private static final String EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static final String EXTRA_COLOUR_RESOURCES = "EXTRA_COLOUR_RESOURCES";

    private List<Orders> mListOfData;
    private LayoutInflater mLayoutInflater;
    private RecyclerView mRecyclerView;
    private CustomAdapter mCustomAdapter;

    private DriverContentMainController mController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_content_main);

        mRecyclerView = findViewById(R.id.rec_list_driver_activity_main);
        mLayoutInflater = getLayoutInflater();
        mController = new DriverContentMainController(this, new FakeDataSource());
    }

    @Override
    public void startDetailActivity(String dateAndTime, String message, int colorResource) {
        Intent i = new Intent(this, DriverListDetailActivity.class);
        i.putExtra(EXTRA_DATE_AND_TIME, dateAndTime);
        i.putExtra(EXTRA_MESSAGE, message);
        i.putExtra(EXTRA_COLOUR_RESOURCES, colorResource);

        startActivity(i);
    }

    @Override
    public void setUpAdapterAndView(List<Orders> listOfData) {
        this.mListOfData = listOfData;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCustomAdapter = new CustomAdapter();
        mRecyclerView.setAdapter(mCustomAdapter);
    }
    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomerViewHolder>{
        @Override
        public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = mLayoutInflater.inflate(R.layout.start_list_item_driver, parent, false);

            return new CustomerViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomerViewHolder holder, int position) {
            Orders currentItem = mListOfData.get(position);
            holder.mViewColoredCircle.setBackgroundResource(
                    currentItem.getColorResource()
            );
            holder.mTextViewmessage.setText(
                    currentItem.getMessage()
            );
            holder.mTextViewDateAndTime.setText(
                    currentItem.getDateAndTime()
            );
        }

        @Override
        public int getItemCount() {
            return mListOfData.size();
        }

        class CustomerViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
            private View mViewColoredCircle;
            private TextView mTextViewDateAndTime;
            private TextView mTextViewmessage;
            private ViewGroup mViewGroupContainer;

            public CustomerViewHolder(View itemView) {
                super(itemView);

                this.mViewColoredCircle = itemView.findViewById(R.id.imv_list_item_circle);
                this.mTextViewDateAndTime = itemView.findViewById(R.id.lbl_date_and_time);
                this.mTextViewmessage = itemView.findViewById(R.id.lbl_message);
                this.mViewGroupContainer = itemView.findViewById(R.id.root_home_list_item);

                this.mViewGroupContainer.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Orders orderItem = mListOfData.get(
                        this.getAdapterPosition()
                );

                mController.onListItemClick(
                        orderItem
                );
            }
        }
    }
}
