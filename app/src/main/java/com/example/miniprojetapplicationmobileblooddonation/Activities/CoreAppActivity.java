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

import com.example.miniprojetapplicationmobileblooddonation.Fragments.DemandersListFragment;
import com.example.miniprojetapplicationmobileblooddonation.Fragments.DonorsListFragment;
import com.example.miniprojetapplicationmobileblooddonation.Fragments.HomeFragment;
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
        setTitle(R.string.home);
        setContentView(R.layout.activity_core_app);

        //replaceFragment(new DonorsListFragment());

        // definir le toolbar
        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); // this sets the button to the back icon
        drawerToggle.syncState();

        // pour que la page profile soit la premirer a etre ouverte
        if (savedInstanceState == null){
            // condition pour savoir si c'est la premiere fois que la page est creer ou pas
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.menu_home);
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
        // on teste si le drawer est deja ouvert alors on le ferme
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
            // en fonction de l'item selectionne sur le menu on effectue une action
            // les id sont les meme utilses pour les referencer dans la liste menu dans main_menu.xml
            case R.id.menu_home: {
                replaceFragment(new HomeFragment());
                // on change le title de l'activite qui contient les fragments
                setTitle(R.string.home);
                break;
            }
            case R.id.profile: {
                replaceFragment(new ProfileFragment());
                setTitle(R.string.profile);
                break;
            }
            case R.id.donorList: {
                replaceFragment(new DonorsListFragment());
                setTitle(R.string.donors_list);
                break;
            }
            case R.id.demanderList: {
                replaceFragment(new DemandersListFragment());
                setTitle(R.string.demanders_list);
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