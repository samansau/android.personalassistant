package android.dev.personalassistant.kym;

import android.content.Intent;
import android.dev.personalassistant.R;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.enums.CardType;
import android.dev.personalassistant.enums.Relations;
import android.dev.personalassistant.helpers.BankAccountHelper;
import android.dev.personalassistant.helpers.CardHelper;
import android.dev.personalassistant.helpers.DatabaseHelper;
import android.dev.personalassistant.helpers.PersonHelper;
import android.dev.personalassistant.vo.BankAccountVO;
import android.dev.personalassistant.vo.CardVO;
import android.dev.personalassistant.vo.PersonVO;
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
import static android.dev.personalassistant.utils.Keys.passportExpiry;
import static android.dev.personalassistant.utils.Keys.passportNumber;

public class KymShowPersonalDetailsActivity extends AppCompatActivity {

    TextView fullNameObj;
    Spinner spinnerRelations;
    TextView dobObj;
    TextView panCardNumberObj;
    TextView aadharCardNumberObj;
    TextView passportNumberObj;
    TextView passportExpiryObj;
    TextView drivingLisenceNumberObj;
    TextView drivingLisenceExpiryObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kym_show_personal_details);
        fullNameObj=findViewById(R.id.fullNameField);
        spinnerRelations = (Spinner) findViewById(R.id.relations);
        dobObj=findViewById(R.id.dobField);
        panCardNumberObj=findViewById(R.id.panNumberField);
        aadharCardNumberObj=findViewById(R.id.aadharNumberField);
        passportNumberObj=findViewById(R.id.passPortNumberField);
        passportExpiryObj=findViewById(R.id.passPortExpiryField);
        drivingLisenceNumberObj=findViewById(R.id.drivingLisenceNumberField);
        drivingLisenceExpiryObj=findViewById(R.id.drivingLisenceExpiryField);

        ArrayAdapter<Relations> adapterCardType = new ArrayAdapter<Relations>(this,android.R.layout.simple_spinner_dropdown_item,Relations.values());
        spinnerRelations.setAdapter(adapterCardType);



        try{
        }catch (Exception ie){
            Log.e(KymShowPersonalDetailsActivity.class.getName(),ie.getStackTrace().toString());
        }

    }

    public void savePersonDetails(View view){
        PersonalAssistantDatabase personalAssistantDatabase=DatabaseHelper.getDatabase(getApplicationContext());
        PersonVO personVO=new PersonVO();


        personVO.setFullName(fullNameObj.getText().toString());
        personVO.setRelation(spinnerRelations.getSelectedItem().toString());
        personVO.setDob(dobObj.getText().toString());
        personVO.setPanCardNumber(panCardNumberObj.getText().toString());
        personVO.setAadharCardNumber(aadharCardNumberObj.getText().toString());
        personVO.setPassportNumber(passportNumberObj.getText().toString());
        personVO.setPassportExpiry(passportExpiryObj.getText().toString());
        personVO.setDrivingLisenceNumber(drivingLisenceNumberObj.getText().toString());
        personVO.setDrivingLisenceExpiry(drivingLisenceExpiryObj.getText().toString());


        PersonHelper personHelper=new PersonHelper();

        Log.d("Person VO: ",personVO.toString());

        personHelper.persistPerson(personalAssistantDatabase,personVO);
        finish();
        startActivity(new Intent(this,KymShowPersonalListActivity.class));


    }


}
