package com.example.bookstoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
        final TextView test = findViewById(R.id.textView3);

        final Map<String, String> user = new HashMap<>();
        //user.put("0", "test test");
        //db.collection("User's Names").document("Users").get().then();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String FullName = firstName2.getText().toString().toLowerCase().trim() + " " + lastName2.getText().toString().toLowerCase().trim();
                db.collection("User's Names").document("Users")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                DocumentSnapshot document = task.getResult();
                                if (!document.exists())
                                {
                                    Toast.makeText(getApplicationContext(), "No one signed up yet. Be the first one sign up below", Toast.LENGTH_LONG);
                                }
                                Map<String, Object> tmp = document.getData();
                                final boolean[] notfound = {true};
                                for (Map.Entry<String, Object> entry : tmp.entrySet()) {
                                   if (entry.getValue().equals(FullName))
                                   {
                                       notfound[0] = false;
                                       Toast.makeText(getApplicationContext(), "Found your Account. Logging in...", Toast.LENGTH_SHORT);
                                   }

                                }
                                if (notfound[0] == false)
                                {
                                    Toast.makeText(getApplicationContext(), "Credentials were not found. Create an account below.", Toast.LENGTH_LONG);
                                }
                            }
                        });
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String FullName = firstName2.getText().toString().toLowerCase().trim() + " " + lastName2.getText().toString().toLowerCase().trim();
                final Random random = new Random();
                final boolean[] notfound = {true};
                final int[] randid2 = {random.nextInt((10000 - 1) + 1) + 1};
                String t = "Users" + randid2;
                db.collection("User's Names").document("Users")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                DocumentSnapshot document = task.getResult();
                                if (!document.exists())
                                {
                                    user.put(randid2[0] + "", FullName);
                                    db.collection("User's Names").document("Users").set(user, SetOptions.merge());
                                }
                                else
                                {
                                    Map<String, Object> tmp = document.getData();
                                    for (int i = 0; i < 1; i++)
                                    {
                                        notfound[0] = true;
                                        for (Map.Entry<String, Object> entry : tmp.entrySet()) {
                                            if (Integer.parseInt(entry.getKey()) == randid2[0])
                                            {
                                                randid2[0] = random.nextInt((10000-1) + 1) + 1;
                                                i=-1;
                                                notfound[0] = false;
                                                break;
                                            }
                                            else if (entry.getValue().equals(FullName))
                                            {
                                                notfound[0] = false;
                                                // System.out.println(tmp.toString());
                                                Toast.makeText(getApplicationContext(), "User already exists. Please login above", Toast.LENGTH_LONG).show();
                                                break;

                                            }
                                        }
                                    }
                                    if (notfound[0] == true)
                                    {
                                        //System.out.println("Hi");
                                        user.put(randid2[0] + "", FullName);
                                        db.collection("User's Names").document("Users").set(user, SetOptions.merge());
                                    }
                                }
                            }
                        });
            }
        });
    }
}