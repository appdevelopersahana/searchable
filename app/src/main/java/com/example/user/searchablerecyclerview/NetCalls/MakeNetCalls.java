package com.example.user.searchablerecyclerview.NetCalls;

import android.content.Intent;

import com.example.user.searchablerecyclerview.models.SellerData;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 3/22/2018.
 */

public class MakeNetCalls {
    private OkHttpClient mClient ;
    private List<Object> mSellersList;
    private SellerData mSellerData;
    private String mResponse;
    private CallBack mCallBack;
    private static MakeNetCalls sMakeNetCalls;

    public static MakeNetCalls getInstance() {
        if (sMakeNetCalls == null)
            sMakeNetCalls = new MakeNetCalls();
        return sMakeNetCalls;
    }

    public void getProductValues(final CallBack callBack,String URL) {
        mClient = new OkHttpClient();
        mSellersList = new ArrayList<>();
        Request request = new Request.Builder().url(URL).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.getMessage();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                mResponse = response.body().string();
                Gson gson = new Gson();
                mSellerData = gson.fromJson(mResponse, SellerData.class);
                mSellersList.clear();
                mSellersList.addAll(mSellerData.sellers);
                if (callBack != null)
                    callBack.onSuccess(mSellersList);
            }
        });

    }

    public interface CallBack {
        void onSuccess(List<Object> sellersData);

    }
}
