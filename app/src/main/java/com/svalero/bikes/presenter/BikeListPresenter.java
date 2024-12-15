package com.svalero.bikes.presenter;

import com.svalero.bikes.contract.BikeListContract;
import com.svalero.bikes.domain.Bike;
import com.svalero.bikes.model.BikeListModel;

import java.util.List;

public class BikeListPresenter implements BikeListContract.Presenter, BikeListContract.Model.OnLoadBikesListener {

    private BikeListContract.View view;
    private BikeListContract.Model model;

    public BikeListPresenter(BikeListContract.View view) {
        this.view = view;
        model = new BikeListModel();
    }

    @Override
    public void loadBikes() {
        model.loadBikes(this);
    }

    @Override
    public void OnLoadBikesSuccess(List<Bike> bikeList) {
        view.listBikes(bikeList);
    }

    @Override
    public void OnLoadBikesError(String message) {
        view.showErrorMessages(message);
    }
}
