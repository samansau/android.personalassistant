package android.dev.personalassistant.helpers.expenses;

import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.expense.Expense;
import android.dev.personalassistant.entities.expense.ExpenseTag;
import android.dev.personalassistant.vo.expenses.ExpenseTagVO;
import android.dev.personalassistant.vo.expenses.ExpenseVO;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpensesHelper {


    public ExpenseVO fetchExpenseVOById(final PersonalAssistantDatabase personalAssistantDatabase,int expenseId) throws InterruptedException {
        final ExpenseVO expenseVO = new ExpenseVO();
        Thread fetchThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Expense expense = personalAssistantDatabase.getExpenseDAO().fetchExpenseById(expenseId);
                if (expense != null) {
                    expenseVO.setExpenseId(expense.getExpenseId());
                    List<String> expensedFor=new ArrayList<>();
                    expense.getExpensedForTags().stream().forEach(exp -> {
                        expensedFor.add(exp.getTagName());
                    });
                    expenseVO.setExpensedForTags(expensedFor);

                    List<String> expensedOn=new ArrayList<>();
                    expense.getExpensedOnTags().stream().forEach(exp -> {
                        expensedOn.add(exp.getTagName());
                    });
                    expenseVO.setExpensedOnTags(expensedOn);

                    expenseVO.setBriefDescription(expense.getBriefDescription());
                    expenseVO.setExpenseAmount(expense.getExpenseAmount());
                    expenseVO.setExpenseDate(expense.getExpenseDate());

                }
            }
        });
        fetchThread.start();
        fetchThread.join();
        return expenseVO;
    }


    public List<ExpenseVO> fetchAllExpenseVOs(final PersonalAssistantDatabase personalAssistantDatabase) throws InterruptedException{
        final List<ExpenseVO> expenseVOS=new ArrayList<>();
        Thread fetchThread=new Thread(new Runnable() {
            @Override
            public void run() {
                List<Expense> expenses = personalAssistantDatabase.getExpenseDAO().fetchAllExpenses();
                if(expenses!=null){
                    for(Expense expense:expenses){
                        Log.d(ExpensesHelper.class.getName()+" Expense Object : ",expense.toString());
                        ExpenseVO expenseVO=new ExpenseVO();
                        expenseVO.setExpenseId(expense.getExpenseId());
                        expenseVO.setExpenseAmount(expense.getExpenseAmount());

                        List<String> expensedForTag=new ArrayList<>();
                        List<ExpenseTag> expenseForTags=expense.getExpensedForTags();
                        if(expenseForTags!=null) {
                            for (ExpenseTag expensedFor : expenseForTags) {
                                expensedForTag.add(expensedFor.getTagName());
                                Log.d("expensedForTag : ",expensedFor.getTagName());
                            }
                        }
                        expenseVO.setExpensedForTags(expensedForTag);

                        List<String> expensedOnTag=new ArrayList<>();
                        List<ExpenseTag> expenseOnTags=expense.getExpensedOnTags();
                        if(expenseOnTags!=null){
                            for(ExpenseTag expensedOn:expenseOnTags){
                                expensedOnTag.add(expensedOn.getTagName());
                                Log.d("expensedOnTag : ",expensedOn.getTagName());
                            }
                        }
                        expenseVO.setExpensedOnTags(expensedOnTag);

                        expenseVO.setBriefDescription(expense.getBriefDescription());
                        expenseVO.setExpenseDate(expense.getExpenseDate());
                        expenseVO.setExpenseTimeStamp(expense.getExpenseTimeStamp());

                        expenseVOS.add(expenseVO);
                    }
                }
            }
        });
        fetchThread.start();
        fetchThread.join();
        return expenseVOS;
    }

    public void persistExpense(final PersonalAssistantDatabase personalAssistantDatabase, final ExpenseVO expenseVO){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Expense expense = new Expense();

                expense.setExpenseAmount(expenseVO.getExpenseAmount());
                List<String> expensedForTag =expenseVO.getExpensedForTags();
                List<String> expensedOnTag =expenseVO.getExpensedOnTags();
                Log.d("persistExpense:expensedForTag ",expensedForTag.toString());
                Log.d("persistExpense:expensedOnTag ",expensedOnTag.toString());

                ArrayList<ExpenseTag> expenseForTagObj=new ArrayList<>();
                ArrayList<ExpenseTag> expenseOnTagObj=new ArrayList<>();
                ExpensesTagHelper expensesTagHelper=new ExpensesTagHelper();
                try {
                    List<ExpenseTagVO> expenseTagVOS=expensesTagHelper.fetchAllExpenseTagVOs(personalAssistantDatabase);
                    Log.d(ExpensesHelper.class.getName()+" expenseTagVOS: ",expenseTagVOS.toString());
                    expenseTagVOS.stream().filter(expenseTagVO -> expensedForTag.contains(expenseTagVO.getTagName())).
                            forEach(expenseTagVO ->
                                    {
                                        ExpenseTag expenseTag=new ExpenseTag();
                                        expenseTag.setTagId(expenseTagVO.getTagId());
                                        expenseTag.setTagName(expenseTagVO.getTagName());
                                        expenseForTagObj.add(expenseTag);
                                    }
                            );
                    expenseTagVOS.stream().filter(expenseTagVO -> expensedOnTag.contains(expenseTagVO.getTagName())).
                            forEach(expenseTagVO ->
                                    {
                                        ExpenseTag expenseTag=new ExpenseTag();
                                        expenseTag.setTagId(expenseTagVO.getTagId());
                                        expenseTag.setTagName(expenseTagVO.getTagName());
                                        expenseOnTagObj.add(expenseTag);
                                    }
                            );

                    Log.d(ExpensesHelper.class.getName()+" expenseForTagObj: ",expenseForTagObj.toString());
                    Log.d(ExpensesHelper.class.getName()+" expenseOnTagObj: ",expenseOnTagObj.toString());

                }catch (Exception ex){
                    ex.printStackTrace();
                }


                expense.setExpensedForTags(expenseForTagObj);
                expense.setExpensedOnTags(expenseOnTagObj);

                expense.setBriefDescription(expenseVO.getBriefDescription());
                expense.setExpenseDate(expenseVO.getExpenseDate());
                expense.setManuallyEntered(expenseVO.isManuallyEntered());


                int expenseId=expenseVO.getExpenseId();

                if (expenseId >= 0) {
                    expense.setExpenseId(expenseId);
                    long expenseTimestamp=personalAssistantDatabase.getExpenseDAO().fetchExpenseById(expenseId).getExpenseTimeStamp();
                    expense.setExpenseTimeStamp(expenseTimestamp);
                    personalAssistantDatabase.getExpenseDAO().updateExpense(expense);
                }else{
                    expense.setExpenseTimeStamp(expenseVO.getExpenseTimeStamp());
                    personalAssistantDatabase.getExpenseDAO().insertExpense(expense);
                }

            }
        }).start();
    }


}
