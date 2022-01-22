package com.example.publicsafetycomission.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.publicsafetycomission.APIinterface.API;
import com.example.publicsafetycomission.Modelclasses.Complaint_categories;
import com.example.publicsafetycomission.Modelclasses.Districts;
import com.example.publicsafetycomission.R;
import com.example.publicsafetycomission.Response.CategoryResponse;
import com.example.publicsafetycomission.Response.DistrictResponse;
import com.example.publicsafetycomission.Response.RegisterComplaintResponse;
import com.example.publicsafetycomission.Response.RestAdapter;
import com.example.publicsafetycomission.Response.UpdateUserResponse;
import com.example.publicsafetycomission.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintActivity extends AppCompatActivity {

    //    TextView testing;
//    SharedPrefManager sharedPrefManager;
    EditText contact, fullname, fathername, cinic, email, unioncouncil, address, complaintsdetails;
    String contact_comp, fullname_comp, fathername_comp, gender_comp, cinic_comp, email_comp, district_comp, unioncouncil_comp, address_comp, complaintdetials_comp, passtokencomp;
    Button nextcomp;
    public SharedPrefManager sharedPrefManager;

    Spinner districtspinner_comp, spinnergender_comp, spinner_category;
    List<String> ListDistrictName;
    List<String> ListDistrictID;
    String districtID;
    String districtName;


    List<String> ListCategoryName;
    List<String> ListCategoryID;
    String categoryID;
    String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_activity);
//        testing = findViewById(R.id.testing);
//        sharedPrefManager = new  SharedPrefManager(this);
//        testing.setText(sharedPrefManager.getUser().getToken());

        contact = findViewById(R.id.contact_comp);
        fullname = findViewById(R.id.fullname_comp);
        fathername = findViewById(R.id.fname_comp);
        cinic = findViewById(R.id.cinic_comp);
        email = findViewById(R.id.email_comp);
        unioncouncil = findViewById(R.id.unioncouncil_comp);
        address = findViewById(R.id.address_comp);
        complaintsdetails = findViewById(R.id.complaintdetials_comp);


        spinnergender_comp = findViewById(R.id.spinnergender);
        spinner_category = findViewById(R.id.spinnercat_comp);



        districtspinner_comp = findViewById(R.id.spinnerdistrict_comp);
        ListDistrictName = new ArrayList<>();
        ListDistrictID = new ArrayList<>();
        ListDistrictName.add("Select District");
        ListDistrictID.add("0");

        ListCategoryName = new ArrayList<>();
        ListCategoryID = new ArrayList<>();
        ListCategoryName.add("Select Category");
        ListCategoryID.add("0");

        sharedPrefManager = new SharedPrefManager(getApplicationContext());


        if (sharedPrefManager != null)
            passtokencomp = sharedPrefManager.getUser().getToken();
        else
            Toast.makeText(this, "pref is null", Toast.LENGTH_LONG).show();

        Log.d("aa", "token= " + passtokencomp);


        getdistricts();
        getcomplaintcategories();


        districtspinner_comp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                districtName = ListDistrictName.get(i);
                districtID = ListDistrictID.get(i);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


//        String[] districts = getResources().getStringArray(R.array.districts);
//        ArrayAdapter arrayAdapterd = new ArrayAdapter(this, R.layout.dropdown_menu_item, districts);
//        AutoCompleteTextView dropdistrict = findViewById(R.id.dropdistrict);
//        dropdistrict.setAdapter(arrayAdapterd);

        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categoryName = ListCategoryName.get(i);
                categoryID = ListCategoryID.get(i);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        findViewById(R.id.nextcomp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact_comp = contact.getText().toString();
                fullname_comp = fullname.getText().toString();
                fathername_comp = fathername.getText().toString();
                cinic_comp = cinic.getText().toString();
                email_comp = email.getText().toString();
                unioncouncil_comp = unioncouncil.getText().toString();
                address_comp = address.getText().toString();
                complaintdetials_comp = complaintsdetails.getText().toString();
                district_comp = String.valueOf(districtID);
                gender_comp = String.valueOf(spinnergender_comp);

                if (contact_comp.isEmpty()) {
                    contact.setError("this feild is required to fill");
                    contact.requestFocus();
                    return;
                }
//
                if (fullname_comp.isEmpty()) {
                    fullname.setError("this feild is required to fill");
                    fullname.requestFocus();
                    return;
                }
                if (fathername_comp.isEmpty()) {
                    fathername.setError("this feild is required to fill");
                    fathername.requestFocus();
                    return;
                }
                if (cinic_comp.isEmpty()) {
                    cinic.setError("this feild is required to fill");
                    cinic.requestFocus();
                    return;
                }
                if (email_comp.isEmpty()) {
                    email.setError("this feild is required to fill");
                    email.requestFocus();
                    return;
                }

                if (unioncouncil_comp.isEmpty()) {
                    unioncouncil.setError("this feild is required to fill");
                    unioncouncil.requestFocus();
                    return;
                }
                if (address_comp.isEmpty()) {
                    address.setError("this feild is required to fill");
                    address.requestFocus();
                    return;
                }
                if (complaintdetials_comp.isEmpty()) {
                    complaintsdetails.setError("this feild is required to fill");
                    complaintsdetails.requestFocus();
                    return;
                }


                registercomplaint();
            }
        });
        // get saved phone number
