package com.svalero.bikes.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bikes.R;

public class DetailActivityView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        Intent intent = getIntent();
        long bikeId = intent.getLongExtra("bikeId", 0);
        printBike(bikeId);
    }

    private void printBike(long bikeId) {
        ((TextView) findViewById(R.id.bike_id)).setText(String.valueOf(bikeId));
    }
}