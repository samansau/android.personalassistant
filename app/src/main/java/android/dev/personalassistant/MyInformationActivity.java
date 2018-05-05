package android.dev.personalassistant;

import android.dev.personalassistant.main.BaseActivity;
import android.os.Bundle;

public class MyInformationActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);
        initDrawer();
        createExpenseToolBar();

    }

//    public void showBankList(View view){
//        Intent intent=new Intent(this,KymShowBankListActivity.class);
//        startActivity(intent);
//    }






}
