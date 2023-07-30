package com.ph25590.mob403_labs.lab6;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetData {

    public void getStringVolley(Context context, TextView textView){
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "http://www.google.com/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response.substring(0,1000));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.getMessage());
            }
        });

        queue.add(stringRequest);
    }

    String kq = "";

    public void getJSONArray(Context context, TextView textView){
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
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                    textView.setText(kq);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.getMessage());
            }
        });

        queue.add(stringRequest);
    }

}
