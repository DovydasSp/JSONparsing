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
import speckauskas.dovydas.jsonparsing.Services.Models.User;


//Singleton
public class UsersRepository {
    private static final String TAG = "UsersRepository";
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static UsersRepository instance;

    public static UsersRepository getInstance(){
        if(instance == null){
            instance = new UsersRepository();
        }
        return instance;
    }

    public LiveData<List<User>> getUsers(){
        final MutableLiveData<List<User>> data = new MutableLiveData<>();

        //Retrofit calls
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JSONPlaceholderAPI.class);
        Call<List<User>> usersCall = jsonPlaceholderAPI.getUsers();

        usersCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                data.setValue(response.body());

                Log.d(TAG, "onResponse: Server responded with code:" + response.toString());
                Log.d(TAG, "onResponse: " + response.body().toString());

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e(TAG, "onFailure: "+ t.getMessage());
                data.setValue(null);
            }
        });



        return data;
    }
}
