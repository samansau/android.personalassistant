package android.dev.personalassistant.investments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.helpers.kym.BankAccountHelper;
import android.dev.personalassistant.helpers.kym.DatabaseHelper;
import android.dev.personalassistant.helpers.SMSHelper;
import android.dev.personalassistant.vo.kym.BankAccountVO;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.dev.personalassistant.R;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.dev.personalassistant.utils.Keys.accountNumber;
import static android.dev.personalassistant.utils.Keys.bank;
import static android.dev.personalassistant.utils.Keys.bankBalance;
import static android.dev.personalassistant.utils.Keys.branch;

public class SavingsInvestmentListActivity extends AppCompatActivity {
    static final ArrayList<HashMap<String,String>> list =
            new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings_investment_list);

        final ListView listView = (ListView) findViewById(R.id.listSavings);
        final ListAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.four_line_list_item_investment_savings,
                new String[] {bank,branch,accountNumber,bankBalance},
                new int[] {R.id.text1,R.id.text2,R.id.text3,R.id.text4}
        );
        populateListOfBankSavings();
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                showSavings(view);
            }
        });
    }

    public void showSavings(View view){
        Intent intent =new Intent(view.getContext(),SavingsInvestmentDetailActivity.class);
        startActivity(intent);
    }

    public void scanSMSInbox(View view){
        if(ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") != PackageManager.PERMISSION_GRANTED){
            final int REQUEST_CODE_ASK_PERMISSIONS = 123;
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
        }
        SMSHelper smsHelper=new SMSHelper();
        List<Map<String,String>> allSMS=smsHelper.fetchSMSMessages(getContentResolver());
        String [] latestMessage=new String[1];
        long[]dateTime=new long[1];
        allSMS.stream().forEach(m-> {
            String messageBody=m.get("body");
            Long messageDateSent=Long.parseLong(m.get("date_sent"));
            list.stream().map( banks ->  banks.get(accountNumber)).forEach( an -> {
                if(messageBody.contains("balance") && messageBody.contains(an)){
                    if(dateTime[0]<messageDateSent){
                        dateTime[0]=messageDateSent;
                        latestMessage[0]=messageBody;
                    }
                }
            });
        });
        System.out.println("LatestMessage>>"+latestMessage[0]);
    }

    private void populateListOfBankSavings(){
        list.clear();
        BankAccountHelper bankAccountHelper=new BankAccountHelper();
        PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(getApplicationContext());
        try {
            List<BankAccountVO> bankAccountVO=bankAccountHelper.fetchAllBankAccountVOs(personalAssistantDatabase);
            bankAccountVO.stream().forEach(vo -> {
                HashMap<String,String> accountDetails=new HashMap<String, String>();
                accountDetails.put(bank,vo.getBankNameValue());
                accountDetails.put(branch,vo.getBankBranchValue());
                accountDetails.put(accountNumber,vo.getAccountNumberValue());
                accountDetails.put(bankBalance,"₹ 0");
                list.add(accountDetails);
            });
        }catch (InterruptedException ie){
            Log.e("populateListOfBankSav..", ie.getStackTrace().toString());
        }


    }
}
