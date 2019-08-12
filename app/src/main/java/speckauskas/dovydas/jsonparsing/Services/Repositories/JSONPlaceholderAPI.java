package speckauskas.dovydas.jsonparsing.Services.Repositories;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import speckauskas.dovydas.jsonparsing.Services.Models.*;

public interface JSONPlaceholderAPI {

    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("users")
    Call<List<User>> getUsers();

    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") String userId);

    @GET("albums")
    Call<List<Album>> getAlbums(@Query("userId") String userId);
}
