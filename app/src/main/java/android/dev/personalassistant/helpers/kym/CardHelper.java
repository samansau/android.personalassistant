package android.dev.personalassistant.helpers.kym;

import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.kym.BankAccount;
import android.dev.personalassistant.entities.kym.Card;
import android.dev.personalassistant.vo.kym.CardVO;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 6/2/18.
 */

public class CardHelper {

    public void deleteCard(final PersonalAssistantDatabase personalAssistantDatabase, final String cardNumber) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Card card = new Card();
                try {
                    CardVO cardVOOld=fetchCardVOByCardNumber(personalAssistantDatabase, cardNumber);
                    card.setCardId(cardVOOld.getCardId());
                    personalAssistantDatabase.getCardDAO().deleteCard(card);
                }catch (InterruptedException ie){
                    Log.e("deleteCard",ie.getStackTrace().toString());
                }

            }
        }).start();
    }


    public void persistCard(final PersonalAssistantDatabase personalAssistantDatabase, final CardVO cardVO){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Card card = new Card();

                int cardIdValue=cardVO.getCardId();

                card.setBankAccountId(cardVO.getBankAccountId());
                card.setCardType(cardVO.getCardTypeValue());
                card.setCardNumber(cardVO.getCardNumberValue());

                card.setCardCategory(cardVO.getCardCategoryValue());
                card.setCardNumber(cardVO.getCardNumberValue());
                card.setCardExpiryDate(cardVO.getCardExpiryDateValue());
                card.setCardCvv(cardVO.getCardCvvValue());

                Log.d("Card Object : ",card.toString());
                if (cardIdValue >= 0) {
                    card.setCardId(cardVO.getCardId());
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
