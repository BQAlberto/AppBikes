package com.svalero.bikes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bikes.R;
import com.svalero.bikes.domain.Bike;

import org.w3c.dom.Text;

import java.util.List;

public class BikeAdapter extends RecyclerView.Adapter<BikeAdapter.BikeHolder> {

    private List<Bike> bikeList;

    public BikeAdapter(List<Bike> bikeList) {
        this.bikeList = bikeList;
    }

    @NonNull
    @Override
    public BikeAdapter.BikeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bikesview_item, parent, false);
        return new BikeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BikeAdapter.BikeHolder holder, int position) {
        holder.brand.setText(bikeList.get(position).getBrand());
        holder.model.setText(bikeList.get(position).getModel());
    }

    @Override
    public int getItemCount() {
        return bikeList.size();
    }

    public class BikeHolder extends RecyclerView.ViewHolder {

        //Por el momento la foto fija
        private TextView brand;
        private TextView model;

        public BikeHolder(@NonNull View itemView) {
            super(itemView);

            brand = itemView.findViewById(R.id.item_brand);
            model = itemView.findViewById(R.id.item_model);
        }
    }
}
