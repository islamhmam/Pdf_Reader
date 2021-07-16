package com.example.pdfreader;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MainViewHolder extends RecyclerView.ViewHolder {
    TextView tv;
    CardView cd;

    public MainViewHolder(@NonNull  View itemView) {
        super(itemView);

        tv=itemView.findViewById(R.id.item_pdf_tv);
        cd = itemView.findViewById(R.id.item_pdf_card);

    }
}
