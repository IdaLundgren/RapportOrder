package com.ida.rapportorder.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;
import android.app.AlertDialog.Builder;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.ida.rapportorder.R;
import com.ida.rapportorder.controller.RestManager;
import com.ida.rapportorder.model.adapter.ItemClickListener;
import com.ida.rapportorder.model.adapter.StartListDetailAdapter;
import com.ida.rapportorder.model.pojo.Order;
import com.ida.rapportorder.model.pojo.OrderRow;
import com.ida.rapportorder.model.pojo.User;
import com.ida.rapportorder.viewmodel.StartListDetailViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class StartDetailFragment extends Fragment implements ItemClickListener {
    public TextView mTextViewCustomerName;
    public TextView mTextViewDetailCustomerName;
    public TextView mTextViewVehicle;
    public TextView mTextViewDate;
    public TextView mTextViewDriver;
    public TextView mTextViewMessage;
    public RelativeLayout mButton;

    public TextView mTextViewDialogCustomerName;
    public ExpandableRelativeLayout mExpandableLayout;
    private FloatingActionButton mFloatingActionButton;

    private StartListDetailAdapter mStartListDetailAdapter;
    private List<OrderRow> mListOfData;
    private LayoutInflater mLayoutInflater;
    private RecyclerView mRecyclerView;
    private RestManager mRestManager;
    private android.support.v7.widget.Toolbar mToolbar;

    private List<OrderRow> mOrderRowsList;

    private StartListDetailViewModel mStartListDetailViewModel;
    private static final String FRAGMENT_TAG = "create_workday";
    private static final String KEY_ORDER = "order";

    private Order mOrder;
    private User mUser;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBundleParcable();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_list_detail_fragment, container, false);
        setUpViewAndAdapter(view);
        mOrder = new Order();
        mUser = new User();
        mToolbar = view.findViewById(R.id.toolbar_order_list_detail);
        setMenu();
        getBundleParcable();
        toggleCustomerInfo(view);
        setUI(mOrder);
        mOrderRowsList = new ArrayList<>();
        mLayoutInflater = getLayoutInflater();
        mRestManager = new RestManager();
        mFloatingActionButton = view.findViewById(R.id.floatingActionButton_create_workorder);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CreateWorkDayFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("order", mOrder);
                bundle.putParcelable("user", mUser);
                fragment.setArguments(bundle);
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.base_container, fragment)
                        .addToBackStack(FRAGMENT_TAG)
                        .commit();
            }
        });
        mStartListDetailViewModel = ViewModelProviders.of(getActivity()).get(StartListDetailViewModel.class);
        mStartListDetailViewModel.getOrderrows(mOrder.getId()).observe(this, new Observer<List<OrderRow>>() {
            @Override
            public void onChanged(@Nullable List<OrderRow> orderRows) {
                mOrderRowsList = orderRows;
                for (int i = 0; i < orderRows.size(); i++) {
                    OrderRow orderRow = orderRows.get(i);
                    mStartListDetailAdapter.addOrderRow(orderRow);
                }
            }
        });
        return view;
    }

    private void getBundleParcable(){
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mOrder = bundle.getParcelable(KEY_ORDER);
            mUser = bundle.getParcelable("user");
        }
    }
    private void setMenu(){
        ((BaseActivity) getActivity()).setSupportActionBar(mToolbar);
        setHasOptionsMenu(true);
    }
    private void toggleCustomerInfo(View view){
        mTextViewCustomerName = view.findViewById(R.id.txtview_order_list_customer_header);
        mTextViewDetailCustomerName = view.findViewById(R.id.lbl_order_list_detail_item_customer_nme);
        mTextViewVehicle = view.findViewById(R.id.lbl_order_list_detail_item_vehicle);
        mTextViewDate = view.findViewById(R.id.lbl_order_list_detail_item_created);
        mTextViewDriver = view.findViewById(R.id.lbl_order_list_detail_item_driver);
        mTextViewMessage = view.findViewById(R.id.lbl_order_list_detail_item_message);
        //Buttons
        mButton = view.findViewById(R.id.order_list_detail_button);

        //Layout
        mExpandableLayout = view.findViewById(R.id.FUNKA);
        mExpandableLayout.collapse();

        //Dialog
        mTextViewDialogCustomerName = view.findViewById(R.id.dialog_customer_name);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandableLayout.toggle();
            }
        });
    }
    private void setUI(Order order){
        String topTitle = order.getCustomer_name() + " " + order.getVehicle().getVehicle_nr();
        String User = order.getUser().getFirstname() + " " + order.getUser().getLastname();
        mTextViewCustomerName.setText(topTitle);
        mTextViewDetailCustomerName.setText(order.getCustomer_name());
        mTextViewVehicle.setText(order.getVehicle().getName());
        mTextViewDate.setText(order.getStart_date());
        mTextViewDriver.setText(User);
        mTextViewMessage.setText(order.getMessage_to_employer());
    }

    private void setUpViewAndAdapter(View v){
        mRecyclerView = v.findViewById(R.id.rec_list_detail_fragment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mStartListDetailAdapter = new StartListDetailAdapter(this);
        mRecyclerView.setAdapter(mStartListDetailAdapter);
        DividerItemDecoration dividerItem = new DividerItemDecoration(
                mRecyclerView.getContext(),
                layoutManager.getOrientation()
        );

        dividerItem.setDrawable(ContextCompat.getDrawable(
                getContext(),
                R.drawable.divider_grey
        ));

        mRecyclerView.addItemDecoration(
                dividerItem
        );
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        if(mUser.getId() == mOrder.getUser().getId()) {
            inflater.inflate(R.menu.driver_menu, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }else if( mUser.getRole().equals("admin") || mUser.getRole().equals("editor")){
            inflater.inflate(R.menu.admin_supervisor_menu, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    @Override
    public void onClick(int position) {
        OrderRow selectedOrderRow = mStartListDetailAdapter.getSelectedOrderrow(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View mView = getLayoutInflater().inflate(R.layout.workday_dialog, null);
        //Textviews
        TextView dialogCustomerName = mView.findViewById(R.id.dialog_customer_name);
        TextView dialogTime = mView.findViewById(R.id.txtview_dialog_vehicle);
        TextView dialogDate = mView.findViewById(R.id.txtview_dialog_date);
        TextView dialogDriver = mView.findViewById(R.id.txtview_dialog_driver);
        TextView dialogComment = mView.findViewById(R.id.txtview_dialog_comment);
        TextView nrOfHours = mView.findViewById(R.id.txtview_nr_hours);

        //Dialog
        dialogCustomerName.setText(selectedOrderRow.getOrder().getCustomer_name());
        dialogTime.setText(selectedOrderRow.getStarttime() + " - " + selectedOrderRow.getEndtime());
        dialogDate.setText(selectedOrderRow.getStartdate());
        dialogDriver.setText(selectedOrderRow.getUser().getFirstname() + " " + selectedOrderRow.getUser().getLastname());
        dialogComment.setText(selectedOrderRow.getComment());
        nrOfHours.setText(String.valueOf(selectedOrderRow.getTotalWorkHours()));
        builder.setView(mView);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
