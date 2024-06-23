package com.example.delete.bllotothh;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delete.R;

public class ListAdapter2 extends RecyclerView.Adapter<ListAdapter2.MyViewHolder> {
    private ArrayList<Model2> devices = new ArrayList<>();
    private RecylerviewCliCK itemClick;

    public ListAdapter2(ArrayList<Model2> devices, RecylerviewCliCK itemClick) {
        this.devices = devices;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvName.setText(devices.get(position).name);
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClick != null)
                    itemClick.itemClick(devices.get(position).address);
            }
        });
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.itemTextView);
        }
    }
}
