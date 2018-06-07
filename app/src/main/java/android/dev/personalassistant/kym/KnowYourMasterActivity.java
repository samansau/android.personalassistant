package android.dev.personalassistant.kym;

import android.app.Activity;
import android.content.Intent;
import android.dev.personalassistant.R;
import android.dev.personalassistant.kym.KymShowBankDetailsActivity;
import android.dev.personalassistant.kym.KymShowBankListActivity;
import android.dev.personalassistant.kym.KymTabFragment;
import android.dev.personalassistant.tabs.TabAdapter;
import android.dev.personalassistant.tabs.TabFragment;
import android.dev.personalassistant.tabs.TabUtils;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class KnowYourMasterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_your_master);
        populateKymTabs();
    }

    private void populateKymTabs(){
        ViewPager viewPager = (ViewPager) findViewById(R.id.kymViewPager);
        String kymTitles[]=new String[]{"Me","Banks","Cards","Home/s","Car/s"};
        TabFragment kymTabFragment=new KymTabFragment();
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(),kymTitles,kymTabFragment);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.kymTabs);
        TabUtils.populateTabs(viewPager,adapter,tabLayout);

    }

    public void showResidentialDetails(View view){
        Intent intent=new Intent(this,KymShowResidentialDetailsActivity.class);
        startActivity(intent);
    }

    public void showFamilyDetails(View view){
        Intent intent=new Intent(this,KymShowFamilyDetailsActivity.class);
        startActivity(intent);
    }




    public void addPersonalDetails(View view){
        showPersonalDetails(view);
    }

    public void addCarDetails(View view){
        showCarDetails(view);
    }

    public void showPersonalDetails(View view){
        Intent intent=new Intent(this,KymShowPersonalDetailsActivity.class);
        startActivity(intent);
    }

    public void addBankDetails(View view){
        showBankDetails(view);
    }

    public void addCardDetails(View view){
        Intent intent=new Intent(this,KymShowCardDetailsActivity.class);
        startActivity(intent);
    }

    public void showBankDetails(View view){
        Intent intent=new Intent(this,KymShowBankDetailsActivity.class);
        startActivity(intent);
    }

    public void showCarDetails(View view){
        Intent intent=new Intent(this,KymShowCarDetailsActivity.class);
        startActivity(intent);
    }



    public void showBankList(View view){
        Intent intent=new Intent(this,KymShowBankListActivity.class);
        startActivity(intent);
    }
}
