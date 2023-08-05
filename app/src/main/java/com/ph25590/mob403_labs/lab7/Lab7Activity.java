package com.ph25590.mob403_labs.lab7;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ph25590.mob403_labs.R;

public class Lab7Activity extends AppCompatActivity {

    Button button;
    FloatingActionButton add;
    RecyclerView recyclerView;
    GetData getData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab7);

        button = findViewById(R.id.getButton);
        recyclerView = findViewById(R.id.listView);
        add = findViewById(R.id.addButton);

        getData = new GetData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getData.getJSONArray(Lab7Activity.this,recyclerView);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMem();
            }
        });

    }

    private void addMem() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View v = getLayoutInflater().inflate(R.layout.addmem, null);
        dialog.setView(v);
        EditText id = v.findViewById(R.id.edt_id);
        EditText name = v.findViewById(R.id.edt_name);
        EditText age = v.findViewById(R.id.edt_age);
        EditText price = v.findViewById(R.id.edt_price);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getData.insertVolley(
                        Lab7Activity.this,
                        id.getText().toString(),
                        name.getText().toString(),
                        age.getText().toString(),
                        price.getText().toString()
                );
                getData.getJSONArray(Lab7Activity.this,recyclerView);
            }
        });

        dialog.setNegativeButton("KO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.create().dismiss();
            }
        });

        AlertDialog alert = dialog.create();
        alert.show();

    }

    public void updateNg(int txtId,String txtName,int txtAge,int txtPrice) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View v = getLayoutInflater().inflate(R.layout.addmem, null);
        dialog.setView(v);
        EditText id = v.findViewById(R.id.edt_id);
        EditText name = v.findViewById(R.id.edt_name);
        EditText age = v.findViewById(R.id.edt_age);
        EditText price = v.findViewById(R.id.edt_price);

        id.setText(txtId+"");
        name.setText(txtName);
        age.setText(txtAge+"");
        price.setText(txtPrice+"");

        id.setEnabled(false);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getData.updateVolley(
                        Lab7Activity.this,
                        id.getText().toString(),
                        name.getText().toString(),
                        age.getText().toString(),
                        price.getText().toString()
                );
                getData.getJSONArray(Lab7Activity.this,recyclerView);
            }
        });

        dialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getData.deleteVolley(Lab7Activity.this,txtId+"");
                dialog.create().dismiss();
                getData.getJSONArray(Lab7Activity.this,recyclerView);
            }
        });

        AlertDialog alert = dialog.create();
        alert.show();
    }
}