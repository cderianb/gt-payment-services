package com.example.payment.services.helper;

import com.example.payment.services.models.Response;
public class ResponseHelper {
    public static<T> Response<T> ok(T data){
        return Response.<T>builder()
                .result(true)
                .data(data)
                .build();
    }

    public static<T> Response<T> okList(T data){
        return Response.<T>builder()
                .result(true)
                .data(data)
                .build();
    }
}
