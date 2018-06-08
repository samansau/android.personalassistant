package android.dev.personalassistant.kym;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.dev.personalassistant.R;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.enums.CardType;
import android.dev.personalassistant.helpers.BankAccountHelper;
import android.dev.personalassistant.helpers.CarHelper;
import android.dev.personalassistant.helpers.CardHelper;
import android.dev.personalassistant.helpers.DatabaseHelper;
import android.dev.personalassistant.helpers.PersonHelper;
import android.dev.personalassistant.tabs.TabFragment;
import android.dev.personalassistant.vo.BankAccountVO;
import android.dev.personalassistant.vo.CarVO;
import android.dev.personalassistant.vo.CardVO;
import android.dev.personalassistant.vo.PersonVO;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.dev.personalassistant.utils.Keys.aadharCardNumber;
import static android.dev.personalassistant.utils.Keys.accountNumber;
import static android.dev.personalassistant.utils.Keys.bank;
import static android.dev.personalassistant.utils.Keys.bankAccountId;
import static android.dev.personalassistant.utils.Keys.branch;
import static android.dev.personalassistant.utils.Keys.cardCategory;
import static android.dev.personalassistant.utils.Keys.cardCvv;
import static android.dev.personalassistant.utils.Keys.cardExpiryDate;
import static android.dev.personalassistant.utils.Keys.cardId;
import static android.dev.personalassistant.utils.Keys.cardNumber;
import static android.dev.personalassistant.utils.Keys.cardType;
import static android.dev.personalassistant.utils.Keys.dob;
import static android.dev.personalassistant.utils.Keys.drivingLisenceExpiry;
import static android.dev.personalassistant.utils.Keys.drivingLisenceNumber;
import static android.dev.personalassistant.utils.Keys.fullName;
import static android.dev.personalassistant.utils.Keys.netBankingCustomerId;
import static android.dev.personalassistant.utils.Keys.netBankingPassword;
import static android.dev.personalassistant.utils.Keys.panCardNumber;
import static android.dev.personalassistant.utils.Keys.passportExpiry;
import static android.dev.personalassistant.utils.Keys.passportNumber;
import static android.dev.personalassistant.utils.Keys.personId;
import static android.dev.personalassistant.utils.Keys.phoneBankingNumber;
import static android.dev.personalassistant.utils.Keys.relation;

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

    static final ArrayList<Map<String,String>> personList =
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
                view= getPersonalView(inflater, container);
                break;
            case 1:
                view = getBankAccountView(inflater, container);
                break;
            case 2:
                view = getCardView(inflater, container);
                break;
            case 3:
                view= inflater.inflate(R.layout.activity_kym_show_residential_details, container, false);
                break;
            case 4:
                view= getCarView(inflater, container);//inflater.inflate(R.layout.activity_kym_show_car_details, container, false);
                break;

        }
        return view;
    }

    @NonNull
    private View getCarView(LayoutInflater inflater, ViewGroup container) {
        View view;
        view= inflater.inflate(R.layout.activity_kym_show_car_details, container, false);
        String [] carNumbers= populateCarNumberList(getContext());

        Spinner carNumbersObj = (Spinner) view.findViewById(R.id.carNumber);
        ArrayAdapter<String> adapterCardType = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_dropdown_item,carNumbers);
        carNumbersObj.setAdapter(adapterCardType);

        TextView carNumberFieldObj = view.findViewById(R.id.carNumberField);;
        TextView carNameObj = view.findViewById(R.id.carNameField);;
        TextView carInsuranceObj = view.findViewById(R.id.carInsuranceNumberField);;
        TextView carInsuranceExpiry = view.findViewById(R.id.carInsuranceExpiryField);;
        TextView carPUCExpiry = view.findViewById(R.id.carPUCExpiryField);;
        List<CarVO> carList= populateCarList(getContext());

        carNumbersObj.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long is) {
                CarVO carVO=carList.get(pos);
                carVO.setNew(false);
                if(adapterView.getSelectedItem()!=null) {
                    carNumberFieldObj.setText(adapterView.getSelectedItem().toString());

                }
                carNameObj.setText(carVO.getCarName());
                carInsuranceObj.setText(carVO.getCarInsuranceNumber());
                carInsuranceExpiry.setText(carVO.getCarInsuranceExpiry());
                carPUCExpiry.setText(carVO.getCarPUCExpiry());

            }
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }


    @NonNull
    private View getPersonalView(LayoutInflater inflater, ViewGroup container) {
        View view;
        view= inflater.inflate(R.layout.activity_kym_show_personal_list, container, false);
        ListView personalListView = (ListView) view.findViewById(R.id.listPersons);
        ListAdapter personAdapter = new SimpleAdapter(
                view.getContext(),
                personList,
                R.layout.two_line_list_item,
                new String[] {fullName,relation},
                new int[] {R.id.text1,R.id.text2}
        );
        populatePersonalList(getContext());
        personalListView.setAdapter(personAdapter);
        personalListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                PersonHelper personHelper=new PersonHelper();
                HashMap<String,String> personData=(HashMap)adapterView.getItemAtPosition(pos);;
                final String personFullName=personData.get(fullName);
                PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(view.getContext());
                personHelper.deletePerson(personalAssistantDatabase,personFullName);
                ((Activity)view.getContext()).recreate();
                return false;
            }
        });


        personalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long is) {
                Intent intent=new Intent(getContext(),KymShowPersonalDetailsActivity.class);
                Bundle extras=new Bundle();

                PersonHelper personHelper=new PersonHelper();
                HashMap<String,String> personData=(HashMap)adapterView.getItemAtPosition(pos);
                final String personFullName=personData.get(fullName);
                PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(view.getContext());
                try {
                    PersonVO personVO = personHelper.fetchPersonVOByPersonName(personalAssistantDatabase,personFullName);
                    extras.putInt(personId,personVO.getPersonId());
                    extras.putString(fullName, personVO.getFullName());
                    extras.putString(relation, personVO.getRelation());
                    extras.putString(dob, personVO.getDob());
                    extras.putString(panCardNumber, personVO.getPanCardNumber());
                    extras.putString(aadharCardNumber, personVO.getAadharCardNumber());
                    extras.putString(passportNumber, personVO.getPassportNumber());
                    extras.putString(passportExpiry, personVO.getPassportExpiry());
                    extras.putString(drivingLisenceNumber, personVO.getDrivingLisenceNumber());
                    extras.putString(drivingLisenceExpiry, personVO.getDrivingLisenceExpiry());

                }catch (InterruptedException ie){
                    Log.e("InterruptedException",ie.getStackTrace().toString());
                }
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        return view;
    }

    @NonNull
    private View getCardView(LayoutInflater inflater, ViewGroup container) {
        View view;
        view= inflater.inflate(R.layout.activity_kym_show_card_list, container, false);
        ListView cardListView = (ListView) view.findViewById(R.id.listCards);
        ListAdapter cardAdapter = new SimpleAdapter(
                view.getContext(),
                cardList,
                R.layout.three_line_list_item,
                new String[] {bank,cardNumber,cardExpiryDate},
                new int[] {R.id.text1,R.id.text2,R.id.text3}
        );
        populateCardList(getContext());
        cardListView.setAdapter(cardAdapter);

        cardListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                CardHelper cardHelper=new CardHelper();
                HashMap<String,String> cardData=(HashMap)adapterView.getItemAtPosition(pos);;
                final String cardNumberValue=cardData.get(cardNumber);
                PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(view.getContext());
                cardHelper.deleteCard(personalAssistantDatabase,cardNumberValue);
                ((Activity)view.getContext()).recreate();
                return false;
            }
        });

        cardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long is) {
                Intent intent=new Intent(getContext(),KymShowCardDetailsActivity.class);
                Bundle extras=new Bundle();

                CardHelper cardHelper=new CardHelper();
                HashMap<String,String> cardData=(HashMap)adapterView.getItemAtPosition(pos);
                final String cardNumberStr=cardData.get(cardNumber);
                PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(view.getContext());
                try {
                    CardVO cardVO = cardHelper.fetchCardVOByCardNumber(personalAssistantDatabase,cardNumberStr);
                    extras.putInt(cardId, cardVO.getCardId());
                    extras.putString(cardType, cardVO.getCardTypeValue());
                    extras.putString(bank, cardVO.getBankName());
                    extras.putString(bankAccountId, cardVO.getBranch());
                    extras.putString(cardCategory, cardVO.getCardCategoryValue());
                    extras.putString(cardNumber, cardVO.getCardNumberValue());
                    extras.putString(cardExpiryDate, cardVO.getCardExpiryDateValue());
                    extras.putString(cardCvv, cardVO.getCardCvvValue());
                }catch (InterruptedException ie){
                    Log.e("InterruptedException",ie.getStackTrace().toString());
                }
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        return view;
    }

    private View getBankAccountView(LayoutInflater inflater, ViewGroup container) {
        View view;
        view= inflater.inflate(R.layout.activity_kym_show_bank_list, container, false);
        ListView bankAccountListView = (ListView) view.findViewById(R.id.listBanks);
        populateBankList(getContext());
        ListAdapter bankAdapter = new SimpleAdapter(
                view.getContext(),
                bankList,
                R.layout.three_line_list_item,
                new String[] {bank,branch,accountNumber},
                new int[] {R.id.text1,R.id.text2,R.id.text3}
        );

        bankAccountListView.setAdapter(bankAdapter);

        bankAccountListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                BankAccountHelper bankAccountHelper=new BankAccountHelper();
                HashMap<String,String> bankAccountData=(HashMap)adapterView.getItemAtPosition(pos);;
                final String accountNumberValue=bankAccountData.get(accountNumber);
                PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(view.getContext());
                try {
                    bankAccountHelper.deleteCard(personalAssistantDatabase, accountNumberValue);

                }catch (SQLiteConstraintException ex){
                    Toast.makeText(view.getContext(),("Some Card/s hold refernce to "+accountNumberValue+" bank reference"),
                            Toast.LENGTH_SHORT).show();
                }
                ((Activity) view.getContext()).recreate();
                return false;
            }
        });

        bankAccountListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                map.put(bank, cardVO.getBankName());
                map.put(cardNumber, cardVO.getCardNumberValue());
                map.put(cardExpiryDate, cardVO.getCardExpiryDateValue());
                cardList.add(map);
            }

        }catch (InterruptedException ie){
            Log.e(KymTabFragment.class.getName() , ie.getStackTrace().toString());
        }
    }

    private String [] populateCarNumberList(final Context context) {
        String [] carNumbers = null;
        try {
            CarHelper carHelper = new CarHelper();
            List<CarVO> carVOs=carHelper.fetchAllCarVOs(DatabaseHelper.getDatabase(context));
            carNumbers=new String[carVOs.size()];
            int i=0;
            for(CarVO carVO : carVOs){
                carNumbers[i++]=carVO.getCarNumber();
            }

        }catch (InterruptedException ie){
            Log.e(KymTabFragment.class.getName() , ie.getStackTrace().toString());
        }
        return  carNumbers;
    }

    private List<CarVO> populateCarList(final Context context) {
        List<CarVO> carVOs =null;
        try {
            CarHelper carHelper = new CarHelper();
            carVOs=carHelper.fetchAllCarVOs(DatabaseHelper.getDatabase(context));
        }catch (InterruptedException ie){
            Log.e(KymTabFragment.class.getName() , ie.getStackTrace().toString());
        }
        return carVOs;
    }

    private void populatePersonalList(final Context context) {
        personList.clear();
        try {
            PersonHelper personHelper = new PersonHelper();
            List<PersonVO> personVOs=personHelper.fetchAllPersonVOs(DatabaseHelper.getDatabase(context));
            for(PersonVO personVO : personVOs){
                Map<String, String> map = new HashMap();
                map.put(fullName, personVO.getFullName());
                map.put(relation, personVO.getRelation());
                personList.add(map);
            }

        }catch (InterruptedException ie){
            Log.e(KymTabFragment.class.getName() , ie.getStackTrace().toString());
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }



}
