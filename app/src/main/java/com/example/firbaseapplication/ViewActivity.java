package com.example.firbaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    private RecyclerView rview;
    private MyAdapter adapter;
    private ArrayList<Student> list;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        reference = FirebaseDatabase.getInstance().getReference("Student");

        rview = findViewById(R.id.recycle);
        list = new ArrayList<>();
        rview.setHasFixedSize(true);
        rview.setLayoutManager(new LinearLayoutManager(this));
        rview.addItemDecoration(new DividerItemDecoration(rview.getContext(),
                DividerItemDecoration.VERTICAL));
        adapter = new MyAdapter(this,list);
        rview.setAdapter(adapter);

        reference.addListenerForSingleValueEvent(listener);


    }
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            if (dataSnapshot.exists()){
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Student s = snapshot.getValue(Student.class);
                    list.add(s);
                }
                adapter.notifyDataSetChanged();
            }
            else{
                Toast.makeText(ViewActivity.this,"No Record found",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

            Toast.makeText(ViewActivity.this,databaseError.toString(),Toast.LENGTH_SHORT).show();
        }
    };
}
