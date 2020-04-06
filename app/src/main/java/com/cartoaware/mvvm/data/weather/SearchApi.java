package com.cartoaware.mvvm.data.weather;

import com.cartoaware.mvvm.model.weather.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {

    @GET("onecall")
    Call<WeatherResponse> getOneCallForLocation(@Query("lat") Double lat,
                                                @Query("lon") Double lon,
                                                @Query("units") String units,
                                                @Query("appid") String apiKey);
}

