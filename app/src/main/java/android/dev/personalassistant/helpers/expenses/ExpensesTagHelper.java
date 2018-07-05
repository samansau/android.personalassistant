package android.dev.personalassistant.helpers.expenses;

import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.expense.ExpenseTag;
import android.dev.personalassistant.vo.expenses.ExpenseTagVO;

import java.util.ArrayList;
import java.util.List;

public class ExpensesTagHelper {

    public List<ExpenseTagVO> fetchAllExpenseTagVOs(final PersonalAssistantDatabase personalAssistantDatabase) throws InterruptedException{
        final List<ExpenseTagVO> expenseTagVOS=new ArrayList<>();
        Thread fetchThread=new Thread(new Runnable() {
            @Override
            public void run() {
                List<ExpenseTag> expenseTags = personalAssistantDatabase.getExpenseTagDAO().fetchAllExpenseTags();
                if(expenseTags!=null){
                    for(ExpenseTag expenseTag:expenseTags){
                        ExpenseTagVO expenseTagVO=new ExpenseTagVO();
                        expenseTagVO.setTagCategory(expenseTag.getTagCategory());
                        expenseTagVO.setTagId(expenseTag.getTagId());
                        expenseTagVO.setTagName(expenseTag.getTagName());
                        expenseTagVO.setTagParentId(expenseTag.getTagParentId());
                        expenseTagVOS.add(expenseTagVO);
                    }
                }
            }
        });
        fetchThread.start();
        fetchThread.join();
        return expenseTagVOS;
    }

    public void persistExpenseTag(final PersonalAssistantDatabase personalAssistantDatabase, final ExpenseTagVO expenseTagVO){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ExpenseTag expenseTag = new ExpenseTag();


                expenseTag.setTagCategory(expenseTagVO.getTagCategory());
                expenseTag.setTagName(expenseTagVO.getTagName());
                expenseTag.setTagParentId(expenseTagVO.getTagParentId());
                int tagId=expenseTagVO.getTagId();

                if (tagId >= 0) {
                    expenseTag.setTagId(tagId);
                    personalAssistantDatabase.getExpenseTagDAO().updateExpenseTags(expenseTag);
                }else{
                    personalAssistantDatabase.getExpenseTagDAO().insertExpenseTag(expenseTag);
                }

            }
        }).start();
    }


}
