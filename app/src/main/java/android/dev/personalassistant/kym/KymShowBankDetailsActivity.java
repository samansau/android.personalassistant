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

import static android.dev.personalassistant.utils.Keys.accountNumber;
import static android.dev.personalassistant.utils.Keys.bank;
import static android.dev.personalassistant.utils.Keys.branch;
import static android.dev.personalassistant.utils.Keys.netBankingCustomerId;
import static android.dev.personalassistant.utils.Keys.netBankingPassword;
import static android.dev.personalassistant.utils.Keys.phoneBankingNumber;

public class KymShowBankDetailsActivity extends BaseActivity implements Constants{

    TextView bankNameObj;
    TextView bankBranchObj;
    TextView accountNumberObj;
    TextView netBankingCustomerIdObj;
    TextView netBankingPasswordObj;
    TextView phoneBankingNumberObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kym_show_bank_details);

        mDrawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name,R.string.app_name);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        bankNameObj =findViewById(R.id.bankNameField);
        bankBranchObj = findViewById(R.id.bankBranchField);
        accountNumberObj =findViewById(R.id.accountNumberField);
        netBankingCustomerIdObj = findViewById(R.id.netBankingCustomerIdField);
        netBankingPasswordObj =findViewById(R.id.netBankingPasswordField);
        phoneBankingNumberObj = findViewById(R.id.phoneBankingNumberField);
        initDrawer();
        createExpenseToolBar();
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            String bankValue = bundle.getString(bank);
            String branchValue = bundle.getString(branch);
            String accountNumberValue = bundle.getString(accountNumber);
            String netBankingCustomerIdValue = bundle.getString(netBankingCustomerId);
            String netBankingPasswordValue = bundle.getString(netBankingPassword);
            String phoneBankingNumberValue = bundle.getString(phoneBankingNumber);

            bankNameObj.setText(bankValue);
            bankBranchObj.setText(branchValue);
            accountNumberObj.setText(accountNumberValue);
            netBankingCustomerIdObj.setText(netBankingCustomerIdValue);
            netBankingPasswordObj.setText(netBankingPasswordValue);
            phoneBankingNumberObj.setText(phoneBankingNumberValue);

        }
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
                String bankNameField=bankNameObj.getText().toString();
                String bankBranchField=bankBranchObj.getText().toString();
                String accountNumberField=accountNumberObj.getText().toString();
                String netBankingCustomerIdField=netBankingCustomerIdObj.getText().toString();
                String netBankingPasswordField=netBankingPasswordObj.getText().toString();
                String phoneBankingNumberField=phoneBankingNumberObj.getText().toString();

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
