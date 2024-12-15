package com.svalero.bikes.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bikes.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.bikes.contract.RegisterBikeContract;
import com.svalero.bikes.domain.Bike;
import com.svalero.bikes.presenter.RegisterBikePresenter;
import com.svalero.bikes.util.DateUtil;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

public class RegisterBikeView extends AppCompatActivity implements RegisterBikeContract.View {

    private RegisterBikePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_bike);

        presenter = new RegisterBikePresenter(this);
    }

    public void register(View view) {
        try {
            String brand = ((EditText) findViewById(R.id.brand)).getText().toString();
            String model = ((EditText) findViewById(R.id.model)).getText().toString();
            Date releaseDate = DateUtil.format(((EditText) findViewById(R.id.release_date)).getText().toString());
            String color = ((EditText) findViewById(R.id.color)).getText().toString();

            Bike bike = new Bike(brand, model, releaseDate, color, 0, 0);
            presenter.registerBike(bike);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
    }

    @Override
    public void showErrorMessages(String message) {
        Snackbar.make(findViewById(R.id.add_bike_button), message, BaseTransientBottomBar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void showSourcesMessage(String message) {
        Snackbar.make(findViewById(R.id.add_bike_button), message, BaseTransientBottomBar.LENGTH_SHORT).show();
    }
}