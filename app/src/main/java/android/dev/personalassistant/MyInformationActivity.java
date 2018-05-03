package android.dev.personalassistant;

import android.content.Intent;
import android.dev.personalassistant.kym.components.KymShowBankListActivity;
import android.dev.personalassistant.main.BaseActivity;
import android.os.Bundle;
import android.view.View;

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
