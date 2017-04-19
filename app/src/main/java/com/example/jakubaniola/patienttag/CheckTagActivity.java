package com.example.jakubaniola.patienttag;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckTagActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;
    @BindView(R.id.change_fragment_floating_button)
    FloatingActionButton changeFragmentFB;

    private boolean readTagOpened = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_tag);
        ButterKnife.bind(this);

        setupTabLayout();
        setupButton();
    }

    private void setupTabLayout() {
        viewPager.setAdapter(new TagFragmentPagerAdapter(getSupportFragmentManager(),
                CheckTagActivity.this));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupButton(){
        changeFragmentFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readTagOpened) {
                    viewPager.setCurrentItem(0);
                } else {
                    viewPager.setCurrentItem(1);
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffsetPixels == 0 && readTagOpened == false){
                    changeFragmentFB.setImageDrawable(getResources().getDrawable(R.drawable.read_tag));
                    readTagOpened = true;
                } else if (positionOffsetPixels == 0 && readTagOpened == true) {
                    changeFragmentFB.setImageDrawable(getResources().getDrawable(R.drawable.add_new_tag));
                    readTagOpened = false;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {

    }

}
