package com.ida.rapportorder.data;

import android.arch.lifecycle.LiveData;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * Håller kopplingen till databasen. Samt utför förfrågning
 * till den angående det som finns på framsidan.
 */
public class OrderDoa  {
    protected FirebaseFirestore mDb;

    public OrderDoa() {
        mDb = FirebaseFirestore.getInstance();
    }
}
