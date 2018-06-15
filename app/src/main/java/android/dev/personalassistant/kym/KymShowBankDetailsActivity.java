package android.dev.personalassistant.kym;

import android.content.Intent;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.helpers.kym.BankAccountHelper;
import android.dev.personalassistant.helpers.kym.DatabaseHelper;
import android.dev.personalassistant.main.BaseActivity;
import android.dev.personalassistant.utils.Constants;
import android.dev.personalassistant.vo.kym.BankAccountVO;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.dev.personalassistant.R;
import android.view.View;
import android.widget.TextView;

import static android.dev.personalassistant.utils.Keys.accountNumber;
import static android.dev.personalassistant.utils.Keys.bank;
import static android.dev.personalassistant.utils.Keys.bankAccountId;
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

    int bankAccountIdValue=-1;

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
            this.bankAccountIdValue=bundle.getInt(bankAccountId);
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
        PersonalAssistantDatabase personalAssistantDatabase=DatabaseHelper.getDatabase(getApplicationContext());


        BankAccountVO bankAccountVO=new BankAccountVO();
        bankAccountVO.setAccountNumberValue(accountNumberObj.getText().toString());
        bankAccountVO.setBankAccountIdValue(bankAccountIdValue);
        bankAccountVO.setBankNameValue(bankNameObj.getText().toString());
        bankAccountVO.setBankBranchValue(bankBranchObj.getText().toString());
        bankAccountVO.setNetBankingCustomerIdValue(netBankingCustomerIdObj.getText().toString());
        bankAccountVO.setNetBankingPasswordValue(netBankingPasswordObj.getText().toString());
        bankAccountVO.setPhoneBankingNumberValue(phoneBankingNumberObj.getText().toString());

        BankAccountHelper bankAccountHelper=new BankAccountHelper();
        bankAccountHelper.persistBankAccount(personalAssistantDatabase,bankAccountVO);
        finish();
        startActivity(new Intent(this,KymShowBankListActivity.class));


    }



}
