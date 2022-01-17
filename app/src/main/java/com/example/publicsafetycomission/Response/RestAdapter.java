package com.example.publicsafetycomission.Response;


import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.publicsafetycomission.APIinterface.API;
import com.example.publicsafetycomission.Activities.UserloginActivity;
import com.example.publicsafetycomission.BuildConfig;
import com.example.publicsafetycomission.SharedPrefManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RestAdapter {
    public static final String BASE_URL = "https://ppsc.kp.gov.pk/Api/";

//    private static   SharedPrefManager sharedPrefManager;
    public static API createAPI() {
//String gottoken = sharedPrefManager.getUser().token;

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            builder
//                    .addInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request newRequest = chain.request().newBuilder()
//                            .addHeader("token",gottoken).build();
//                    return chain.proceed(newRequest);
//
//                }
//            })
                    .addInterceptor(logging);
        }
        builder.cache(null);
        OkHttpClient okHttpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(API.class);
    }


}
