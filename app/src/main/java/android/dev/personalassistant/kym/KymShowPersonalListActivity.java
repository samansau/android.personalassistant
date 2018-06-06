package android.dev.personalassistant.kym;

import android.app.ListActivity;
import android.dev.personalassistant.R;
import android.os.Bundle;

public class KymShowPersonalListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kym_show_personal_list);
    }

}
