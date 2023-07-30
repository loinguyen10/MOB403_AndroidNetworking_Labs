package com.ph25590.mob403_labs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ph25590.mob403_labs.lab1.Lab1Activity;
import com.ph25590.mob403_labs.lab2.Lab2Activity;
import com.ph25590.mob403_labs.lab3.Lab3Activity;
import com.ph25590.mob403_labs.lab4.Lab4Activity;
import com.ph25590.mob403_labs.lab5.Lab5Activity;

public class MainActivity extends AppCompatActivity {

    Button lab1,lab2,lab3,lab4,lab5,lab6,lab7,lab8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lab1 = findViewById(R.id.lab1Button);
        lab2 = findViewById(R.id.lab2Button);
        lab3 = findViewById(R.id.lab3Button);
        lab4 = findViewById(R.id.lab4Button);
        lab5 = findViewById(R.id.lab5Button);
        lab6 = findViewById(R.id.lab6Button);
        lab7 = findViewById(R.id.lab7Button);
        lab8 = findViewById(R.id.lab8Button);

        lab7.setEnabled(false);
        lab8.setEnabled(false);

        lab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/Y7_PVuJjdGM"));
                startActivity(new Intent(MainActivity.this, Lab1Activity.class));
                hienThongBao("lab1");
            }
        });

        lab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Lab2Activity.class));
                hienThongBao("lab2");
            }
        });

        lab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Lab3Activity.class));
                hienThongBao("lab3");
            }
        });

        lab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Lab4Activity.class));
                hienThongBao("lab4");
            }
        });

        lab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Lab5Activity.class));
                hienThongBao("lab5");
            }
        });

        lab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lab7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lab8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void hienThongBao(String txt){
        Toast.makeText(this,txt,Toast.LENGTH_SHORT);
    }
}