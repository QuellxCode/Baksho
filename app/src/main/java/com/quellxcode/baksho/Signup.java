package com.quellxcode.baksho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;

/**
 * Created by Bukhari on 12/14/2017.
 */

public class Signup extends AppCompatActivity {

    @Bind(R.id.btn_Signup)
    Button btn_signup;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


    }
}
