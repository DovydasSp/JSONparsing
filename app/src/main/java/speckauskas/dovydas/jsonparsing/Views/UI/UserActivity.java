package speckauskas.dovydas.jsonparsing.Views.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import speckauskas.dovydas.jsonparsing.Views.Adapters.FragmentsPagerAdapter;
import speckauskas.dovydas.jsonparsing.R;

public class UserActivity extends AppCompatActivity {
    private static final String TAG = "UserActivity";
    private FragmentsPagerAdapter fragmentsPagerAdapter;
    private ViewPager viewPager;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Bundle b = getIntent().getExtras();
        if(b != null)
            userId = b.getString("userId");

        fragmentsPagerAdapter = new FragmentsPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.containerUser);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabsUser);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_posts);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_album);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView_barUser);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_user:
                        Intent intent = new Intent(UserActivity.this, MainActivity.class);
                        finish();
                        startActivity(intent);
                        break;

                    case R.id.ic_info:
                        Intent intent2 = new Intent(UserActivity.this, InfoActivity.class);
                        finish();
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });
        Menu menu= bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setCheckable(false);
         menuItem = menu.getItem(1);
        menuItem.setCheckable(false);
    }

    private void setupViewPager(ViewPager viewPager){
        FragmentsPagerAdapter adapter = new FragmentsPagerAdapter(getSupportFragmentManager());
        PostsFragment postsFragment = new PostsFragment();
        AlbumsFragment albumsFragment = new AlbumsFragment();

        Bundle bundle = new Bundle();
        bundle.putString("msg", userId);
        // set Fragmentclass Arguments
        postsFragment.setArguments(bundle);
        albumsFragment.setArguments(bundle);

        adapter.addFragment(postsFragment);
        adapter.addFragment(albumsFragment);
        viewPager.setAdapter(adapter);
    }
}
