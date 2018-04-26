package com.ida.rapportorder.view;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ida.rapportorder.R;

import org.w3c.dom.Text;

public class DriverListDetailActivity extends AppCompatActivity {
    private static final String EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static final String EXTRA_COLOUR_RESOURCES = "EXTRA_COLOUR_RESOURCES";

    private TextView mTextViewDateAndTime;
    private TextView mTextViewMessage;
    private View mViewColoredCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_list_detail);
        Intent i = getIntent();
        String dateAndTimeExtra = i.getStringExtra(EXTRA_DATE_AND_TIME);
        String message = i.getStringExtra(EXTRA_MESSAGE);
        int colorResourcesExtra = i.getIntExtra(EXTRA_COLOUR_RESOURCES, 0);

        mTextViewDateAndTime = findViewById(R.id.lbl_date_and_time_header);
        mTextViewDateAndTime.setText(dateAndTimeExtra);

        mTextViewMessage = findViewById(R.id.lbl_message_body);
        mTextViewMessage.setText(message);

        mViewColoredCircle = findViewById(R.id.cont_colored_background);
        mViewColoredCircle.setBackgroundColor(ContextCompat.getColor(this, colorResourcesExtra));
    }
}
