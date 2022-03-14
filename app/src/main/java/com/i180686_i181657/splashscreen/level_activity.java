package com.i180686_i181657.splashscreen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class level_activity extends AppCompatActivity {

    Button easy , medium , hard ;
    TextView description ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        Objects.requireNonNull(getSupportActionBar()).hide();

        easy = findViewById(R.id.easy) ;
        medium = findViewById(R.id.medium) ;
        hard = findViewById(R.id.hard);

        description = findViewById(R.id.description);


        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(level_activity.this , black_screen.class));
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(level_activity.this , black_screen.class));

            }
        });

        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append("This activity involves three levels. In the first level the user has to dodge the obstacle by moving down. "+ "\n" +
                                        "In the second level, the user has to move up, down and jump as well. " + "\n" +
                                        "In the last level, another obstacle, which is a ball, is thrown towards the user"  + "\n");




                AlertDialog.Builder builder = new AlertDialog.Builder(level_activity.this);
                builder.setCancelable(true);
                builder.setTitle("Game Description");
                builder.setMessage(stringBuffer.toString());
                builder.show();
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(level_activity.this , black_screen.class));

            }
        });
    }
}