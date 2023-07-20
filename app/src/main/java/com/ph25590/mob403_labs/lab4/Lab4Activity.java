package com.ph25590.mob403_labs.lab4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ph25590.mob403_labs.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Lab4Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button buttonAdd,buttonGet;
    List<Human> list; //= new ArrayList<>();
    Human human = new Human();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab4);
        recyclerView = findViewById(R.id.recyclerView);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonGet = findViewById(R.id.buttonGet);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelect();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMem();
            }
        });

    }

    private void getSelect() {
        //b1. tao doi tuong retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Goi interface, chuan bi ham va goi ham
        InterfaceSelect interfaceSelect = retrofit.create(InterfaceSelect.class);
        Call<SvrResponseSelect> call = interfaceSelect.getHuman();
        call.enqueue(new Callback<SvrResponseSelect>() {
            //thanh cong
            @Override
            public void onResponse(Call<SvrResponseSelect> call, Response<SvrResponseSelect> response) {
                SvrResponseSelect svrResponseSelect = response.body();//lay ket qua server tra ve
                Log.d("22", svrResponseSelect + "");
                list = new ArrayList<>(Arrays.asList(svrResponseSelect.getHuman()));//chuyen du lieu sang list
//                for (Human p : list)//cho vao vong for de doc tung doi tuong
//                {
//                    kq += "Name: " + p.getName() +
//                            "; Price: " + p.getPrice() +
//                            "; Age: " + p.getAge() + "\n";
//                }
//                txt.setText(kq);

                Adapter adapter = new Adapter(Lab4Activity.this, (ArrayList<Human>) list);

                recyclerView.setAdapter(adapter);
            }

            //that bai
            @Override
            public void onFailure(Call<SvrResponseSelect> call, Throwable t) {
                Toast.makeText(Lab4Activity.this, t.getMessage(), Toast.LENGTH_SHORT);
                Log.d("Message: ", t.getMessage());
                Log.e("Message: ", t.getMessage());
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
                insertData(
                        Integer.parseInt(id.getText().toString()),
                        name.getText().toString(),
                        Integer.parseInt(age.getText().toString()),
                        Integer.parseInt(price.getText().toString())
                        );
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

    private void insertData(int id, String name, int age, int price) {
        Log.d("insert ", id + " " + name + " " + age + " " + price);
        //B0. Dua du lieu vao doi tuong
        human.setName(name);
        human.setAge(age);
        human.setId(id);
        human.setPrice(price);
        //B1. Tao doi tuong retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Chuan bi ham va thuc thi ham
        //2.1 - goi inteface
        InterfaceInsert interfaceInsert = retrofit.create(InterfaceInsert.class);
        //2.2. chuan bi ham
        Call<SvrResponseInsert> call =
                interfaceInsert.insertHuman(human.getId(),human.getName(), human.getAge(), human.getPrice());
        //2.3 goi ham
        call.enqueue(new Callback<SvrResponseInsert>() {
            //thanh cong
            @Override
            public void onResponse(Call<SvrResponseInsert> call, Response<SvrResponseInsert> response) {
                SvrResponseInsert svrResponseInsert = response.body();//lay noi dung server tra ve
                Toast.makeText(Lab4Activity.this, svrResponseInsert.getMessage(), Toast.LENGTH_SHORT);
                Log.d("Message: ", svrResponseInsert.getMessage());
                Log.e("Message: ", svrResponseInsert.getMessage());
            }

            //that bai
            @Override
            public void onFailure(Call<SvrResponseInsert> call, Throwable t) {
                Toast.makeText(Lab4Activity.this, t.getMessage(), Toast.LENGTH_SHORT);
                Log.d("Message: ", t.getMessage());
                Log.e("Message: ", t.getMessage());
            }
        });
    }

}