package com.ph25590.mob403_labs.lab5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ph25590.mob403_labs.lab5.Adapter;

import com.ph25590.mob403_labs.R;
import com.ph25590.mob403_labs.sharedFiles.InterfaceDelete;
import com.ph25590.mob403_labs.sharedFiles.InterfaceInsert;
import com.ph25590.mob403_labs.sharedFiles.InterfaceSelect;
import com.ph25590.mob403_labs.sharedFiles.Human;
import com.ph25590.mob403_labs.sharedFiles.InterfaceUpdate;
import com.ph25590.mob403_labs.sharedFiles.SvrResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Lab5Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button buttonLoad;
    List<Human> list; //= new ArrayList<>();
    Human human = new Human();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab5);
        recyclerView = findViewById(R.id.recyclerView);
        buttonLoad = findViewById(R.id.buttonLoad);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelect();
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
        Call<SvrResponse> call = interfaceSelect.getHuman();
        call.enqueue(new Callback<SvrResponse>() {
            //thanh cong
            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseSelect = response.body();//lay ket qua server tra ve
                list = new ArrayList<>(Arrays.asList(svrResponseSelect.getHumans()));//chuyen du lieu sang list
                Adapter adapter = new Adapter(Lab5Activity.this, (ArrayList<Human>) list);
                recyclerView.setAdapter(adapter);
            }

            //that bai
            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Log.d("Message: ", t.getMessage());
            }
        });
    }

    public void updateMem(int txtId,String txtName,int txtAge,int txtPrice) {
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
                updateData(
                        Integer.parseInt(id.getText().toString()),
                        name.getText().toString(),
                        Integer.parseInt(age.getText().toString()),
                        Integer.parseInt(price.getText().toString())
                        );
            }
        });

        dialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteData(txtId);
                dialog.create().dismiss();
            }
        });

        AlertDialog alert = dialog.create();
        alert.show();

    }

    private void updateData(int id, String name, int age, int price) {
        Log.d("update ", id + " " + name + " " + age + " " + price);

        human.setName(name);
        human.setAge(age);
        human.setId(id);
        human.setPrice(price);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceUpdate interfaceUpdate = retrofit.create(InterfaceUpdate.class);

        Call<SvrResponse> call =
                interfaceUpdate.updateHuman(human.getId(),human.getName(), human.getAge(), human.getPrice());

        call.enqueue(new Callback<SvrResponse>() {

            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseUpdate = response.body();//lay noi dung server tra ve
                Toast.makeText(Lab5Activity.this,"OK",Toast.LENGTH_SHORT);
                Log.d("Message: ", svrResponseUpdate.getMessage());
            }

            //that bai
            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Log.d("Message: ", t.getMessage());
            }
        });
    }

    private void deleteData(int id){
        human.setId(id);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceDelete interfaceDelete = retrofit.create(InterfaceDelete.class);

        Call<SvrResponse> call = interfaceDelete.deleteHuman(human.getId());

        call.enqueue(new Callback<SvrResponse>() {
            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseDelete = response.body();
                Log.d("delete",svrResponseDelete+"");
                if(svrResponseDelete == null){
                    Toast.makeText(Lab5Activity.this,"KO",Toast.LENGTH_SHORT);
                    Log.d("delete",svrResponseDelete.getMessage()+"");
                }else{
                    Toast.makeText(Lab5Activity.this,"OK",Toast.LENGTH_SHORT);
                    Log.d("delete",svrResponseDelete.getMessage()+"");
                }
            }

            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Log.d("Message: ", t.getMessage());
            }
        });
    }

}