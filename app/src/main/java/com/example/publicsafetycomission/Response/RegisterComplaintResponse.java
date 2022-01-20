package com.example.publicsafetycomission.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterComplaintResponse {
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("response_msg")
    @Expose
    private String responsemsg;
    @SerializedName("complaint_id")
    @Expose
    private String complaintID;
    public String getResponse() {
        return response;
    }

    public String getResponsemsg() {
        return responsemsg;
    }

    public void setResponsemsg(String responsemsg) {
        this.responsemsg = responsemsg;
    }

    public String getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(String complaintID) {
        this.complaintID = complaintID;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}