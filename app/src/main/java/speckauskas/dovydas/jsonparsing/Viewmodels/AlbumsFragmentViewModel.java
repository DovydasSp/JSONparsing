package speckauskas.dovydas.jsonparsing.Viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import speckauskas.dovydas.jsonparsing.Services.Models.Album;
import speckauskas.dovydas.jsonparsing.Services.Repositories.AlbumsRepository;

public class AlbumsFragmentViewModel extends AndroidViewModel {
    private LiveData<List<Album>> posts;

    public AlbumsFragmentViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<List<Album>> getAlbums(){
        return posts;
    }

    public void parseAlbums(String id){
        posts = AlbumsRepository.getInstance().getAlbums(id);
    }
}