package android.dev.personalassistant.vo.expenses;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.dev.personalassistant.entities.expense.ExpenseTag;

import java.util.Date;
import java.util.List;

@Entity
public class ExpenseVO {


    public ExpenseVO(){

    }

    public int expenseId;

    public double expenseAmount;


    public List<String> expensedForTags;

    public List<String> expensedOnTags;

    public String briefDescription;

    public Long expenseDate;

    public boolean isManuallyEntered;

    public long expenseTimeStamp;

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public List<String> getExpensedForTags() {
        return expensedForTags;
    }

    public void setExpensedForTags(List<String> expensedForTags) {
        this.expensedForTags = expensedForTags;
    }

    public List<String> getExpensedOnTags() {
        return expensedOnTags;
    }

    public void setExpensedOnTags(List<String> expensedOnTags) {
        this.expensedOnTags = expensedOnTags;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public Long getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Long expenseDate) {
        this.expenseDate = expenseDate;
    }

    public boolean isManuallyEntered() {
        return isManuallyEntered;
    }

    public void setManuallyEntered(boolean manuallyEntered) {
        isManuallyEntered = manuallyEntered;
    }

    public long getExpenseTimeStamp() {
        return expenseTimeStamp;
    }

    public void setExpenseTimeStamp(long expenseTimeStamp) {
        this.expenseTimeStamp = expenseTimeStamp;
    }

    @Override
    public String toString() {
        return "ExpenseVO{" +
                "expenseId=" + expenseId +
                ", expenseAmount=" + expenseAmount +
                ", expensedForTags=" + expensedForTags +
                ", expensedOnTags=" + expensedOnTags +
                ", briefDescription='" + briefDescription + '\'' +
                ", expenseDate=" + expenseDate +
                ", isManuallyEntered=" + isManuallyEntered +
                ", expenseTimeStamp=" + expenseTimeStamp +
                '}';
    }
}
