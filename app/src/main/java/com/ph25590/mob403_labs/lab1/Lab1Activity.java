package com.ph25590.mob403_labs.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ph25590.mob403_labs.MainActivity;
import com.ph25590.mob403_labs.R;

public class Lab1Activity extends AppCompatActivity {

    Button selectBtn,insertBtn,updateBtn,deleteBtn,previewBtn;
    ImageView selectImg,insertImg,updateImg,deleteImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1);

        previewBtn = findViewById(R.id.previewButton);

        selectBtn = findViewById(R.id.selectButton);
        insertBtn = findViewById(R.id.insertButton);
        updateBtn = findViewById(R.id.updateButton);
        deleteBtn = findViewById(R.id.deleteButton);

        selectImg = findViewById(R.id.txtSelect);
        insertImg = findViewById(R.id.txtInsert);
        updateImg = findViewById(R.id.txtUpdate);
        deleteImg = findViewById(R.id.txtDelete);

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectImg.getVisibility() == View.GONE) {
                    selectImg.setVisibility(View.VISIBLE);
                }
                if(selectImg.getVisibility() == View.VISIBLE){
                    selectImg.setVisibility(View.GONE);
                }
                Toast.makeText(Lab1Activity.this,""+selectImg.getVisibility(),Toast.LENGTH_SHORT);
                Log.e("select",selectImg.getVisibility()+"");
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(insertImg.getVisibility() == View.GONE) {
                    insertImg.setVisibility(View.VISIBLE);
                }
                if(insertImg.getVisibility() == View.VISIBLE) {
                    insertImg.setVisibility(View.GONE);
                }
                Toast.makeText(Lab1Activity.this,""+insertImg.getVisibility(),Toast.LENGTH_SHORT);
                Log.e("insert",insertImg.getVisibility()+"");
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(updateImg.getVisibility() == View.GONE) {
                    updateImg.setVisibility(View.VISIBLE);
                }
                if(updateImg.getVisibility() == View.VISIBLE) {
                    updateImg.setVisibility(View.GONE);
                }
                Toast.makeText(Lab1Activity.this,""+updateImg.getVisibility(),Toast.LENGTH_SHORT);
                Log.e("update",updateImg.getVisibility()+"");
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deleteImg.getVisibility() == View.GONE) {
                    deleteImg.setVisibility(View.VISIBLE);
                }
                if(deleteImg.getVisibility() == View.VISIBLE) {
                    deleteImg.setVisibility(View.GONE);
                }
                Toast.makeText(Lab1Activity.this,""+deleteImg.getVisibility(),Toast.LENGTH_SHORT);
                Log.e("delete",deleteImg.getVisibility()+"");
            }
        });

        previewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/Y7_PVuJjdGM"));
                startActivity(intent);
            }
        });
    }
}