package com.cartoaware.mvvm.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherResponse {
    @SerializedName("lat")
    @Expose
    public Double lat;
    @SerializedName("lon")
    @Expose
    public Double lon;
    @SerializedName("timezone")
    @Expose
    public String timezone;
    @SerializedName("current")
    @Expose
    public Current current;
    @SerializedName("hourly")
    @Expose
    public List<Hourly> hourly = new ArrayList<>();
    @SerializedName("daily")
    @Expose
    public List<Daily> daily = new ArrayList<Daily>();

}
