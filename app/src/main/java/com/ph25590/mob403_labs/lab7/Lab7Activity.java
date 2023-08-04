package com.ph25590.mob403_labs.lab7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.util.Map;

public class Lab7Activity extends AppCompatActivity {

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
               getData.getJSONArray(Lab7Activity.this,txt);
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

    private void deleteVolley() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue= Volley.newRequestQueue(context);
        //b3. url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab7/delete_product.php";
        //b4. Xac dinh loai request
        //StringRequest(method,url,thanhCong,thatBai){thamso};
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txt.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txt.setText(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("pid",txt1.getText().toString());
                return mydata;
            }
        };
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }

    private void updateVolley() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue=Volley.newRequestQueue(context);
        //b3. url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab7/update_product.php";
        //b4. Xac dinh loai request
        //StringRequest(method,url,thanhCong,thatBai){thamso};
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txt.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txt.setText(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("pid",txt1.getText().toString());
                mydata.put("name",txt2.getText().toString());
                mydata.put("price",txt3.getText().toString());
                mydata.put("description",txt4.getText().toString());
                return mydata;
            }
        };
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }

    private void insertVolley() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue=Volley.newRequestQueue(context);
        //b3. url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab7/create_product.php";
        //b4. Xac dinh loai request
        //StringRequest(method,url,thanhCong,thatBai){thamso};
        StringRequest request= new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txt.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txt.setText(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("name",txt2.getText().toString());
                mydata.put("price",txt3.getText().toString());
                mydata.put("description",txt4.getText().toString());
                return mydata;
            }
        };
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }

}