package com.example.rdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference firebaseReference;
    Button vacate,book;
    TextView noofdrivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeActivity();
       firebaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               final String driverno = dataSnapshot.getValue().toString();
               noofdrivers.setText(driverno);

               book.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       int drivernoint = Integer.parseInt(driverno);
                       drivernoint--;
                       firebaseReference.setValue(drivernoint);
                   }
               });

               vacate.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       int drivernoint = Integer.parseInt(driverno);
                       drivernoint++;
                       firebaseReference.setValue(drivernoint);
                   }
               });
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

    }
    private  void initializeActivity(){
        firebaseReference= FirebaseDatabase.getInstance().getReference().child("noOfDrivers");
        vacate=findViewById(R.id.vacate);
        book=findViewById(R.id.book);
        noofdrivers=findViewById(R.id.number_of_drivers);
    }
}

