package speckauskas.dovydas.jsonparsing.Services.Repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import speckauskas.dovydas.jsonparsing.Services.Models.Post;

public class PostsRepository {
    private static final String TAG = "PostsRepository";
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static PostsRepository instance;

    public static PostsRepository getInstance(){
        if(instance == null){
            instance = new PostsRepository();
        }
        return instance;
    }

    public LiveData<List<Post>> getPosts(String id){
        final MutableLiveData<List<Post>> data = new MutableLiveData<>();

        //Retrofit calls
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JSONPlaceholderAPI.class);
        Call<List<Post>> postsCall = jsonPlaceholderAPI.getPosts(id);

        postsCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                data.setValue(response.body());

                Log.d(TAG, "onResponse: Server responded with code:" + response.toString());
                Log.d(TAG, "onResponse: " + response.body().toString());

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e(TAG, "onFailure: "+ t.getMessage());
                data.setValue(null);
            }
        });



        return data;
    }
}
