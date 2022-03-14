package com.i180686_i181657.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

        Thread splashTread = new Thread()
        {


            @Override

            public void run()
            {

                try
                {

                    sleep(3000);

                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                    finish();

                }
                catch (InterruptedException e)
                {

                    e.printStackTrace();

                }


                super.run();

            }

        };
        splashTread.start();





    }




}
