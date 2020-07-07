package com.example.firbaseapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment {
    private String em,pass;
    private TextView frm,frp;


    public FragmentB() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_b, container, false);

        frm = v.findViewById(R.id.frm);
        frp = v.findViewById(R.id.frp);

        frm.setText(getArguments().getString("Email"));
        frp.setText(getArguments().getString("Password"));

   return v ;

    }

}
