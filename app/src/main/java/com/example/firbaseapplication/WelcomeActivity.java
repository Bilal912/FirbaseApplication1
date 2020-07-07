package com.example.firbaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {
    private TextView lgoemail,lgoId,lgoverify;
    private FirebaseAuth auth;
    private FirebaseUser user;     //  for get current user from firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        auth = FirebaseAuth.getInstance();            //  for get current user from firebase
        user = auth.getCurrentUser();                   //  for get current user from firebase

        lgoemail = findViewById(R.id.lgoemail);
        lgoId = findViewById(R.id.lgoId);
        lgoverify = findViewById(R.id.lgoverify);

        if (user.isEmailVerified()) {
            lgoId.setText(user.getUid());                   //  for get current user from firebase
            lgoemail.setText(user.getEmail());             //  for get current user from firebase
            lgoverify.setText("User is Verified");
        }
        else {
            // for Verification of User
            lgoverify.setText("User is Not Verified");
            lgoverify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.sendEmailVerification()
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(WelcomeActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                }
                            });
                }
            });
        }
    }

    public void logout(View view) {
        auth.signOut();
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        finish();
    }
}
