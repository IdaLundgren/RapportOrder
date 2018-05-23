package com.ida.rapportorder.view;

import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.ida.rapportorder.R;
import com.ida.rapportorder.controller.RestManager;
import com.ida.rapportorder.model.pojo.Result;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.Calendar;


public class CreateWorkDayFragment extends Fragment {
    private EditText mEditTextStartDate;
    private EditText mEditTextEnddate;
    private EditText mEditTextStarttime;
    private EditText mEditTextEndtime;
    private EditText mEditTextBreak;
    private EditText mEditTextComment;
    private EditText mEditTextExtraTime;
    private RadioButton mRadioButtonYes;
    private RadioButton mRadioButtonNo;
    private RadioGroup mRadioGroup;
    private Button mButtonCreateWorkDay;
    private FloatingActionButton mFloatingActionButton;

    private RestManager mRestManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_workday_fragment, container, false);

        mEditTextStartDate = view.findViewById(R.id.edittext_startdate);
        mEditTextEnddate = view.findViewById(R.id.edittext_enddate);
        mEditTextStarttime = view.findViewById(R.id.edittext_starttime);
        mEditTextEndtime = view.findViewById(R.id.edittext_endtime);
        mEditTextBreak = view.findViewById(R.id.edittx_break_in_minutes);
        mEditTextComment = view.findViewById(R.id.edittext_commemt_from_driver);
        mEditTextExtraTime = view.findViewById(R.id.edittx_extra_in_minutes);
        mRadioButtonYes = view.findViewById(R.id.radioButton_yes);
        mRadioButtonNo = view.findViewById(R.id.radioButton_no);
        mRadioGroup = view.findViewById(R.id.radiogroupe_extra);

        mButtonCreateWorkDay = view.findViewById(R.id.btn_create_workday);

        mRestManager = new RestManager();
        mEditTextStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        setDate(year, month, dayOfMonth, mEditTextStartDate);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        mEditTextEnddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        setDate(year, month, dayOfMonth, mEditTextEnddate);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        mEditTextStarttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(minute == 0 || hourOfDay < 10){
                            mEditTextStarttime.setText("0" + hourOfDay + ":00");
                        }else{
                            mEditTextStarttime.setText(hourOfDay + ":" + minute);
                        }
                    }
                },hour,minute,true);
                timePickerDialog.show();
            }
        });

        mEditTextEndtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(minute == 0){
                            mEditTextEndtime.setText(hourOfDay + ":00");
                        }else{
                            mEditTextEndtime.setText(hourOfDay + ":" + minute);
                        }
                    }
                },hour,minute,true);
                timePickerDialog.show();
            }
        });

        mButtonCreateWorkDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startdate = mEditTextStartDate.getText().toString();
                String enddate = mEditTextEnddate.getText().toString();
                String starttime = mEditTextStarttime.getText().toString();
                String endtime = mEditTextEndtime.getText().toString();
                String breakMin = mEditTextBreak.getText().toString();
                String comment = mEditTextComment.getText().toString();
                String extraBool = "0";
                String extraMin = mEditTextExtraTime.getText().toString();

                Call<Result> call = mRestManager.getOrderFromApi().inserOrderrow(startdate, enddate, starttime, endtime, breakMin, comment, extraBool, extraMin, "2");
                call.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if(response.isSuccessful()){
                            if(!response.body().getError()){
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                                getActivity().getSupportFragmentManager().popBackStack();
                            }else{
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.d("Helvete", "onResponse: FUNKAR INTE HELVETE " + t.getMessage());
                    }
                });
            }
        });
        return view;
    }
    public void setDate(int year, int month, int dayOfMonth, EditText field){
        month += 1;
        if(month < 10){
            field.setText(year + "-0" + month + "-" + dayOfMonth);
        }else{
            field.setText(year + "-" + month + "-" + dayOfMonth);
        }
    }
}
