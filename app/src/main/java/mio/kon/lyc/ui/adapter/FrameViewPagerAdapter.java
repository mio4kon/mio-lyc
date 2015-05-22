package mio.kon.lyc.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import mio.kon.lyc.R;
import mio.kon.lyc.factory.MainFragmentFactory;

/**
 * Created by mio on 15-5-14.
 */
public class FrameViewPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private String[] titles;


    public FrameViewPagerAdapter(FragmentManager fm, Context context) {
        super (fm);
        this.context = context;
        titles = context.getResources ().getStringArray (R.array.lyc_tab_array);
    }

    @Override
    public Fragment getItem(int position) {

        return MainFragmentFactory.createFragment (position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }






}
