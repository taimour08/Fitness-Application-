package com.i180686_i181657.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Objects;

public class pythonactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pythonactivity);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}