package com.ph25590.mob403_labs.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ph25590.mob403_labs.R;

public class Lab2Activity extends AppCompatActivity implements View.OnClickListener, Demo11Interface {

    ImageView imgView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);

        imgView = findViewById(R.id.imgView);
        button = findViewById(R.id.btnButton);

        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        new SyncImg(this,this)
                .execute("https://tinypic.com/images/goodbye.jpg");
    }

    public void onLoadBitmap(Bitmap bitmap) {
        imgView.setImageBitmap(bitmap);
        Toast.makeText(this, "Load thanh cong", Toast.LENGTH_SHORT);
    }

    public void onError() {
        Toast.makeText(this, "Load that bai", Toast.LENGTH_SHORT);
    }
}