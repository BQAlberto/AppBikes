package com.svalero.bikes.contract;

import com.svalero.bikes.domain.Bike;

import java.util.List;

public interface RegisterBikeContract {

    interface Model {
        interface OnRegisterBikeListener {
            void OnRegisterBikeSuccess(Bike registeredBike);
            void OnRegisterBikeError(String message);
        }
        void registerBike(Bike bike, OnRegisterBikeListener listener);
    }

    interface View {
        //void listBikes(List<Bike> bikeList);
        void showErrorMessages(String message);
        void showSourcesMessage(String message);
    }

    interface Presenter {
        void registerBike(Bike bike);
    }
}
