package com.ph25590.mob403_labs.lab7;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ph25590.mob403_labs.R;
import com.ph25590.mob403_labs.sharedFiles.Human;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    ArrayList<Human> list;

    public Adapter(Context context, ArrayList<Human> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Human human = list.get(position);

        holder.txtId.setText(human.getId()+"");
        holder.txtName.setText(human.getName());
        holder.txtPrice.setText(human.getPrice()+"");
        holder.txtAge.setText(human.getAge()+"");

        Log.d("ss", human.getId() + " + " + human.getName() + " + " + human.getAge() + " + " + human.getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText txtId, txtName, txtAge, txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.id);
            txtName = itemView.findViewById(R.id.name);
            txtPrice = itemView.findViewById(R.id.price);
            txtAge = itemView.findViewById(R.id.age);

        }
    }
}
