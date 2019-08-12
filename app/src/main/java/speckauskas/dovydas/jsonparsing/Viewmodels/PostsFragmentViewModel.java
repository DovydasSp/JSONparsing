package speckauskas.dovydas.jsonparsing.Viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import speckauskas.dovydas.jsonparsing.Services.Models.Post;
import speckauskas.dovydas.jsonparsing.Services.Repositories.PostsRepository;

public class PostsFragmentViewModel extends AndroidViewModel {
    private LiveData<List<Post>> posts;

    public PostsFragmentViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<List<Post>> getPosts(){
        return posts;
    }

    public void parsePosts(String id){
        posts = PostsRepository.getInstance().getPosts(id);
    }
}
