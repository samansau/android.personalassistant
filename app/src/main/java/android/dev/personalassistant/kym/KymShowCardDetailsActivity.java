package android.dev.personalassistant.kym;

import android.content.Intent;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.BankAccount;
import android.dev.personalassistant.enums.CardType;
import android.dev.personalassistant.helpers.BankAccountHelper;
import android.dev.personalassistant.helpers.CardHelper;
import android.dev.personalassistant.helpers.DatabaseHelper;
import android.dev.personalassistant.vo.BankAccountVO;
import android.dev.personalassistant.vo.CardVO;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.dev.personalassistant.R;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class KymShowCardDetailsActivity extends AppCompatActivity {

    Spinner spinnerCardType;
    Spinner spinnerCardBankBranchName;
    TextView cardCategoryObj;
    TextView cardNumberObj;
    TextView cardExpiryDateObj;
    TextView cardCvvObj;

    int cardIdValue=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kym_show_card_details);

        spinnerCardType = (Spinner) findViewById(R.id.cardType);
        ArrayAdapter<CardType> adapterCardType = new ArrayAdapter<CardType>(this,android.R.layout.simple_spinner_dropdown_item,CardType.values());
        spinnerCardType.setAdapter(adapterCardType);

        spinnerCardBankBranchName = (Spinner) findViewById(R.id.cardBankName);
        try {
            BankAccountHelper bankAccountHelper = new BankAccountHelper();
            List<BankAccountVO> bankAccountVOs= bankAccountHelper.fetchAllBankAccountVOs(DatabaseHelper.getDatabase(getBaseContext()));


            String [] bankBranchNames=bankAccountVOs.stream().map(bankAccountVO -> {
                return bankAccountVO.getBankNameValue()+","+bankAccountVO.getBankBranchValue();
            }).toArray(String [] ::new);

            ArrayAdapter<String> adapterBankName = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,bankBranchNames);
            spinnerCardBankBranchName.setAdapter(adapterBankName);

            cardCategoryObj= findViewById(R.id.cardCategoryField);
            cardNumberObj =findViewById(R.id.cardNumberField);
            cardExpiryDateObj=findViewById(R.id.cardExpiryField);
            cardCvvObj=findViewById(R.id.cardCvvField);



        }catch (InterruptedException ie){
            Log.e(KymShowCardDetailsActivity.class.getName(),ie.getStackTrace().toString());
        }

    }

    public void saveCardDetails(View view){
        PersonalAssistantDatabase personalAssistantDatabase=DatabaseHelper.getDatabase(getApplicationContext());
        CardVO cardVO=new CardVO();
        String bankBranch=spinnerCardBankBranchName.getSelectedItem().toString();
        String bankName=bankBranch.split(",")[0];
        String branch=bankBranch.split(",")[1];

        cardVO.setCardId(cardIdValue);
        cardVO.setBankNameValue(bankName);
        cardVO.setBranchValue(branch);
        cardVO.setCardTypeValue(spinnerCardType.getSelectedItem().toString());


        cardVO.setCardCategoryValue(cardCategoryObj.getText().toString());
        cardVO.setCardExpiryDateValue(cardExpiryDateObj.getText().toString());
        cardVO.setCardCvvValue(cardCvvObj.getText().toString());
        cardVO.setCardNumberValue(cardNumberObj.getText().toString());

        CardHelper cardHelper=new CardHelper();

        cardHelper.persistCard(personalAssistantDatabase,cardVO);
        finish();
        startActivity(new Intent(this,KymShowCardListActivity.class));


    }


}
