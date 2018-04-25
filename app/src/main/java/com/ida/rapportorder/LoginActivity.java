package com.ida.rapportorder;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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
    //Executes when the register button is clicked
    public void registerNewUser (View v){
        Intent newIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        finish();
        startActivity(newIntent);
    }
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
                    DocumentReference user = db.collection("users").document(mAuth.getCurrentUser().getUid());
                    user.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(documentSnapshot.exists()){
                                UserModel newUser = documentSnapshot.toObject(UserModel.class);
                                if(newUser.getRole().equals("supervisor")){
                                    Intent intent = new Intent(LoginActivity.this, SupervisorAdminContentMain.class);
                                    intent.putExtra("userId", mAuth.getCurrentUser().getUid());
                                    finish();
                                    startActivity(intent);
                                }else if(newUser.getRole().equals("driver")){
                                    Intent intent = new Intent(LoginActivity.this, DriverContentMain.class);
                                    intent.putExtra("userId", mAuth.getCurrentUser().getUid());
                                    finish();
                                    startActivity(intent);
                                }else if(newUser.getRole().equals("admin")){
                                    Intent intent = new Intent(LoginActivity.this, SupervisorAdminContentMain.class);
                                    intent.putExtra("userId", mAuth.getCurrentUser().getUid());
                                    finish();
                                    startActivity(intent);
                                }
                            }
                        }
                    });
                    /*String userId = mAuth.getCurrentUser().getUid();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("userId", userId);
                    finish();
                    startActivity(intent);*/
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
