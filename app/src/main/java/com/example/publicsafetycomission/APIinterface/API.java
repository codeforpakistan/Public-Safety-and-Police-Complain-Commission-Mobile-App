package com.example.publicsafetycomission.APIinterface;

import com.example.publicsafetycomission.Response.DistrictResponse;
import com.example.publicsafetycomission.Response.LoginUserResponse;
import com.example.publicsafetycomission.Response.RegsiterResponse;
import com.example.publicsafetycomission.Response.UpdateUserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

//    String BaseURL = "http://ppsc.kp.gov.pk/Api/";


    //register

    @FormUrlEncoded
    @POST("complainant_registration")
    Call<RegsiterResponse> getUserRegi(
            @Field("user_name") String username,
            @Field("user_password") String password,
            @Field("user_contact") String phone
    );

    //    Update profile
    @FormUrlEncoded
    @POST("complainant_profile_update")
    Call<UpdateUserResponse> sentUserUpdateddata(
            @Field("complainant_contact") String contact,
            @Field("complainant_name") String firstname,
            @Field("complainant_guardian_name") String fname,
            @Field("complainant_gender") String gender,
            @Field("complainant_cnic") String cnic,
            @Field("complainant_email") String email,
            @Field("complainant_district_id_fk") String district,
            @Field("complainant_union_council") String unioncouncil,
            @Field("complainant_address") String address,
            @Field("token") String passtoken
    );


    //    login
    @FormUrlEncoded
    @POST("login_complainant")
    Call<LoginUserResponse> getUserLogin(
            @Field("user_name") String user_name,
            @Field("user_password") String user_password
    );

   //get districts
    @FormUrlEncoded
   @POST("districts_get")
   Call<DistrictResponse> getDistricts(
            @Field("token") String passtoken

    );




}
