package com.example.clgapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

public class LogedIN extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loged_in);

        //Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        //Objects.requireNonNull(this.getSupportActionBar()).setTitle("Marks");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //By default marks is selected
        navigationView.getMenu().getItem(0).setChecked(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_display,new BlankFragment()).commit();

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.loged_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment selectedFrag = new profile();
        if (id==R.id.profile)
        {
            selectedFrag = new profile();
            getSupportActionBar().setTitle("My Profile");

        }
        else if (id == R.id.marks) {
            // Handle the marks fragment
            selectedFrag = new BlankFragment();
            getSupportActionBar().setTitle("Marks");

        } else if (id == R.id.attendence) {
            selectedFrag = new Attendence_Fragment();
            getSupportActionBar().setTitle("Attendance");

        }else if(id==R.id.calendarView)
        {
            selectedFrag = new Calender();
            getSupportActionBar().setTitle("Events");
        }
        else if (id == R.id.map) {
            startActivity(new Intent(this, MapsActivity.class));
        }
        else if(id==R.id.extras)
        {
            selectedFrag = new extras();
            getSupportActionBar().setTitle("Extras");
        }
        else if(id==R.id.contact)
        {
            selectedFrag = new contact();
            getSupportActionBar().setTitle("Contacts");
        }
        else if(id==R.id.map)
        {
            selectedFrag = new Fragment();
            getSupportActionBar().setTitle("Map");
        }
        else if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(LogedIN.this,MainActivity.class));
            finish();
            return true;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_display,selectedFrag).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


