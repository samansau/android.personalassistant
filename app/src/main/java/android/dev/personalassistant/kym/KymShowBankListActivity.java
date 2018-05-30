package android.dev.personalassistant.kym;

import android.app.ListActivity;
import android.dev.personalassistant.R;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.Bank;
import android.dev.personalassistant.entities.BankAccount;
import android.dev.personalassistant.helpers.DatabaseHelper;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KymShowBankListActivity extends ListActivity {


    static final ArrayList<Map<String,String>> list =
            new ArrayList<Map<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kym_show_bank_list);

    }

    public void loadList(){
        ListAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.two_line_list_item,
                new String[] {"bank","branch"},
                new int[] {R.id.text1,R.id.text2}
        );
        populateList();
        setListAdapter(adapter);
    }






    private void populateList() {
        DatabaseHelper databaseHelper=new DatabaseHelper();
        PersonalAssistantDatabase database=databaseHelper.getDatabase(getApplicationContext());
        List<BankAccount> allBankAccounts=database.getBankAccountDAO().fetchAllBankAccounts();
        for(BankAccount bankAccount:allBankAccounts){
            Map<String,String> map = new HashMap();
            Bank bank=bankAccount.getBank();
            map.put("bank",bank.getBankName());
            map.put("branch",bank.getBranch());
            list.add(map);
        }
    }
}
