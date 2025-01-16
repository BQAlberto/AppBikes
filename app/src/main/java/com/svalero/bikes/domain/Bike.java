package com.svalero.bikes.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Bike implements Parcelable {

    private long id;
    private String brand;
    private String model;
    private Date releaseDate;
    private String color;
    private double latitude;
    private double longitude;

    public Bike(String brand, String model, Date releaseDate, String color, double latitude, double longitude) {
        this.brand = brand;
        this.model = model;
        this.releaseDate = releaseDate;
        this.color = color;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected Bike(Parcel in) {
        id = in.readLong();
        brand = in.readString();
        model = in.readString();
        color = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Bike> CREATOR = new Creator<Bike>() {
        @Override
        public Bike createFromParcel(Parcel in) {
            return new Bike(in);
        }

        @Override
        public Bike[] newArray(int size) {
            return new Bike[size];
        }
    };


    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color){this.color = color;}

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(brand);
        dest.writeString(model);
        dest.writeString(color);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
