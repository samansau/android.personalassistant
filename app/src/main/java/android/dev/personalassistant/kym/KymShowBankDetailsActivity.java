package android.dev.personalassistant.kym;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.Bank;
import android.dev.personalassistant.entities.BankAccount;
import android.dev.personalassistant.helpers.DatabaseHelper;
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
        DatabaseHelper databaseHelper=new DatabaseHelper();
        final PersonalAssistantDatabase personalAssistantDatabase=databaseHelper.getDatabase(getApplicationContext());
        new Thread(new Runnable() {
            @Override
            public void run() {
                BankAccount bankAccount =new BankAccount();
                Bank bank =new Bank();

                TextView bankName = findViewById(R.id.bankNameField);
                String bankNameField=bankName.getText().toString();

                TextView bankbranch = findViewById(R.id.bankBranchField);
                String bankBranchField=bankbranch.getText().toString();


                TextView accountNumber = findViewById(R.id.accountNumberField);
                String accountNumberField=accountNumber.getText().toString();


                TextView netBankingCustomerId = findViewById(R.id.netBankingCustomerIdField);
                String netBankingCustomerIdField=netBankingCustomerId.getText().toString();


                TextView netBankingPassword = findViewById(R.id.netBankingPasswordField);
                String netBankingPasswordField=netBankingPassword.getText().toString();


                TextView phoneBankingNumber = findViewById(R.id.phoneBankingNumberField);
                String phoneBankingNumberField=phoneBankingNumber.getText().toString();

                bank.setBankName(bankNameField);
                bank.setBranch(bankBranchField);
                bankAccount.setBank(bank);
                bankAccount.setAccountNumber(accountNumberField);
                bankAccount.setNetBankingCustomerId(netBankingCustomerIdField);
                bankAccount.setNetBankingPassword(netBankingPasswordField);
                bankAccount.setPhoneBankingNumber(phoneBankingNumberField);

                Log.d("bank account : ",bankAccount.toString());



                personalAssistantDatabase.getBankAccountDAO().insertBankAccount(bankAccount);
            }
        }) .start();
    }
}
