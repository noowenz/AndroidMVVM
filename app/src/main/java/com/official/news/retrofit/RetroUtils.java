package com.official.news.retrofit;

/**
 * Created by nabin on 8/26/2016.
 */

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class RetroUtils {
    public static ErrorResponse parseError(Response<?> response) {
        Converter<ResponseBody, ErrorResponse> converter =
                ApiClient.getRetrofit().responseBodyConverter(ErrorResponse.class, new Annotation[0]);
        ErrorResponse error;
        try {
            error = converter.convert(response.errorBody());

        } catch (IOException e) {
            return new ErrorResponse();
        }

        return error;
    }


}
