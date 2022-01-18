
package com.example.publicsafetycomission.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
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
import com.example.publicsafetycomission.Response.LoginUserResponse;
import com.example.publicsafetycomission.Response.RestAdapter;
import com.example.publicsafetycomission.Response.RetrofitClient;
import com.example.publicsafetycomission.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserloginActivity extends AppCompatActivity {

    EditText etloginusername, etloginpassword;
    Button loginsubmit;
    TextView register, forgotpass;
    String loginusername, loginpassword;
    ImageView passvisible, passhide;
    SharedPrefManager sharedPrefManager;
    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        passvisible = findViewById(R.id.pastePinfirstlog);
        forgotpass = findViewById(R.id.forgetpassword);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserloginActivity.this, ForgetPassword.class);
                startActivity(i);
            }
        });
        passvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etloginpassword.setTransformationMethod(null);
                passvisible.setVisibility(View.GONE);
                passhide.setVisibility(View.VISIBLE);

            }

        });

        passhide = findViewById(R.id.pastePinfirsthide);
        passhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etloginpassword.setTransformationMethod(new PasswordTransformationMethod());
                passhide.setVisibility(View.GONE);
                passvisible.setVisibility(View.VISIBLE);

            }
        });
        etloginusername = findViewById(R.id.loginusername);
        etloginpassword = findViewById(R.id.loginpassword);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        loginsubmit = findViewById(R.id.loginsubmit);

        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserloginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });


        loginsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginusername = etloginusername.getText().toString();
                loginpassword = etloginpassword.getText().toString();
                if (loginusername.isEmpty()) {
                    etloginusername.setError("this feild is required to fill");
                    etloginusername.requestFocus();
                    return;
                }
                if (loginpassword.isEmpty()) {
                    etloginpassword.setError("this feild is required to fill");
                    etloginpassword.requestFocus();
                    return;
                }
                loginUser();

            }
        });


    }

    private void loginUser() {
        API api = RestAdapter.createAPI();

//        API service = RetrofitClient.getRetrofitInstance().create(API.class);
        Log.d("call_call", "onClick: " + loginusername + loginpassword);
        Call<LoginUserResponse> call = api.getUserLogin(loginusername, loginpassword);
        call.enqueue(new Callback<LoginUserResponse>() {
            @Override
            public void onResponse(Call<LoginUserResponse> call, Response<LoginUserResponse> response) {
                LoginUserResponse loginresponse = response.body();
                if (response.isSuccessful()) {
                    if (loginresponse != null) {
                        token = response.body().getData().getToken();
                        sharedPrefManager.saveuser(loginresponse.getData());
                        Toast.makeText(UserloginActivity.this, "login Successfully" + token, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserloginActivity.this, HomeScreen.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                        Log.i("onSuccess", response.body().getData().getToken());


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
            public void onFailure(Call<LoginUserResponse> call, Throwable t) {
                Log.d("Login_error", "onFailure: " + "Throw" + t.toString());
            }
        });

    }

    /*   private void Login() {

           Log.d("userdata", "onClick: " + loginusername + loginpassword);

           API api = RetrofitClient.getRetrofitInstance().create(API.class);
           Call<LoginUserResponse> call = api.getUserLogin(loginusername, loginpassword);
           call.enqueue(new Callback<LoginUserResponse>() {
               @Override
               public void onResponse(@NonNull Call<LoginUserResponse> call, @NonNull Response<LoginUserResponse> response) {
                   LoginUserResponse loginresponse = response.body();
                   if (response.isSuccessful()) {
                       if (loginresponse.equals("1")) {
   //                                sharedPrefManager.saveuser(loginresponse.getData());
   //                                Log.d("token","token"+loginresponse.getData() );
                           token = response.body().getData().getToken();

   //                                Intent intent = new Intent(UserloginActivity.this,HomeScreen.class);
   //                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
   //                                startActivity(intent);
                           Toast.makeText(UserloginActivity.this, "login Successfully" + token, Toast.LENGTH_SHORT).show();

                       } else {
                           Toast.makeText(UserloginActivity.this, "incorrect username or password", Toast.LENGTH_SHORT).show();
                       }
                   }
               }

               @Override
               public void onFailure(@NonNull Call<LoginUserResponse> call, @NonNull Throwable t) {
                   Log.d("failed", "onFailure: " + t.toString());
               }
           });
       }
   */
    @Override
    protected void onStart() {
        super.onStart();
        if (sharedPrefManager.isLoggedIn()) {

            Toast.makeText(this, "isloggedin", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UserloginActivity.this, HomeScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

}