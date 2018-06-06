package android.dev.personalassistant.kym;

import android.content.Intent;
import android.dev.personalassistant.R;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.enums.CardType;
import android.dev.personalassistant.enums.Relations;
import android.dev.personalassistant.helpers.BankAccountHelper;
import android.dev.personalassistant.helpers.CardHelper;
import android.dev.personalassistant.helpers.DatabaseHelper;
import android.dev.personalassistant.vo.BankAccountVO;
import android.dev.personalassistant.vo.CardVO;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.dev.personalassistant.utils.Keys.bank;
import static android.dev.personalassistant.utils.Keys.cardCategory;
import static android.dev.personalassistant.utils.Keys.cardCvv;
import static android.dev.personalassistant.utils.Keys.cardExpiryDate;
import static android.dev.personalassistant.utils.Keys.cardId;
import static android.dev.personalassistant.utils.Keys.cardNumber;
import static android.dev.personalassistant.utils.Keys.cardType;

public class KymShowPersonalDetailsActivity extends AppCompatActivity {

    Spinner spinnerRelations;


    int cardIdValue=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kym_show_personal_details);

        spinnerRelations = (Spinner) findViewById(R.id.relations);
        ArrayAdapter<Relations> adapterCardType = new ArrayAdapter<Relations>(this,android.R.layout.simple_spinner_dropdown_item,Relations.values());
        spinnerRelations.setAdapter(adapterCardType);

        try{
        }catch (Exception ie){
            Log.e(KymShowPersonalDetailsActivity.class.getName(),ie.getStackTrace().toString());
        }

    }

    public void saveCardDetails(View view){
        PersonalAssistantDatabase personalAssistantDatabase=DatabaseHelper.getDatabase(getApplicationContext());
        CardVO cardVO=new CardVO();


        cardVO.setCardId(cardIdValue);


        CardHelper cardHelper=new CardHelper();

        Log.d("Card VO: ",cardVO.toString());

        cardHelper.persistCard(personalAssistantDatabase,cardVO);
        finish();
        startActivity(new Intent(this,KymShowCardListActivity.class));


    }


}
