package android.dev.personalassistant.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by saurabh on 4/5/18.
 */

public class TabAdapter extends FragmentPagerAdapter {

    private String title[] = {};
    private TabFragment tabFragment=null;
    private int size;
    public TabAdapter(FragmentManager fragmentManager, String title[], TabFragment tabFragment){
        super(fragmentManager);
        this.title=title;
        this.tabFragment=tabFragment;
    }

    public TabAdapter(FragmentManager fragmentManager, int size, TabFragment tabFragment){
        super(fragmentManager);
        this.size=size;
        this.tabFragment=tabFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return tabFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        if(title.length==0) return size;
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }



}
