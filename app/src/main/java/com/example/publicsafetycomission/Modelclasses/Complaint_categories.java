package com.example.publicsafetycomission.Modelclasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Complaint_categories {

    @SerializedName("complaint_category_id")
    @Expose
    private String complaintId;
    @SerializedName("complaint_category_name")
    @Expose
    private String complaintName;
    @SerializedName("complaint_category_status")
    @Expose
    private String complaintStatus;

    public String getComplaintId() {
        return complaintId;
    }

    public String getComplaintName() {
        return complaintName;
    }

    public String getComplaintStatus() {
        return complaintStatus;
    }
}
