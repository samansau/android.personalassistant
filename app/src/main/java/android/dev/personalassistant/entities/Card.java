package android.dev.personalassistant.entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.dev.personalassistant.enums.CardType;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by saurabh on 5/8/18.
 */
@Entity(foreignKeys = {@ForeignKey(entity= BankAccount.class,
                                    parentColumns = "bankAccountId",
                                    childColumns ="bankAccountId"
                                  )
                      },
        indices = {@Index(value =
                {"cardNumber"}, unique = true)}

       )
public class Card {

    @PrimaryKey(autoGenerate = true)
    public int cardId;

    @Nullable
    private int bankAccountId;

    private String cardType;

    private String cardCategory;

    @NonNull
    private String cardNumber;
    private String cardExpiryDate;
    private String cardCvv;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }


    public String getCardCategory() {
        return cardCategory;
    }

    public void setCardCategory(String cardCategory) {
        this.cardCategory = cardCategory;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getCardCvv() {
        return cardCvv;
    }

    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", bankAccountId=" + bankAccountId +
                ", cardType='" + cardType + '\'' +
                ", cardCategory='" + cardCategory + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardExpiryDate='" + cardExpiryDate + '\'' +
                ", cardCvv='" + cardCvv + '\'' +
                '}';
    }
}
