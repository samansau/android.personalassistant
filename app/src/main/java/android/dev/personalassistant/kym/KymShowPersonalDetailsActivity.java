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

import static android.dev.personalassistant.utils.Keys.aadharCardNumber;
import static android.dev.personalassistant.utils.Keys.bank;
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
import static android.dev.personalassistant.utils.Keys.panCardNumber;
import static android.dev.personalassistant.utils.Keys.passportExpiry;
import static android.dev.personalassistant.utils.Keys.passportNumber;
import static android.dev.personalassistant.utils.Keys.relation;

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
    boolean isNew=true;

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

        ArrayAdapter<Relations> adapterRelation = new ArrayAdapter<Relations>(this,android.R.layout.simple_spinner_dropdown_item,Relations.values());
        spinnerRelations.setAdapter(adapterRelation);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            String relationValue = bundle.getString(relation);
            int personPosition =adapterRelation.getPosition(Relations.fromString(relationValue));
            spinnerRelations.setSelection(personPosition);

            fullNameObj.setText(bundle.getString(fullName));
            dobObj.setText(bundle.getString(dob));
            panCardNumberObj.setText(bundle.getString(panCardNumber));
            aadharCardNumberObj.setText(bundle.getString(aadharCardNumber));
            passportNumberObj.setText(bundle.getString(passportNumber));
            passportExpiryObj.setText(bundle.getString(passportExpiry));
            drivingLisenceNumberObj.setText(bundle.getString(drivingLisenceNumber));
            drivingLisenceExpiryObj.setText(bundle.getString(drivingLisenceExpiry));
            isNew=false;
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

        personVO.setNew(isNew);
        PersonHelper personHelper=new PersonHelper();

        Log.d("Person VO: ",personVO.toString());

        personHelper.persistPerson(personalAssistantDatabase,personVO);
        finish();
        startActivity(new Intent(this,KymShowPersonalListActivity.class));


    }


}
