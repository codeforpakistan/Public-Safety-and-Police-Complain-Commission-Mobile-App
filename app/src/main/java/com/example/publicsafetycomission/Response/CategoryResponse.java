package com.example.publicsafetycomission.Response;

import com.example.publicsafetycomission.Modelclasses.Complaint_categories;
import com.example.publicsafetycomission.Modelclasses.Districts;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {

    @SerializedName("response")
    private String response;
    @SerializedName("response_msg")
    private String response_msg;
    @SerializedName("complaint_categories")
    private List <Complaint_categories> complaint_categories;

    public String getResponse() {
        return response;
    }

    public String getResponse_msg() {
        return response_msg;
    }

    public List<Complaint_categories> getComplaint_categories() {
        return complaint_categories;
    }
}
