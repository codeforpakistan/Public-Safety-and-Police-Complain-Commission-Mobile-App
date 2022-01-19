package com.example.publicsafetycomission.Response;

import com.example.publicsafetycomission.Modelclasses.Districts;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistrictResponse {

    @SerializedName("response")
    private String response;
    @SerializedName("response_msg")
    private String response_msg;
    @SerializedName("districts")
    private List <Districts> districts;

    public String getResponse() {
        return response;
    }

    public String getResponse_msg() {
        return response_msg;
    }

    public List<Districts> getDistricts() {
        return districts;
    }
}
