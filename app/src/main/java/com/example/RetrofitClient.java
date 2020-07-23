package com.example;

import com.Fragment3;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitClient {
    @GET("photos")
    Call<ArrayList<Fragment3.Instancefield>> getObject();
}
