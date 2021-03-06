package com.exequieltiglao.cif_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.exequieltiglao.cif_retrofit.adapter.PostAdapter;
import com.exequieltiglao.cif_retrofit.api.ApiInterface;
import com.exequieltiglao.cif_retrofit.model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private RecyclerView mRecyclerView;
    private PostAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Post> mPostArrayList = new ArrayList<>();

    ApiInterface apiInterface;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.results);

        mRecyclerView = findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new PostAdapter(mPostArrayList);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        getPost();
    }

    public void getPost() {

        Call<List<Post>> call = apiInterface.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                ArrayList<Post> posts = new ArrayList<>();
                posts.addAll(response.body());
                mAdapter.setPostArrayList(posts);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

                mRecyclerView.setHasFixedSize(true);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(TAG, "onFailure: .... " + t.getMessage());
                result.setText(" " + t.getMessage());
            }
        });

    }

}
