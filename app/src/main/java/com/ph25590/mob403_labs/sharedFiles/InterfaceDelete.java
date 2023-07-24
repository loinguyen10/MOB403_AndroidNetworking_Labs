package com.ph25590.mob403_labs.sharedFiles;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceDelete {
    @FormUrlEncoded
    @POST("api_postDelete.php")
    Call<SvrResponse> deleteHuman(
            @Field("id") int id
    );
}
