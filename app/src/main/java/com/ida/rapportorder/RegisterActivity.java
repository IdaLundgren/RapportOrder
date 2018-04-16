package com.ida.rapportorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    //UI references
    private EditText mFirstnameView;
    private EditText mLastnameView;
    private EditText mCellphoneView;
    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mConfirmPasswordView;
    private EditText mUserRoleView;

    //Firebase instance variables
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirstnameView = findViewById(R.id.fname);
        mLastnameView = findViewById(R.id.lname);
        mCellphoneView = findViewById(R.id.cellphone);
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mConfirmPasswordView = findViewById(R.id.confirmpassword);
        mUserRoleView = findViewById(R.id.userRole);

        mAuth = FirebaseAuth.getInstance();
    }
    private boolean isEmailValid(String email){
        return email.contains("@");
    }
    private boolean isPasswordValid(String password){
        String confirmPassword = mConfirmPasswordView.getText().toString();
        return confirmPassword.equals(password) && password.length() > 4;
    }
}
