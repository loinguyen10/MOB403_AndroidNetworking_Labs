package com.ph25590.mob403_labs.sharedFiles;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceInsert {
    @FormUrlEncoded
    @POST("api_postCreate.php")
    Call<SvrResponse> insertHuman(
            @Field("id") int id,
            @Field("name") String name,
            @Field("age") int age,
            @Field("price") int price
    );
}
