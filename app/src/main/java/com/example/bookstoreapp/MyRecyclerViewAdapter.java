package com.example.bookstoreapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView hold;
        public Button instore;
        public Button website;

        public ViewHolder(View itemView) {

            super(itemView);

            hold = itemView.findViewById(R.id.txthold);
            instore = itemView.findViewById(R.id.btnInStore);
            website = itemView.findViewById(R.id.btnWebsite);
        }
    }
    private List<String> bookstores;

    public MyRecyclerViewAdapter(List<String> mbookstores)
    {
        bookstores = mbookstores;
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

        TextView hold = holder.hold;
        hold.setText(bookstorename);
        Button instore = holder.instore;
        instore.setText("IN STORE");
        Button website = holder.website;
        website.setText("BUY ONLINE");

    }

    @Override
    public int getItemCount() {
        return bookstores.size();
    }
}
