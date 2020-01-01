package com.devarsh.retrofit_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Hero> heroList = new ArrayList<Hero>();
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Hero>> call = api.getHeroes();


        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code() , Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Hero> heroslist = response.body();
                //Creating an String array for the ListView
                String name1;
                String realname1;
                String createdby;
                String team;
                String imageurl;
                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroslist.size(); i++) {
                    name1 = heroslist.get(i).getName();
                    realname1 = heroslist.get(i).getRealname();
                    createdby = heroslist.get(i).getCreatedby();
                    team = heroslist.get(i).getTeam();
                    imageurl = heroslist.get(i).getImageurl();

                    Hero hero = new Hero(name1,realname1,team,null,createdby,null,imageurl,null );
                    heroList.add(hero);
                    mAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        mAdapter = new RecyclerViewAdapter(heroList);
        recyclerView.setAdapter(mAdapter);
    }

}
