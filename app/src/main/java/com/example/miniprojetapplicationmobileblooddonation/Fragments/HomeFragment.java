package com.example.miniprojetapplicationmobileblooddonation.Fragments;


import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.miniprojetapplicationmobileblooddonation.Adapters.OnboardingAdapter;

import com.example.miniprojetapplicationmobileblooddonation.R;

/**
 * Home Fragment
 */
public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private CardView next;
    private LinearLayout dotsLayout;
    private TextView[] dots;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_on_boarding, container, false);

        return view;
    }
    // Display pages on the home page
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        viewPager = view.findViewById(R.id.viewPager);
        next = view.findViewById(R.id.nextCard);
        dotsLayout = view.findViewById(R.id.dotsLayout);


        OnboardingAdapter adapter = new OnboardingAdapter(getContext());
        viewPager.setAdapter(adapter);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1, true);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dotsFunction(position);
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (position < 3) {
                            viewPager.setCurrentItem(position + 1, true);
                        }
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        dotsFunction(0);

    }
    // dots animation
    private void dotsFunction(int pos) {
        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0 ; i< dots.length ; i++){

            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("."));
            //unselected dots color
            dots[i].setTextColor(color(R.color.grey));
            //unselected dots size
            dots[i].setTextSize(100);

            dotsLayout.addView(dots[i]);

        }

        if (dots.length > 0){
            //selected dot color
            dots[pos].setTextColor(color(R.color.primary_color));
            //selected dots size
            dots[pos].setTextSize(100);
        }
    }
    // setting color
    @ColorInt
    private int color(@ColorRes int res) {

        return ContextCompat.getColor(getContext(), res);
    }

}
