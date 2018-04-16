package com.ida.rapportorder;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class LoginActivity extends AppCompatActivity {
    //UI references
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    //Firebase instances
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.login_email);
        mPasswordView = (EditText) findViewById(R.id.login_password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.integer.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    public void signInUser (View v){
        attemptLogin();
    }
    //Executes when register button is clicked
    public void registerNewUser (View v){
        Intent newIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        finish();
        startActivity(newIntent);
    }
    private void attemptLogin(){
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("RapportOrder", "signInWithEmailAndPassword() onComplete " + task.isSuccessful());

                if(!task.isSuccessful()){
                    Log.d("RapportOrder", "Inloggnings problem " + task.getException());
                    showErrorDialog("Det blev problem när du skulle logga in");
                }else{

                    FirebaseUser mFireBaseUser = mAuth.getCurrentUser();
                    String userId = mFireBaseUser.getUid();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("userId", userId);
                    finish();
                    startActivity(intent);
                }
            }
        });
    }
    private void showErrorDialog(String message){
        new AlertDialog.Builder(LoginActivity.this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
        ;
    }
}