//        SharedPreferences prefs =  getApplicationContext().getSharedPreferences("USER_PREF",
//                Context.MODE_PRIVATE);
//        phoneNumber = prefs.getString("phoneNumber", NULL);

//        mobileNumber = findViewById(R.id.mobileNumber);
//        mobileNumber.setText(phoneNumber);

    }

    private void registercomplaint() {
        API api = RestAdapter.createAPI();
        //        API service = RetrofitClient.getRetrofitInstance().create(API.class);
        Log.d("call_call", "onClick: " + passtokencomp);
        Call<RegisterComplaintResponse> call = api.register_complaint(gender_comp, district_comp, unioncouncil_comp, complaintdetials_comp, passtokencomp);
        call.enqueue(new Callback<RegisterComplaintResponse>() {
            @Override
            public void onResponse(Call<RegisterComplaintResponse> call, Response<RegisterComplaintResponse> response) {
                RegisterComplaintResponse registerComplaintResponse = response.body();
                if (response.isSuccessful()) {
                    if (registerComplaintResponse != null) {

                        Toast.makeText(ComplaintActivity.this, "complaint added successfully" + passtokencomp, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ComplaintActivity.this, HomeScreen.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                        Log.i("onSuccess", String.valueOf(response.body()));
//                        Toast.makeText(UserloginActivity.this, "logined", Toast.LENGTH_SHORT).show();


                        try {
//                            Toast.makeText(UserloginActivity.this, "logined", Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterComplaintResponse> call, Throwable t) {
                Log.d("LoginBHai_error", "onFailure: " + "Throw" + t.toString());
            }
        });

    }

    private void getdistricts() {

        API api = RestAdapter.createAPI();
        Call<DistrictResponse> call = api.getDistricts(passtokencomp);
        Log.d("get", "onClick: " + passtokencomp);

        call.enqueue(new Callback<DistrictResponse>() {
            @Override
            public void onResponse(@NonNull Call<DistrictResponse> call, @NonNull Response<DistrictResponse> response) {
                DistrictResponse districtResponse = response.body();
                assert districtResponse != null;
                List<Districts> districts = districtResponse.getDistricts();
//                districts.addAll(districtResponse.getDistricts());
                int size = districts.size();
                if (response.isSuccessful()) {
                    if (districtResponse != null) {
                        for (int i = 0; i < size; i++) {
                            ListDistrictName.add(districts.get(i).getDistrictName());
                            ListDistrictID.add(districts.get(i).getDistrictId());
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ComplaintActivity.this, android.R.layout.simple_spinner_item, ListDistrictName);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            districtspinner_comp.setAdapter(adapter);
                        }
                    }

                    try {


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<DistrictResponse> call, Throwable t) {
                Toast.makeText(ComplaintActivity.this, "failed to load" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getcomplaintcategories() {

        API api = RestAdapter.createAPI();
        Call<CategoryResponse> call = api.getcomplaintcategories(passtokencomp);
        Log.d("get", "onClick: " + passtokencomp);

        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(@NonNull Call<CategoryResponse> call, @NonNull Response<CategoryResponse> response) {
                CategoryResponse categoryResponse = response.body();
//                assert categoryResponse != null;
                List<Complaint_categories> complaint_categories = categoryResponse.getComplaint_categories();
                int size = complaint_categories.size();
                if (response.isSuccessful()) {
                    if (categoryResponse != null) {
                        for (int i = 0; i < size; i++) {
                            ListCategoryName.add(complaint_categories.get(i).getComplaintName());
                            ListCategoryID.add(complaint_categories.get(i).getComplaintId());
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ComplaintActivity.this, android.R.layout.simple_spinner_item, ListCategoryName);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_category.setAdapter(adapter);
                            Toast.makeText(ComplaintActivity.this, "categories fetched", Toast.LENGTH_SHORT).show();
                        }
                    }

                    try {


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Toast.makeText(ComplaintActivity.this, "failed to load" , Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(ComplaintActivity.this, HomeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        super.onBackPressed();
    }

}
