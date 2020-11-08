package com.example.bookstoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String ID_Key = "ID";
    public static final String FirstN_Key = "First Name";
    public static final String LastN_Key = "Last Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText firstName = findViewById(R.id.edtFirstName);
        final EditText firstName2 = findViewById(R.id.edtFirstName2);
        EditText lastName = findViewById(R.id.edtLastName);
        final EditText lastName2 = findViewById(R.id.edtLastName2);
        Button login = findViewById(R.id.btnLogin);
        Button create = findViewById(R.id.btnCreate);
        Map<String, List<Object>> user = new HashMap<>();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Object> fullName = new ArrayList<>();
                fullName.add(firstName2.getText());
                fullName.add(lastName2.getText());


            }
        });
    }
}