package com.example.teamwork.modules;

import android.content.Intent;
import android.os.Bundle;

import com.example.teamwork.MainActivity;
import com.example.teamwork.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.teamwork.modules.ui.main.SectionsPagerAdapter;

import org.w3c.dom.Text;

public class home extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "home";

    private TabLayout tabLayout;
    private ViewPager view_pager;
    private View hView;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try{
            Intent intent = getIntent();
            email = intent.getStringExtra("Email");
            Log.d(TAG, "onCreate: user name"+ email);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.Home_Page_View);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.Home_tabs);
        tabs.setupWithViewPager(viewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        hView = navigationView.getHeaderView(0);

        final TextView textEmail = (TextView)   hView.findViewById(R.id.emailTextView);

       textEmail.setText(email);





    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.logout){
            Log.d(TAG, "onNavigationItemSelected: logout clicked");
            Intent intent = new Intent(home.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        return false;
    }
}