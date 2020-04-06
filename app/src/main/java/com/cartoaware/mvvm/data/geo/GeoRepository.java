package com.cartoaware.mvvm.data.geo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.cartoaware.mvvm.model.geo.GeoResponse;
import com.davidhodge92.weather.BuildConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeoRepository {

    private static GeoRepository geoRepository = new GeoRepository();
    private GeoApi geoApi = GeoApiService.createApiService(GeoApi.class);

    public static GeoRepository getInstance() {
        return geoRepository;
    }

    public MutableLiveData<GeoResponse> getLocalGeoData(Double lat, Double lon) {
        MutableLiveData<GeoResponse> mutableLiveData = new MutableLiveData<>();
        Call<GeoResponse> call = geoApi.reverseLatLng(lat,
                lon, BuildConfig.GEO_API_KEY, "json");
        call.enqueue(new Callback<GeoResponse>() {
            @Override
            public void onResponse(Call<GeoResponse> call, Response<GeoResponse> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                }else{
                    try {
                        Log.e("WMVVM", response.errorBody().string());
                    }catch (Exception e){}
                }
            }

            @Override
            public void onFailure(Call<GeoResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
                Log.e("WMVVM", t.toString());
            }
        });
        return mutableLiveData;
    }
}
