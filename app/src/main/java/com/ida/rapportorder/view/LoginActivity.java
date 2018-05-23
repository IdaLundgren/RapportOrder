package com.ida.rapportorder.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.ida.rapportorder.R;
import com.ida.rapportorder.controller.RestManager;
import com.ida.rapportorder.model.pojo.Result;
import com.ida.rapportorder.model.pojo.User;

import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    //UI references
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button mLoginButton;
    private RestManager mRestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //View
        mEmailView = (AutoCompleteTextView) findViewById(R.id.login_email);
        mPasswordView = (EditText) findViewById(R.id.login_password);
        mLoginButton = findViewById(R.id.login_sign_in_button);
        mEmailView.addTextChangedListener(loginTextWatcher);
        mPasswordView.addTextChangedListener(loginTextWatcher);

        //Api
        mRestManager = new RestManager();
    }
    private TextWatcher loginTextWatcher = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String email = mEmailView.getText().toString().trim();
            String password = mPasswordView.getText().toString().trim();

            //If the fields are filled in the button is enabled and a clicklistener is added
            if(!email.isEmpty() && password.isEmpty()){
                mLoginButton.setEnabled(true);
                mLoginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        attemptLogin();
                    }
                });
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    //Attempt to login user
    private void attemptLogin(){
        final String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();


        retrofit2.Call<Result> call =  mRestManager.getOrderFromApi().loginUser(email, password);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(retrofit2.Call<Result> call, Response<Result> response) {
                if(response.isSuccessful()){
                    if(response.body() == null){
                        Toast.makeText(getApplicationContext(), "Något gick fel! Testa starta om appen!", Toast.LENGTH_LONG).show();
                    }else {
                        if(!response.body().getError()){
                            Toast.makeText(getApplicationContext(), "Du loggas in...", Toast.LENGTH_SHORT).show();
                            User user = new User();
                            user = response.body().getUser();
                            Intent intent = new Intent(LoginActivity.this, BaseActivity.class);
                            intent.putExtra("user", user);
                            finish();
                            startActivity(intent);
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Ogiltigt email eller lösenord", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(retrofit2.Call<Result> call, Throwable t) {
                Log.d("Skapa", "onFailure: " +  t.getMessage());
                Toast.makeText(getApplicationContext(),"Något gick fel! Det gick inte logga in!", Toast.LENGTH_SHORT).show();
            }
        });




    }
    //Shows error when login fails
    private void showErrorDialog(String message){
        new AlertDialog.Builder(LoginActivity.this)
                .setTitle("Fel vid inloggning")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
        ;
    }
}
