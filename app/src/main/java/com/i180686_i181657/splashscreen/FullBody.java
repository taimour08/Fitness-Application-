package com.i180686_i181657.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class FullBody extends AppCompatActivity {

    ListView listView ;
    ImageView img ;
    TextView tv1 , tv2 ;


    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_body);

        listView = findViewById(R.id.listviewbody) ;

        ArrayList<String> arrayList = new ArrayList<>();
        searchView = findViewById(R.id.searching);
        img = findViewById(R.id.bodyimage);
        tv1 = findViewById(R.id.tv1) ;
        tv2 = findViewById(R.id.tv2) ;




        arrayList.add("Day1");
        arrayList.add("Day2");
        arrayList.add("Day3");
        arrayList.add("Day4");
        arrayList.add("Day5");
        arrayList.add("Day6");
        arrayList.add("Day7");

        arrayList.add("Demo Video");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 , arrayList);
        listView.setAdapter(arrayAdapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayAdapter.getFilter().filter(s);
                return false;
            }
        });

        img.setImageResource(getIntent().getIntExtra("imagename" , 0));
        tv1.setText(getIntent().getStringExtra("ExerciesName"));
        tv2.setText(getIntent().getStringExtra("Description"));





    }

}