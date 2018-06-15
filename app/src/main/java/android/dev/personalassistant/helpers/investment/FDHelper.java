package android.dev.personalassistant.helpers.investment;

import android.database.sqlite.SQLiteConstraintException;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.dao.kym.BankAccountDAO;
import android.dev.personalassistant.entities.investment.FixedDeposit;
import android.dev.personalassistant.entities.investment.FixedDepositWithOwners;
import android.dev.personalassistant.entities.kym.BankAccount;
import android.dev.personalassistant.entities.kym.Person;
import android.dev.personalassistant.enums.FDStatus;
import android.dev.personalassistant.helpers.kym.BankAccountHelper;
import android.dev.personalassistant.vo.investment.FixedDepositVO;
import android.dev.personalassistant.vo.kym.BankAccountVO;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 6/2/18.
 */

public class FDHelper {




    public void persistFD(final PersonalAssistantDatabase personalAssistantDatabase, final FixedDepositVO fdVO){
        new Thread(new Runnable() {
            @Override
            public void run() {
//                FixedDeposit fixedDeposit=new FixedDeposit();
//                fixedDeposit.setBankAccountId(fdVO.getBankAccountId());
//                fixedDeposit.setFdInceptionDate(fdVO.getFdInceptionDate());
//                fixedDeposit.setFdMaturityDate(fdVO.getFdMaturityDate());
//                fixedDeposit.setInterest(fdVO.getInterest());
//                fixedDeposit.setMaturityAmount(fdVO.getMaturityAmount());
//                fixedDeposit.setPrincipleAmount(fdVO.getPrincipleAmount());
//                FixedDepositWithOwners fixedDepositWithOwners=new FixedDepositWithOwners();
//
//                fixedDepositWithOwners.setFixedDeposit(fixedDeposit);
//                List<Person> persons = personalAssistantDatabase.getPersonDAO().fetchAllPersonsWithName(fdVO.getPersons());
//                fixedDepositWithOwners.setFdOwners(persons);
//
//
//                if (fdVO.getFdStatus().equals(FDStatus.Renewed)) {
//                    personalAssistantDatabase.getFixedDepositDAO().updateFixedDeposit(fixedDepositWithOwners);
//                }else{ if(fdVO.getFdStatus().equals(FDStatus.Active))
//                    personalAssistantDatabase.getFixedDepositDAO().insertFixedDeposit(fixedDepositWithOwners);
//                }

            }
        }).start();
    }



    public List<FixedDepositVO> fetchAllFixedDepositVOs(final PersonalAssistantDatabase personalAssistantDatabase) throws InterruptedException{
        final List<FixedDepositVO> fixedDepositVOs=new ArrayList<>();
        Thread fetchThread=new Thread(new Runnable() {
            @Override
            public void run() {
//                List<FixedDepositWithOwners> fixedDepositWithOwners = personalAssistantDatabase.getFixedDepositDAO().fetchAllFixedDepositWithOwners();
//                if(fixedDepositWithOwners!=null){
//                    for(FixedDepositWithOwners fixedDeposits:fixedDepositWithOwners){
//                        FixedDepositVO fixedDepositVO=new FixedDepositVO();
//                        FixedDeposit fixedDeposit=fixedDeposits.getFixedDeposit();
//                        int bankAccountId=fixedDeposit.getBankAccountId();
//
//                        BankAccount bankAccount=personalAssistantDatabase.getBankAccountDAO().fetchBankAccountByBankAccountId(bankAccountId);
//                        List<Person> fixedDepositOwners=fixedDeposits.getFdOwners();
//                        fixedDepositVO.setBankName(bankAccount.getBankName());
//                        fixedDepositVO.setFdMaturityDate(fixedDeposit.getFdMaturityDate());
//                        fixedDepositVO.setMaturityAmount(fixedDeposit.getMaturityAmount());
//
//
//                        fixedDepositVOs.add(fixedDepositVO);
//                    }
//                }
            }
        });
        fetchThread.start();
        fetchThread.join();
        return fixedDepositVOs;
    }
}
