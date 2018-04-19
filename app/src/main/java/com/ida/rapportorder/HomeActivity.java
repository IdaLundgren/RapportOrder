package com.ida.rapportorder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class HomeActivity extends AppCompatActivity {
    private TextView text;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        text = findViewById(R.id.welcome_text);
        db = FirebaseFirestore.getInstance();


    }
    private void getUserInfo(String userId){
     /*   DocumentReference user = db.collection("users").document(userId);
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder fields = new StringBuilder("");
                    fields.append("Hej " ).append(doc.get("firstname"));
                    text.setText(fields.toString());
                }
            }
        });*/
        //DocumentReference user = db.collection("users").document(userId);
        db.collection("orders").whereEqualTo("supervisor." + userId + "", true).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Log.d("RapportOrder", e.getMessage());
                }

                for(DocumentSnapshot doc : queryDocumentSnapshots){
                    String customer = doc.getString("customer");
                    Log.d("RapportOrder", customer);
                }
            }
        });
        /*query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("RapportOrder", document.getId() + " => " + document.getData());
                    }
                }else{
                    Log.d("RapportOrder", "något gick fel!");
                }
            }
        });*/
        /*user.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    UserModel newUser = documentSnapshot.toObject(UserModel.class);
                    if(newUser.getRole().equals("supervisor")){
                        text.setText("Du är en arbetsledare");
                    }else if(newUser.getRole().equals("driver")){
                        text.setText("Du är en förare");
                    }else if(newUser.getRole().equals("admin")){
                        text.setText(newUser.getFirstname() + " du är en admin");
                    }
                }
            }
        });*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent i = getIntent();
        String userId = i.getExtras().getString("userId");
        getUserInfo(userId);
    }
}
