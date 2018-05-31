package android.dev.personalassistant.kym;

import android.content.Context;
import android.content.Intent;
import android.dev.personalassistant.R;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.Bank;
import android.dev.personalassistant.entities.BankAccount;
import android.dev.personalassistant.helpers.DatabaseHelper;
import android.dev.personalassistant.tabs.TabFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.dev.personalassistant.utils.Keys.accountNumber;
import static android.dev.personalassistant.utils.Keys.bank;
import static android.dev.personalassistant.utils.Keys.branch;
import static android.dev.personalassistant.utils.Keys.netBankingCustomerId;
import static android.dev.personalassistant.utils.Keys.netBankingPassword;
import static android.dev.personalassistant.utils.Keys.phoneBankingNumber;

/**
 * Created by saurabh on 4/5/18.
 */

public class KymTabFragment extends TabFragment {
    int position;
    private TextView textView;
    DatabaseHelper databaseHelper = new DatabaseHelper();
    PersonalAssistantDatabase database;
    static final ArrayList<Map<String,String>> list =
            new ArrayList<Map<String,String>>();

    public  Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        KymTabFragment tabFragment = new KymTabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =null;
        switch(position){
            case 0:
                view= inflater.inflate(R.layout.kym_personal_fragment, container, false);
                break;
            case 1:
                view= inflater.inflate(R.layout.activity_kym_show_bank_list, container, false);
                ListView listView = (ListView) view.findViewById(R.id.listBanks);
                ListAdapter adapter = new SimpleAdapter(
                        view.getContext(),
                        list,
                        R.layout.three_line_list_item,
                        new String[] {bank,branch,accountNumber},
                        new int[] {R.id.text1,R.id.text2,R.id.text3}
                );
                //Utils.populateList(list);
                populateList(view.getContext());
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long is) {
                        HashMap<String,String> bankData=(HashMap)adapterView.getItemAtPosition(pos);
                        final String bankName=bankData.get(bank);
                        final String branchName=bankData.get(branch);
                        final String accountNumberStr=bankData.get(accountNumber);

                        if(database==null)
                            database = databaseHelper.getDatabase(view.getContext());


                        final Intent intent=new Intent(getContext(),KymShowBankDetailsActivity.class);
                        final Bundle extras=new Bundle();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                    BankAccount bankAccount = database.getBankAccountDAO().fetchBankAccountByAccountNumber(accountNumberStr);

                                extras.putString(bank,bankName);
                                extras.putString(branch,branchName);
                                extras.putString(accountNumber,accountNumberStr);
                                extras.putString(netBankingCustomerId,bankAccount.getNetBankingCustomerId());
                                extras.putString(netBankingPassword,bankAccount.getNetBankingPassword());
                                extras.putString(phoneBankingNumber,bankAccount.getPhoneBankingNumber());
                                intent.putExtras(extras);
                                startActivity(intent);
                                }
                            }).start();






                    }
                });
                break;
        }
        return view;
    }

    private void populateList(final Context context) {
        list.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(database==null)
                    database = databaseHelper.getDatabase(context);
                List<BankAccount> allBankAccounts = database.getBankAccountDAO().fetchAllBankAccounts();
                Log.d("bank accounts : ", allBankAccounts.size()+"");
                for (BankAccount bankAccount : allBankAccounts) {
                    Map<String, String> map = new HashMap();
                    Bank bankObj = bankAccount.getBank();
                    map.put(bank, bankObj.getBankName());
                    map.put(branch, bankObj.getBranch());
                    map.put(accountNumber, bankAccount.getAccountNumber());

                    list.add(map);
                }
            }
        }).start();
    }






    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

}
