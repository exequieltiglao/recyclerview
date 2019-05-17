package com.exequieltiglao.cif_retrofit.api;

import com.exequieltiglao.cif_retrofit.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("posts")
    Call<List<Post>> getPost();

}
