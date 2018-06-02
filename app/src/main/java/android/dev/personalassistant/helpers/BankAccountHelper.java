package android.dev.personalassistant.helpers;

import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.Bank;
import android.dev.personalassistant.entities.BankAccount;
import android.dev.personalassistant.vo.BankAccountVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 6/2/18.
 */

public class BankAccountHelper {

    public void persistBankAccount(final PersonalAssistantDatabase personalAssistantDatabase, final BankAccountVO bankAccountVO){
        new Thread(new Runnable() {
            @Override
            public void run() {
                BankAccount bankAccount = new BankAccount();
                Bank bank = new Bank();

                int bankAccountIdValue = bankAccountVO.getBankAccountIdValue();

                bank.setBankName(bankAccountVO.getBankNameValue());
                bank.setBranch(bankAccountVO.getBankBranchValue());
                bankAccount.setBank(bank);
                bankAccount.setAccountNumber(bankAccountVO.getAccountNumberValue());
                bankAccount.setNetBankingCustomerId(bankAccountVO.getNetBankingCustomerIdValue());
                bankAccount.setNetBankingPassword(bankAccountVO.getNetBankingPasswordValue());
                bankAccount.setPhoneBankingNumber(bankAccountVO.getPhoneBankingNumberValue());

                if (bankAccountIdValue >= 0) {
                    bankAccount.setBankAccountId(bankAccountIdValue);
                    personalAssistantDatabase.getBankAccountDAO().updateBankAccount(bankAccount);
                }else{
                    personalAssistantDatabase.getBankAccountDAO().insertBankAccount(bankAccount);
                }

            }
        }).start();
    }

    public BankAccountVO fetchBankAccountVOFromAccountNumber(final PersonalAssistantDatabase personalAssistantDatabase,final String accountNumber) throws InterruptedException{
        final BankAccountVO bankAccountVO=new BankAccountVO();

        Thread fetchThread=new Thread(new Runnable() {
            @Override
            public void run() {
                BankAccount bankAccount = personalAssistantDatabase.getBankAccountDAO().fetchBankAccountByAccountNumber(accountNumber);
                if(bankAccount!=null){
                    Bank bank=bankAccount.getBank();
                    bankAccountVO.setBankNameValue(bank.getBankName());
                    bankAccountVO.setBankBranchValue(bank.getBranch());
                    bankAccountVO.setAccountNumberValue(bankAccount.getAccountNumber());
                    bankAccountVO.setBankAccountIdValue(bankAccount.getBankAccountId());
                    bankAccountVO.setNetBankingCustomerIdValue(bankAccount.getNetBankingCustomerId());
                    bankAccountVO.setNetBankingPasswordValue(bankAccount.getNetBankingPassword());
                    bankAccountVO.setPhoneBankingNumberValue(bankAccount.getPhoneBankingNumber());
                }
            }
        });
        fetchThread.start();
        fetchThread.join();
        return bankAccountVO;
    }

    public List<BankAccountVO> fetchAllBankAccountVOs(final PersonalAssistantDatabase personalAssistantDatabase) throws InterruptedException{
        final List<BankAccountVO> bankAccountVOs=new ArrayList<>();
        Thread fetchThread=new Thread(new Runnable() {
            @Override
            public void run() {
                List<BankAccount> bankAccounts = personalAssistantDatabase.getBankAccountDAO().fetchAllBankAccounts();
                if(bankAccounts!=null){
                    for(BankAccount bankAccount:bankAccounts){
                        BankAccountVO bankAccountVO=new BankAccountVO();
                        Bank bank=bankAccount.getBank();
                        bankAccountVO.setBankNameValue(bank.getBankName());
                        bankAccountVO.setBankBranchValue(bank.getBranch());
                        bankAccountVO.setAccountNumberValue(bankAccount.getAccountNumber());
                        bankAccountVO.setBankAccountIdValue(bankAccount.getBankAccountId());
                        bankAccountVO.setNetBankingCustomerIdValue(bankAccount.getNetBankingCustomerId());
                        bankAccountVO.setNetBankingPasswordValue(bankAccount.getNetBankingPassword());
                        bankAccountVO.setPhoneBankingNumberValue(bankAccount.getPhoneBankingNumber());
                        bankAccountVOs.add(bankAccountVO);
                    }
                }
            }
        });
        fetchThread.start();
        fetchThread.join();
        return bankAccountVOs;
    }
}
