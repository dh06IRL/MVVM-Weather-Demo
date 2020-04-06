package com.cartoaware.mvvm.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Daily {
    @SerializedName("dt")
    @Expose
    public Long dt;
    @SerializedName("sunrise")
    @Expose
    public Long sunrise;
    @SerializedName("sunset")
    @Expose
    public Long sunset;
    @SerializedName("temp")
    @Expose
    public Temp temp;
    @SerializedName("feels_like")
    @Expose
    public FeelsLike feelsLike;
    @SerializedName("pressure")
    @Expose
    public Integer pressure;
    @SerializedName("humidity")
    @Expose
    public Integer humidity;
    @SerializedName("dew_point")
    @Expose
    public Double dewPoint;
    @SerializedName("wind_speed")
    @Expose
    public Double windSpeed;
    @SerializedName("wind_deg")
    @Expose
    public Integer windDeg;
    @SerializedName("weather")
    @Expose
    public List<Weather> weather = new ArrayList<>();
    @SerializedName("clouds")
    @Expose
    public Integer clouds;
    @SerializedName("uvi")
    @Expose
    public Double uvi;
//    @SerializedName("rain")
//    @Expose
//    public Double rain;
}
