package android.dev.personalassistant.kym;

import android.content.Intent;
import android.dev.personalassistant.main.BaseActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.dev.personalassistant.R;
import android.view.View;

public class KymShowBankDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kym_show_bank_details);

        mDrawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name,R.string.app_name);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        initDrawer();
        createExpenseToolBar();
    }

    public void showCardList(View view){
        Intent intent=new Intent(this,KymShowCardListActivity.class);
        startActivity(intent);
    }
}