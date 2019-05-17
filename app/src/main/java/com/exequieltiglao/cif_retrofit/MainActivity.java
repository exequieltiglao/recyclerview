package com.exequieltiglao.cif_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.exequieltiglao.cif_retrofit.adapter.ExampleAdapter;
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

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static final String TAG = "MainActivity";
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private ArrayList<Post> mPostArrayList = new ArrayList<>();

    ApiInterface apiInterface;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.results);

        mRecyclerView = findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new ExampleAdapter(mPostArrayList);

//        ArrayList<ExampleItem> exampleList = new ArrayList<>();
//        exampleList.add(new ExampleItem(R.drawable.ic_android, "Text 1", "Text 2"));
//        exampleList.add(new ExampleItem(R.drawable.ic_android_black, "Text 1", "Text 2"));
//        exampleList.add(new ExampleItem(R.drawable.ic_android_white, "Text 1    ", "Text 2"));

        /*
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
         */

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
                mAdapter.setmPostArrayList(posts);
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
