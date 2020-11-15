package com.example.bookstoreapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SearchScreen extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<RecentSearches> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);

        Button search = findViewById(R.id.btnSearch);
        final AutoCompleteTextView booktitle = findViewById(R.id.autoBookTitle);

        final NameOfUser nou = LoginScreen.getNameOfUser();
        final Map<String, String> RecentSearches = new HashMap<>();
        final ArrayList<String> drop = new ArrayList<>();

        final RoomDB2 database;
        database = RoomDB2.getInstance(this);
        dataList = database.SearchDAO().getAll();
        //database.SearchDAO().reset(dataList);



        db.collection("Users Searches").document("User " + nou.getID())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists())
                        {
                            Map<String, Object> tmp = document.getData();
                            for (Map.Entry<String, Object> entry : tmp.entrySet())
                            {
                                drop.add(entry.getValue().toString());
                            }

                        }
                    }
                });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, drop);
        booktitle.setThreshold(1);
        booktitle.setAdapter(adapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (booktitle.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please Enter a Book Title", Toast.LENGTH_LONG).show();
                }
                else
                {
                    final Random random = new Random();
                    final int[] randid2 = {random.nextInt((10000 - 1) + 1) + 1};
                    final boolean[] notfound = {true};
                    final String book = booktitle.getText().toString().trim();
                    final RecentSearches RS = new RecentSearches();

                    db.collection("Users Searches").document("User " + nou.getID())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    DocumentSnapshot document = task.getResult();
                                    if (!document.exists())
                                    {
                                        RecentSearches.put("0", book);
                                        //System.out.println("HI");
                                        db.collection("Users Searches").document("User " + nou.getID()).set(RecentSearches, SetOptions.merge());
                                        RS.setID(nou.getID() + "");
                                        RS.setSearches(book + ",");
                                        database.SearchDAO().insert(RS);
                                        dataList.clear();
                                        dataList.addAll(database.SearchDAO().getAll());
                                    }
                                    else
                                    {
                                        String tmp2;
                                        tmp2 = database.SearchDAO().loadSearches(nou.getID() + "");

                                        Map<String, Object> tmp = document.getData();
                                        for (Map.Entry<String, Object> entry : tmp.entrySet())
                                        {
                                            if (entry.getValue().equals(book))
                                            {
                                                notfound[0] = false;
                                                //Go to bookstore Screen
                                            }
                                        }
                                        if (notfound[0] == true)
                                        {
                                            RecentSearches.put(randid2[0] + "", book);
                                            db.collection("Users Searches").document("User " + nou.getID()).set(RecentSearches, SetOptions.merge());
                                            tmp2 += book + ",";
                                            database.SearchDAO().update(nou.getID()+"", tmp2);
                                        }
                                    }

                                }
                            });
                }
            }
        });
    }
}
