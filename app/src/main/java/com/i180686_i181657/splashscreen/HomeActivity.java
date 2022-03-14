package com.i180686_i181657.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    TextView Forgot_btn;
    TextView Create_acc;
    Button loginbtn;
    Button Guest_SignIn_btn;
    EditText Email, password_id;
    ProgressBar progressBar ;

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        Objects.requireNonNull(getSupportActionBar()).hide();


        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(HomeActivity.this , HomeScreen.class));
        }

        Email = findViewById(R.id.nameadress);
        password_id = findViewById(R.id.DateField);
        loginbtn = findViewById(R.id.plan);
        Guest_SignIn_btn = findViewById(R.id.submitplan);
        Forgot_btn = findViewById(R.id.Forgot_Button);
        Create_acc = findViewById(R.id.CreateAccount);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        FirebaseMessaging.getInstance().subscribeToTopic("Exercise")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                    }
                });

        loginbtn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


//                startActivity(new Intent(HomeActivity.this , SignUp.class));

                String loginEmail = Email.getText().toString().trim();
                String loginPass = password_id.getText().toString() ;



                if (TextUtils.isEmpty(loginEmail)) {
                    Email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(loginPass)) {
                    password_id.setError("Password is required");
                    return;
                }
                if(loginPass.length() < 6)
                {
                    password_id.setError("Password Must be 6 digits");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                mAuth.signInWithEmailAndPassword(loginEmail , loginPass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(HomeActivity.this, "Logged In Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(HomeActivity.this , HomeScreen.class));
                        }
                        else
                        {
                            Toast.makeText(HomeActivity.this, "Error ! "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        Create_acc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this , SignUp.class));

            }
        });

        Guest_SignIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this , HomeScreen.class));
            }
        });

        Forgot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this , forgetpassword.class));

            }
        });
    }



}