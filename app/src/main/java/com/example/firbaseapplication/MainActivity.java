package com.example.firbaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText etmail,etpass;
    private String mail,pass;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

           etmail = findViewById(R.id.etemail);
           etpass = findViewById(R.id.etpassword);
    }

    public void register(View view) {
        mail = etmail.getText().toString();
        pass = etpass.getText().toString();

        auth.createUserWithEmailAndPassword(mail,pass)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
          .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
              @Override
              public void onSuccess(AuthResult authResult) {
                  Toast.makeText(MainActivity.this,"Successful saved",Toast.LENGTH_SHORT).show();
              }
          });


    }

    public void Clear(View view) {
    }
}
