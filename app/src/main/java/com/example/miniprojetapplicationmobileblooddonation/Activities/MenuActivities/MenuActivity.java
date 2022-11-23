package com.example.miniprojetapplicationmobileblooddonation.Activities.MenuActivities;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.miniprojetapplicationmobileblooddonation.Adapters.DrawerAdapter;
import com.example.miniprojetapplicationmobileblooddonation.Fragments.DemandersListFragment;
import com.example.miniprojetapplicationmobileblooddonation.Fragments.DonorsListFragment;
import com.example.miniprojetapplicationmobileblooddonation.Fragments.HomeFragment;
import com.example.miniprojetapplicationmobileblooddonation.Fragments.ProfileFragment;
import com.example.miniprojetapplicationmobileblooddonation.Models.AnItem;
import com.example.miniprojetapplicationmobileblooddonation.Models.DrawerItem;
import com.example.miniprojetapplicationmobileblooddonation.Models.SimpleItem;
import com.example.miniprojetapplicationmobileblooddonation.R;

import java.util.Arrays;

import slidingrootnav.SlidingRootNav;
import slidingrootnav.SlidingRootNavBuilder;

public class MenuActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener{
    private static final int POS_CLOSE = 0;
    private static final int POS_HOME = 1;
    private static final int POS_PROFILE= 2;
    private static final int POS_DONORSLIST = 3;
    private static final int POS_REQUESTERSLIST= 4;
    private static final int POS_LOGOUT = 6;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_HOME).setChecked(true),
                createItemFor(POS_PROFILE),
                createItemFor(POS_REQUESTERSLIST),
                createItemFor(POS_DONORSLIST),
                new AnItem(260),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        adapter.setSelected(POS_HOME);

        dialog = new Dialog(this);

        openFailDialog();
    }

    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (position == POS_HOME) {
            HomeFragment home= new HomeFragment();
            transaction.replace(R.id.container,home);
            setTitle(R.string.home);
        }
        else if (position == POS_DONORSLIST) {
            DonorsListFragment donors_list= new DonorsListFragment();
            transaction.replace(R.id.container,donors_list);
            setTitle(R.string.donors_list);
        }
        else if (position == POS_REQUESTERSLIST) {
            DemandersListFragment requesters_list= new DemandersListFragment();
            transaction.replace(R.id.container,requesters_list);
            setTitle(R.string.demanders_list);
        }
        else if (position == POS_PROFILE) {
            ProfileFragment profile= new ProfileFragment();
            transaction.replace(R.id.container,profile);
            setTitle(R.string.profile);
        }

        else if (position == POS_LOGOUT) {
            finish();

        }

        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
        /*Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
        showFragment(selectedScreen);*/
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    //set color on selected Toolbar
    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.primary_color))
                .withTextTint(color(R.color.textColorPrimary))
                .withSelectedIconTint(color(R.color.pink))
                .withSelectedTextTint(color(R.color.pink));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

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

    private void openFailDialog() {
        dialog.setContentView(R.layout.dialog_error_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //ImageView imgClose= dialog.findViewById(R.id.imageViewClose);
        Button btnOk = dialog.findViewById(R.id.btn_Error);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
}