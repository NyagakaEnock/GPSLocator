package com.ft.nyagakaenock.gpslocator;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Nyagaka Enock on 1/17/2017.
 */

public interface ServerInterface {
    @FormUrlEncoded
    @POST("/index.php")
    public void SendGPSLocation(
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("serialNo") String serialNo,
            @Field("modelNo") String modelNo,
            @Field("physicalLocation") String physicalLocation,
            Callback<Response> callback);
}
