package com.ida.rapportorder.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ida.rapportorder.R;
import com.ida.rapportorder.model.pojo.User;


public class OrderFragment extends Fragment {
    private FloatingActionButton mFloatingActionButton;
    private User mUserLoggedIn;

    //Const
    private static final String KEY_USER = "user";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_fragment, container, false);
        mUserLoggedIn = new User();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mUserLoggedIn = bundle.getParcelable(KEY_USER);
        }
        mFloatingActionButton = view.findViewById(R.id.floatingActionButton_create_order);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putParcelable(KEY_USER, mUserLoggedIn);

                Fragment createOrder = new CreateOrderFragment();
                createOrder.setArguments(bundle);
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.base_container, createOrder)
                        .addToBackStack("Order")
                        .commit();
                /*setArguments(bundle);
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.base_container, new CreateOrderFragment())
                        .addToBackStack("Order")
                        .commit();*/
            }
        });
        return view;
    }
}
