package com.example.publicsafetycomission.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginUserResponse {


    @SerializedName("response")
    @Expose
    private Integer response;

    @SerializedName("data")
    @Expose
    private Data data;


    @SerializedName("response_msg")
    @Expose
    private String responseMsg;

    public Integer getResponse() {
        return response;
    }

    public Data getData() {
        return data;
    }

    public String getResponseMsg() {
        return responseMsg;
    }








}

