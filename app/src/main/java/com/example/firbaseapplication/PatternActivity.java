package com.example.firbaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Pattern;

public class PatternActivity extends AppCompatActivity {
    EditText ptemail, ptpass, ptname, ptaddress;
    RadioGroup ptradioGroup;
    RadioButton ptradioButton;
    private String pttmail, pttpass, pttname, pttaddress, pttgender,pttimagename,pttimageurl;
    private Pattern    passpatterns = Pattern.compile(
            "^"+
                    "(?=.*[0-9])"+
                    "(?=.*[a-z])"+
                    "(?=.*[A-Z])"+
                    "(?=.*[@#$%&=+])"+
      //              "(?=\\$+$)"+
                    ".{6,}"+
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);

        ptemail = findViewById(R.id.pt_email);
        ptpass = findViewById(R.id.pt_password);
        ptname = findViewById(R.id.pt_name);
        ptaddress = findViewById(R.id.pt_address);
        ptradioGroup = findViewById(R.id.pt_gender);

    }

    public void checkPattern(View view) {
        pttmail = ptemail.getText().toString();
        pttpass = ptpass.getText().toString();
        pttname = ptname.getText().toString();
        pttaddress = ptaddress.getText().toString();


        if (passpatterns.matcher(pttpass).matches()){
            Toast.makeText(this, "Weak Password", Toast.LENGTH_SHORT).show();
        }



    }
}
