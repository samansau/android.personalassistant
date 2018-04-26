package android.dev.personalassistant.kym;

import android.dev.personalassistant.R;
import android.dev.personalassistant.android.dev.personalassistant.adapters.ExpenseTabFragment;
import android.dev.personalassistant.android.dev.personalassistant.adapters.KymTabFragment;
import android.dev.personalassistant.android.dev.personalassistant.adapters.TabAdapter;
import android.dev.personalassistant.android.dev.personalassistant.adapters.TabFragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class KnowYourMasterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_your_master);
        populateFinancialTransactionsTabs();
    }

    private void populateFinancialTransactionsTabs(){
        ViewPager viewPager;viewPager = (ViewPager) findViewById(R.id.kymViewPager);
        String kymTitles[]=new String[]{"Per", "Fin", "Pas","Nos"};
        TabFragment kymTabFragment=new KymTabFragment();
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(),kymTitles,kymTabFragment);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.expenseTabs);
        tabLayout.setupWithViewPager(viewPager);

    }
}
