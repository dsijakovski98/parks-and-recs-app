package com.example.parksandrecs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class LoginRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        // Check if user is already logged in
        Bundle userInfo = CurrentUserManager.getCurrentUser(LoginRegisterActivity.this);
        if(userInfo.getBoolean(CurrentUserManager.LOGGED_IN_KEY)) {
            Intent goToCities = new Intent(LoginRegisterActivity.this, CitiesActivity.class);
            startActivity(goToCities);
            finish();
        }

        // Setup toolbar
        Toolbar mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        // Setup tab layout fragments titles
        TabLayout tabLayout = findViewById(R.id.login_register_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Register"));

        // Make the tab layout fill width of activity
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Setup view pager
        final ViewPager viewPager = findViewById(R.id.login_register_view_pager);

        // Setup tabs adapter
        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        // Set the adapter
        viewPager.setAdapter(tabsAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Nothing
            }
        });
    }
}