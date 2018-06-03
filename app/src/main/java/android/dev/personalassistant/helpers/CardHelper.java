package android.dev.personalassistant.helpers;

import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.BankAccount;
import android.dev.personalassistant.entities.Card;
import android.dev.personalassistant.vo.BankAccountVO;
import android.dev.personalassistant.vo.CardVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 6/2/18.
 */

public class CardHelper {


    public void persistCard(final PersonalAssistantDatabase personalAssistantDatabase, final CardVO cardVO){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Card card = new Card();
                int cardIdValue=cardVO.getCardId();
                card.setCardType(cardVO.getCardTypeValue());
                card.setCardNumber(cardVO.getCardNumberValue());
//                Bank bankObj=null;
//                try {
//                    BankHelper bankHelper = new BankHelper();
//
//                    bankObj=bankHelper.fetchBankByBankNameAndBranch(personalAssistantDatabase, cardVO.getBankNameValue(), cardVO.getBranchValue());
//                    Log.d("bank value :",bankObj.toString());
//                }catch(InterruptedException ie){
//                    Log.e(CardHelper.class.getName(),ie.getStackTrace().toString());
//                }
//
//
//                card.setBank(bankObj);

                card.setCardCategory(cardVO.getCardCategoryValue());
                card.setCardNumber(cardVO.getCardNumberValue());
                card.setCardExpiryDate(cardVO.getCardExpiryDateValue());
                card.setCardCvv(cardVO.getCardCvvValue());






                if (cardIdValue >= 0) {
                    card.setId(cardIdValue);
                    personalAssistantDatabase.getCardDAO().updateCards(card);
                }else{
                    personalAssistantDatabase.getCardDAO().insertCard(card);
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
                    //Bank bank=bankAccount.getBank();
                   // bankAccountVO.setBankNameValue(bank.getBankName());
                   // bankAccountVO.setBankBranchValue(bank.getBranch());
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

    public List<CardVO> fetchAllCardVOs(final PersonalAssistantDatabase personalAssistantDatabase) throws InterruptedException{
        final List<CardVO> cardVOs=new ArrayList<>();
        Thread fetchThread=new Thread(new Runnable() {
            @Override
            public void run() {
                List<Card> cards = personalAssistantDatabase.getCardDAO().fetchAllCards();
                if(cards!=null){
                    for(Card card:cards){
                        CardVO cardVO=new CardVO();

                        cardVO.setCardCategoryValue(card.getCardCategory());
                        cardVO.setCardCvvValue(card.getCardCvv());
                        cardVO.setCardExpiryDateValue(card.getCardExpiryDate());
                        cardVO.setCardNumberValue(card.getCardNumber());
                        cardVO.setCardTypeValue(card.getCardType());

                        cardVOs.add(cardVO);
                    }
                }
            }
        });
        fetchThread.start();
        fetchThread.join();
        return cardVOs;
    }
}
