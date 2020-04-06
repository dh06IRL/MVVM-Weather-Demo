package com.cartoaware.mvvm.data.weather;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.cartoaware.mvvm.model.weather.WeatherResponse;
import com.cartoaware.mvvm.utils.Constants;
import com.davidhodge92.weather.BuildConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private static WeatherRepository weatherRepository = new WeatherRepository();
    private SearchApi searchApi = ApiService.createApiService(SearchApi.class);

    public static WeatherRepository getInstance() {
        return weatherRepository;
    }

    public MutableLiveData<WeatherResponse> getLocalWeatherData(Double lat, Double lon) {
        MutableLiveData<WeatherResponse> mutableLiveData = new MutableLiveData<>();
        Call<WeatherResponse> call = searchApi.getOneCallForLocation(lat,
                lon, Constants.DEFAULT_UNITS, BuildConfig.WEATHER_API_KEY);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                }else{
                    try {
                        Log.e("WMVVM", response.errorBody().string());
                    }catch (Exception e){}
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
                Log.e("WMVVM", t.toString());
            }
        });
        return mutableLiveData;
    }
}
