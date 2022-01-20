package com.example.publicsafetycomission.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegsiterUserResponse {



    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("response_msg")
    @Expose
    private String responseMsg;
    @SerializedName("data")
    @Expose
    private RegisterUserDataClass registerUserDataClass;

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

    public RegisterUserDataClass getData() {
        return registerUserDataClass;
    }

    public void setData(Data data) {
        this.registerUserDataClass = registerUserDataClass;
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
