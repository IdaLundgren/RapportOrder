package com.ida.rapportorder.view;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ida.rapportorder.R;
import com.ida.rapportorder.controller.RestManager;
import com.ida.rapportorder.model.adapter.ItemClickListener;
import com.ida.rapportorder.model.adapter.StartOrderListAdapter;
import com.ida.rapportorder.model.pojo.Order;
import com.ida.rapportorder.model.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartFragment extends Fragment implements ItemClickListener {
    private static final String EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static final String EXTRA_COLOUR_RESOURCES = "EXTRA_COLOUR_RESOURCES";

    private StartOrderListAdapter mStartOrderListAdapter;
    private List<Order> mListOfData;
    private LayoutInflater mLayoutInflater;
    private RecyclerView mRecyclerView;
    private TextView mTextViewlblHeader;
    private RestManager mRestManager;
    private Bundle mBundleRecyclerViewState;
    private FloatingActionButton mFloatingActionButton;
    private RelativeLayout mRelativeLayout;

    private User mUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            mUser = bundle.getParcelable("user");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_content_main, container, false);
        setUpViewAndAdapter(view);
        mFloatingActionButton = view.findViewById(R.id.floatingActionButton_start_fragment);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.base_container, new CreateOrderFragment())
                        .commit();
            }
        });
        mRelativeLayout = view.findViewById(R.id.start_fragment);
        mTextViewlblHeader = container.findViewById(R.id.lbl_start_list_header);
        mLayoutInflater = getLayoutInflater();
        mRestManager = new RestManager();
        Call<List<Order>> listcall = mRestManager.getOrderFromApi().getAllOrders();
        listcall.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if(response.isSuccessful()){
                    List<Order> orderList = response.body();
                    mListOfData = response.body();

                    for (int i = 0; i < orderList.size(); i++) {
                        Order order = orderList.get(i);
                        mStartOrderListAdapter.addOrder(order);
                    }
                }else{
                    Log.d("Lista", "funkar inte!");
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("Lista", t.toString());
            }
        });



        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable("recycler_state", listState);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(mBundleRecyclerViewState != null){
            Parcelable listState = mBundleRecyclerViewState.getParcelable("recycler_state");
            mRecyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    private void setUpViewAndAdapter(View v){
        mRecyclerView = v.findViewById(R.id.rec_list_driver_activity_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mStartOrderListAdapter = new StartOrderListAdapter(this);
        mRecyclerView.setAdapter(mStartOrderListAdapter);

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
    public void onClick(int position) {
        Order selectedOrder = mStartOrderListAdapter.getSelectedOrder(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable("order", selectedOrder);
        bundle.putParcelable("user", mUser);
        Fragment startDetailFragment = new StartDetailFragment();
        startDetailFragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.base_container, startDetailFragment)
                .commit();
    }
}
