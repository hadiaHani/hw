package com.example.hw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText etdName;
    EditText etNumber;
    EditText etAddress;
    ListView lv;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etdName=findViewById(R.id.name);
        etNumber=findViewById(R.id.number);
        etAddress=findViewById(R.id.address);
        lv= findViewById(R.id.lv);


    }
    public void saveToFirebase(View v){
        String Name = etdName.getText().toString();
        String PhoneNumber = etNumber.getText().toString();
        String Address = etAddress.getText().toString();

        Map<String, Object> information = new HashMap<>();
        information.put("Name", Name);
        information.put("Phone Number", PhoneNumber);
        information.put("address", Address);

        db.collection("personal information's")
                .add(information)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("TAG", "Error ", e);
            }
        });

      Intent intent= new Intent(getApplicationContext() , MainActivity2.class);
        startActivity(intent);
    }

}


