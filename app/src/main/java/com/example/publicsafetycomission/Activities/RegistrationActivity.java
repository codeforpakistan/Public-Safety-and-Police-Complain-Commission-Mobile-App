package com.example.publicsafetycomission.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.publicsafetycomission.APIinterface.API;
import com.example.publicsafetycomission.R;
//import com.example.publicsafetycomission.Response.API;
import com.example.publicsafetycomission.Response.RegsiterUserResponse;
import com.example.publicsafetycomission.Response.RestAdapter;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrationActivity extends AppCompatActivity {

    EditText usernamereg, passwordreg, editTextCountryCode, phonereg;
    Button btnregister;
    TextView tvlogin;
    String username, password, phone, code;
    ImageView passvisible,passhide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        usernamereg = findViewById(R.id.etuserreg);
        passwordreg = findViewById(R.id.etpasswordreg);
        editTextCountryCode = findViewById(R.id.editTextCountryCode);
        phonereg = findViewById(R.id.etphonereg);
        btnregister = findViewById(R.id.btnregister);
        tvlogin = findViewById(R.id.tvlogin);
        passvisible = findViewById(R.id.pastePinfirstreg);
        passvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordreg.setTransformationMethod(null);
                passvisible.setVisibility(View.GONE);
                passhide.setVisibility(View.VISIBLE);

            }

        });

        passhide = findViewById(R.id.pastePinfirsthidereg);
        passhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordreg.setTransformationMethod(new PasswordTransformationMethod());
                passhide.setVisibility(View.GONE);
                passvisible.setVisibility(View.VISIBLE);

            }
        });


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernamereg.getText().toString();
                code = editTextCountryCode.getText().toString().trim();
                phone = phonereg.getText().toString();
                password = passwordreg.getText().toString();
//
                if (username.isEmpty()) {
                    usernamereg.setError("username is required");
                    usernamereg.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    passwordreg.setError("password is required");
                    passwordreg.requestFocus();
                    return;
                }

                if (phone.isEmpty() || phone.length() < 10) {
                    phonereg.setError("Valid number is required");
                    phonereg.requestFocus();
                    return;
                }

                String phoneNumber = code + phone;

                Intent intent = new Intent(RegistrationActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
                Log.d("userdata", "onClick: " + username + password);
                registerMe();
            }
        });
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(RegistrationActivity.this, UserloginActivity.class);
                startActivity(n);
            }
        });
    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
//            Intent intent = new Intent(this, VerifyPhoneActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//            startActivity(intent);
//        }
//    }




    private void registerMe() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        API api = retrofit.create(API.class);
        API api = RestAdapter.createAPI();

        Call<RegsiterUserResponse> call = api.getUserRegi(username, password,phone);
        call.enqueue(new Callback<RegsiterUserResponse>() {
            @Override
            public void onResponse(Call<RegsiterUserResponse> call, Response<RegsiterUserResponse> response) {
//                Log.i("Responsestring", response.body().toString());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d( response.body().getResponse(),"responseLog");
                        Log.i("onSuccess", response.body().toString());
                        Toast.makeText(RegistrationActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                        String jsonresponse = response.body().getResponse();
                        try {
                            parseRegData(jsonresponse);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegsiterUserResponse> call, Throwable t) {
                Log.d("go", "onFailure: " + t.toString());
            }
        });
    }


    private void parseRegData(String response) throws JSONException {

            Toast.makeText(RegistrationActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(RegistrationActivity.this, VerifyPhoneActivity.class);
//            finish();
//            startActivity(intent);
//
    }



}
