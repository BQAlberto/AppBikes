package com.svalero.bikes;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.svalero.bikes.view.BikeListView;

public class MapActivity extends AppCompatActivity implements Style.OnStyleLoaded{

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapView = findViewById(R.id.mapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        initializePointAnnotationManager();
    }

    private void initializePointAnnotationManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(
                annotationPlugin, annotationConfig);
    }

    private void viewBikes() {
//        for (Bike bike : BikeListView.bikeList) {
//            addMarker(bike.getBrand(), bike.getLatitude(), bike.getLongitude());
//        }
    }

    private void addMarker(String message, double latitude, double longitude) {
        PointAnnotationOptions marker = new PointAnnotationOptions()
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.bike))
                .withTextField(message)
                .withPoint(Point.fromLngLat(longitude, latitude));
        pointAnnotationManager.create(marker);
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {
        viewBikes();
    }
}