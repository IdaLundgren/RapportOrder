package com.ida.rapportorder.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.ida.rapportorder.R;
import com.ida.rapportorder.model.pojo.User;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class BaseActivity extends AppCompatActivity {
    private User mUserLoggedIn;
    private BottomNavigationView mBottomNavigationView;
    private static final String KEY_USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mBottomNavigationView = findViewById(R.id.nav_bottom);
        BottomNavigationView bottomNav = findViewById(R.id.nav_bottom);
        mUserLoggedIn = new User();
        mUserLoggedIn = getIntent().getExtras().getParcelable(KEY_USER);
        bottomNav.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener);

        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_USER, mUserLoggedIn);
        Fragment startfragment = new StartFragment();
        startfragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.base_container, startfragment)
                .commit();

        KeyboardVisibilityEvent.setEventListener(this, new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if(isOpen){
                    mBottomNavigationView.setVisibility(View.GONE);
                }else{
                    mBottomNavigationView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(KEY_USER, mUserLoggedIn);
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
