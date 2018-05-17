package com.ida.rapportorder.view;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.ida.rapportorder.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    //constants
    private static final String USERID_KEY = "user_id";
    private static final String FIRSTNAME_KEY = "firstname";
    private static final String LASTNAME_KEY = "lastname";
    private static final String CELLPHONE_KEY = "cellphone";
    private static final String EMAIL_KEY = "email";
    //UI references
    private EditText mFirstnameView;
    private EditText mLastnameView;
    private EditText mCellphoneView;
    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mConfirmPasswordView;
    private EditText mUserRoleView;


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

        // Keyboard sign in action
        mConfirmPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.integer.register_form_finished || id == EditorInfo.IME_NULL) {
                    attemptRegistration();
                    return true;
                }
                return false;
            }
        });

    }
    private boolean isEmailValid(String email){
        return email.contains("@");
    }
    private boolean isPasswordValid(String password){
        String confirmPassword = mConfirmPasswordView.getText().toString();
        return confirmPassword.equals(password) && password.length() > 4;
    }
    private void createFireBaseUser(){
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
    }
    private void createUser(String uid){
        String firstname = mFirstnameView.getText().toString();
        String lastname = mLastnameView.getText().toString();
        String cellphone = mCellphoneView.getText().toString();
        String email = mEmailView.getText().toString();
        String userRole = mUserRoleView.getText().toString();

        Map<String, Object> newUser = new HashMap<>();
        newUser.put(USERID_KEY, uid);
        newUser.put(FIRSTNAME_KEY, firstname);
        newUser.put(LASTNAME_KEY, lastname);
        newUser.put(CELLPHONE_KEY, cellphone);
        newUser.put(EMAIL_KEY, email);

        Map<String, Object> newRole = new HashMap<>();
        newRole.put("driver" , true);

        newUser.put("roles", newRole);

    }
    //Executes when registrera button is pressed
    public void signUp(View v){
        attemptRegistration();
    }
    private void attemptRegistration(){

        // Reset errors displayed in the form.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            createFireBaseUser();
        }
    }
    private void showErrorDialog(String message){
        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
        ;
    }
}
