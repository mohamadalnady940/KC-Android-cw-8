package com.example.timemanagement2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    FirebaseFirestore firestore;
    ArrayList<String> listitems = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        firestore = FirebaseFirestore.getInstance();

        Map<String,Object> user = new HashMap<>();
        user.put("tasks" , "Easy");
        user.put("tasks" , "Time");
        user.put("tasks" , "Complete");

        firestore.collection("tasks").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Savad",Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_LONG).show();
            }
        });

        EditText input = findViewById(R.id.et_input);
        Button Save = findViewById(R.id.bt_save);
        ListView listView = findViewById(R.id.List_view);
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listitems);
        listView.setAdapter(adapter);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listitems.add(input.getText().toString());
                adapter.notifyDataSetChanged();
                input.setText("");

            }
        });

    }
}