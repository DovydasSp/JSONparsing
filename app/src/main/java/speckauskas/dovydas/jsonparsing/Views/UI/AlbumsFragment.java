package speckauskas.dovydas.jsonparsing.Views.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.List;
import speckauskas.dovydas.jsonparsing.Viewmodels.AlbumsFragmentViewModel;
import speckauskas.dovydas.jsonparsing.Views.Adapters.AlbumsRecyclerViewAdapter;
import speckauskas.dovydas.jsonparsing.R;
import speckauskas.dovydas.jsonparsing.Services.Models.Album;


public class AlbumsFragment extends Fragment implements AlbumsRecyclerViewAdapter.OnItemClickListener {
    private String id;
    private AlbumsRecyclerViewAdapter.OnItemClickListener listener = this;
    private AlbumsRecyclerViewAdapter adapter;
    private AlbumsFragmentViewModel albumsFragmentViewModel;
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getArguments().getString("msg");

        albumsFragmentViewModel = ViewModelProviders.of(this).get(AlbumsFragmentViewModel.class);
        albumsFragmentViewModel.parseAlbums(id);

        view = inflater.inflate(R.layout.albums_fragment,container,false);

        initRecyclerView();
        observeViewModel(albumsFragmentViewModel);

        return  view;
    }

    private void observeViewModel(AlbumsFragmentViewModel viewModel){
        viewModel.getAlbums().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(@Nullable List<Album> posts) {
                if(posts != null){
                    adapter.setAlbumsList(posts);
                }
            }
        });
    }

    private void initRecyclerView(){
        //Databinding to recycler view
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewAlbums);
        adapter = new AlbumsRecyclerViewAdapter(view.getContext(), listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }



    @Override
    public void onItemClick(int position) {
        Toast.makeText(getView().getContext(), albumsFragmentViewModel.getAlbums().getValue().get(position).getId(), Toast.LENGTH_SHORT).show();
        /*Intent intent = new Intent(MainActivity.this, UserActivity.class);
        Bundle b = new Bundle();
        b.putString("userId", users.get(position).getId()); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
        finish();*/
    }
}