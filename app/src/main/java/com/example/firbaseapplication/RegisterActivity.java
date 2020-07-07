package com.example.firbaseapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.net.URI;

public class RegisterActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 10;
    EditText etemail, etpass, etname, etaddress;
    RadioGroup radioGroup;
    RadioButton radioButton;
    private ImageView imageselect;
    private String mail, pass, name, address, gender,imagename,imageurl;
    FirebaseDatabase database;
    DatabaseReference reference;
    private FirebaseAuth auth;
    private StorageReference mStorageRef;
    private Uri imageuri;
    private AlertDialog dialog;
    private AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        builder = new AlertDialog.Builder(this);
        builder.setMessage("Record is saving... Please wait");
        builder.setCancelable(false);

        reference = FirebaseDatabase.getInstance().getReference("Student");
        mStorageRef = FirebaseStorage.getInstance().getReference();

        etemail = findViewById(R.id.et_email);
        etpass = findViewById(R.id.et_password);
        etname = findViewById(R.id.et_name);
        etaddress = findViewById(R.id.et_address);
        imageselect = findViewById(R.id.imageselect);
        radioGroup = findViewById(R.id.rg_gender);
        imageselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, PICK_IMAGE_REQUEST);
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = findViewById(checkedId);
                gender = radioButton.getText().toString();
            }
        });

    }

    @Override


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageuri = data.getData();
            imagename = getFileName(imageuri);
            Picasso.get().load(imageuri).fit().centerCrop().into(imageselect);
        }
    }

    public void saveRecord(View view) {

        mail = etemail.getText().toString();
        pass = etpass.getText().toString();
        name = etname.getText().toString();
        address = etaddress.getText().toString();

        dialog = builder.create();
        dialog.show();


        auth.createUserWithEmailAndPassword(mail, pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        final StorageReference srefer = mStorageRef.child("image").child("student").child(imagename);
                        srefer.putFile(imageuri)
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

                                    }
                                })
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        srefer.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                imageurl = uri.toString();
                                                Student s = new Student(name, mail, pass, address, gender,imageurl);
                                                reference.child(auth.getCurrentUser().getUid()).setValue(s).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                        dialog.dismiss();
                                                        Toast.makeText(RegisterActivity.this, "Data Upload Successful", Toast.LENGTH_SHORT).show();
                                                    }
                                                })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                dialog.dismiss();

                                                                Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                            }
                                        });

                                    }
                                });

                    }
                }
                )
                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
    }
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
            if (result == null) {
                result = uri.getPath();
                int cut = result.lastIndexOf('/');
                if (cut != -1) {
                    result = result.substring(cut + 1);
                }
            }
            return result;
        }


}
