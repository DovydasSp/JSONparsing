package speckauskas.dovydas.jsonparsing.Views.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import speckauskas.dovydas.jsonparsing.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //Bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_user:
                        Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                        finish();
                        startActivity(intent);
                        break;

                    case R.id.ic_info:

                        break;
                }

                return false;
            }
        });
        Menu menu= bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
    }
}
