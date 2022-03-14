package com.i180686_i181657.splashscreen;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Objects;

public class HomeScreen extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;



    Button create , view , lgot ;

    TextView viewprofile;
    ListView listView;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Objects.requireNonNull(getSupportActionBar()).hide();
        navigationView = findViewById(R.id.navigationbar);
        drawerLayout = findViewById(R.id.drawablelayout);
        toolbar = findViewById(R.id.mytoolbar);

        lgot = findViewById(R.id.lgot) ;
//        b1 = findViewById(R.id.include);
        create = findViewById(R.id.create);
//        view = findViewById(R.id.view) ;

        viewprofile = findViewById(R.id.viewprofile);

        viewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeScreen.this , EditProfile.class));
            }
        });


        recyclerView = findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ExercisesData[] exercisesData = new ExercisesData[]
                {
                        new ExercisesData("GET MOVING", "Exercise Through Game", R.drawable.squats),
                        new ExercisesData("FITNESS MASTER", "Exercise Through Game", R.drawable.pushups),
                        new ExercisesData("FULL BODY", "7*4 Challenge", R.drawable.fullbody),
                        new ExercisesData("LOWER BODY", "7*4 Challenge", R.drawable.lowerbody),
                        new ExercisesData("BEST QUARANTINE WORKOUT", "5 workouts", R.drawable.qurentine),
                        new ExercisesData("ABS BEGINNER", "7*4 Challenge", R.drawable.abs_beginner),
                        new ExercisesData("CHEST BEGINNER", "7*4 Challenge", R.drawable.chestbeginner),
                        new ExercisesData("ARM BEGINNER", "7*4 Challenge", R.drawable.armbeginners),


                };




        MyExerciseAdapter myExerciseAdapter = new MyExerciseAdapter(exercisesData , HomeScreen.this);
        recyclerView.setAdapter(myExerciseAdapter);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(HomeScreen.this, drawerLayout, R.string.navigation_close, R.string.navigation_open);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        lgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logout();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeScreen.this , CreatePlan.class));
            }
        });


    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(HomeScreen.this, HomeActivity.class));
        finish();
    }

}