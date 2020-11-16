package com.example.bookstoreapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookstoreScreen extends AppCompatActivity {
    private ArrayList<String> bookstores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookstorescreen);

        RecyclerView rvBookstores = findViewById(R.id.rvBookStores);

        bookstores.add("Chapters");
        bookstores.add("Indigo");
        bookstores.add("Coles");

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(bookstores);
        rvBookstores.setAdapter(adapter);
        rvBookstores.setLayoutManager(new LinearLayoutManager(this));
    }
}
