package android.dev.personalassistant.android.dev.personalassistant.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by saurabh on 4/5/18.
 */

public class ExpenseTabAdapters extends FragmentPagerAdapter {

    private String title[] = {"All", "Electronic", "Manual"};
    public ExpenseTabAdapters(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return ExpenseTabFragment.getInstance(position);
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
