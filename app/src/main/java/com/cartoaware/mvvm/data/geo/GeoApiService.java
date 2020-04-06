package com.cartoaware.mvvm.data.geo;

import com.davidhodge92.weather.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeoApiService {

    private static Retrofit retrofit = new Retrofit.Builder()
            .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
            .baseUrl(BuildConfig.GEO_BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createApiService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
