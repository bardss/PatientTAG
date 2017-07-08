package com.example.jakubaniola.patienttag.activities.checktag;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TagFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Read ID", "Give ID" };
    private ReadTagFragment readTagFragment;
    private WriteTagFragment writeTagFragment;

    public TagFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        readTagFragment = ReadTagFragment.newInstance();
        writeTagFragment = WriteTagFragment.newInstance();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return readTagFragment;
        } else {
           return writeTagFragment;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}