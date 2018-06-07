package android.dev.personalassistant.kym;

import android.content.Intent;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.helpers.BankAccountHelper;
import android.dev.personalassistant.helpers.CarHelper;
import android.dev.personalassistant.helpers.DatabaseHelper;
import android.dev.personalassistant.vo.BankAccountVO;
import android.dev.personalassistant.vo.CarVO;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.dev.personalassistant.R;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class KymShowCarDetailsActivity extends AppCompatActivity {

    Spinner carNumberObj;
    TextView carNumberFieldObj;
    TextView carNameFieldObj;
    TextView carInsuranceNumberFieldObj;
    TextView carInsuranceExpiryFieldObj;
    TextView carPUCExpiryFieldObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kym_show_car_details);

        carNumberObj = (Spinner) findViewById(R.id.carNumber);
        carNumberFieldObj=findViewById(R.id.carNameField);
        carNameFieldObj=findViewById(R.id.carNameField);
        carInsuranceNumberFieldObj=findViewById(R.id.carInsuranceNumberField);
        carInsuranceExpiryFieldObj=findViewById(R.id.carInsuranceExpiryField);
        carPUCExpiryFieldObj=findViewById(R.id.carPUCExpiryField);


    }


    public void saveCarDetails(View view){
        PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(getApplicationContext());


        CarVO carVO=new CarVO();
        carVO.setCarNumber(carNumberObj.getSelectedItem().toString());
        carVO.setCarName(carNameFieldObj.getText().toString());
        carVO.setCarInsuranceNumber(carInsuranceNumberFieldObj.getText().toString());
        carVO.setCarInsuranceExpiry(carInsuranceExpiryFieldObj.getText().toString());
        carVO.setCarPUCExpiry(carPUCExpiryFieldObj.getText().toString());

        CarHelper carHelper=new CarHelper();
        carHelper.persistCar(personalAssistantDatabase,carVO);
        finish();
        startActivity(new Intent(this,KymShowCarDetailsActivity.class));


    }
}
