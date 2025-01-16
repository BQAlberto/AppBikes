package com.svalero.bikes.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.bikes.R;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.svalero.bikes.domain.Bike;
import com.svalero.bikes.util.MapUtil;

import java.util.ArrayList;

public class MapActivityView extends AppCompatActivity implements Style.OnStyleLoaded {

    private ArrayList<Bike> bikeList;
    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        bikeList = intent.getParcelableArrayListExtra("bikeList");

        mapView = findViewById(R.id.mapView);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mapType = preferences.getString("preference_map_type", "Calles");
        if (mapType.equals("Calles")) {
            mapType = Style.MAPBOX_STREETS;
        } else if (mapType.equals("Satelite")) {
            mapType = Style.SATELLITE;
        }
        mapView.getMapboxMap().loadStyleUri(mapType, this);
        pointAnnotationManager = MapUtil.initializePointAnnotationManager(mapView);
    }

    private void viewBikes() {
        for (Bike bike : bikeList) {
            addMarker(bike.getBrand(), bike.getLatitude(), bike.getLongitude());
        }
    }

    private void addMarker(String message, double latitude, double longitude) {
        PointAnnotationOptions marker = new PointAnnotationOptions()
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker))
                .withTextField(message)
                .withPoint(Point.fromLngLat(longitude, latitude));
        pointAnnotationManager.create(marker);
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {
        viewBikes();
    }
}