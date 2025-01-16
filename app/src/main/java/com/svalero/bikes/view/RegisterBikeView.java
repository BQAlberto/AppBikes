package com.svalero.bikes.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bikes.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationManager;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.svalero.bikes.contract.RegisterBikeContract;
import com.svalero.bikes.domain.Bike;
import com.svalero.bikes.presenter.RegisterBikePresenter;
import com.svalero.bikes.util.DateUtil;
import com.svalero.bikes.util.MapUtil;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

public class RegisterBikeView extends AppCompatActivity implements RegisterBikeContract.View , Style

        .OnStyleLoaded, OnMapClickListener {

    private RegisterBikePresenter presenter;
    private MapView mapView;
    private AnnotationManager pointAnnotationManager;
    private GesturesPlugin gesturesPlugin;
    private Point currentPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_bike);

        presenter = new RegisterBikePresenter(this);

        initializedMapView();
        pointAnnotationManager = MapUtil.initializePointAnnotationManager(mapView);
        initializeGesturesPlugin();
    }

    public void register(View view) {
        if (currentPoint == null) {
            Toast.makeText(this, "Seleccione una ubicación para registrar su bici", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            String brand = ((EditText) findViewById(R.id.brand)).getText().toString();
            String model = ((EditText) findViewById(R.id.model)).getText().toString();
            Date releaseDate = DateUtil.format(((EditText) findViewById(R.id.release_date)).getText().toString());
            String color = ((EditText) findViewById(R.id.color)).getText().toString();

            Bike bike = new Bike(brand, model, releaseDate, color, currentPoint.latitude(), currentPoint.longitude());
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

    private void initializedMapView() {
        mapView = findViewById(R.id.registerMapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {

    }

    private void initializeGesturesPlugin() {
        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);
    }

    private void addMarker(double latitude, double longitude) {
        PointAnnotationOptions marker = new PointAnnotationOptions()
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker))
                .withPoint(Point.fromLngLat(longitude, latitude));
        pointAnnotationManager.create(marker);
    }

    @Override
    public boolean onMapClick(@NonNull Point point) {
        pointAnnotationManager.deleteAll();
        currentPoint = point;
        addMarker(point.latitude(), point.longitude());
        return true;
    }
}