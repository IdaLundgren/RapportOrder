package com.ida.rapportorder.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by Ida on 2018-05-09.
 */

public class DropDownListAdapter extends ArrayAdapter {
    public DropDownListAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }
}
