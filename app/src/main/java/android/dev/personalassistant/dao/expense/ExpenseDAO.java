package android.dev.personalassistant.dao.expense;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.dev.personalassistant.entities.expense.Expense;
import android.dev.personalassistant.entities.expense.ExpenseTag;

import java.util.List;

/**
 * Created by saurabh on 5/29/18.
 */

@Dao
public interface ExpenseDAO {
    @Insert
    public void insertExpense(List<Expense> expense);


    @Insert
    public void insertExpense(Expense expense);

    @Update
    public void updateExpense(Expense expense);


    @Query("select * from Expense order by expenseDate desc")
    public List<Expense> fetchAllExpenses();

    @Query("select * from Expense where expenseId =:expenseId")
    public Expense fetchExpenseById(int expenseId);

    @Delete
    public void deleteExpenseTag(ExpenseTag expenseTag);


}
