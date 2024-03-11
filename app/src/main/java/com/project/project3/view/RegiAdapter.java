package com.project.project3.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.project3.R;
import com.project.project3.model.RegiVO;

import java.util.ArrayList;

public class RegiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<RegiVO> dataset;

    public RegiAdapter(ArrayList<RegiVO> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.price,parent,false);
        RegiViewHolder holder = new RegiViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RegiVO items = dataset.get(position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
