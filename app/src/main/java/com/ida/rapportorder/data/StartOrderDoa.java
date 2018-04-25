package com.ida.rapportorder.data;

import android.arch.lifecycle.LiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * Håller kopplingen till databasen. Samt utför förfrågning
 * till den angående det som finns på framsidan.
 */
public class StartOrderDoa implements StartOrderListInterface {
    protected FirebaseFirestore mDb;

    public StartOrderDoa() {
        mDb = FirebaseFirestore.getInstance();
    }

    @Override
    public LiveData<List<Orders>> getAllOrders() {
        return null;
    }

    @Override
    public LiveData<List<Orders>> getAllOrdersByUserId(String userId) {
        return null;
    }
}
