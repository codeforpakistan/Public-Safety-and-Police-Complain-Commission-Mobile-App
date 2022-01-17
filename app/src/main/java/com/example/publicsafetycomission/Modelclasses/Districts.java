package com.example.publicsafetycomission.Modelclasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Districts {

    @SerializedName("district_id")
    @Expose
    private String districtId;
    @SerializedName("district_name")
    @Expose
    private String districtName;
    @SerializedName("district_status")
    @Expose
    private String districtStatus;

    public String getDistrictId() {
        return districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public String getDistrictStatus() {
        return districtStatus;
    }
}
