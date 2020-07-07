package com.example.firbaseapplication.firestore_CRUD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firbaseapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddValuesActicity extends AppCompatActivity {
    private FirebaseFirestore firestore;
    private Map<String,Object> abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_values_acticity);

        firestore = FirebaseFirestore.getInstance();
        abc = new HashMap<>();
        abc.put("c1","Lahore");
        abc.put("c2",74);
        abc.put("c3",13.5f);
        abc.put("c4",true);

    }

    public void addValueToFireStore(View view) {
        firestore.collection("cities").add(abc).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(AddValuesActicity.this, "Successful", Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddValuesActicity.this,  e.toString(), Toast.LENGTH_SHORT).show();

                    }
                });


    }
}
