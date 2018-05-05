package android.dev.personalassistant.investments;

import android.dev.personalassistant.kym.KymTabFragment;
import android.dev.personalassistant.tabs.TabAdapter;
import android.dev.personalassistant.tabs.TabFragment;
import android.dev.personalassistant.tabs.TabUtils;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.dev.personalassistant.R;

public class InvestmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investments);
        populateInvestmentsTabs();

    }

    private void populateInvestmentsTabs(){
        ViewPager viewPager = (ViewPager) findViewById(R.id.kymViewPager);
        String kymTitles[]=new String[]{"Per", "Fin", "Pas","Nos"};
        TabFragment kymTabFragment=new KymTabFragment();
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(),kymTitles,kymTabFragment);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.kymTabs);
        TabUtils.populateTabs(viewPager,adapter,tabLayout);

    }
}
