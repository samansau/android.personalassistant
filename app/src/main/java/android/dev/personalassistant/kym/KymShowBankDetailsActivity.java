package android.dev.personalassistant.kym;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.BankAccount;
import android.dev.personalassistant.main.BaseActivity;
import android.dev.personalassistant.utils.Constants;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.dev.personalassistant.R;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class KymShowBankDetailsActivity extends BaseActivity implements Constants{

    private PersonalAssistantDatabase personalAssistantDatabase;

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

    public void saveBankDetails(View view){
        personalAssistantDatabase = Room.databaseBuilder(getApplicationContext(),
                PersonalAssistantDatabase.class,DATABASE_NAME).fallbackToDestructiveMigration().build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                BankAccount bankAccount =new BankAccount();
                TextView bankName = (TextView)findViewById(R.id.bankNameField);
                String text=bankName.getText().toString();
                Log.d("bn",text);
                //bankAccount.setAccountNumber(view.get);

                //personalAssistantDatabase.getBankAccountDAO().insertBankAccount(bankAccount);
            }
        }) .start();
    }
}
