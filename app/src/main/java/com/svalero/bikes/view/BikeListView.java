package com.svalero.bikes.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bikes.R;
import com.svalero.bikes.adapter.BikeAdapter;
import com.svalero.bikes.contract.BikeListContract;
import com.svalero.bikes.domain.Bike;
import com.svalero.bikes.presenter.BikeListPresenter;

import java.util.ArrayList;
import java.util.List;

public class BikeListView extends AppCompatActivity implements BikeListContract.View {

    private BikeAdapter bikeAdapter;
    private ArrayList<Bike> bikeList;
    private BikeListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new BikeListPresenter(this);

        bikeList = new ArrayList<>();

        RecyclerView bikesView = findViewById(R.id.bikes_view);
        bikesView.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        bikesView.setLayoutManager(linearLayoutManager);

        bikeAdapter = new BikeAdapter(bikeList);
        bikesView.setAdapter(bikeAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Todo que hace cuando la Activity viene del segundo plano: Refrescar la lista por si ha habido cambios.
        bikeList.clear();
        presenter.loadBikes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.action_map) {
                Intent intent = new Intent(this, MapActivityView.class);
                intent.putParcelableArrayListExtra("bikeList", bikeList);
                startActivity(intent);
            } else if (item.getItemId() == R.id.action_register_bike) {
                Intent intent = new Intent(this, RegisterBikeView.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.action_preferences) {
                Intent intent =new Intent(this, PreferencesActivity.class);
                startActivity(intent);
            }

        return true;
        }

    public void registerBike (View view){
        Intent intent = new Intent(this, RegisterBikeView.class);
        startActivity(intent);
    }

    @Override
    public void listBikes(List<Bike> bikeList) {
        this.bikeList.addAll(bikeList);
        bikeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessages(String message) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSourcesMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}