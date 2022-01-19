package com.example.publicsafetycomission.Response;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.publicsafetycomission.APIinterface.API;
import com.example.publicsafetycomission.BuildConfig;
import com.example.publicsafetycomission.SharedPrefManager;
import com.google.android.gms.common.api.Api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.publicsafetycomission.Activities.UserloginActivity.token;
import static com.example.publicsafetycomission.Response.RestAdapter.BASE_URL;


public class RetrofitClient {

//    public static final String BASE_URL = "http://ppsc.kp.gov.pk/Api/";

//    private static SharedPrefManager sharedPrefManager;
//    private static Context context;
//
//
//    public static API createAPI(Context context1) {
//
//        context = context1;
//
//        sharedPrefManager = new SharedPrefManager(context);
//        String gottoken = sharedPrefManager.getUser().getToken();
//
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout(5, TimeUnit.SECONDS);
//        builder.writeTimeout(10, TimeUnit.SECONDS);
//        builder.readTimeout(30, TimeUnit.SECONDS);
//        if (BuildConfig.DEBUG) {
//            builder
//                    .addInterceptor(new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request newRequest = chain.request().newBuilder()
//                                    .addHeader("Authorization", gottoken).build();
//                            return chain.proceed(newRequest);
//
//                        }
//                    })
//                    .addInterceptor(logging);
//        }
//        builder.cache(null);
//        OkHttpClient okHttpClient = builder.build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build();
//
//        return retrofit.create(API.class);
//    }


}

