package com.ph25590.mob403_labs.lab7;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class GetData {
    String kq = "";
    List<String> list;

    public void getJSONArray(Context context, ListView listView){
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://lmatmet1234.000webhostapp.com/array_json_new.json";

        JsonArrayRequest stringRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i< response.length();i++){
                    try {
                        JSONObject person = response.getJSONObject(i);
                        String name = person.getString("name");
                        String email = person.getString("email");
                        JSONObject phone = person.getJSONObject("phone");
                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");

                        Log.d("json: ",name + email + mobile + home);
                        System.out.println(name + email + mobile + home);

                        kq += "Name: " + name + "\n";
                        kq += "Email: " + email + "\n";
                        kq += "Mobile: " + mobile + "\n";
                        kq += "Home: " + home + "\n";

                        list.add(kq);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                    ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1,list);
                    listView.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });

        queue.add(stringRequest);
    }

}
