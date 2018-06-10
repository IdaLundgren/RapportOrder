package com.ida.rapportorder.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ida.rapportorder.R;
import com.ida.rapportorder.controller.RestManager;
import com.ida.rapportorder.model.pojo.Order;
import com.ida.rapportorder.model.pojo.Result;
import com.ida.rapportorder.model.pojo.User;
import com.ida.rapportorder.model.pojo.Vehicle;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;


public class CreateOrderFragment extends Fragment {
    private TextView mTextViewCustomerName;
    private EditText mTextViewCreated_At;
    private TextView mTextViewCreated_By;
    private TextView mTextViewPrice_Per_hour;
    private TextView mTextViewPrice_Per_Extra;
    private TextView mTextViewMessage_To_Employer;
    private Button mButtonAddOrder;
    private RestManager mRestManager;
    private List<Vehicle> mVehiclesList;
    private BetterSpinner mBetterSpinner;
    private Toolbar mToolbar;
    private User mUserLoggedIn;
    private Vehicle mVehicleSelected;

    //Const
    private static final String KEY_USER = "user";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_order_fragment, container, false);


        mTextViewCustomerName = view.findViewById(R.id.edittxt_customer_name);
        mTextViewCreated_At = view.findViewById(R.id.edittxt_select_date);
        mTextViewPrice_Per_hour = view.findViewById(R.id.edittxt_price_per_hour);
        mTextViewPrice_Per_Extra = view.findViewById(R.id.edittxt_price_per_extra);
        mTextViewMessage_To_Employer = view.findViewById(R.id.edittxt_message_to_employer);
        mBetterSpinner = view.findViewById(R.id.vehicle_dropdown_betterspinner);
        mVehiclesList = new ArrayList<>();
        mRestManager = new RestManager();
        mVehicleSelected = new Vehicle();
        mToolbar = view.findViewById(R.id.toolbar_create_order);

        mUserLoggedIn = new User();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mUserLoggedIn = bundle.getParcelable(KEY_USER);
        }

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
        mTextViewCreated_At.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        setDate(year, month, dayOfMonth,  mTextViewCreated_At);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        ArrayAdapter<Vehicle> arrayAdapter = new ArrayAdapter<Vehicle>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mVehiclesList);
        mBetterSpinner.setAdapter(arrayAdapter);

        mBetterSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mVehicleSelected = mVehiclesList.get(position);
            }
        });
        //Knappar
        mButtonAddOrder = view.findViewById(R.id.btn_create_order);
        mButtonAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String created_at = dateFormat.format(date).toString();
                String start_date = mTextViewCreated_At.getText().toString();
                String uid = String.valueOf(mUserLoggedIn.getId());
                String message_to_employer = mTextViewMessage_To_Employer.getText().toString();
                String price_per_hour = mTextViewPrice_Per_hour.getText().toString();
                String price_per_extra = mTextViewPrice_Per_Extra.getText().toString();
                String customer_name = mTextViewCustomerName.getText().toString();

                if(created_at.isEmpty() || start_date.isEmpty()){
                    Toast.makeText(getContext(), "Du måste fylla i alla fält", Toast.LENGTH_LONG).show();
                }else {
                    Call<Result> call = mRestManager.getOrderFromApi().insertOrder(created_at, uid,message_to_employer,price_per_hour,price_per_extra,customer_name,String.valueOf(mVehicleSelected.getId()),start_date);

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
                            }else {

                            }
                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {
                            Log.d("Skapa", "onFailure: " +  t.getMessage());
                            Toast.makeText(getActivity(),"Något gick fel! Det gick inte att skapa din order.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }
    private void attemptToCreateOrder(){

    }
    public void setDate(int year, int month, int dayOfMonth, EditText field){
        month += 1;
        if(month < 10 || dayOfMonth < 10){
            field.setText(year + "-0" + month + "-0" + dayOfMonth);
        }else{
            field.setText(year + "-" + month + "-" + dayOfMonth);
        }
    }
}
