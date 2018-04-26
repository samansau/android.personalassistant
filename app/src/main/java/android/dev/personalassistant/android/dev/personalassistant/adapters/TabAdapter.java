package android.dev.personalassistant.android.dev.personalassistant.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by saurabh on 4/5/18.
 */

public class TabAdapter extends FragmentPagerAdapter {

    private String title[] = {};
    private TabFragment tabFragment=null;
    public TabAdapter(FragmentManager fragmentManager, String title[], TabFragment tabFragment){
        super(fragmentManager);
        this.title=title;
        this.tabFragment=tabFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return tabFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }



}
