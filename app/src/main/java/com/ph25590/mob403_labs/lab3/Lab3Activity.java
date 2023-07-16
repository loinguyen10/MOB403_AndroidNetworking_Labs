package com.ph25590.mob403_labs.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ph25590.mob403_labs.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Lab3Activity extends AppCompatActivity {

    EditText txt1, txt2;
    Button btn1,next;
    TextView tvKQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3);

        txt1 = findViewById(R.id.demo31Txt1);
        txt2 = findViewById(R.id.demo31Txt2);
        btn1 = findViewById(R.id.demo31Btn1);
        tvKQ = findViewById(R.id.demo31Tv1);
        next = findViewById(R.id.next);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GETAsyncTask().execute();  //goi asynctask
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Lab3Activity.this,Lab31Activity.class));
            }
        });
    }

    class GETAsyncTask extends AsyncTask<Void, Void, Void> {
        String ketqua = "";

        //khai bao duong dan
        String path = "https://lmatmet1234.000webhostapp.com/get_bai3.php";

        @Override
        protected Void doInBackground(Void... voids) {
            path += "?toan=" + txt1.getText().toString() + "&van=" + txt2.getText().toString();
            //truyen tham so cho duong dan
            try {
                //bien duong dan thanh url
                URL url = new URL(path);
                //tao bo dem doc du lieu (o to cho cat)
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                //doc tung dong du lieu (ro xuc cat)
                String line = "";
                StringBuilder stringBuilder = new StringBuilder();
                //vung chua du lieu
                while ((line = br.readLine()) != null) {
                    //kiem tra dieu kien (neu con cat)
                    stringBuilder.append(line);
                    //dua tung dong du lieu vao vung chua du lieu
                }
                ketqua = stringBuilder.toString();//truyen du lieu vao ket qua
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            tvKQ.setText(ketqua);//tra ket qua ve client }
        }
    }

}