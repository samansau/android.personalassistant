package android.dev.personalassistant.vo;

/**
 * Created by saurabh on 6/2/18.
 */

public class BankAccountVO {

    int bankAccountIdValue;
    String bankNameValue;
    String bankBranchValue;
    String accountNumberValue;
    String netBankingCustomerIdValue;
    String netBankingPasswordValue;
    String phoneBankingNumberValue;


    public int getBankAccountIdValue() {
        return bankAccountIdValue;
    }

    public void setBankAccountIdValue(int bankAccountIdValue) {
        this.bankAccountIdValue = bankAccountIdValue;
    }

    public String getBankNameValue() {
        return bankNameValue;
    }

    public void setBankNameValue(String bankNameValue) {
        this.bankNameValue = bankNameValue;
    }

    public String getBankBranchValue() {
        return bankBranchValue;
    }

    public void setBankBranchValue(String bankBranchValue) {
        this.bankBranchValue = bankBranchValue;
    }

    public String getAccountNumberValue() {
        return accountNumberValue;
    }

    public void setAccountNumberValue(String accountNumberValue) {
        this.accountNumberValue = accountNumberValue;
    }

    public String getNetBankingCustomerIdValue() {
        return netBankingCustomerIdValue;
    }

    public void setNetBankingCustomerIdValue(String netBankingCustomerIdValue) {
        this.netBankingCustomerIdValue = netBankingCustomerIdValue;
    }

    public String getNetBankingPasswordValue() {
        return netBankingPasswordValue;
    }

    public void setNetBankingPasswordValue(String netBankingPasswordValue) {
        this.netBankingPasswordValue = netBankingPasswordValue;
    }

    public String getPhoneBankingNumberValue() {
        return phoneBankingNumberValue;
    }

    public void setPhoneBankingNumberValue(String phoneBankingNumberValue) {
        this.phoneBankingNumberValue = phoneBankingNumberValue;
    }
}
