package com.ph25590.mob403_labs.sharedFiles;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceUpdate {
    @FormUrlEncoded
    @POST("api_postUpdate.php")
    Call<SvrResponse> updateHuman(
            @Field("id") int id,
            @Field("name") String name,
            @Field("age") int age,
            @Field("price") int price
    );
}
