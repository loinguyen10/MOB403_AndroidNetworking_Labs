package com.ph25590.mob403_labs.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ph25590.mob403_labs.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Lab6Activity extends AppCompatActivity {

    Button button;
    TextView txt;
    GetData getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab6);

        button = findViewById(R.id.getButton);
        txt = findViewById(R.id.txtGet);

        getData = new GetData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getData.getJSONArray(Lab6Activity.this,txt);
                //getJSON_ObjectsOfArray();
            }
        });

    }

    String strKQ="";
    private void getJSON_ObjectsOfArray() {
        //0.Tao hang doi
        RequestQueue queue= Volley.newRequestQueue(this);
        //1.url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab6/array_json_new.json";
        //2. Tao request -> (xac dinh Loai request)
        //Truong hop nay la ArrayRequest (vi day la mang cua cac doi tuong)
        //JsonArrayRequest(url,thanhCong,thatBai)
        JsonArrayRequest request=new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //ket qua tra ve MANG cua cac DOITUONG
                        //-> can dung vong For de doc het cac doi tuong
                        strKQ="";
                        for(int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject person=response.getJSONObject(i);//lay doi tuong thu i
                                String id=person.getString("id");
                                String name=person.getString("name");
                                String email=person.getString("email");
                                JSONObject phone=person.getJSONObject("phone");
                                String mobile=phone.getString("mobile");
                                String home=phone.getString("home");
                                //Noi chuoi
                                strKQ += "id: "+id+"\n\n";
                                strKQ += "name: "+name+"\n\n";
                                strKQ += "email: "+email+"\n\n";
                                strKQ += "mobile: "+mobile+"\n\n";
                                strKQ += "home: "+home+"\n\n";
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        //dua ket qua len man hinh
                        txt.setText(strKQ);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txt.setText(error.getMessage());//in ra loi
                    }
                });
        //3.Truyen tham so (neu co)
        //b4.Xu ly request
        queue.add(request);

    }

}