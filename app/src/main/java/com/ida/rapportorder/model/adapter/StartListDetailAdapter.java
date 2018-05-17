package com.ida.rapportorder.model.adapter;


import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.ida.rapportorder.R;
import com.ida.rapportorder.model.pojo.Order;
import com.ida.rapportorder.model.pojo.OrderRow;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StartListDetailAdapter extends RecyclerView.Adapter<StartListDetailAdapter.Holder> {
    private List<OrderRow> mOrderRowsList;
    SparseBooleanArray expandState = new SparseBooleanArray();

    public StartListDetailAdapter() {
        mOrderRowsList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_detail_item_without_child, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        OrderRow orderRow = mOrderRowsList.get(position);
        holder.mTextViewDateDetail.setText(
                "12"
        );
        holder.mTextViewDayDetail.setText(
                "Månd"
        );
        holder.mTextViewDesc.setText(
                orderRow.getComment()
        );
        holder.mTextViewTime.setText(
                "Utförd tid: 9 timmar"
        );
    }

    @Override
    public int getItemCount() {
        return mOrderRowsList.size();
    }

    public void addOrderRow(OrderRow orderRow){
        mOrderRowsList.add(orderRow);
        notifyDataSetChanged();
    }
    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewDateDetail;
        private TextView mTextViewDayDetail;
        private TextView mTextViewDesc;
        private TextView mTextViewTime;
        private View mViewLeftImage;
        private View mViewVerticalDivider;
        private View mViewIconClock;

        public Holder(View itemView) {
            super(itemView);
            mTextViewDateDetail = itemView.findViewById(R.id.lbl_date_list_detail);
            mTextViewDayDetail = itemView.findViewById(R.id.lbl_date_day_list_detail);
            mTextViewDesc = itemView.findViewById(R.id.lbl_order_list_detial_item_desc);
            mTextViewTime = itemView.findViewById(R.id.lbl_order_list_detial_item_time);
            mViewLeftImage = itemView.findViewById(R.id.list_decor_left);
            mViewVerticalDivider = itemView.findViewById(R.id.order_list_detail_item_divider);
            mViewIconClock = itemView.findViewById(R.id.icon_order_list_item_clock);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
