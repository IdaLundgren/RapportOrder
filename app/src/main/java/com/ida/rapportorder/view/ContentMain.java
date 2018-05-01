package com.ida.rapportorder.view;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ida.rapportorder.R;
import com.ida.rapportorder.data.FakeDataSource;
import com.ida.rapportorder.data.Orders;
import com.ida.rapportorder.data.remote.StartListDataSource;
import com.ida.rapportorder.logic.ContentMainController;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContentMain extends AppCompatActivity implements ViewInterface {
    private static final String EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static final String EXTRA_COLOUR_RESOURCES = "EXTRA_COLOUR_RESOURCES";

    private List<Orders> mListOfData;
    private LayoutInflater mLayoutInflater;
    private RecyclerView mRecyclerView;
    private CustomAdapter mCustomAdapter;
    private TextView mTextViewlblHeader;

    private ContentMainController mController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_main);
        String userRole = "driver";

        mRecyclerView = findViewById(R.id.rec_list_driver_activity_main);
        mTextViewlblHeader = findViewById(R.id.lbl_start_list_header);
        mLayoutInflater = getLayoutInflater();
        mController = new ContentMainController(this, new StartListDataSource());
    }

    @Override
    public void startDetailActivity(String dateAndTime, String message, int colorResource) {
        Intent i = new Intent(this, ListDetailActivity.class);
        i.putExtra(EXTRA_DATE_AND_TIME, dateAndTime);
        i.putExtra(EXTRA_MESSAGE, message);
        i.putExtra(EXTRA_COLOUR_RESOURCES, colorResource);

        startActivity(i);
    }

    @Override
    public void setUpAdapterAndView(List<Orders> listOfData) {
        this.mListOfData = listOfData;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mCustomAdapter = new CustomAdapter();
        mRecyclerView.setAdapter(mCustomAdapter);

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
    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomerViewHolder>{
        @Override
        public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = mLayoutInflater.inflate(R.layout.start_list_item, parent, false);

            return new CustomerViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomerViewHolder holder, int position) {
            Orders currentItem = mListOfData.get(position);
            holder.mViewColoredCircle.setImageResource(
                    currentItem.getColor_res()
            );
            holder.mTextViewmessage.setText(
                    currentItem.getCustomer()
            );
            holder.mTextViewDateAndTime.setText(
                    currentItem.getCreated()
            );
            holder.mProgressBarLoading.setVisibility(mRecyclerView.INVISIBLE);
        }

        @Override
        public int getItemCount() {
            return mListOfData.size();
        }

        class CustomerViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
            private CircleImageView mViewColoredCircle;
            private TextView mTextViewDateAndTime;
            private TextView mTextViewmessage;
            private ViewGroup mViewGroupContainer;
            private ProgressBar mProgressBarLoading;

            public CustomerViewHolder(View itemView) {
                super(itemView);

                this.mViewColoredCircle = itemView.findViewById(R.id.imv_list_item_circle);
                this.mTextViewDateAndTime = itemView.findViewById(R.id.lbl_date_and_time);
                this.mTextViewmessage = itemView.findViewById(R.id.lbl_message);
                this.mViewGroupContainer = itemView.findViewById(R.id.root_home_list_item);
                this.mProgressBarLoading = itemView.findViewById(R.id.pro_load_item_start);

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
