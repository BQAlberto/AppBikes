package com.svalero.bikes.model;

import com.svalero.bikes.api.BikesApi;
import com.svalero.bikes.api.BikesApiInterface;
import com.svalero.bikes.contract.BikeListContract;
import com.svalero.bikes.domain.Bike;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BikeListModel implements BikeListContract.Model {

    @Override
    public void loadBikes(OnLoadBikesListener listener) {
        BikesApiInterface bikesApi = BikesApi.buildInstance();
        Call<List<Bike>> getBikesCall = bikesApi.getBikes();
        getBikesCall.enqueue(new Callback<List<Bike>>() {
            @Override
            public void onResponse(Call<List<Bike>> call, Response<List<Bike>> response) {
                if (response.code() == 200) {
                    listener.OnLoadBikesSuccess(response.body());
                } else if (response.code() == 500) {
                    listener.OnLoadBikesError("API no disponible");
                } else {
                    listener.OnLoadBikesError(String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Bike>> call, Throwable t) {
                listener.OnLoadBikesError("No se ha podido conectar" + "Inténtelo de nuevo");
            }
        });
    }
}
