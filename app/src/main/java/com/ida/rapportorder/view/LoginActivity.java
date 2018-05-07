package com.ida.rapportorder.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ida.rapportorder.R;
import com.ida.rapportorder.model.pojo.User;

public class LoginActivity extends AppCompatActivity {
    //UI references
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button mLoginButton;

    //Firebase instances
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
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

        //Init firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
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
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        Toast.makeText(getApplicationContext(), "Du loggas in...", Toast.LENGTH_SHORT).show();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("RapportOrder", "signInWithEmailAndPassword() onComplete " + task.isSuccessful());
                if(!task.isSuccessful()){
                    showErrorDialog("Det blev problem när du skulle logga in. Har du skrivit rätt användarnamn och lösenord?");
                }else{
                    Intent intent = new Intent(LoginActivity.this, BaseActivity.class);
                    intent.putExtra("userId", mAuth.getCurrentUser().getUid());
                    finish();
                    startActivity(intent);
                }
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
