package com.i180686_i181657.splashscreen;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Objects;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import android.os.Bundle;

import java.util.Objects;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;


public class SignUp extends AppCompatActivity {

    private EditText email, password , conf_password , name , mobile ;
    private Button create ;
    private ImageView profilepic ;
    int SELECT_PICTURE = 200;
    Uri imageuri;
    Bitmap bitmap ;

    private FirebaseAuth mAuth ;
    private DatabaseReference databaseReference ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();
        name            = findViewById(R.id.UserNameField) ;
        email           = findViewById(R.id.emailField) ;
        password        = findViewById(R.id.passwordfield) ;
        conf_password   = findViewById((R.id.Confirmpasswordfield)) ;
        mobile          = findViewById(R.id.MobileField) ;
        create          = findViewById(R.id.AccountCreate_button) ;
        profilepic      = findViewById(R.id.AddImageClick) ;
        mAuth            = FirebaseAuth.getInstance();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("My Notification" , "My Notification" , NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        profilepic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                imageChooser();

            }
        });


        create.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                NotificationCompat.Builder builder = new NotificationCompat.Builder(SignUp.this , "My Notification");
                builder.setContentTitle("NOTIFICATION") ;
                builder.setContentText("YOUR ACCOUNT HAS BEEN CREATED SUCCESSFULLY") ;
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(SignUp.this);
                managerCompat.notify(1,builder.build());

                create_User();
                UploadToFirebase();

            }
        });




    }

    private void UploadToFirebase()
    {
        String m_email = email.getText().toString();
        String m_pass = password.getText().toString();
        String m_name = name.getText().toString();
        String m_mobile = mobile.getText().toString();
        String m_conf_pass = conf_password.getText().toString();


        if (TextUtils.isEmpty(m_email)) {
            email.setError("Email is required");
            return;
        }
        if (TextUtils.isEmpty(m_pass)) {
            password.setError("Password is required");
            return;
        }
        if (TextUtils.isEmpty(m_name)) {
            name.setError("Name is required");
            return;
        }
        if (TextUtils.isEmpty(m_mobile)) {
            mobile.setError("Contact number is required");
            return;
        }
        if (TextUtils.isEmpty(m_conf_pass)) {
            conf_password.setError("Please type confirm password");
            return;
        }
        if (m_pass.length() < 6) {
            password.setError("Password must be 6 digit long");
            return;
        }
        if (m_mobile.length() <=  11) {
            mobile.setError("You entered incorrect number");
            return;
        }
        if (m_conf_pass.length() != m_pass.length()) {
            conf_password.setError("Password mismatched");
            return;
        }

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("File Uploading");
        dialog.show();
//

        FirebaseStorage storage = FirebaseStorage.getInstance() ;
        StorageReference uploader = storage.getReference("image1"+ new Random().nextInt(50));

        uploader.putFile(imageuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                dialog.dismiss();
                                FirebaseDatabase db = FirebaseDatabase.getInstance();
                                DatabaseReference root = db.getReference("users");

                                DataHolder obj = new DataHolder(imageuri.toString() , name.getText().toString() , mobile.getText().toString() , email.getText().toString() , password.getText().toString());

                                root.child(mobile.getText().toString()).setValue(obj) ;

                                name.setText("");
                                mobile.setText("");
                                email.setText("");
                                password.setText("");
                                profilepic.setImageResource(R.drawable.ic_add_a_photo);
                                conf_password.setText("");

                                Toast.makeText(SignUp.this, "Account Created SuccessFully", Toast.LENGTH_SHORT).show();




                            }
                        });
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot)
                    {
                        float percent = (100 * snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                        dialog.setMessage("Uploaded:" + (int)percent+ "%");

                    }
                });

    }


    void imageChooser()
    {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null)
        {

            imageuri = data.getData() ;

            profilepic.setImageURI(imageuri);

        }
        else
        {

            Toast.makeText(SignUp.this, "Error , Try Again!!!!!", Toast.LENGTH_SHORT).show();
        }
    }



    private void create_User()
    {
        String m_email = email.getText().toString();
        String m_pass = password.getText().toString();
        String m_name = name.getText().toString();
        String m_mobile = mobile.getText().toString();
        String m_conf_pass = conf_password.getText().toString();

        if (TextUtils.isEmpty(m_email)) {
            email.setError("Email is required");
            return;
        }
        if (TextUtils.isEmpty(m_pass)) {
            password.setError("Password is required");
            return;
        }
        if (TextUtils.isEmpty(m_name)) {
            name.setError("Name is required");
            return;
        }
        if (TextUtils.isEmpty(m_mobile)) {
            mobile.setError("Contact number is required");
            return;
        }
        if (TextUtils.isEmpty(m_conf_pass)) {
            conf_password.setError("Please type confirm password");
            return;
        }
        if (m_pass.length() < 6) {
            password.setError("Password must be 6 digit long");
            return;
        }
        if (m_mobile.length() <=  11) {
            mobile.setError("You entered incorrect number");
            return;
        }
        if (m_conf_pass.length() != m_pass.length()) {
            conf_password.setError("Password mismatched");
            return;
        }


        mAuth.createUserWithEmailAndPassword(m_email, m_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignUp.this, "User Created Successfully", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(SignUp.this, "Error ! "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


}