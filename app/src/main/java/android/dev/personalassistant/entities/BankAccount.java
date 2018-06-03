package android.dev.personalassistant.entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by saurabh on 5/8/18.
 */

@Entity
public class BankAccount {
    @PrimaryKey(autoGenerate = true)
    public int bankAccountId;
    @NonNull
    public String accountNumber;

    public String bankName;

    public String branch;

    public String netBankingCustomerId;
    public String netBankingPassword;
    public String phoneBankingNumber;

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


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

    public String getNetBankingCustomerId() {
        return netBankingCustomerId;
    }

    public void setNetBankingCustomerId(String netBankingCustomerId) {
        this.netBankingCustomerId = netBankingCustomerId;
    }

    public String getNetBankingPassword() {
        return netBankingPassword;
    }

    public void setNetBankingPassword(String netBankingPassword) {
        this.netBankingPassword = netBankingPassword;
    }

    public String getPhoneBankingNumber() {
        return phoneBankingNumber;
    }



    public void setPhoneBankingNumber(String phoneBankingNumber) {
        this.phoneBankingNumber = phoneBankingNumber;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "bankAccountId=" + bankAccountId +
                ", accountNumber='" + accountNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", branch='" + branch + '\'' +
                ", netBankingCustomerId='" + netBankingCustomerId + '\'' +
                ", netBankingPassword='" + netBankingPassword + '\'' +
                ", phoneBankingNumber='" + phoneBankingNumber + '\'' +
                '}';
    }
}
