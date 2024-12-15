package com.svalero.bikes.api;

import com.svalero.bikes.domain.Bike;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BikesApiInterface {

    @GET("bikes")
    Call<List<Bike>> getBikes();

    @POST("users/{userId}/bikes")
    Call<Bike> addBike(@Path ("userId") long userId, @Body Bike bike);
}
