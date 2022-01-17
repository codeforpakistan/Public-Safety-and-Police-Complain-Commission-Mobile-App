package com.example.publicsafetycomission.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegsiterResponse {



    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("response_msg")
    @Expose
    private String responseMsg;
    @SerializedName("data")
    @Expose
    private RegisterDataClass registerDataClass;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public RegisterDataClass getData() {
        return registerDataClass;
    }

    public void setData(Data data) {
        this.registerDataClass = registerDataClass;
    }


//    @SerializedName("response")
//    @Expose
//    private String response;
//    public String getResponse() {
//        return response;
//    }
//
//    public void setResponse(String response) {
//        this.response = response;
//    }
}
