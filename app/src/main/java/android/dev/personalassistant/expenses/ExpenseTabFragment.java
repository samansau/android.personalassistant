package android.dev.personalassistant.expenses;

import android.content.Context;
import android.content.Intent;
import android.dev.personalassistant.R;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.helpers.expenses.ExpensesHelper;
import android.dev.personalassistant.helpers.kym.DatabaseHelper;
import android.dev.personalassistant.tabs.TabFragment;
import android.dev.personalassistant.utils.Keys;
import android.dev.personalassistant.utils.Utils;
import android.dev.personalassistant.vo.expenses.ExpenseVO;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static android.dev.personalassistant.utils.Keys.expenseAllTags;
import static android.dev.personalassistant.utils.Keys.expenseAmount;
import static android.dev.personalassistant.utils.Keys.expenseDate;
import static android.dev.personalassistant.utils.Keys.expenseDescription;
import static android.dev.personalassistant.utils.Keys.expenseForTags;
import static android.dev.personalassistant.utils.Keys.expenseId;
import static android.dev.personalassistant.utils.Keys.expenseOnTags;

/**
 * Created by saurabh on 4/5/18.
 */

public class ExpenseTabFragment extends TabFragment {

    static final ArrayList<Map<String,String>> allExpensesList =
            new ArrayList<Map<String,String>>();
    static final ArrayList<Map<String,String>> manualExpensesList =
            new ArrayList<Map<String,String>>();

    static final ArrayList<Map<String,String>> electronicExpensesList  =
            new ArrayList<Map<String,String>>();

    public  Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        ExpenseTabFragment tabFragment = new ExpenseTabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =null;
        switch(position) {
            case 0:
                view =getManualExpensesView(inflater, container);
                break;

            case 1:
                view=getElectronicExpensesView(inflater,container);
                break;

            case 2:
                view = getAllExpensesView(inflater, container);
                break;

        }
        return view;

