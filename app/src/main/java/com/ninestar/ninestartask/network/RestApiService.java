package com.ninestar.ninestartask.network;

import com.google.gson.JsonElement;
import com.ninestar.ninestartask.model.NDAResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RestApiService {
    @GET("search?q=title:DNA")
    Call<NDAResponse> getNdaResponse();

    @GET("")
    Observable<NDAResponse> getNdaResponsee(@Url String url);


}
