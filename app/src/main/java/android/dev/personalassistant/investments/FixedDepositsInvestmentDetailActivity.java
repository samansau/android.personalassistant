package android.dev.personalassistant.investments;

import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.investment.FixedDeposit;
import android.dev.personalassistant.entities.kym.BankAccount;
import android.dev.personalassistant.entities.kym.Person;
import android.dev.personalassistant.enums.FDStatus;
import android.dev.personalassistant.helpers.investment.FDHelper;
import android.dev.personalassistant.helpers.kym.DatabaseHelper;
import android.dev.personalassistant.utils.DateParser;
import android.dev.personalassistant.vo.investment.FixedDepositVO;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.dev.personalassistant.R;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static android.dev.personalassistant.utils.Constants.dateFormat;

public class FixedDepositsInvestmentDetailActivity extends AppCompatActivity {

//    Spinner bankObj;
//    Spinner fdStatusObj;
//    TextView fdComments;
//    MultiAutoCompleteTextView fdOwners;
//    TextView fdInceptionDateObj;
//    TextView fdMaturityDateObj;
//    TextView fdInterestObj;
//    TextView fdPrincipalAmountObj;
//    TextView fdMaturityAmountObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_deposits_investment_detail);


    }

    public void saveFD(View view){
//        PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(getApplicationContext());
//        FDHelper fdHelper=new FDHelper();
//        FixedDepositVO fdVO=new FixedDepositVO();
//        BankAccount bankAccount=(BankAccount)bankObj.getSelectedItem();
//        fdVO.setBankAccountId(bankAccount.getBankAccountId());
//        FDStatus fdStatus=(FDStatus) fdStatusObj.getSelectedItem();
//        fdVO.setFdStatus(fdStatus.getValue());
//        fdVO.setFdComments(fdComments.getText().toString());
//        ListAdapter adapter=fdOwners.getAdapter();
//        List<String> personNames=new ArrayList<>();
//        for(int i=0;i<adapter.getCount();i++){
//            Person person=(Person)adapter.getItem(i);
//            personNames.add(person.getFullName());
//        }
//        fdVO.setPersonNames(personNames);
//        DateParser dateParser=new DateParser(dateFormat);
//        try {
//            fdVO.setFdInceptionDate(dateParser.getDate(fdInceptionDateObj.getText().toString()));
//            fdVO.setFdMaturityDate(dateParser.getDate(fdMaturityDateObj.getText().toString()));
//        }catch ( ParseException pe){
//            Log.e("saveFD",pe.getStackTrace().toString());
//        }
//        fdVO.setInterest(Float.parseFloat(fdInterestObj.getText().toString()));
//        fdVO.setInterest(Float.parseFloat(fdPrincipalAmountObj.getText().toString()));
//        fdVO.setInterest(Float.parseFloat(fdMaturityAmountObj.getText().toString()));
//
//
//
//        fdHelper.persistFD(personalAssistantDatabase,fdVO);
    }

}
