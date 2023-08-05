package com.ph25590.mob403_labs.lab7;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ph25590.mob403_labs.sharedFiles.Human;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetData {
    String kq = "";

    public void getJSONArray(Context context, RecyclerView recyclerView) {
        List<Human> list = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);
        //b3. url
        String url = "https://lmatmet1234.000webhostapp.com/api_getSelect.php";
        //b4. Xac dinh loai request
        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray humans = response.getJSONArray("humans");
                    for (int i = 0; i < humans.length(); i++) {
                        JSONObject human = humans.getJSONObject(i);
                        int id = human.getInt("id");
                        String name = human.getString("name");
                        int age = human.getInt("age");
                        int price = human.getInt("price");

                        list.add(new Human(id,name,age,price));
                    }
                    Adapter adapter = new Adapter(context, (ArrayList<Human>) list);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }

    public void updateVolley(Context context, String id, String name, String age, String price) {
        RequestQueue queue=Volley.newRequestQueue(context);
        String url="https://lmatmet1234.000webhostapp.com/api_postUpdate.php";
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("id",id);
                mydata.put("name",name);
                mydata.put("age",age);
                mydata.put("price",price);
                return mydata;
            }
        };
        queue.add(request);
    }
}
