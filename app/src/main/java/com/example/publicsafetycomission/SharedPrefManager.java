package com.example.publicsafetycomission;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.publicsafetycomission.Response.Data;

public class SharedPrefManager {
    private static String SHARED_PREF_NAME = "publicsafetycommission";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    Context context;

    public SharedPrefManager(Context context) {
        this.context = context;

    }


    public void saveuser(Data data){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("token", data.getToken());
        editor.putBoolean("logged",true);
        editor.apply();
    }

    public boolean isLoggedIn(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean("logged",false);

    }
    public Data getUser(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        return new Data(sharedPreferences.getString("token",null));

    }
   public void Logout(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();


    }
}
