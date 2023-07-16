package com.ph25590.mob403_labs.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ph25590.mob403_labs.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Lab31Activity extends AppCompatActivity {

    EditText txt1;
    Button btn1,back;
    TextView tvKQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab31);
        txt1 = findViewById(R.id.demo32Txt1);
        btn1 = findViewById(R.id.demo32Btn1);
        tvKQ = findViewById(R.id.demo32Tv1);
        back = findViewById(R.id.back);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new POSTAsyncTask().execute();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Lab31Activity.this,Lab3Activity.class));
            }
        });
    }

    class POSTAsyncTask extends AsyncTask<Void, Void, Void> {
        String kq = "";
        String path = "https://lmatmet1234.000webhostapp.com/post_bai3.php";

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                //1.chuyen path thanh url
                URL url = new URL(path);
                //2. ma hoa tham so
                String param = "canh=" + URLEncoder.encode(txt1.getText().toString(), "utf-8");
                //3. Mo ket noi
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //4. Thiet lap cac thuoc tinh cho ket noi
                urlConnection.setDoOutput(true);//lay ve ket qua
                urlConnection.setRequestMethod("POST");//xac dinh phuong thuc
                urlConnection.setFixedLengthStreamingMode(param.getBytes().length);//do dai tham so
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                //5. thiet lap tham so
                PrintWriter printWriter = new PrintWriter(urlConnection.getOutputStream());
                printWriter.print(param);
                printWriter.close();
                //6. doc du lieu
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line);
                    Log.e("line",line+" ");
                }
                //7. tra ve ket qua
                kq = stringBuilder.toString();
                Log.e("kq",kq);
                urlConnection.disconnect();//dong ket noi

            } catch (MalformedURLException e) {
                e.printStackTrace();
                tvKQ.setText(e.getMessage());

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                tvKQ.setText(e.getMessage());

            } catch (IOException e) {
                e.printStackTrace();
                tvKQ.setText(e.getMessage()+"");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            tvKQ.setText(kq);//tra ket qua ve client
        }
    }
}