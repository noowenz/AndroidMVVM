package com.official.news.retrofit;
import com.official.news.helpers.UrlHelpers;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiServices {
    @FormUrlEncoded
    @POST(UrlHelpers.login)
    Call<ErrorResponse> loginRequest(
            @Field("login_type") String login_type,
            @Field("device_id") String device_id,
            @Field("device_type") String device_type,
            @Field("device_token") String device_token,
            @Field("email") String email,
            @Field("password") String password);
}
