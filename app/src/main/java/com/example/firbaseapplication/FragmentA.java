package com.example.firbaseapplication;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment {
    private EditText fragemail,fragpassword;
    private Button btn;
    private String em,pas;
    private FragmentTransaction transaction;


    public FragmentA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_a, container, false);
        fragemail = v.findViewById(R.id.fragemail);
        fragpassword = v.findViewById(R.id.fragpassword);
        btn = v.findViewById(R.id.frabtn);
      btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                em = fragemail.getText().toString();
                pas = fragpassword.getText().toString();
                transaction = getFragmentManager().beginTransaction();

                FragmentB fb = new FragmentB();
                Bundle b = new Bundle();
                b.putString("Email",em);
                b.putString("Password",pas);
                fb.setArguments(b);

                transaction.replace(R.id.fragView,fb).commit();
            }
        });
        return v;
    }

}
