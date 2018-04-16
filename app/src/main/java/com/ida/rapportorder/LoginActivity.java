package com.ida.rapportorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }


    //Executes when register button is clicked
    public void registerNewUser (View v){
        Intent newIntent = new Intent(this, RegisterActivity.class);
        finish();
        startActivity(newIntent);
    }
}
