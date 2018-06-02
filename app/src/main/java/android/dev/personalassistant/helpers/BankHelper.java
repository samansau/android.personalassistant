package android.dev.personalassistant.helpers;

import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.Bank;
import android.dev.personalassistant.entities.BankAccount;
import android.dev.personalassistant.vo.BankAccountVO;
import android.dev.personalassistant.vo.BankVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 6/2/18.
 */

public class BankHelper {

    public Bank fetchBankByBankNameAndBranch(final PersonalAssistantDatabase personalAssistantDatabase, final String bankName,String branch) throws InterruptedException {
        List<Bank> banks=new ArrayList<>();

        Thread fetchThread = new Thread(new Runnable() {

            @Override
            public void run() {
                //banks.add(personalAssistantDatabase.getBankDAO().fetchBankByBankNameAndBranch(bankName,branch));
                banks.add(personalAssistantDatabase.getBankDAO().fetchAllBanks().get(0));

            }
        });
        fetchThread.start();
        fetchThread.join();
        Bank bank=banks.size()>0?banks.get(0):null;
        return bank;
    }
}
