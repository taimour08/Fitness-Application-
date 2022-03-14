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

import org.w3c.dom.Text;

public class CreatePlan extends AppCompatActivity {

    private Button save , view ;

    DatabaseHelper databaseHelper ;

    private TextView tv1 , tv2 , tv3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        tv1 = findViewById(R.id.nameadress);
        tv2 = findViewById(R.id.DateField);
        tv3 = findViewById(R.id.plan);
        save = findViewById(R.id.submitplan) ;
        view = findViewById(R.id.submitplan2) ;

        databaseHelper = new DatabaseHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = tv1.getText().toString() ;
                String date = tv2.getText().toString() ;
                String plan = tv3.getText().toString() ;

                boolean checkinsertdata = databaseHelper.insertuserdata(name , date , plan);
                if(checkinsertdata == true )
                {
                    Toast.makeText(CreatePlan.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(CreatePlan.this, "Failed to Insert", Toast.LENGTH_SHORT).show();

                }

            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = databaseHelper.getdata();
                if(res.getCount() == 0)
                {
                    Toast.makeText(CreatePlan.this, "no entry exists", Toast.LENGTH_SHORT).show();

                    return;
                }

                StringBuffer stringBuffer = new StringBuffer();
                while (res.moveToNext())
                {
                    stringBuffer.append("Name" + res.getString(0)+ "\n");
                    stringBuffer.append("Duration" + res.getString(1)+ "\n");
                    stringBuffer.append("Plan" + res.getString(2)+ "\n\n");
                }


                AlertDialog.Builder builder = new AlertDialog.Builder(CreatePlan.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(stringBuffer.toString());
                builder.show();
            }
        });


    }


}