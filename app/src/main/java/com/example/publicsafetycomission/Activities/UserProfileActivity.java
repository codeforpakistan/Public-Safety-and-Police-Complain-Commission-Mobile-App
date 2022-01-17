package com.example.publicsafetycomission.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.publicsafetycomission.APIinterface.API;
import com.example.publicsafetycomission.R;
import com.example.publicsafetycomission.Response.DistrictResponse;
import com.example.publicsafetycomission.Response.RestAdapter;
import com.example.publicsafetycomission.Response.UpdateUserResponse;
import com.example.publicsafetycomission.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {
    String phoneNumber;
    TextView mobileNumber;

    Spinner districtspinner;
    List<String> ListDistrictName;
    List<String> ListDistrictID;
    String districtID;
    String districtName;

    EditText fullname, cinic, email, address, fathername, uc, contact;
    Button next;
    String profilename, profilefname, profilegender, profilecnic, profileemail, profiledistrict, profileuc, profileaddress, profilecontact, passtoken;
    public SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        districtspinner = findViewById(R.id.spinnerd);
        ListDistrictName = new ArrayList<>();
        ListDistrictID = new ArrayList<>();
        ListDistrictName.add("Select District");
        ListDistrictID.add("0");

        sharedPrefManager = new SharedPrefManager(getApplicationContext());


        if (sharedPrefManager != null)
            passtoken = sharedPrefManager.getUser().getToken();
        else
            Toast.makeText(this, "pref is null", Toast.LENGTH_LONG).show();

        Log.d("aa", "token= " + passtoken);


        String[] genders = getResources().getStringArray(R.array.gender);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_menu_item, genders);
        AutoCompleteTextView autocompleteTV = findViewById(R.id.datesFilterSpinner);
        autocompleteTV.setAdapter(arrayAdapter);
        displayDistrict();


        districtspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                districtName = ListDistrictName.get(i);
//                districtID = ListDistrictID.get(i);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


//        String[] districts = getResources().getStringArray(R.array.districts);
//        ArrayAdapter arrayAdapterd = new ArrayAdapter(this, R.layout.dropdown_menu_item, districts);
//        AutoCompleteTextView dropdistrict = findViewById(R.id.dropdistrict);
//        dropdistrict.setAdapter(arrayAdapterd);


        fullname = findViewById(R.id.fullname);
        contact = findViewById(R.id.contact);
        fathername = findViewById(R.id.fname);
        cinic = findViewById(R.id.cinic);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        uc = findViewById(R.id.unioncouncil);
        next = findViewById(R.id.next);

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilename = fullname.getText().toString();
                profilefname = fathername.getText().toString().trim();
                profilecnic = cinic.getText().toString();
                profiledistrict = String.valueOf(districtspinner.getAdapter());
                profilegender = String.valueOf(autocompleteTV.getAdapter());
                profileemail = email.getText().toString();
                profileaddress = address.getText().toString();
                profileuc = uc.getText().toString();
                profilecontact = contact.getText().toString();
//
                if (profilename.isEmpty()) {
                    fullname.setError("this feild is required to fill");
                    fullname.requestFocus();
                    return;
                }
                if (profilefname.isEmpty()) {
                    fathername.setError("this feild is required to fill");
                    fathername.requestFocus();
                    return;
                }
                if (profilecontact.isEmpty()) {
                    contact.setError("this feild is required to fill");
                    contact.requestFocus();
                    return;
                }

                if (profilecnic.isEmpty()) {
                    cinic.setError("this feild is required to fill");
                    cinic.requestFocus();
                    return;
                }
                if (profileaddress.isEmpty()) {
                    address.setError("this feild is required to fill");
                    address.requestFocus();
                    return;
                }
                if (profileemail.isEmpty()) {
                    email.setError("this feild is required to fill");
                    email.requestFocus();
                    return;
                }

                if (profileuc.isEmpty()) {
                    uc.setError("this feild is required to fill");
                    uc.requestFocus();
                    return;
                }

                update();
            }
        });
        // get saved phone number
//        SharedPreferences prefs =  getApplicationContext().getSharedPreferences("USER_PREF",
//                Context.MODE_PRIVATE);
//        phoneNumber = prefs.getString("phoneNumber", NULL);

//        mobileNumber = findViewById(R.id.mobileNumber);
//        mobileNumber.setText(phoneNumber);

    }

    private void update() {
        API api = RestAdapter.createAPI();
        //        API service = RetrofitClient.getRetrofitInstance().create(API.class);
        Log.d("call_call", "onClick: " + profilename + cinic+passtoken);
        Call<UpdateUserResponse> call = api.sentUserUpdateddata(profilecontact, profilename, profilefname, profilegender, profilecnic, profileemail, profiledistrict, profileuc, profileaddress, passtoken);
        call.enqueue(new Callback<UpdateUserResponse>() {
            @Override
            public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {
                UpdateUserResponse loginresponse = response.body();
                if (response.isSuccessful()) {
                    if (loginresponse != null) {

                        Toast.makeText(UserProfileActivity.this, "profile updated" + passtoken, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserProfileActivity.this, HomeScreen.class);
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
            public void onFailure(Call<UpdateUserResponse> call, Throwable t) {
                Log.d("LoginBHai_error", "onFailure: " + "Throw" + t.toString());
            }
        });

    }

    private void displayDistrict() {

        API api = RestAdapter.createAPI();
        Call<DistrictResponse> call = api.getDistricts(passtoken);
        call.enqueue(new Callback<DistrictResponse>() {
            @Override
            public void onResponse(@NonNull Call<DistrictResponse> call, @NonNull Response<DistrictResponse> response) {
                DistrictResponse districtresponse = response.body();
                if (response.isSuccessful()) {
                    if (districtresponse != null) {

                        Toast.makeText(UserProfileActivity.this, "district update"+response, Toast.LENGTH_SHORT).show();



                        try {

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DistrictResponse> call, Throwable t) {
                Toast.makeText(UserProfileActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(UserProfileActivity.this, HomeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        super.onBackPressed();
    }
}