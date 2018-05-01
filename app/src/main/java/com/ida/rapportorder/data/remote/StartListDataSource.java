package com.ida.rapportorder.data.remote;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ida.rapportorder.R;
import com.ida.rapportorder.data.DataSourceInterface;
import com.ida.rapportorder.data.Orders;

import java.nio.channels.AsynchronousByteChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ida on 2018-04-30.
 */

public class StartListDataSource implements DataSourceInterface {
    private FirebaseFirestore db;
    private Orders order;
    public StartListDataSource() {
        db = FirebaseFirestore.getInstance();
        order = new Orders();
    }

    @Override
    public List<Orders> getListOfData() {
        final ArrayList<Orders> listOfData = new ArrayList<>();
        try {
            db.collection("orders").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    if(!queryDocumentSnapshots.isEmpty()){
                        for(QueryDocumentSnapshot document : queryDocumentSnapshots){
                            listOfData.add(document.toObject(Orders.class));
                        }
                    }
                }
            });
        }catch(Exception ex){

        }finally {
            return  listOfData;
        }
    }
}
