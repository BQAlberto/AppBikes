package com.svalero.bikes.contract;

import com.svalero.bikes.domain.Bike;

import java.util.List;

public interface BikeListContract {

    interface Model {
        interface OnLoadBikesListener {
            void OnLoadBikesSuccess(List<Bike> bikeList);
            void OnLoadBikesError(String message);
        }
        void loadBikes(OnLoadBikesListener listener);
    }

    interface View {
        void listBikes(List<Bike> bikeList);
        void showErrorMessages(String message);
        void showSourcesMessage(String message);
    }

    interface Presenter {
        void loadBikes();
    }
}
