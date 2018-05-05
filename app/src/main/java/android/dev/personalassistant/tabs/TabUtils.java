package android.dev.personalassistant.tabs;

import android.dev.personalassistant.R;
import android.dev.personalassistant.kym.KymTabFragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

/**
 * Created by saurabh on 5/5/18.
 */

public class TabUtils {
    public static void populateTabs(ViewPager viewPager,
                                       TabAdapter adapter,
                                       TabLayout tabLayout){

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }



}
