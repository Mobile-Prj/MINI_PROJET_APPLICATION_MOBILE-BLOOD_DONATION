package com.example.miniprojetapplicationmobileblooddonation.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.miniprojetapplicationmobileblooddonation.Fragments.DonorsListFragment;
import com.example.miniprojetapplicationmobileblooddonation.Fragments.ProfileFragment;
import com.example.miniprojetapplicationmobileblooddonation.R;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class CoreAppActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core_app);

        replaceFragment(new DonorsListFragment());

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        //navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); // this sets the button to the back icon
        drawerToggle.syncState();

        // pour que la page profile soit la premirer a etre ouverte
        if (savedInstanceState == null){
            // condition pour savoir si c'est la premiere fois que la page est creer ou pas
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.profile);
        }


    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile: {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                Toast.makeText(CoreAppActivity.this, "Page profile", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.donorList: {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DonorsListFragment()).commit();
                Toast.makeText(CoreAppActivity.this, "Page donor list", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.demanderList: {
                Toast.makeText(CoreAppActivity.this, "Page demander list", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.deconnection: {
                Toast.makeText(CoreAppActivity.this, "Page deconnection", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}