package com;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.RetrofitClient;
import com.example.teamwork.R;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static android.content.ContentValues.TAG;

public class Fragment3 extends Fragment {

public class Instancefield{


    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;

    public Instancefield(int id, String title, String url){
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString(){
        return title;
    }
}

    interface ApiService{
    @GET("photos")
        Call<List<Instancefield>> getInstancefield();

    }
//
//    static  class RetrofitClientInstance{
//    private static Retrofit retrofit;
//
//        public static Retrofit getRetrofit() {
//
//            if (retrofit == null) {
//                retrofit = new Retrofit.Builder()
//                        .baseUrl("https://jsonplaceholder.typicode.com/")
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//
//
//            }
//            return retrofit;
//        }
//
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
//        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        View rootView = inflater.inflate(R.layout.fragment3, parent, false);
        final RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(new RecyclerAdapter());
        final RecyclerAdapter adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        Retrofit  retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<ArrayList<Instancefield>> getObject = retrofitClient.getObject();



        getObject.enqueue(new Callback<ArrayList<Instancefield>>() {
            @Override
            public void onResponse(Call<ArrayList<Instancefield>> call, Response<ArrayList<Instancefield>> response) {
                Log.d(TAG, "onResponse:called  "+ response.code());
                ArrayList<Instancefield> instancefield = response.body();
                Log.d(TAG, "onResponse: HERE"+ response.body());
                Log.d(TAG, "onResponse: test "+ instancefield.toString());
                adapter.setDatas(instancefield);

            }

            @Override
            public void onFailure(Call<ArrayList<Instancefield>> call, Throwable t) {

            }
        });

        return rootView;


    }

}
