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
import java.util.List;
import speckauskas.dovydas.jsonparsing.Viewmodels.PostsFragmentViewModel;
import speckauskas.dovydas.jsonparsing.Views.Adapters.PostsRecyclerViewAdapter;
import speckauskas.dovydas.jsonparsing.R;
import speckauskas.dovydas.jsonparsing.Services.Models.Post;


public class PostsFragment extends Fragment{
    private static final String TAG = "PostsFragment";
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private String id;
    private PostsRecyclerViewAdapter adapter;
    private PostsFragmentViewModel postsFragmentViewModel;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getArguments().getString("msg");

        postsFragmentViewModel = ViewModelProviders.of(this).get(PostsFragmentViewModel.class);
        postsFragmentViewModel.parsePosts(id);

        view = inflater.inflate(R.layout.posts_fragment,container,false);

        initRecyclerView();
        observeViewModel(postsFragmentViewModel);

        return  view;
    }

    private void observeViewModel(PostsFragmentViewModel viewModel){
        viewModel.getPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> posts) {
                if(posts != null){
                    adapter.setPostsList(posts);
                }
            }
        });
    }

    private void initRecyclerView(){
        //Databinding to recycler view
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPosts);
        adapter = new PostsRecyclerViewAdapter(view.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
}