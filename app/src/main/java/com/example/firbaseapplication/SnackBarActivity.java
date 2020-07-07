package com.example.firbaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class SnackBarActivity extends AppCompatActivity {
    private FloatingActionButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        btn = findViewById(R.id.ftbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(getApplicationContext(),GPS_Location.class));
            }
        });
    }

    public void openSnakbar(View view) {

       Snackbar snackbar = Snackbar.make(view,"Is it working ?",Snackbar.LENGTH_INDEFINITE);
       View v = snackbar.getView();
       v.setBackgroundColor(Color.GREEN);
        TextView tv = (TextView) v.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setTextColor(Color.BLUE);
        tv.setTypeface(null, Typeface.BOLD_ITALIC);
                snackbar.setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                 .setActionTextColor(Color.WHITE)
                .show();

    }
}