        //return inflater.inflate(R.layout.expense_tabs, container, false);
    }


    private View getElectronicExpensesView(LayoutInflater inflater, ViewGroup container){
        View view=inflater.inflate(R.layout.activity_expenses_show_electronic,container,false);
        ListView electronicExpensesListView = (ListView) view.findViewById(R.id.listElectronicExpenses);
        ListAdapter electronicExpensesAdapter = new SimpleAdapter(
                view.getContext(),
                electronicExpensesList,
                R.layout.three_line_list_item_expenses,
                new String[] {expenseAllTags,expenseDate,expenseAmount,expenseId},
                new int[] {R.id.text1,R.id.text2,R.id.text3,R.id.expenseId}
        );
        electronicExpensesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent=new Intent(getContext(),AddEditExpensesActivity.class);
                PersonalAssistantDatabase personalAssistantDatabase=DatabaseHelper.getDatabase(view.getContext());
                Bundle extras=new Bundle();
                ExpensesHelper expensesHelper=new ExpensesHelper();
                HashMap<String,String> expensesData=(HashMap)adapterView.getItemAtPosition(pos);
                final int expenseId=Integer.parseInt(expensesData.get(Keys.expenseId));
                Log.d("manualExpensesListView expenseId",expensesData.get(Keys.expenseId));
                try {
                    ExpenseVO expenseVO=expensesHelper.fetchExpenseVOById(personalAssistantDatabase, expenseId);
                    extras.putInt(Keys.expenseId,expenseId);
                    extras.putDouble(expenseAmount,expenseVO.getExpenseAmount());
                    extras.putStringArrayList(expenseForTags,new ArrayList<>(expenseVO.getExpensedForTags()));
                    extras.putStringArrayList(expenseOnTags,new ArrayList<>(expenseVO.getExpensedOnTags()));
                    extras.putString(expenseDescription,expenseVO.getBriefDescription());
                    extras.putLong(expenseDate,expenseVO.getExpenseDate());



                }catch(Exception ex){
                    ex.printStackTrace();
                }
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        populateExpensesList(getContext(),false);
        electronicExpensesListView.setAdapter(electronicExpensesAdapter);
        return view;
    }


    private View getManualExpensesView(LayoutInflater inflater, ViewGroup container){
        View view=inflater.inflate(R.layout.activity_expenses_show_manual,container,false);
        ListView manualExpensesListView = (ListView) view.findViewById(R.id.listManualExpenses);
        ListAdapter manualExpensesAdapter = new SimpleAdapter(
                view.getContext(),
                manualExpensesList,
                R.layout.three_line_list_item_expenses,
                new String[] {expenseAllTags,expenseDate,expenseAmount,expenseId},
                new int[] {R.id.text1,R.id.text2,R.id.text3,R.id.expenseId}
        );
        manualExpensesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent=new Intent(getContext(),AddEditExpensesActivity.class);
                PersonalAssistantDatabase personalAssistantDatabase=DatabaseHelper.getDatabase(view.getContext());
                Bundle extras=new Bundle();
                ExpensesHelper expensesHelper=new ExpensesHelper();
                HashMap<String,String> expensesData=(HashMap)adapterView.getItemAtPosition(pos);
                final int expenseId=Integer.parseInt(expensesData.get(Keys.expenseId));
                Log.d("manualExpensesListView expenseId",expensesData.get(Keys.expenseId));
                try {
                    ExpenseVO expenseVO=expensesHelper.fetchExpenseVOById(personalAssistantDatabase, expenseId);
                    extras.putInt(Keys.expenseId,expenseId);
                    extras.putDouble(expenseAmount,expenseVO.getExpenseAmount());
                    extras.putStringArrayList(expenseForTags,new ArrayList<>(expenseVO.getExpensedForTags()));
                    extras.putStringArrayList(expenseOnTags,new ArrayList<>(expenseVO.getExpensedOnTags()));
                    extras.putString(expenseDescription,expenseVO.getBriefDescription());
                    extras.putLong(expenseDate,expenseVO.getExpenseDate());



                }catch(Exception ex){
                    ex.printStackTrace();
                }
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        populateExpensesList(getContext(),true);
        manualExpensesListView.setAdapter(manualExpensesAdapter);
        return view;
    }


    private View getAllExpensesView(LayoutInflater inflater, ViewGroup container){
        View view;
        view= inflater.inflate(R.layout.activity_expenses_show_summary, container, false);

        return view;
    }

    private void populateAllExpensesList(Context context){
        Utils.populateListOf4Items(allExpensesList);
    }
    public void addManualExpensesDetails(View view){
        Intent intent=new Intent(view.getContext(),AddEditExpensesActivity.class);
        startActivity(intent);

    }


    private void populateExpensesList(Context context,boolean isManual){
        if(isManual) manualExpensesList.clear();
        else electronicExpensesList.clear();
        ExpensesHelper expensesHelper=new ExpensesHelper();
        PersonalAssistantDatabase database= DatabaseHelper.getDatabase(this.getContext());
        try {
           List<ExpenseVO> expenseVOs=expensesHelper.fetchAllExpenseVOs(database).stream().filter(vo -> vo.isManuallyEntered()==isManual).collect(Collectors.toList());

            Log.d("populateManualExpensesList",expenseVOs.toString());
           for(ExpenseVO expenseVO:expenseVOs){
               HashMap map = new HashMap();
               List<String> expenseTags=new ArrayList<>();
               expenseTags.addAll(expenseVO.getExpensedForTags());
               expenseTags.addAll(expenseVO.getExpensedOnTags());
               map.put(expenseAllTags,expenseTags );
               SimpleDateFormat sdf=new SimpleDateFormat("dd/MMM/yyyy");
               map.put(expenseDate, sdf.format(expenseVO.getExpenseDate()));
               map.put(expenseAmount,"₹ "+expenseVO.getExpenseAmount());
               map.put(expenseId,expenseVO.getExpenseId()+"");
               if(isManual)
                    manualExpensesList.add(map);
               else electronicExpensesList.add(map);
           }
        }catch (Exception ex){
            ex.printStackTrace();
        }



    }

}
