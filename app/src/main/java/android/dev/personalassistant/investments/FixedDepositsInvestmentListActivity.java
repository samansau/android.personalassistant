package android.dev.personalassistant.investments;

import android.content.Intent;
import android.dev.personalassistant.helpers.investment.FDHelper;
import android.dev.personalassistant.helpers.kym.DatabaseHelper;
import android.dev.personalassistant.utils.DateParser;
import android.dev.personalassistant.vo.investment.FixedDepositVO;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.dev.personalassistant.R;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.dev.personalassistant.utils.Constants.dateFormat;
import static android.dev.personalassistant.utils.Keys.bank;
import static android.dev.personalassistant.utils.Keys.fdMaturityDate;
import static android.dev.personalassistant.utils.Keys.fdMaurityAmount;


public class FixedDepositsInvestmentListActivity extends AppCompatActivity {
    static final ArrayList<Map<String,String>> fdList =
            new ArrayList<Map<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_deposits_investment_list);


//        final ListView listView = (ListView) findViewById(R.id.listFDs);
//        final ListAdapter adapter = new SimpleAdapter(
//                this,
//                fdList,
//                R.layout.three_line_list_item,
//                new String[] {bank,fdMaturityDate,fdMaurityAmount},
//                new int[] {R.id.text1,R.id.text2,R.id.text3}
//        );
//        populateFDList();
//        listView.setAdapter(adapter);


//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
//                addUpdateFD(view);
//            }
//        });
    }

//    private void populateFDList(){
//        fdList.clear();
//        try {
//            FDHelper fdHelper = new FDHelper();
//            List<FixedDepositVO> fixedDepositVOs=fdHelper.fetchAllFixedDepositVOs(DatabaseHelper.getDatabase(this.getBaseContext()));
//            DateParser dateParser=new DateParser(dateFormat);
//            for(FixedDepositVO fixedDepositVO : fixedDepositVOs){
//                Map<String, String> map = new HashMap();
//                map.put(bank, fixedDepositVO.getBankName());
//                map.put(fdMaturityDate, dateParser.getString(fixedDepositVO.getFdMaturityDate()));
//                map.put(fdMaurityAmount, fixedDepositVO.getMaturityAmount()+"");
//
//                fdList.add(map);
//            }
//
//        }catch (InterruptedException ie){
//            Log.e("populateFDList" , ie.getStackTrace().toString());
//        }
//
//
//    }

//    public void addUpdateFD(View view){
//        Intent intent =new Intent(view.getContext(),FixedDepositsInvestmentDetailActivity.class);
//        startActivity(intent);
//    }
}
