package com.example.pdfreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {
    Context context;
    List<File> pdfList;
    OnItemSelected listener;

    public MainAdapter(Context context, List<File> pdfList, OnItemSelected listener) {
        this.context = context;
        this.pdfList = pdfList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pdf,
                parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  MainViewHolder holder, int position) {
        holder.tv.setText(pdfList.get(position).getName());
        holder.tv.setSelected(true);
        holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.selectItem(pdfList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pdfList.size();
    }
}
