package com.ida.rapportorder.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ida.rapportorder.R;

public class BaseActivity extends AppCompatActivity {
    private String mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        BottomNavigationView bottomNav = findViewById(R.id.nav_bottom);
        bottomNav.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener);
        Intent intent = getIntent();
        mUserId = intent.getStringExtra("userId");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.base_container, new StartFragment())
                .commit();


    }
    private BottomNavigationView.OnNavigationItemSelectedListener mNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Bundle bundle = new Bundle();
            bundle.putString("userId", mUserId);
            Fragment selectedFragment = null;

            switch(item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new StartFragment();
                    selectedFragment.setArguments(bundle);
                    break;
                case R.id.nav_order:
                    selectedFragment = new OrderFragment();
                    selectedFragment.setArguments(bundle);
                    break;
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.base_container, selectedFragment)
                    .commit();

            return true;
        }
    };
}
