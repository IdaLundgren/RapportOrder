package com.ida.rapportorder.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ida.rapportorder.R;
import com.ida.rapportorder.model.pojo.IconDrawable;
import com.ida.rapportorder.model.pojo.Order;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StartOrderListAdapter extends RecyclerView.Adapter<StartOrderListAdapter.Holder> {
    private List<Order> mOrders;
    private final ItemClickListener mItemClickListener;

    public StartOrderListAdapter(ItemClickListener itemClickListener){
        mItemClickListener = itemClickListener;
        mOrders = new ArrayList<>();
    }

    @Override
    public StartOrderListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.start_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(StartOrderListAdapter.Holder holder, int position) {
        Order currentItem = mOrders.get(position);
        String title = currentItem.getCustomer_name() + " " + currentItem.getVehicle().getVehicle_nr();
        String created = "Skapad av: " + currentItem.getUser().getFirstname() + " " + currentItem.getUser().getLastname() + ", Månad: " + currentItem.getMonth();
        String userSign = currentItem.getUser().getUserSign();


        IconDrawable avatarDrawable = new IconDrawable();
        holder.mViewColoredCircle.setImageResource(
                avatarDrawable.getUserAvatar(currentItem.getUser())
        );
        holder.mTextViewSign.setText(
               userSign
        );
        holder.mTextViewMessage.setText(
                title
        );
        holder.mTextViewDateAndTime.setText(
                created
        );
        //holder.mProgressBarLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    public void addOrder(Order order){
        mOrders.add(order);
        notifyDataSetChanged();
    }

    public void removeOrder(int position){
        mOrders.remove(position);
        notifyItemRemoved(position);
    }
    public void restoreOrder(Order order, int position){
        mOrders.add(position,order);
        notifyItemInserted(position);
    }
    public Order getSelectedOrder(int position) {
        return mOrders.get(position);
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CircleImageView mViewColoredCircle;
        private TextView mTextViewDateAndTime;
        private TextView mTextViewMessage;
        //private ProgressBar mProgressBarLoading;
        private TextView mTextViewSign;

        public Holder(View itemView) {
            super(itemView);
            mViewColoredCircle = itemView.findViewById(R.id.imv_list_item_circle);
            mTextViewDateAndTime = itemView.findViewById(R.id.lbl_date_and_time);
            mTextViewMessage = itemView.findViewById(R.id.lbl_message);
            //mProgressBarLoading = itemView.findViewById(R.id.pro_load_item_start);
            mTextViewSign = itemView.findViewById(R.id.lbl_author_sign);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onClick(getLayoutPosition());
        }
    }
}
