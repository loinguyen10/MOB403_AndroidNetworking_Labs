package com.ph25590.mob403_labs.lab5;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

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

        holder.txt.setText(human.getId() + " - " + human.getName() + " - " + human.getAge() + " - " + human.getPrice());
        holder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context instanceof Lab5Activity){
                    ((Lab5Activity) context).updateMem(human.getId(), human.getName(), human.getAge() , human.getPrice());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.text1);

        }
    }
}
