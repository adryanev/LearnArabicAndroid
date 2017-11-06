package com.inkubator.adryan.learnarabic.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.stetho.Stetho;
import com.inkubator.adryan.learnarabic.R;
import com.inkubator.adryan.learnarabic.fragment.FragmentDefault;
import com.inkubator.adryan.learnarabic.fragment.FragmentUjian;
import com.inkubator.adryan.learnarabic.fragment.FragmentVideo;
import com.inkubator.adryan.learnarabic.fragment.MateriFragment;
import com.inkubator.adryan.learnarabic.utils.SessionManager;
import com.inkubator.adryan.learnarabic.utils.SyncManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SessionManager sessionManager;
    Fragment fragment;
    Toolbar toolbar;
    SyncManager syncManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            fragment = getSupportFragmentManager().getFragment(savedInstanceState, "myFragmentName");
        }else{
            fragment = new FragmentDefault();
        }
        sessionManager = new SessionManager(getApplicationContext());
        if(!sessionManager.isLoggedIn()){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        syncManager = new SyncManager(MainActivity.this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actionLogout) {
            sessionManager.logoutUser();
            Intent i = new Intent(this, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.sync){
            syncDatabase();
        }

        return super.onOptionsItemSelected(item);
    }

    private void syncDatabase() {

        syncManager.syncAll();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        fragment = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragment = new FragmentDefault();
        } else if (id == R.id.nav_materi) {
            fragment = new MateriFragment();
        } else if (id == R.id.nav_ujian) {
            fragment = new FragmentUjian();
        }
        else if(id == R.id.nav_video){
            fragment = new FragmentVideo();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left);
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean checkConnectivity(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

       if(networkInfo !=null && networkInfo.isConnected()) return true;
       else return false;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "myFragmentName", fragment);
    }
}
