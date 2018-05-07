package com.ida.rapportorder.view;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ida.rapportorder.R;

public class ListDetailActivity extends AppCompatActivity {
    private static final String EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static final String EXTRA_COLOUR_RESOURCES = "EXTRA_COLOUR_RESOURCES";

    private TextView mTextViewDateAndTime;
    private TextView mTextViewMessage;
    private View mViewColoredCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);
        Intent i = getIntent();
        String dateAndTimeExtra = i.getStringExtra(EXTRA_DATE_AND_TIME);
        String message = i.getStringExtra(EXTRA_MESSAGE);
        int colorResourcesExtra = i.getIntExtra(EXTRA_COLOUR_RESOURCES, 0);

    }
}
