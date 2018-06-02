package android.dev.personalassistant.kym;

import android.content.Context;
import android.content.Intent;
import android.dev.personalassistant.R;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.Bank;
import android.dev.personalassistant.entities.BankAccount;
import android.dev.personalassistant.helpers.BankAccountHelper;
import android.dev.personalassistant.helpers.CardHelper;
import android.dev.personalassistant.helpers.DatabaseHelper;
import android.dev.personalassistant.tabs.TabFragment;
import android.dev.personalassistant.vo.BankAccountVO;
import android.dev.personalassistant.vo.CardVO;
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
import static android.dev.personalassistant.utils.Keys.bankAccountId;
import static android.dev.personalassistant.utils.Keys.branch;
import static android.dev.personalassistant.utils.Keys.cardExpirydate;
import static android.dev.personalassistant.utils.Keys.cardNumber;
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
    static final ArrayList<Map<String,String>> bankList =
            new ArrayList<Map<String,String>>();

    static final ArrayList<Map<String,String>> cardList =
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
                populateBankList(getContext());
                ListAdapter bankAdapter = new SimpleAdapter(
                        view.getContext(),
                        bankList,
                        R.layout.three_line_list_item,
                        new String[] {bank,branch,accountNumber},
                        new int[] {R.id.text1,R.id.text2,R.id.text3}
                );

                listView.setAdapter(bankAdapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long is) {
                        Intent intent=new Intent(getContext(),KymShowBankDetailsActivity.class);
                        Bundle extras=new Bundle();

                        BankAccountHelper bankAccountHelper=new BankAccountHelper();
                        HashMap<String,String> bankData=(HashMap)adapterView.getItemAtPosition(pos);
                        final String accountNumberStr=bankData.get(accountNumber);
                        PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(view.getContext());
                        try {
                            BankAccountVO bankAccountVO = bankAccountHelper.fetchBankAccountVOFromAccountNumber(personalAssistantDatabase, accountNumberStr);

                            extras.putString(bank, bankAccountVO.getBankNameValue());
                            extras.putString(branch, bankAccountVO.getBankBranchValue());
                            extras.putInt(bankAccountId, bankAccountVO.getBankAccountIdValue());
                            extras.putString(accountNumber, accountNumberStr);
                            extras.putString(netBankingCustomerId, bankAccountVO.getNetBankingCustomerIdValue());
                            extras.putString(netBankingPassword, bankAccountVO.getNetBankingPasswordValue());
                            extras.putString(phoneBankingNumber, bankAccountVO.getPhoneBankingNumberValue());
                        }catch (InterruptedException ie){
                            Log.e("InterruptedException",ie.getStackTrace().toString());
                        }
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });
                break;

            case 2:
                view= inflater.inflate(R.layout.activity_kym_show_card_list, container, false);
                ListView cardListView = (ListView) view.findViewById(R.id.listCards);
                ListAdapter cardAdapter = new SimpleAdapter(
                        view.getContext(),
                        cardList,
                        R.layout.three_line_list_item,
                        new String[] {bank,cardNumber,cardExpirydate},
                        new int[] {R.id.text1,R.id.text2,R.id.text3}
                );
                populateCardList(getContext());
                cardListView.setAdapter(cardAdapter);

        }
        return view;
    }

    private void populateBankList(final Context context) {
        bankList.clear();
        try {
            BankAccountHelper bankAccountHelper = new BankAccountHelper();
            List<BankAccountVO> bankAccountVOs=bankAccountHelper.fetchAllBankAccountVOs(DatabaseHelper.getDatabase(context));
            for(BankAccountVO bankAccountVO : bankAccountVOs){
                Map<String, String> map = new HashMap();
                map.put(bank, bankAccountVO.getBankNameValue());
                map.put(branch, bankAccountVO.getBankBranchValue());
                map.put(accountNumber, bankAccountVO.getAccountNumberValue());
                bankList.add(map);
            }

        }catch (InterruptedException ie){
            Log.e(KymTabFragment.class.getName() , ie.getStackTrace().toString());
        }
    }

    private void populateCardList(final Context context) {
        cardList.clear();
        try {
            CardHelper cardHelper = new CardHelper();
            List<CardVO> cardVOs=cardHelper.fetchAllCardVOs(DatabaseHelper.getDatabase(context));
            for(CardVO cardVO : cardVOs){
                Map<String, String> map = new HashMap();
                map.put(bank, cardVO.getBankNameValue());
                map.put(cardNumber, cardVO.getCardNumberValue());
                map.put(cardExpirydate, cardVO.getCardExpiryDateValue());
                cardList.add(map);
            }

        }catch (InterruptedException ie){
            Log.e(KymTabFragment.class.getName() , ie.getStackTrace().toString());
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

}
