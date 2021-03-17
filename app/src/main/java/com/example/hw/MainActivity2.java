package com.example.hw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity2 extends AppCompatActivity {
    private TextView Name;
    private TextView Number;
    private TextView Address;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String id ,name0 , address0 , phone0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Name = findViewById(R.id.name0);
        Number = findViewById(R.id.number0);
        Address = findViewById(R.id.address0);
        db.collection("personal information's")

                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.isEmpty()) {
                            Log.w("TAG", "Error.");


                        } else {
                            for(DocumentSnapshot documentSnapshots : queryDocumentSnapshots){
                                id = documentSnapshots.getId();
                                String name = (String) documentSnapshots.get("name");
                                String phone = (String) documentSnapshots.get("phone");
                                String address = (String) documentSnapshots.get("address");

                                Name.setText(name);
                                Number.setText(phone);
                                Address.setText(address);


                            }                        }
                    }


                });

    }
}