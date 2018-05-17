package com.ida.rapportorder.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ida.rapportorder.R;
import com.ida.rapportorder.controller.RestManager;
import com.ida.rapportorder.model.pojo.Order;
import com.ida.rapportorder.model.pojo.Result;
import com.ida.rapportorder.model.pojo.Vehicle;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;


public class CreateOrderFragment extends Fragment {
    private TextView mTextViewCustomerName;
    private TextView mTextViewCreated_At;
    private TextView mTextViewCreated_By;
    private TextView mTextViewPrice_Per_hour;
    private TextView mTextViewPrice_Per_Extra;
    private TextView mTextViewMessage_To_Employer;
    private Button mButtonAddOrder;
    private RestManager mRestManager;
    private List<Vehicle> mVehiclesList;
    private BetterSpinner mBetterSpinner;
    private Toolbar mToolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_order_fragment, container, false);


        mTextViewCustomerName = view.findViewById(R.id.edittxt_customer_name);
        mTextViewCreated_At = view.findViewById(R.id.edittxt_select_date);
        mTextViewCreated_By = view.findViewById(R.id.edittxt_driver);
        mTextViewPrice_Per_hour = view.findViewById(R.id.edittxt_price_per_hour);
        mTextViewPrice_Per_Extra = view.findViewById(R.id.edittxt_price_per_extra);
        mTextViewMessage_To_Employer = view.findViewById(R.id.edittxt_message_to_employer);
        mBetterSpinner = view.findViewById(R.id.vehicle_dropdown_betterspinner);
        mVehiclesList = new ArrayList<>();
        mRestManager = new RestManager();
        mToolbar = view.findViewById(R.id.toolbar_create_order);


        Call<List<Vehicle>> vehicleCall = mRestManager.getOrderFromApi().getAllVehicles();
        vehicleCall.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                if(response.isSuccessful()){
                    List<Vehicle> vehicles = response.body();
                    for (int i = 0; i < vehicles.size(); i++) {
                        Vehicle vehicle = vehicles.get(i);
                        mVehiclesList.add(vehicle);
                    }

                }else{
                    Log.d("Lista", "funkar inte!");
                }
            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {

            }
        });

        ArrayAdapter<Vehicle> arrayAdapter = new ArrayAdapter<Vehicle>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mVehiclesList);
        mBetterSpinner.setAdapter(arrayAdapter);
        //Knappar
        mButtonAddOrder = view.findViewById(R.id.btn_create_order);
        mButtonAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String created_at = dateFormat.format(date).toString();
                String start_date = mTextViewCreated_At.getText().toString();
                String uid = mTextViewCreated_By.getText().toString();
                String message_to_employer = mTextViewMessage_To_Employer.getText().toString();
                String price_per_hour = mTextViewPrice_Per_hour.getText().toString();
                String price_per_extra = mTextViewPrice_Per_Extra.getText().toString();
                String customer_name = mTextViewCustomerName.getText().toString();
                String va = mBetterSpinner.getText().toString();
                String vid = "";
                String[] splited = va.split("\\s+");
                va = splited[2];
                for(Vehicle vehicle : mVehiclesList){
                    if(vehicle.getVehicle_nr().equals(va)){
                        vid = String.valueOf(vehicle.getId());
                    }
                }
                Call<Result> call = mRestManager.getOrderFromApi().insertOrder(created_at, uid,message_to_employer,price_per_hour,price_per_extra,customer_name,vid,start_date);

                call.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        if(!response.body().getError()){
                            getActivity()
                                    .getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.base_container, new OrderFragment())
                                    .commit();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.d("Skapa", "onFailure: " +  t.getMessage());
                        Toast.makeText(getActivity(),"Något gick fel! DEt gick inte att skapa din order.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }
    private void attemptToCreateOrder(){

    }
}
