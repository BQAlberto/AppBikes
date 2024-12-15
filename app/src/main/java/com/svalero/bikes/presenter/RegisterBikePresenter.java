package com.svalero.bikes.presenter;

import com.svalero.bikes.contract.RegisterBikeContract;
import com.svalero.bikes.domain.Bike;
import com.svalero.bikes.model.RegisterBikeModel;

public class RegisterBikePresenter implements RegisterBikeContract.Presenter, RegisterBikeContract.Model.OnRegisterBikeListener {

    private RegisterBikeContract.Model model;
    private RegisterBikeContract.View view;

    public RegisterBikePresenter(RegisterBikeContract.View view) {
        model = new RegisterBikeModel();
        this.view = view;
    }

    @Override
    public void registerBike(Bike bike) {
        if (bike.getBrand().isEmpty()) {
            view.showErrorMessages("El campo no puede estar vacío");
            return;
        }
        model.registerBike(bike, this);
    }

    @Override
    public void OnRegisterBikeSuccess(Bike registeredBike) {
        view.showSourcesMessage("Bici registrada correctamente" + registeredBike.getId());
    }

    @Override
    public void OnRegisterBikeError(String message) {
        view.showErrorMessages(message);
    }
}
