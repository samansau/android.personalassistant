package android.dev.personalassistant.entities.expense;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.dev.personalassistant.converters.ExpenseTypeConverter;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
        //(indices = {@Index(value = "expenseTimeStamp",unique = true)})
public class Expense {


    public Expense(){

    };
    @PrimaryKey(autoGenerate = true)
    public int expenseId;

    public double expenseAmount;

    //@Embedded
    @TypeConverters(ExpenseTypeConverter.class)
    public List<ExpenseTag> expensedForTags;

    //@Embedded
    @TypeConverters(ExpenseTypeConverter.class)
    public List<ExpenseTag> expensedOnTags;

    public String briefDescription;


    public Long expenseDate;

    public boolean isManuallyEntered;

    @NonNull
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

    public List<ExpenseTag> getExpensedForTags() {
        return expensedForTags;
    }

    public void setExpensedForTags(ArrayList<ExpenseTag> expensedForTags) {
        this.expensedForTags = expensedForTags;
    }

    public List<ExpenseTag> getExpensedOnTags() {
        return expensedOnTags;
    }

    public void setExpensedOnTags(ArrayList<ExpenseTag> expensedOnTags) {
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
        return "Expense{" +
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
