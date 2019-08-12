package speckauskas.dovydas.jsonparsing.Views.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import speckauskas.dovydas.jsonparsing.Views.Adapters.UsersRecyclerViewAdapter;
import speckauskas.dovydas.jsonparsing.R;
import speckauskas.dovydas.jsonparsing.Services.Models.User;
import speckauskas.dovydas.jsonparsing.Viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements UsersRecyclerViewAdapter.OnItemClickListener {
    private static final String TAG = "MainActivity";
    private UsersRecyclerViewAdapter adapter;
    private MainActivityViewModel mainActivityViewModel;

    //private List<User> users;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        initBottomNavBar();
        initRecyclerView();
        observeViewModel(mainActivityViewModel);
    }

    private void observeViewModel(MainActivityViewModel viewModel){
        viewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> projects) {
                if (projects != null) {
                    adapter.setUsersList(projects);
                }
            }
        });
    }

    private void initRecyclerView(){
        //Databinding to recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMain);
        adapter = new UsersRecyclerViewAdapter(MainActivity.this, MainActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void initBottomNavBar(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_user:

                        break;

                    case R.id.ic_info:
                        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                        finish();
                        startActivity(intent);
                        break;
                }

                return false;
            }
        });
        Menu menu= bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        Bundle b = new Bundle();
        b.putString("userId", mainActivityViewModel.getUsers().getValue().get(position).getId()); //Your id
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }
}
