package com.ida.rapportorder.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ida.rapportorder.R;


public class OrderFragment extends Fragment {
    private FloatingActionButton mFloatingActionButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_fragment, container, false);
        String i = "";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            i = bundle.getString("userId");
        }
        mFloatingActionButton = view.findViewById(R.id.floatingActionButton_create_order);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.base_container, new CreateOrderFragment())
                        .addToBackStack("Order")
                        .commit();
            }
        });
        return view;
    }
}
