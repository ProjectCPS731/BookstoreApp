package com.example.bookstoreapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.data.BitmapTeleporter;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private static String name;
    private List<String> bookstores;
    private List<Double> Prices;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView hold;
        public TextView pricehold;
        public Button instore;
        public Button website;
        public Context context;

        public ViewHolder(View itemView) {

            super(itemView);

            context = itemView.getContext();
            hold = itemView.findViewById(R.id.txthold);
            pricehold = itemView.findViewById(R.id.txtPrice);
            instore = itemView.findViewById(R.id.btnInStore);
            website = itemView.findViewById(R.id.btnWebsite);
            instore.setOnClickListener(this);
            website.setOnClickListener(this::onClick2);
        }

        @Override
        public void onClick(View v) {
            Intent intent = null;
            name = bookstores.get(getLayoutPosition());
            intent = new Intent(context, MapsActivity.class);

            context.startActivity(intent);
        }

        public void onClick2(View v) {
            Intent intent = new Intent();
            if (bookstores.get(getLayoutPosition()).equals("Coles") || bookstores.get(getLayoutPosition()).equals("Indigo") || bookstores.get(getLayoutPosition()).equals("Chapters"))
            {
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.chapters.indigo.ca/en-ca/books/search/?keywords=" +SearchScreen.booktitle()));
            }
            else
            {
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://shop.bookcity.ca/?searchtype=keyword&qs=" + SearchScreen.booktitle() + "&qs_file=&q=h.tviewer&using_sb=status&qsb=keyword"));
            }
            context.startActivity(intent);
        }
    }


    public MyRecyclerViewAdapter(List<String> mbookstores, List<Double> mPrices)
    {
        bookstores = mbookstores;
        Prices = mPrices;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View bookstoreView = inflater.inflate(R.layout.bookscreen2, parent, false);
        ViewHolder viewHolder = new ViewHolder(bookstoreView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position) {
        String bookstorename = bookstores.get(position);
        Double bookprice = Prices.get(position);

        TextView hold = holder.hold;
        hold.setText(bookstorename);
        TextView textView = holder.pricehold;
        textView.setText(bookprice.toString());

        Button instore = holder.instore;
        instore.setText("IN STORE");
        Button website = holder.website;
        website.setText("BUY ONLINE");
        if (position == 4)
        {
            website.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return bookstores.size();
    }

    public static String getBookstorename ()
    {
        return name;
    }



}
