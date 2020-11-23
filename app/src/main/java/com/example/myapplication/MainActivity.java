package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.models.Userdata;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity{

    EditText M_name;
    EditText M_age;
    EditText M_phone;
    EditText M_description;
    Button btn_submit;
    Button btn_next;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        M_name = findViewById(R.id.main_name);
        M_age = findViewById(R.id.main_age);
        M_phone = findViewById(R.id.main_phone);
        M_description = findViewById(R.id.main_description);
        btn_submit=findViewById(R.id.btn_submit);
        btn_next=findViewById(R.id.btn_next);

        db = FirebaseFirestore.getInstance();
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              move();
            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name =M_name.getText().toString();
                String age =M_age.getText().toString();
                String phone =M_phone.getText().toString();
                String description =M_description.getText().toString();
                addData(name,age,phone,description);

            }
        });


    }

    public void move(){
        Intent intent =new Intent(getApplicationContext(),upload.class);
        startActivity(intent);
    }

    public void addData(String name,String age,String phone,String description){

        Userdata userdata = new Userdata(name,age,phone,description);
        db.collection("userdata")
                .add(userdata)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Submitted success",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });



}
}