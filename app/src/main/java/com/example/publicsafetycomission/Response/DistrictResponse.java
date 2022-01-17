package com.example.publicsafetycomission.Response;

import com.example.publicsafetycomission.Modelclasses.Districts;
import com.google.gson.annotations.SerializedName;

public class DistrictResponse {

    @SerializedName("response")
    private Integer response;
    @SerializedName("response_msg")
    private String response_msg;
    @SerializedName("districts")
    private Districts districts;

    public Integer getResponse() {
        return response;
    }

    public String getResponse_msg() {
        return response_msg;
    }

    public Districts getDistricts() {
        return districts;
    }
}
