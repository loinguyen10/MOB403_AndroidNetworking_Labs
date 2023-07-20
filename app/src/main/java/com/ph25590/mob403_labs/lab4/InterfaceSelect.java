package com.ph25590.mob403_labs.lab4;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceSelect {
    @GET("api_getSelect.php")
    Call<SvrResponseSelect> getHuman();
}
