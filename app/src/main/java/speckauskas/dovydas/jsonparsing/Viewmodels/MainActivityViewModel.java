package speckauskas.dovydas.jsonparsing.Viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import speckauskas.dovydas.jsonparsing.Services.Models.User;
import speckauskas.dovydas.jsonparsing.Services.Repositories.UsersRepository;

public class MainActivityViewModel extends AndroidViewModel {

    private LiveData<List<User>> users;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        users = UsersRepository.getInstance().getUsers();
    }

    public LiveData<List<User>> getUsers(){
        return users;
    }
}
