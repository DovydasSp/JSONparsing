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
import speckauskas.dovydas.jsonparsing.Services.Models.Album;

public class AlbumsRepository {
    private static final String TAG = "AlbumsRepository";
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static AlbumsRepository instance;

    public static AlbumsRepository getInstance(){
        if(instance == null){
            instance = new AlbumsRepository();
        }
        return instance;
    }

    public LiveData<List<Album>> getAlbums(String id){
        final MutableLiveData<List<Album>> data = new MutableLiveData<>();

        //Retrofit calls
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JSONPlaceholderAPI.class);
        Call<List<Album>> albumsCall = jsonPlaceholderAPI.getAlbums(id);

        albumsCall.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                data.setValue(response.body());

                Log.d(TAG, "onResponse: Server responded with code:" + response.toString());
                Log.d(TAG, "onResponse: " + response.body().toString());

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.e(TAG, "onFailure: "+ t.getMessage());
                data.setValue(null);
            }
        });



        return data;
    }
}
