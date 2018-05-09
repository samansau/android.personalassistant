package android.dev.personalassistant.entities;

/**
 * Created by saurabh on 5/8/18.
 */

public class BankAccount {
    public int id;
    public String accountNumber;
    public Bank bank;
    public String netBankingCustomerId;
    public String netBankingPassword;
    public String phoneBankingNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
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
}
