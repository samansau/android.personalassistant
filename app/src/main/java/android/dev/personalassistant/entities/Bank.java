package android.dev.personalassistant.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by saurabh on 5/8/18.
 */
@Entity(primaryKeys = {"bankName","branch"})
public class Bank {


    @NonNull
    public String bankName;

    @NonNull
    public String branch;


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Bank{" +
                ", bankName='" + bankName + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
