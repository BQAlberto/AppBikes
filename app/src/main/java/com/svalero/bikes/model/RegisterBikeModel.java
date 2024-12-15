package com.svalero.bikes.model;

import com.svalero.bikes.api.BikesApi;
import com.svalero.bikes.api.BikesApiInterface;
import com.svalero.bikes.contract.RegisterBikeContract;
import com.svalero.bikes.domain.Bike;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterBikeModel implements RegisterBikeContract.Model {

    @Override
    public void registerBike(Bike bike, OnRegisterBikeListener listener) {
        BikesApiInterface bikesApi = BikesApi.buildInstance();
        Call<Bike> callRegisterBike = bikesApi.addBike(1, bike);
        callRegisterBike.enqueue(new Callback<Bike>() {
            @Override
            public void onResponse(Call<Bike> call, Response<Bike> response) {
                switch (response.code()) {
                    case 201:
                        listener.OnRegisterBikeSuccess(response.body());
                        break;
                    case 400:
                        listener.OnRegisterBikeError("Error validando petición" + response.message());
                        break;
                    case 500:
                        listener.OnRegisterBikeError("Error interno de la API" + response.message());
                        break;
                    default:
                        listener.OnRegisterBikeError("Error invocando la API" + response.message());
                        break;
                }
            }

            @Override
            public void onFailure(Call<Bike> call, Throwable t) {
                listener.OnRegisterBikeError("No se ha podido conectar" + "Inténtelo de nuevo");
            }
        });
    }
}
