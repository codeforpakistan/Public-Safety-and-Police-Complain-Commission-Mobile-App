package com.example.publicsafetycomission.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterUserDataClass {


    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("complainant_id")
    @Expose
    private Integer complainantId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getComplainantId() {
        return complainantId;
    }

    public void setComplainantId(Integer complainantId) {
        this.complainantId = complainantId;
    }
}
