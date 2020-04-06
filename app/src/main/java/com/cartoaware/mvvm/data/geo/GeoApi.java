package com.cartoaware.mvvm.data.geo;

import com.cartoaware.mvvm.model.geo.GeoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoApi {

    @GET("reverse.php")
    Call<GeoResponse> reverseLatLng(@Query("lat") Double lat,
                                    @Query("lon") Double lon,
                                    @Query("key") String apikey,
                                    @Query("format") String format);
}
