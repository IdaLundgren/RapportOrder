package com.ida.rapportorder.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ida.rapportorder.controller.RestManager;
import com.ida.rapportorder.model.pojo.Order;
import com.ida.rapportorder.model.pojo.OrderRow;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StartListDetailViewModel extends ViewModel {
    private MutableLiveData<List<OrderRow>> mListMutableLiveDataOrderrows;

    public LiveData<List<OrderRow>> getOrderrows(int id){
        mListMutableLiveDataOrderrows = new MutableLiveData<>();
        loadOrderrows(id);
        return mListMutableLiveDataOrderrows;
    }
    public void loadOrderrows(int id){
        RestManager restManager = new RestManager();
        Call<List<OrderRow>> call = restManager.getOrderFromApi().getOrderRows(id);
        call.enqueue(new Callback<List<OrderRow>>() {
            @Override
            public void onResponse(Call<List<OrderRow>> call, Response<List<OrderRow>> response) {
                if(response.isSuccessful()){
                    mListMutableLiveDataOrderrows.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<OrderRow>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
