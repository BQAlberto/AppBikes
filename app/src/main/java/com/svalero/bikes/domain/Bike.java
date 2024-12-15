package com.svalero.bikes.domain;

import java.time.LocalDate;
import java.util.Date;

public class Bike {

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

    public void setMaxSpeed(String color) {
        this.color = color;
    }

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
}
