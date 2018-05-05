package android.dev.personalassistant.kym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.dev.personalassistant.R;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class KymShowCardDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kym_show_card_details);

        Spinner spinnerCardType = (Spinner) findViewById(R.id.cardType);
        ArrayAdapter<CharSequence> adapterCardType = ArrayAdapter.createFromResource(this,
                R.array.card_type_array, android.R.layout.simple_spinner_item);
        adapterCardType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCardType.setAdapter(adapterCardType);

        Spinner spinnerCardBankName = (Spinner) findViewById(R.id.cardBankName);
        ArrayAdapter<CharSequence> adapterCardBankName = ArrayAdapter.createFromResource(this,
                R.array.bank_name_array, android.R.layout.simple_spinner_item);
        adapterCardBankName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCardBankName.setAdapter(adapterCardBankName);

        Spinner spinnerCardOwnerName = (Spinner) findViewById(R.id.cardOwnerName);
        ArrayAdapter<CharSequence> adapterCardOwnerName = ArrayAdapter.createFromResource(this,
                R.array.card_owner_array, android.R.layout.simple_spinner_item);
        adapterCardOwnerName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCardOwnerName.setAdapter(adapterCardOwnerName);

    }
}
