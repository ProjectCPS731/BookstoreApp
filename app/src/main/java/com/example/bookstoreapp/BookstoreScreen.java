package com.example.bookstoreapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;
import java.util.ArrayList;

public class BookstoreScreen extends AppCompatActivity {
    private ArrayList<String> bookstores = new ArrayList<>();
    private ArrayList<Double> prices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookstorescreen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView rvBookstores = findViewById(R.id.rvBookStores);

        bookstores.add("Chapters");
        bookstores.add("Indigo");
        bookstores.add("Coles");
        bookstores.add("Book City");
        bookstores.add("The Monkey's Paw");

        for(int x = 0; x < bookstores.size(); x++)
        {
            prices.add(bookPrices());
        }


        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(bookstores, prices);
        rvBookstores.setAdapter(adapter);
        rvBookstores.setLayoutManager(new LinearLayoutManager(this));
    }
    private Double bookPrices()
    {
        Random random = new Random();
        int rand = random.nextInt((40 - 10) + 10) + 10;
        double result = rand + 0.99;
        return result;
    }
}
