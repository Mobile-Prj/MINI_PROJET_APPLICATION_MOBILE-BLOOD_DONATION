package com.example.miniprojetapplicationmobileblooddonation.Activities.MenuActivities;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.miniprojetapplicationmobileblooddonation.Adapters.DrawerAdapter;
import com.example.miniprojetapplicationmobileblooddonation.Fragments.DemandersListFragment;
import com.example.miniprojetapplicationmobileblooddonation.Fragments.DonorsListFragment;
import com.example.miniprojetapplicationmobileblooddonation.Fragments.HomeFragment;
import com.example.miniprojetapplicationmobileblooddonation.Fragments.ProfileFragment;
import com.example.miniprojetapplicationmobileblooddonation.Models.SpaceItem;
import com.example.miniprojetapplicationmobileblooddonation.Models.DrawerItem;
import com.example.miniprojetapplicationmobileblooddonation.Models.SimpleItem;
import com.example.miniprojetapplicationmobileblooddonation.R;

import java.util.Arrays;

import slidingnav.SlidingRootNav;
import slidingnav.SlidingRootNavBuilder;

/**
 * Menu Activity for the Menu of Navigation
 */
public class MenuActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener{
    //instantiation of the positions of the Menu elements
    private static final int POS_CLOSE = 0;
    private static final int POS_HOME = 1;
    private static final int POS_PROFILE= 2;
    private static final int POS_DONORSLIST = 3;
    private static final int POS_REQUESTERSLIST= 4;
    private static final int POS_LOGOUT = 6;

    //instantiation of Menu titles and icons
    private String[] screenTitles;
    private Drawable[] screenIcons;

    //Other declarations
    public static SlidingRootNav slidingRootNav;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Retrieve  data from login activity
        bundle = getIntent().getExtras();

        //instantiation of the SlidingNavigation drawer
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        //Load icons and titles
        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        //instantiation of the Adapter
        DrawerAdapter adapter = new DrawerAdapter(this,Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_HOME).setChecked(true),
                createItemFor(POS_PROFILE),
                createItemFor(POS_REQUESTERSLIST),
                createItemFor(POS_DONORSLIST),
                new SpaceItem(260),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);
        //instantiation of the RecyclerView
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        adapter.setSelected(POS_HOME);

    }


    //Changing the current fragment according to the selected position
    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (position == POS_HOME) {
            HomeFragment home= new HomeFragment();
            home.setArguments(bundle);
            transaction.replace(R.id.container,home);
            setTitle(R.string.home);
        }
        else if (position == POS_DONORSLIST) {
            DonorsListFragment donors_list= new DonorsListFragment();
            donors_list.setArguments(bundle);
            transaction.replace(R.id.container,donors_list);
            setTitle(R.string.donors_list);
        }
        else if (position == POS_REQUESTERSLIST) {
            DemandersListFragment requesters_list= new DemandersListFragment();
            requesters_list.setArguments(bundle);
            transaction.replace(R.id.container,requesters_list);
            setTitle(R.string.demanders_list);
        }
        else if (position == POS_PROFILE) {
            ProfileFragment profile= new ProfileFragment();
            profile.setArguments(bundle);
            transaction.replace(R.id.container,profile);
            setTitle(R.string.profile);
        }

        else if (position == POS_LOGOUT) {
            finish();

        }

        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();

    }



    //Set colors for texts and icons
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.primary_color))
                .withTextTint(color(R.color.textColorPrimary))
                .withSelectedIconTint(color(R.color.pink))
                .withSelectedTextTint(color(R.color.pink));
    }

    //function to load the titles of Menu items
    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    //function to load icons of Menu items
    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    //Recover colors
    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }


}