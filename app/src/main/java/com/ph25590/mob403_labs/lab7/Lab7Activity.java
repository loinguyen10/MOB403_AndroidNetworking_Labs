package com.ph25590.mob403_labs.lab7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ph25590.mob403_labs.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lab7Activity extends AppCompatActivity {

    Button button;
    ListView listView;
    GetData getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab7);

        button = findViewById(R.id.getButton);
        listView = findViewById(R.id.listView);

        getData = new GetData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getData.getJSONArray(Lab7Activity.this,listView);
            }
        });

    }
}