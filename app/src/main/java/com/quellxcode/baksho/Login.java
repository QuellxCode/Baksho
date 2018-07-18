package com.quellxcode.baksho;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bukhari on 12/14/2017.
 */

public class Login extends AppCompatActivity {
Context context;
    Object ob;
    ProgressDialog progressDialog;
    String email,password;
    JSONObject object;
    JSONObject user;
    @Bind(R.id.edt_Email)
    EditText edt_email;
    @Bind(R.id.edt_password)
    EditText edt_password;
    @Bind(R.id.btn_Login)
    Button btn_login;
    @Bind(R.id.btn_Google)
    Button btn_google;
    @Bind(R.id.btn_Facebook)
    Button btn_facebook;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        ButterKnife.bind(this);
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            //  login();
                Intent intent= new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });
        btn_facebook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //  login();
                Intent intent= new Intent(Login.this,Home.class);
                startActivity(intent);
            }
        });


    }

    public void login() {
        if (!validate()) {
            onLoginFailed();
            return;
        }
        if (isNetworkAvailable()) {
            progressDialog = new ProgressDialog(Login.this, R.style.AppTheme);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
            object = new JSONObject();
            try {
                object.put("email", email);
                object.put("password", password);
                user = new JSONObject();
                user.put("user", object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Login();
        } else {
            Toast.makeText(context, "Please connect to internet!", Toast.LENGTH_SHORT).show();
        }
    }

        public boolean validate() {
            boolean valid = true;
            email = edt_email.getText().toString();
            password = edt_password.getText().toString();
            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edt_email.setError("enter a valid email address.");
                valid = false;
            } else {
                edt_email.setError(null);
            }
            if (password.isEmpty()) {
                edt_password.setError("password cannot be empty.");
                valid = false;
            } else {
                edt_password.setError(null);
            }
            return valid;
        }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void onLoginFailed() {
        Toast.makeText(context, "Login Failed!", Toast.LENGTH_SHORT).show();
        btn_login.setEnabled(true);
    }


    public void Login() {
        final AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Content-Type", "application/json");
        client.addHeader("Access_Token", "e89d681a9bbf8a353d5013209bbffcfc1658");


    }






    }
