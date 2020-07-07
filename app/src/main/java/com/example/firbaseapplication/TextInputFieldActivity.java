package com.example.firbaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class TextInputFieldActivity extends AppCompatActivity {
    TextInputLayout layname,layemail,laypass,layaddress,laygender;
    EditText ttfemail, ttfpass, ttfname, ttfaddress;
    RadioGroup ttfradioGroup;
    RadioButton ttfradioButton;
    private String tfmail, tfpass, tfname, tfaddress, tfgender,tfimagename,tfimageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_field);

        layaddress = findViewById(R.id.lay_address);
        layemail = findViewById(R.id.lay_email);
        layname = findViewById(R.id.lay_name);
        laypass = findViewById(R.id.lay_pass);
        laygender = findViewById(R.id.lay_gender);

        ttfemail = findViewById(R.id.tf_email);
        ttfpass = findViewById(R.id.tf_password);
        ttfname = findViewById(R.id.tf_name);
        ttfaddress = findViewById(R.id.tf_address);
        ttfradioGroup = findViewById(R.id.tf_gender);

        ttfname.addTextChangedListener(new MyTextWatcher(ttfname));
        ttfemail.addTextChangedListener(new MyTextWatcher(ttfemail));
        ttfpass.addTextChangedListener(new MyTextWatcher(ttfpass));
        ttfaddress.addTextChangedListener(new MyTextWatcher(ttfaddress));
    }

    public void SaveRecord(View view) {


        if (!checkname()){
            return;
        }
        if (!checkEmail()){
            return;
        }
        if (!checkPassword()){
            return;
        }
        if (!checkAddress()){
            return;
        }
    }
    private boolean checkname(){
        tfname = ttfname.getText().toString();

        if (tfname.isEmpty()){
            layname.setError("Enter Name");
      //      Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            ttfname.requestFocus();
            return false;
        }
        else {
            layname.setErrorEnabled(false);
        }
       return true;
    }

    private boolean checkEmail(){
        tfmail = ttfemail.getText().toString();

        if (tfmail.isEmpty()){
            ttfemail .setError("Enter Email");
       //     Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            ttfemail.requestFocus();
            return false;
        }

       else if (!checkValidEmail(tfmail)){
            layemail.setError("Email is not correct");
    //        Toast.makeText(this, "Email is not correct", Toast.LENGTH_SHORT).show();
            ttfemail.requestFocus();
            return false;
        }
        else {
            layemail.setErrorEnabled(false);
        }
        return true ;
    }
    private boolean checkPassword(){
        tfpass = ttfpass.getText().toString();

        if (tfpass.isEmpty()){
            laypass.setError("Enter Password");
      //      Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
            ttfpass.requestFocus();
            return false;
        }
      else   if (tfpass.length()>10){
            laypass.setError("Password should be 10 digits");
      //      Toast.makeText(this, " password must be 10 digits", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            laypass.setErrorEnabled(false);
        }
        return true;
    }
    private boolean checkAddress(){
        tfaddress = ttfaddress.getText().toString();
        if (tfaddress.isEmpty()){
            layaddress.setError("Enter Address");
     //       Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
            ttfaddress.requestFocus();
            return false;
        }
        else {
            layaddress.setErrorEnabled(false);
        }
        return true;
    }
     private boolean checkValidEmail(String tfmail){
       return Patterns.EMAIL_ADDRESS.matcher(tfmail).matches();
     }




     private class MyTextWatcher implements TextWatcher{
        private View v;

         public MyTextWatcher(View v) {
             this.v = v;
         }

         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

         }

         @Override
         public void afterTextChanged(Editable s) {

             switch (v.getId()){
                 case R.id.tf_name:
                 checkname();
                 break;

                 case R.id.tf_email:
                     checkEmail();
                     break;

                 case R.id.tf_password:
                     checkPassword();
                     break;

                 case R.id.tf_address:
                     checkAddress();
                     break;


             }

         }
     }
}



