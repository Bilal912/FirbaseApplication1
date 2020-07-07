package com.example.firbaseapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class FragmentActivity extends AppCompatActivity {

    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }

    public void abc(View view) {

        FragmentA fa = new FragmentA();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragView,fa).commit();
    }
}
