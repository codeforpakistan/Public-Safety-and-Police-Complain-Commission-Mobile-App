package com.example.publicsafetycomission.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {


    @SerializedName("token")
    @Expose
    public String token;


    public Data(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }


}
