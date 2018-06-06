package android.dev.personalassistant.helpers;

import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.BankAccount;
import android.dev.personalassistant.entities.Card;
import android.dev.personalassistant.vo.CardVO;
import android.dev.personalassistant.vo.PersonVO;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 6/2/18.
 */

public class PersonalHelper {


    public void persistPerson(final PersonalAssistantDatabase personalAssistantDatabase, final PersonVO personVO){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Card card = new Card();

                int personalIdValue=0;



                Log.d("Card Object : ",card.toString());
                if (personalIdValue >= 0) {

                    personalAssistantDatabase.getCardDAO().updateCards(card);
                }else{
                    personalAssistantDatabase.getCardDAO().insertCard(card);
                }

            }
        }).start();
    }


    public CardVO fetchCardVOByCardNumber(final PersonalAssistantDatabase personalAssistantDatabase,final String cardNumber) throws InterruptedException{
        final CardVO cardVO=new CardVO();
        Thread fetchThread=new Thread(new Runnable() {
            @Override
            public void run() {
                Card card = personalAssistantDatabase.getCardDAO().fetchCardByCardNumber(cardNumber);
                if(card!=null){
                    int bankAccountId=card.getBankAccountId();
                    cardVO.setBankAccountId(bankAccountId);
                    BankAccount bankAccount=personalAssistantDatabase.getBankAccountDAO().fetchBankAccountByBankAccountId(bankAccountId);
                    cardVO.setCardId(card.getCardId());
                    cardVO.setBankName(bankAccount.getBankName());
                    cardVO.setBranch(bankAccount.getBranch());
                    cardVO.setCardCategoryValue(card.getCardCategory());
                    cardVO.setCardCvvValue(card.getCardCvv());
                    cardVO.setCardExpiryDateValue(card.getCardExpiryDate());
                    cardVO.setCardNumberValue(card.getCardNumber());
                    cardVO.setCardTypeValue(card.getCardType());
                }
            }
        });
        fetchThread.start();
        fetchThread.join();
        return cardVO;
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
                        int bankAccountId=card.getBankAccountId();
                        cardVO.setBankAccountId(bankAccountId);
                        BankAccount bankAccount=personalAssistantDatabase.getBankAccountDAO().fetchBankAccountByBankAccountId(bankAccountId);

                        cardVO.setCardId(card.getCardId());
                        cardVO.setBankName(bankAccount.getBankName());
                        cardVO.setBranch(bankAccount.getBranch());
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
